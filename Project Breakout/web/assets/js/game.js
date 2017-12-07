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
      console.log(messageObj);
      socket.sendMessage(messageObj);
    }
  }
  if (keyMap[this.rightKey]) {
    if (!messageObj.hasOwnProperty("direction")) {
      messageObj.direction = "right";
      console.log(messageObj);
      socket.sendMessage(messageObj);
    }
  }

  if (!keyMap[this.leftKey]) {
    if (!messageObj.hasOwnProperty("direction")) {
      messageObj.direction = "stop";
      console.log(messageObj);
      socket.sendMessage(messageObj);
    }
  }
  if (!keyMap[this.rightKey]) {
    if (!messageObj.hasOwnProperty("direction")) {
      messageObj.direction = "stop";
      console.log(messageObj);
      socket.sendMessage(messageObj);
    }
  }
};

var ip = 'x.x.x.x'; //voor later
var port = ':8080';

var keyMap = {};
var players = [];
onkeydown = onkeyup = function(e) {
  e = e || event; // to deal with IE
  keyMap[e.keyCode] = e.type === 'keydown';
  $(".key").html("" + e.keyCode);

  for (var player in players) {
    players[player].move(keyMap);
  }
}

var init = function() {
  var fireModal = function() {
    $("#selectController").modal().modal('open');;
    var cols = 12;
    var players = prompt("How many playersssss?");
    var grootteCols = (cols / players);
    var currentslot = 1;
    while (currentslot <= players) {
      $(".modal-content").append("<div class='controllercol center col s" + grootteCols + "'>" + currentslot + " player(s) <br/><a class='dropdown-button btn' href='#' data-activates='dropdown1'>Drop Me!</a><ul id='dropdown1' class='dropdown-content'><li><a href='#!'><i class='material-icons'>keyboard</i>keys</a></li><li><a href='#!'><i class='material-icons'>phone_iphone</i>phone</a></li></ul></div>");
      console.log("new slot");
      currentslot += 1;
    }
  };
  return {
    fireModal
  };
}();
var comms = function() {
  // Private
  var gameInterval = null;
  var infoInterval = null;
  var getGameInfo = function() {
    var messageObj = {
      type: "gameInfo"
    };
    socket.sendMessage(messageObj);
  };
  var getPosistion = function() {
    var messageObj = {
      type: "updateMe"
    };
    socket.sendMessage(messageObj);
  };
  // Public
  var startGame = function() {
    //$("#selectController").hide();
    var playerAmount = prompt("How many players");
    for (var i = 0; i < parseInt(playerAmount); i++) {
      var leftKeyCode = parseInt(prompt("left Key:").charCodeAt(0)-32);
      var rightKeyCode = parseInt(prompt("right key:").charCodeAt(0)-32);
      var abilityKeyCode = parseInt(prompt("ability").charCodeAt(0)-32);
      players.push(new Player(leftKeyCode, rightKeyCode, abilityKeyCode, ""+(i+1)));
    }
    var messageObj = {
      type: "startGame",
      playerAmount
    };
    console.log("sending start ...");
    console.log(messageObj);
    socket.sendMessage(messageObj);
    getUpdate();
  };
  var getUpdate = function() {
    gameInterval = setInterval(getPosistion, 15);
    infoInterval = setInterval(getGameInfo, 50);
  };
  var stopUpdates = function() {
    clearInterval(gameInterval);
    clearInterval(infoInterval);
  };
  return {startGame, getUpdate, stopUpdates};
}();
var lel = true;
var gui = function() {
  var drawFromPosistion = function(message) {
    const posArray = message;
    pallet = null;
    ball = null;
    bricks = [];
    for (var sprite in posArray) {
      var oneSprite = posArray[sprite];
      switch (oneSprite.type) {
        case "Pallet":
          if(pallet == null) {
              pallet = [];
          }
          pallet.push(new Pallet(oneSprite.x, oneSprite.y, oneSprite.width, oneSprite.height, imgPallet));
          break;
        case "Ball":
            if(ball == null) {
              ball = [];
          }
          ball.push(new Ball(oneSprite.radius, oneSprite.x, oneSprite.y, imgBall));
          break;
        case "Brick":
          if(lel) { console.log(oneSprite); lel = false;}
          bricks.push(new Brick(oneSprite.x, oneSprite.y, oneSprite.width, oneSprite.height, getImage(oneSprite.color)));
          break;
      }
    }
  };
  var gameInfo = function(player) {
    var lives = player.lives;
    var score = player.score;
  };
  return {
    drawFromPosistion,
    gameInfo
  };
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
var ball = null;
var pallet = null;
var imgBall = null;
var imgPallet = null;
var bricks = [];
var imgArray = {};
var lives = 0;
var score = 0;
var preload = function() {
  imgPallet = loadImage('assets/media/pallet.png');
  imgBall = loadImage('assets/media/ball.png');
  imgArray.black = loadImage('assets/media/black_block.png');
  imgArray.green = loadImage('assets/media/green_block.png');
  imgArray.purple = loadImage('assets/media/purple_block.png');
  imgArray.red = loadImage('assets/media/red_block.png');
  imgArray.yellow = loadImage('assets/media/yellow_block.png');
};
function getImage(color) {
  switch (color) {
    case "yellow":
      return imgArray.yellow;
      break;
    case "blue":
      return imgArray.black; // TODO: Actually make this be the blue blocks..
      break;
    case "purple":
      return imgArray.purple;
      break;
    case "red":
      return imgArray.red;
      break;
    case "green":
      return imgArray.green;
      break;
    default:
      return imgArray.green;
      break;
  }
}
function setup() {
  var canvas = createCanvas(750, 400);
  canvas.parent('game-area');
}
function draw() {
  var check = ball !== null && pallet !== null;
  console.log(check);
  if (check) {
    background(47, 49, 54);
    for(var b in ball) {
        ball[b].show();
    }
    for(var p in pallet) {
        pallet[p].show();
    }
    for (var i = 0; i < bricks.length; i++) {
      bricks[i].show();
    }
  }
}

$(document).ready(function() {
  console.log("game.js is loaded");
  init.fireModal();
  $(".startGame").on("click", comms.startGame);
});
