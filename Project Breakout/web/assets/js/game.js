class Player {

  constructor(left, right, ability, name) {
    this.leftKey = left;
    this.rightKey = right;
    this.abilityKey = ability;
    this.name = name;
  }
}
Player.prototype.move = function(keyMap) {
  var messageObj = {type: "move", player: this.name};
  if (keyMap[this.leftKey]) {
    if (!messageObj.hasOwnProperty("direction")) {
      messageObj.direction = "left";
      socket.sendMessage(messageObj);
    }
  }

  if (keyMap[this.rightKey]) {
    if (!messageObj.hasOwnProperty("direction")) {
      messageObj.direction = "right";
      socket.sendMessage(messageObj);
    }
  }

  if (!keyMap[this.leftKey]) {
    if (!messageObj.hasOwnProperty("direction")) {
      messageObj.direction = "stop";
      socket.sendMessage(messageObj);
    }
  }

  if (!keyMap[this.rightKey]) {
    if (!messageObj.hasOwnProperty("direction")) {
      messageObj.direction = "stop";
      socket.sendMessage(messageObj);
    }
  }
};

var ip = 'x.x.x.x'; //voor later
var port = ':8080';
var lel = true //TODO: DELETE DIS

var init = function() {
  var fireModal = function(playerAmount) {
    $("#selectController").modal().modal('open');;
    var cols = 12;
    var players = playerAmount;
    var grootteCols = (cols / players);
    var currentslot = 1;
    while (currentslot <= players) {
      $(".modal-content").append("<div class='controllercol center col s" + grootteCols + "' data-player='"+currentslot+"'>" + currentslot + " player(s) <br/><a class='dropdown-button btn' href='#' data-activates='dropdown1'>Drop Me!</a><ul id='dropdown1' class='dropdown-content'><li><a href='#!'><i class='material-icons'>keyboard</i>keys</a></li><li><a href='#!'><i class='material-icons'>phone_iphone</i>phone</a></li></ul><form class='inputForm'><div class='input-field col s4'><input id='left-key' type='text'><label for='left-key'>left key:</label></div><div class='input-field  col s4'><input id='right-key' type='text'><label for='right-key'>right key:</label></div><div class='input-field  col s4'><input id='ability-key' type='text'><label for='ability-key'>ability key:</label></div></form></div>");
      console.log("new slot");
      currentslot += 1;
    }
  };
  return {fireModal};
}();

var input = function() {
  // Private
  var keyMap = {};
  onkeydown = onkeyup = function(e) {
    e = e || event; // damn you IE..
    keyMap[e.keyCode] = e.type === 'keydown';

    for (var player in players) {
      players[player].move(keyMap);
    }
  };
  // Public
  var players = [];
  return {players};
}();
var comms = function() {
  // Private
  var gameInterval = null;
  var infoInterval = null;
  var getGameInfo = function() {
    var messageObj = {type: "gameInfo"};
    socket.sendMessage(messageObj);
  };

  var getPosistion = function() {
    var messageObj = {type: "updateMe"};
    socket.sendMessage(messageObj);
  };
  // Public
  var startGame = function() {
    //$("#selectController").hide();
    var playerAmount = prompt("How many players");
    for (var i = 0; i < parseInt(playerAmount); i++) {
      var leftKeyCode = parseInt(prompt("left Key:").charCodeAt(0)-32); // TODO: move to a seperate fucntion perhaps?
      var rightKeyCode = parseInt(prompt("right key:").charCodeAt(0)-32); // #readability
      var abilityKeyCode = parseInt(prompt("ability").charCodeAt(0)-32);
      input.players.push(new Player(leftKeyCode, rightKeyCode, abilityKeyCode, ""+(i+1)));
    }

    var messageObj = {type: "startGame", playerAmount};
    socket.sendMessage(messageObj);
  };

  var getUpdate = function() {
    gameInterval = setInterval(getPosistion, 20);
    infoInterval = setInterval(getGameInfo, 50);
  };

  var stopUpdates = function() {
    clearInterval(gameInterval);
    clearInterval(infoInterval);
  };
  return {startGame, getUpdate, stopUpdates};
}();
var gui = function() {
  var drawFromPosistion = function(message) {
    const posArray = message;
    pallet = [];
    ball = [];
    bricks = [];
    effects = [];
    $('#iconArea').html("");
    for (var sprite in posArray) {
      var oneSprite = posArray[sprite];
      switch (oneSprite.type) {
        case "Pallet":
          pushPallet(oneSprite);
          break;
        case "Ball":
          pushBall(oneSprite);
          break;
        case "Brick":
          pushBrick(oneSprite);
          break;
        case "Powerup":
          pushPowerup(oneSprite);
          break;
      }
    }
  };

  function pushPallet(oneSprite) {
    pallet.push(new Pallet(oneSprite.x, oneSprite.y, oneSprite.width, oneSprite.height, images.pallet));
  }
  function pushBall(oneSprite) {
    if(oneSprite.icon !== undefined) {
      ball.push(new Ball(oneSprite.radius, oneSprite.x, oneSprite.y, getImage(oneSprite.icon)));
    } else {
      ball.push(new Ball(oneSprite.radius, oneSprite.x, oneSprite.y, images.ball)); // TODO: Move this to seperate functions?
    }
  }
  function pushBrick(oneSprite) {
    bricks.push(new Brick(oneSprite.x, oneSprite.y, oneSprite.width, oneSprite.height, getImage(oneSprite.icon)));
  }
  function pushPowerup(oneSprite) {
    effects.push(new Brick(oneSprite.x, oneSprite.y, oneSprite.width, oneSprite.height, getImage(oneSprite.icon)));
    $('#iconArea').append('<img src="'+getImage(oneSprite.icon)+'" alt="">');
  }

  var gameInfo = function(player) {
    var lives = player.lives;
    var score = player.score;
  };

  var showSpells = function(spellObj) {
    var itr = 0;
    for(var thing in spellObj) {
      console.log(spellObj[thing]);
      if (thing !== 'type') {
        $("div.controllercol[data-player="+(itr+1)+"]").append("<div class='row'><a class='btn spellSelect col s12'>"+spellObj[thing]+"</a></div>");
        itr++;
      }
    }
  };
  return {drawFromPosistion, gameInfo, showSpells};
}();
var socket = function() {
  // Private
  var url = "ws://localhost:8080/Project_Breakout/gamepoint";
  var socket = new WebSocket(url);
  socket.onopen = function() {
    //socket.sendMessage(JSON.stringify({"flppn": 3}));
  };

  socket.onmessage = function(messageRecieved) {
    var message = JSON.parse(messageRecieved.data);
    switch (message.type) {
      case "gameStarted":
        comms.getUpdate();
        break;
      case "spells":
       gui.showSpells(message);
       break;
      case "posistion":
        gui.drawFromPosistion(message);
        break;
      case "gameInfo":
        gui.gameInfo(message);
        break;
    }
  };
  // Public
  function sendMessage(message) {
    socket.send(JSON.stringify(message));
  }
  return {sendMessage};
}();

