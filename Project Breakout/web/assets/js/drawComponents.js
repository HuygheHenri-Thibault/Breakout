class Ball {

    constructor(radius, x, y) {
        this.x = x;
        this.y = y;
        this.speedx = 5;
        this.speedy = -5;
        this.diameter = radius/2;
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
        image(imgBall, this.x, this.y, imgBall.width*2.5, imgBall.height*2.5); 
//        fill(255);
//        ellipse(this.x, this.y, this.diameter*2);

    }
}

class Pallet{
    constructor(x, y, length, height, speed){
        this.x =  x;
        this.y = y;
        this.length = length;
        this.height = height;
        this.speed = speed;
        this.dir = false;
        this.xborder = 0;
    }
    
    show(){
//        fill(255, 0, 0);
//        rectMode(CENTER);
//        rect(this.x, this.y, this.length, this.height, 20);
        imageMode(CENTER);
        //image(imgPallet, this.x, this.y, imgPallet.width*1.5, this.height*2); 
        image(imgPallet, this.x, this.y, this.length, this.height*2); 
    }
    
    move(left, right){
        if (keyIsDown(left)) {
            this.x -= this.speed;
        }
        if (keyIsDown(right)){
            this.x += this.speed;
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
        this.height = height
        this.img = img;
    }
    
    show(){
        imageMode(CENTER);
        image(this.img, this.x, this.y, this.width, this.img.height); 
    }
}
//
//class Heart{
//    constructor(startAmmount) {
//        this.startAmount = startAmmount;
//    }
//    
//    show(){
//        imageMode(CENTER);
//        image(heartImg, 15, 15,img.width*0.4, img.height*1.2);
//        imageMode(CENTER);
//        image(heartImg, 40, 15,img.width*0.4, img.height*1.2);
//        imageMode(CENTER);
//        image(heartImg, 65, 15,img.width*0.4, img.height*1.2);
//        fill(0, 255, 0);
//        ellipse(10, 10, 15);
//        fill(0, 255, 0);
//        ellipse(25, 10, 15); 
//        fill(0, 255, 0);
//        ellipse(40, 10, 15);
//        fill(0, 255, 0);
//        ellipse(55, 10, 15); 
//    }
//    
//}