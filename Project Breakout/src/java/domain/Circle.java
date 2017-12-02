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
public class Circle extends Shape implements Collidable{
    private final Level level;
    private final CollissionDetectorUtilities cdu = new CollissionDetectorUtilities();
    private int radius;

    public Circle(Level level, int x, int y, int radius) {
        super(x, y);
        if(level != null){ this.level = level; } else {throw new NullPointerException("Level may not be null");}
        this.radius = radius;
    }
    
    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
    
    @Override
    public boolean checkCollissionWithRect(Rectangle rect){        
        return cdu.checkCollissionBetweenRectangleAndCircle(rect, this);
    }
    
    @Override
    public boolean checkCollissionWithCircle(Circle c){
        double xDif = this.getX() - c.getX();
        double yDif = this.getY()- c.getY();
        double distanceSquared = xDif * xDif + yDif * yDif;
        return distanceSquared < (this.getRadius() + c.getRadius()) * (this.getRadius() + c.getRadius());
    }

    
    @Override
    public boolean checkCollission(Shape s){
        return s.checkCollissionWithCircle(this);
    }

    @Override
    public void updateSpriteBall(Ball aBall) {
        aBall.updateSpriteAfterCollidingWithCircle();
    }
    
}
