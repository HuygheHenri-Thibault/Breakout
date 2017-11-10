class Ball {
    constructor(radius, x, y) {
        this.x = x;
        this.y = y;
        this.speedx = 5;
        this.speedy = -5;
        this.diameter = radius*2;
    }
    
    edges(){
        if (this.y - this.diameter < 0 || this.y + this.diameter > height){
            this.speedy *= -1;
        }
        if (this.x - this.diameter < 0 || this.x + this.diameter > width){
            this.speedx *= -1;   
        }
    }
    
    move(){
        this.x += this.speedx;
        this.y += this.speedy;
    }
    
    show() {
        imageMode(CENTER);
        image(imgBall, this.x, this.y, this.diameter, this.diameter); 

    }
}

class Pallet{
    constructor(x, y, length, height){
        this.x =  x;
        this.y = y;
        this.length = length;
        this.height = height;
    }
    
    show(){
        imageMode(CENTER); 
        image(imgPallet, this.x, this.y, this.length, this.height); 
    }
    
    move(left, right){
        if (keyIsDown(left)) {
            var move = {type: "move", direction: "left"};
            sendMessage(move);
        }
        if (keyIsDown(right)){
            var move = {type: "move", direction: "right"};
            sendMessage(move);
        }
    }
    
    border(){
        if (this.x + this.length/2 + 5 > width){
            this.x -= this.speed;
        }
        if (this.x - this.length/2 - 5 < 0){
            this.x += this.speed;
        }
    }
    
    changeLength(newLength){
        this.length = newLength;
    }
}

class Brick{
    constructor(x, y, width, height, img){
        this.x =  x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = img;
    }
    
    show(){
        image(this.img, this.x+(this.width/2), this.y+(this.height/2), this.width, this.height); 
    }
}