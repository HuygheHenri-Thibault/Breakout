class Ball {

    constructor(radius, x, y, speed) {
        this.x = x;
        this.y = y;
        this.speedx = 2;
        this.speedy = 0;
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
        fill(255);
        ellipse(this.x, this.y, this.diameter*2);

    }
}

class Pallet{
    constructor(x, y, lengte, hoogte, snelheid){
        this.x =  x;
        this.y = y;
        this.lengte = lengte;
        this.hoogte = hoogte;
        this.snelheid = snelheid;
        this.dir = false;
        this.xborder = 0;
    }
    
    show(){
        imageMode(CENTER);
        image(img, this.x, this.y,img.width*1.5, img.height*1.5); 
    }
    
    move(left, right){
        if (keyIsDown(left)) {
            this.x -= this.snelheid;
        }
        if (keyIsDown(right)){
            this.x += this.snelheid;
        }
    }
    
    border(){
        if (this.x + this.lengte/2 + 5 > width){
            this.x -= this.snelheid;
        }
        if (this.x - this.lengte/2 - 5 < 0){
            this.x += this.snelheid;
        }
    }
}

class Brick{
    constructor(x, y, img){
        this.x =  x;
        this.y = y;
        this.img = img;
    }
    
    show(){
        imageMode(CENTER);
        image(this.img, this.x, this.y,img.width*1.5, img.height*1.5); 
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