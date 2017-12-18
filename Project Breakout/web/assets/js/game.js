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

<<<<<<< HEAD
function switchOnTypePlayer(player){
    var lives = player.lives;
    var score = player.score;
    $("#score").html("<p class='white-text left-align'>Lives x" + lives + "</p><p class='white-text left-align'>Score: " + score + "</p>");
}

socket.onmessage = function(messageRecieved) {
    var type = JSON.parse(messageRecieved.data).type;
    console.log(type);
    switch(type){
        case "posistion":
            switchOnTypeComponents(JSON.parse(messageRecieved.data));
            break;
        case "gameInfo":
            switchOnTypePlayer(JSON.parse(messageRecieved.data));
            break;
        default:
            break;
=======
  if (!keyMap[this.leftKey]) {
    if (!messageObj.hasOwnProperty("direction")) {
      messageObj.direction = "stop";
      socket.sendMessage(messageObj);
>>>>>>> master
    }
  }
  if (!keyMap[this.rightKey]) {
    if (!messageObj.hasOwnProperty("direction")) {
      messageObj.direction = "stop";
      socket.sendMessage(messageObj);
    }
  }
};

<<<<<<< HEAD
socket.onopen = function () {
  //sendMessage(JSON.stringify({"flppn": 3}));
};

function startGame() {
  $("#selectController").hide();
  var messageObj = {type: "startGame", playerAmount: prompt("How many players")};
  sendMessage(messageObj);
  getUpdate();
}

function getUpdate() {
   gameInterval = setInterval(getPosistion, 10);
   infoInterval = setInterval(getGameInfo, 50);
}

function stopUpdates() {
  clearInterval(gameInterval);
}

function getPosistion() {
  var messageObj = {type: "updateMe"};
  sendMessage(messageObj);
}


function getGameInfo() {
  var messageObj = {type: "gameInfo"};
  sendMessage(messageObj);
}

function createPallet(palletje){
    pallet = new Pallet(palletje.x, palletje.y, palletje.width, palletje.heigth);
}
=======
var ip = 'x.x.x.x'; //voor later
var port = ':8080';
var lel = true //TODO: DELETE DIS
>>>>>>> master

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
  //new from michael
//  var showSpells = function(){
//    var playerAmount = prompt("How many players");
//    for (var i = 0; i < parseInt(playerAmount); i++) {
//      var leftKeyCode = parseInt(prompt("left Key:").charCodeAt(0)-32); // TODO: move to a seperate fucntion perhaps?
//      var rightKeyCode = parseInt(prompt("right key:").charCodeAt(0)-32); // #readability
//      var abilityKeyCode = parseInt(prompt("ability").charCodeAt(0)-32);
//      input.players.push(new Player(leftKeyCode, rightKeyCode, abilityKeyCode, ""+(i+1)));
//    }
//    var messageObj = {type: "showSpells", playerAmount};
//    socket.sendMessage(messageObj);
//  }
  //
  
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
    getUpdate();
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
    pallet = null;
    ball = null;
    bricks = [];
    effects = [];
    for (var sprite in posArray) {
      var oneSprite = posArray[sprite];
      switch (oneSprite.type) {
        case "Pallet":
          if(pallet == null) {
              pallet = [];
          }
          pallet.push(new Pallet(oneSprite.x, oneSprite.y, oneSprite.width, oneSprite.height, images.pallet));
          break;
        case "Ball":
          if(ball == null) {
              ball = [];
          }
          ball.push(new Ball(oneSprite.radius, oneSprite.x, oneSprite.y, images.ball)); // TODO: Move this to seperate functions?
          break;
        case "Brick":
          bricks.push(new Brick(oneSprite.x, oneSprite.y, oneSprite.width, oneSprite.height, getImage(oneSprite.color)));
          break;
        case "Powerup":
        effects.push(new Brick(oneSprite.x, oneSprite.y, oneSprite.width, oneSprite.height, getImage(oneSprite.icon)));
        break; // TODO: Doesn't work yet ;-;
      }
    }
  };
  var gameInfo = function(player) {
    var lives = player.lives;
    var score = player.score;
  };
  return {drawFromPosistion, gameInfo};
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
      //new from michael
//      case "spells":
//        comm.askSpells();
//        break;
     //
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
var ball = null; // TODO: SOOO MANYY GLOBALS ;-;
var pallet = null;
var bricks = [];
var effects = [];
var images = {};
var imagesToLoad = ['assets/media/pallet.png', 'assets/media/ball.png', 'assets/media/black_block.png', 'assets/media/green_block.png', 'assets/media/purple_block.png', 'assets/media/red_block.png', 'assets/media/yellow_block.png', 'assets/media/gravity.png', 'assets/media/bullet-time.png', 'assets/media/slowed.png', 'assets/media/shrunk.png', 'assets/media/double-trouble.png', 'assets/media/scaffolds.png', 'assets/media/sudden-death.png'];
function setImages(listOfImagesToLoad) {
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
}
function setup() {
  var canvas = createCanvas(750, 400);
  canvas.parent('game-area');
}
function draw() {
  var check = ball !== null && pallet !== null;
  console.log(check); // TODO: remove this in final version, also move the boolean check to the if then
  if (check) {
    background(47, 49, 54);
<<<<<<< HEAD
//    ball.move();
//    ball.edges();
    ball.show();
    pallet.move(LEFT_ARROW, RIGHT_ARROW);
//    pallet.border();
    pallet.show();
    for (var i = 0; i < bricks.length; i++){
        bricks[i].show();
=======
    for(var b in ball) {
        ball[b].show();
>>>>>>> master
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

$(document).ready(function() {
  console.log("game.js is loaded");
  init.fireModal();
  $(".startGame").on("click", comms.startGame);
});
