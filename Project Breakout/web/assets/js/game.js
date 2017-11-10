var url = "ws://localhost:8080/Project_Breakout/gamepoint";
var socket = new WebSocket(url);
var gameInterval = 0;

function sendMessage(message) {
    socket.send(JSON.stringify(message));
}

socket.onmessage = function(messageRecieved) {
  const posArray = JSON.parse(messageRecieved.data);
  // TODO: Add function to draw pieces here
  console.log(posArray);
};

socket.onopen = function () {
  //sendMessage(JSON.stringify({"flppn": 3}));
}

function startGame() {
  $("#selectController").hide();
  var messageObj = {type: "startGame", playerAmount: prompt("How many players")};
  sendMessage(messageObj);
  getUpdate();
}

function getUpdate() {
   gameInterval = setInterval(getPosistion, 200);
}

function stopUpdates() {
  clearInterval(gameInterval);
}

function getPosistion() {
  var messageObj = {type: "updateMe"};
  sendMessage(messageObj);
}




// DRAW FUNCTIONS //
var bricks = [];
var imgArray = [];

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
    ball = new Ball(30, width/2, 330, 80);
    pallet = new Pallet(width/2, 360, 100, 15, 2);
    for (var i = 0; i < 14; i++){
        var r = floor(random(0, imgArray.length));
        bricks[i] = new Brick(i*50+35, 60, imgArray[r]);
    }
}

function draw() {
    background(47, 49, 54);
    ball.move();
    ball.edges();
    ball.show();
    pallet.move(LEFT_ARROW, RIGHT_ARROW);
    pallet.border();
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
