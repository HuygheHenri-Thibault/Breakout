var url = "ws://localhost:8080/Project_Breakout/gamepoint";
var socket = new WebSocket(url);
var ball;
var pallet;
var bricks = [];
var imgArray = [];
var gameInterval = 0;

function sendMessage(message) {
    socket.send(JSON.stringify(message));
}

socket.onmessage = function(messageRecieved) {
  const posArray = JSON.parse(messageRecieved.data);
  // TODO: Add function to draw pieces here
  for (var sprite in posArray){
      switch(posArray[sprite].type){
          case "Pallet":
              pallet = new Pallet(posArray[sprite].x, posArray[sprite].y, posArray[sprite].width, posArray[sprite].heigth);
              break;
          case "Ball":
              ball = new Ball(posArray[sprite].radius, posArray[sprite].x, posArray[sprite].y);
              break;
          case "Brick":
              var r = floor(random(0, imgArray.length));
              bricks.push(new Brick(posArray[sprite].x, posArray[sprite].y, posArray[sprite].width, posArray[sprite].height, imgArray[r]));
          default:
              break;
      }
  }
};

socket.onopen = function () {
  //sendMessage(JSON.stringify({"flppn": 3}));
}

function startGame() {
  var messageObj = {type: "startGame", playerAmount: prompt("How many players")};
  sendMessage(messageObj);
  //getUpdate();
}

function getUpdate() {
   gameInterval = setInterval(getPosistion, 10);
}

function stopUpdates() {
  clearInterval(gameInterval);
}

function getPosistion() {
  var messageObj = {type: "updateMe"};
  sendMessage(messageObj);
}

function preload(){
    imgPallet = loadImage('assets/media/pallet.png');
    imgBall = loadImage('assets/media/ball.png');
    imgArray[0] = loadImage('assets/media/black_block.png');
    imgArray[1] = loadImage('assets/media/green_block.png');
    imgArray[2] = loadImage('assets/media/purple_block.png');
    imgArray[3] = loadImage('assets/media/red_block.png');
    imgArray[4] = loadImage('assets/media/yellow_block.png');
}

function setup() {
    var canvas = createCanvas(750, 400);
    canvas.parent('game-area');
}

function draw() {
    background(47, 49, 54);
//    ball.move();
    ball.edges();
    ball.show();
    pallet.move(LEFT_ARROW, RIGHT_ARROW);
    pallet.border();
    pallet.show();
    for (var i = 0; i < bricks.length; i++){
        bricks[i].show();
    }
}

$(document).ready(function() {
  console.log("DOM is ready");
  startGame();
});
