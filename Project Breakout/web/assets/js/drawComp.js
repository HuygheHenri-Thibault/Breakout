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
        this.lengte = lengte
        this.hoogte = hoogte
        this.snelheid = snelheid;
        this.dir = false;
        this.xborder = 0;
    }
    
    show(){
//        fill(255, 0, 12)
        //rectMode(CENTER);
        image(img, this.x, this.y,img.width*1.5, img.height*1.5); 
        //rect(this.x, this.y, this.lengte, this.hoogte, 20);
    }
    
    move(left, right){
        if (keyCode === left){
            this.x -= this.snelheid;
        }
        if (keyCode === right){
            this.x += this.snelheid;
        }
    }
    
    border(){
        if (this.x + this.lengte/2 + 20 > width){
            this.x -= this.snelheid;
        }
        if (this.x - this.lengte/2 - 20 < 0){
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
        image(this.img, this.x, this.y,img.width*1.5, img.height*1.5); 
    }
    
    move(left, right){
        if (keyCode === left){
            this.x -= this.snelheid;
        }
        if (keyCode === right){
            this.x += this.snelheid;
        }
    }
    
    border(){
        if (this.x + this.lengte/2 + 20 > width){
            this.x -= this.snelheid;
        }
        if (this.x - this.lengte/2 - 20 < 0){
            this.x += this.snelheid;
        }
    }
}