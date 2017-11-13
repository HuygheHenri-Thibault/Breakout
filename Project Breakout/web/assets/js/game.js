var url = "ws://localhost:8080/Project_Breakout/gamepoint";
var socket = new WebSocket(url);
var ball = null;
var pallet = null;
var imgBall = null;
var imgPallet = null;
var bricks = [];
var imgArray = [];
var gameInterval = null;
var infoInterval = null;
var lives = 0;
var score = 0;

// INIT FUNCTIONS //
function preload() {
  imgPallet = loadImage('assets/media/pallet.png');
  imgBall = loadImage('assets/media/ball.png');
  imgArray[0] = loadImage('assets/media/black_block.png');
  imgArray[1] = loadImage('assets/media/green_block.png');
  imgArray[2] = loadImage('assets/media/purple_block.png');
  imgArray[3] = loadImage('assets/media/red_block.png');
  imgArray[4] = loadImage('assets/media/yellow_block.png');
}

function fireModal(){
    $("#selectController").modal().modal('open');;
    var cols = 12;
    var players = prompt("How many playersssss?")
    var grootteCols = (cols/players);
    var currentslot = 1;
    while(currentslot<=players){
    $(".modal-content").append("<div class='controllercol center col s"+grootteCols+"'>"+currentslot+" player(s) <br/><a class='dropdown-button btn' href='#' data-activates='dropdown1'>Drop Me!</a><ul id='dropdown1' class='dropdown-content'><li><a href='#!'><i class='material-icons'>keyboard</i>keys</a></li><li><a href='#!'><i class='material-icons'>phone_iphone</i>phone</a></li></ul></div>");
    console.log("new slot");
    currentslot +=1;
    }
}

// SOCKET FUNCTIONS //
function sendMessage(message) {
    socket.send(JSON.stringify(message));
}

socket.onopen = function () {
  //sendMessage(JSON.stringify({"flppn": 3}));
}

socket.onmessage = function(messageRecieved) {
  var message = JSON.parse(messageRecieved.data);
  switch (message.type) {
    case "posistion":
      drawFromPosistion(message);
      break;
    case "gameInfo":
      gameInfo(message);
      break;
  }
};

// COMUNICATION FUNCTIONS //
function startGame() {
  //$("#selectController").hide();
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
  clearInterval(infoInterval);
}

function getPosistion() {
  var messageObj = {type: "updateMe"};
  sendMessage(messageObj);
}

function getGameInfo() {
  var messageObj = {type: "gameInfo"};
  sendMessage(messageObj);
}

// UPDATE GAME FUNCTIONS (TIED TO COMUNICATION FUNCTIONS) //
function drawFromPosistion(message){
  const posArray = message;
  bricks = [];
  for (var sprite in posArray) {
    var oneSprite = posArray[sprite]
    switch (oneSprite.type) {
      case "Pallet":
        pallet = new Pallet(oneSprite.x, oneSprite.y, oneSprite.width, oneSprite.height, imgPallet);
        break;
      case "Ball":
        ball = new Ball(oneSprite.radius, oneSprite.x, oneSprite.y, imgBall);
        break;
      case "Brick":
        bricks.push(new Brick(oneSprite.x, oneSprite.y, oneSprite.width, oneSprite.height, imgArray[1]));
        break;
    }
  }
}

function gameInfo(player){
    var lives = player.lives;
    var score = player.score;
}

// DRAW FUNCTIONS (P5.JS) //
function setup() {
  var canvas = createCanvas(750, 400);
  canvas.parent('game-area');
}

function draw() {
  var check = ball !== null && pallet !== null;
  console.log(check);
  if (check) {
    background(47, 49, 54);
    ball.show();
    pallet.show();
    for (var i = 0; i < bricks.length; i++) {
      bricks[i].show();
    }
  }
}

$(document).ready(function() {
  console.log("game.js is loaded");
  fireModal();
  $(".startGame").on("click", startGame);
});