// DRAW FUNCTIONS (P5.JS) //
var ball = []; // TODO: SOOO MANYY GLOBALS ;-;
var pallet = [];
var bricks = [];
var effects = [];
var images = {};
var imagesToLoad = ['assets/media/pallet.png', 'assets/media/ball.png', 'assets/media/black_block.png', 'assets/media/green_block.png', 'assets/media/purple_block.png', 'assets/media/red_block.png', 'assets/media/yellow_block.png', 'assets/media/gravity.png', 'assets/media/bullet-time.png', 'assets/media/slowed.png', 'assets/media/shrunk.png', 'assets/media/double-trouble.png', 'assets/media/scaffolds.png', 'assets/media/sudden-death.png', 'assets/media/game-background.jpg', 'assets/media/fireball.png']; // TODO: move to GUI module?
function setImages(listOfImagesToLoad) { // TODO: move to GUI module & make this a array foreach funtion?
  for (var i = 0; i<listOfImagesToLoad.length;i++) {
    var imgPath = listOfImagesToLoad[i].split("/");
    var imgKey = imgPath[imgPath.length-1].split(".")[0];
    images[imgKey] = loadImage(listOfImagesToLoad[i]);
  }
}

var preload = function() {
  setImages(imagesToLoad);
};

function getImage(color) {
  var graphic = images[color];
  if(graphic == undefined) {
    graphic = images.green_block;
  }
  return graphic;
} // TODO: move to GUI module?

function setup() {
  var canvas = createCanvas(750, 400);
  canvas.parent('game-area');

}

function draw() {
  var check = ball !== null && pallet !== null;
  //console.log(check); // TODO: remove this in final version, also move the boolean check to the if then
  if (check) {
    background(images["game-background"]);
    for(var b in ball) {
        ball[b].show();
    }
    for(var p in pallet) {
        pallet[p].show();
    }
    for(var e in effects) {
        effects[e].show();
    }
    for (var i = 0; i < bricks.length; i++) {
      bricks[i].show();
    }
  }
}

function submitStartGameData(e) {
  e.preventDefault();
  var amountOfPlayers = $("#amountOfPlayers").val();
  var dificulty = $("#dificulty").val();
  var username = $("#username").html().split("<")[0];
  var messageObj = {type:"playerAmount", playerAmount:amountOfPlayers, dificulty, username}
  $("#varData").html("");
  init.fireModal(amountOfPlayers);
  socket.sendMessage(messageObj);
}

function selectSpell() {
  var spell = $(this).html();
  var player = $(this).parent(".controllercol").data();
  var messageObj = {type:"selectedSpells", spell, player};
  socket.sendMessage(messageObj);
}



$(document).ready(function() {
  console.log("game.js is loaded");
  $('select').material_select();
  init.fireModal();
  $(".startGame").on("click", comms.startGame);
  $("#modalForm").on("submit", submitStartGameData);
  $(document).on("click", ".spellSelect", selectSpell);
});
