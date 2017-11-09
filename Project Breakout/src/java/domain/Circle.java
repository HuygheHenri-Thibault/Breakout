/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import collissionDetectors.CollissionDetectorUtilities;

/**
 *
 * @author micha
 */
public class Circle extends Sprite{
    //private Sprite s;
    private final Level level;
    private final CollissionDetectorUtilities cdu = new CollissionDetectorUtilities();
    private int radius;

    public Circle(Level level, String color, int x, int y, int radius) {
        super(color, x, y);
        if(level != null){ this.level = level; } else {throw new NullPointerException("Level may not be null");}
        this.radius = radius;
    }
    
    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

//    public Sprite getS() {
//        return s;
//    }
    
    @Override
    public boolean checkCollissionWithRect(Rectangle rect){        
        return cdu.checkCollissionBetweenRectangleAndCircle(rect, this);
    }
    
    @Override
    public boolean checkCollissionWithCircle(Circle c){
        int d = (int) Math.sqrt((this.getX() - c.getX()) + (this.getY() - c.getY()));
        int d1 = (int) (Math.pow(this.getRadius(), 2) - Math.pow(c.getRadius(), 2) + Math.pow(d, 2) / 2 * d);
        int h = (int) Math.sqrt(Math.pow(this.getRadius(), 2) - Math.pow(d1, 2));
        int x3 = (int) (this.getX() + (d1 * (c.getX()- this.getX()) / d));
        int y3 =  this.getY() + (d1 * (c.getY()- this.getY()) / d);
        int x4_i =  x3 + ((h * (c.getY() - this.getY())) / d);
        int y4_i =  (int) (y3 - ((h * (c.getX()- this.getX())) / d));
        int x4_ii =  x3 - ((h * (c.getY() - this.getY())) / d);
        int y4_ii =  (int) (y3 + ((h * (c.getX()- this.getX())) / d));
        return x4_i > 0 && y4_i > 0 && x4_ii > 0 && y4_ii > 0;
    }

    
    @Override
    public boolean checkCollission(Sprite s){
        return s.checkCollissionWithCircle(this);
    }

    @Override
    public void updateSpriteBall(Ball aBall) {
        aBall.updateSpriteAfterCollidingWithCircle();
    }
    
}
