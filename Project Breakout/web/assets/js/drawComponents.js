class Ball {
  constructor(radius, x, y, img) {
    this.x = x;
    this.y = y;
    this.diameter = radius * 2;
    this.img = img;
  }

  show() {
    image(this.img, this.x, this.y, this.diameter, this.diameter);
  }

  // edges(){
  //     if (this.y - this.diameter < 0 || this.y + this.diameter > height){
  //         this.speedy *= -1;
  //     }
  //     if (this.x - this.diameter < 0 || this.x + this.diameter > width){
  //         this.speedx *= -1;
  //     }
  // }
  //
  // move(){
  //     this.x += this.speedx;
  //     this.y += this.speedy;
  // }
}

class Pallet {
  constructor(x, y, width, height, img) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.img = img;
  }

  show() {
    image(this.img, this.x, this.y, this.width, this.height);
  }

  // move(left, right) {
  //   if (keyIsDown(left)) {
  //     var move = {
  //       type: "move",
  //       direction: "left"
  //     };
  //     sendMessage(move);
  //   }
  //   if (keyIsDown(right)) {
  //     var move = {
  //       type: "move",
  //       direction: "right"
  //     };
  //     sendMessage(move);
  //   }
  //   $('body').on('keyup', function() {
  //     if (key === '37' || key === '39') {
  //       var move = {
  //         type: "move",
  //         direction: "stop"
  //       };
  //       sendMessage(move);
  //     }
  //   });
  // }
  // border() {
  //   if (this.x + this.length / 2 + 5 > width) {
  //     this.x -= this.speed;
  //   }
  //   if (this.x - this.length / 2 - 5 < 0) {
  //     this.x += this.speed;
  //   }
  // }
  //
  // changeLength(newLength) {
  //   this.length = newLength;
  // }
}

class Brick {
  constructor(x, y, width, height, img) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.img = img;
  }

  show() {
    image(this.img, this.x, this.y, this.width, this.height);
  }
}

$(document).ready(function() {
  console.log("drawComponents.js is loaded");
})
