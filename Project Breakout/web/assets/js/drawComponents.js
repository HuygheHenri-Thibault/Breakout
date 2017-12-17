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
