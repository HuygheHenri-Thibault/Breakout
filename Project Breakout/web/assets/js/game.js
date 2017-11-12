var url = "ws://localhost:8080/Project_Breakout/gamepoint";
var socket = new WebSocket(url);
var ball;
var pallet;
var bricks = [];
var imgArray = [];
var gameInterval = 0;
var infoInterval;
var lives;
var score;

function sendMessage(message) {
    socket.send(JSON.stringify(message));
}

function switchOnTypeComponents(message){
  const posArray = message;
  for (var sprite in posArray){
      switch(posArray[sprite].type){
          case "Pallet":
              createPallet(posArray[sprite])
              break;
          case "Ball":
              createBall(posArray[sprite]);
              break;
          case "Brick":
              fillBrickArray(posArray[sprite]);
              break;
          default:
              break;
      }
  }
}

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
    }
};

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

function createBall(balletje){
    ball = new Ball(balletje.radius, balletje.x, balletje.y);
}

function fillBrickArray(brick){
    bricks.push(new Brick(brick.x, brick.y, brick.width, brick.height, imgArray[1]));
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
//    ball.edges();
    ball.show();
    pallet.move(LEFT_ARROW, RIGHT_ARROW);
//    pallet.border();
    pallet.show();
    for (var i = 0; i < bricks.length; i++){
        bricks[i].show();
    }
}
function fireModal(){
    $("#selectController").modal().show();
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
$(document).ready(function() {
  console.log("DOM is ready");
  fireModal();
  $(".startGame").on("click", startGame);
});
