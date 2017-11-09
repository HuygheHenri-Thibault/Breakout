var bricks = [];
var imgArray = [];

function preload(){
    img = loadImage('assets/media/pallet.png');
    imgArray[0] = loadImage('assets/media/black_block.png');
    imgArray[1] = loadImage('assets/media/green_block.png');
    imgArray[2] = loadImage('assets/media/purple_block.png');
    imgArray[3] = loadImage('assets/media/red_block.png');
    imgArray[4] = loadImage('assets/media/yellow_block.png');
}

function setup() {
    var canvas = createCanvas(750, 400);
    canvas.parent('game-area');
    x = 100;
    ball = new Ball(30, 20, 322, 80);
    pallet = new Pallet(width/2, 360, 100, 20, 2);
    for (var i = 0; i < 7; i++){
        var r = floor(random(0, imgArray.length));
        bricks[i] = new Brick(i*90+80, 60, imgArray[r]);
    }
}

function draw() {
    background(47, 49, 54);
 //   ball.move();
    ball.edges();
    ball.show();
    pallet.move(LEFT_ARROW, RIGHT_ARROW);
    pallet.border();
    pallet.show();
    for (var i = 0; i < bricks.length; i++){
        bricks[i].show();
    }
}


