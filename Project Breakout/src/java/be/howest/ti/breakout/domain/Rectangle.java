/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain;

import be.howest.ti.breakout.domain.game.Level;
import be.howest.ti.breakout.collissionDetectors.CollissionDetectorUtilities;

/**
 *
 * @author micha
 */
public class Rectangle extends Shape implements Collidable{
    private final CollissionDetectorUtilities cdu = new CollissionDetectorUtilities();
    private final Level level;
    private int length;
    private final int HEIGHT;

    public Rectangle(Level level, int x, int y, int lenght, int height) {
        super(x, y);
        if(level != null){ this.level = level; } else {throw new NullPointerException("Level may not be null");}
        this.length = lenght;
        this.HEIGHT = height;
    }

    public void setLength(int length) {
        this.length = length;
    }
    
    public int getLength() {
        return length;
    }

    public int getHeight() {
        return HEIGHT;
    }

    @Override
    public boolean checkCollissionWithRect(Rectangle rect) {
        int tw = (int) this.getLength();
        int th = (int) this.getHeight();
        int rw = (int) rect.getLength();
        int rh = (int) rect.getHeight();
        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }
        float tx = this.getX();
        int ty = this.getY();
        float rx = rect.getX();
        int ry = rect.getY();
        rw += rx;
        rh += ry;
        tw += tx;
        th += ty;
        //      overflow || intersect
        return ((rw < rx || rw > tx) &&
                (rh < ry || rh > ty) &&
                (tw < tx || tw > rx) &&
                (th < ty || th > ry));
    }

    @Override
    public boolean checkCollissionWithCircle(Circle c) {
        return cdu.checkCollissionBetweenRectangleAndCircle(this, c);
    }

    @Override
    public boolean checkCollission(Shape s) {
        return s.checkCollissionWithRect(this);
    }
    
    @Override
    public void updateSpritePallet(Pallet p){
        if(this.getHeight() == level.getLEFT_BOUNDARY().getHeight() && this.getX() == level.getLEFT_BOUNDARY().getX()){
            p.updateSpriteAfterCollidingWithLeftBoundary();
        }
        if(this.getHeight() == level.getRIGHT_BOUNDARY().getHeight() && this.getX() == level.getRIGHT_BOUNDARY().getX()){
            p.updateSpriteAfterCollidingWithRightBoundary();
        }
    }

    @Override
    public void updateSpriteBall(Ball aBall) {
        if(this.getLength() == level.getTOP_BOUNDARY().getLength() && this.getY() == level.getTOP_BOUNDARY().getY()){
            aBall.updateSpriteAfterCollidingWithTopBoundary();
        } else if(this.getHeight() == level.getLEFT_BOUNDARY().getHeight() && this.getX() == level.getLEFT_BOUNDARY().getX()){
            aBall.updateSpriteAfterCollidingWithLeftBoundary();
        } else if(this.getHeight() == level.getRIGHT_BOUNDARY().getHeight() && this.getX() == level.getRIGHT_BOUNDARY().getX()){
            aBall.updateSpriteAfterCollidingWithRightBoundary();
        }else if(this.getLength() == level.getBOTTOM_BOUNDARY().getLength() && this.getY() == level.getBOTTOM_BOUNDARY().getY()){
            aBall.updateSpriteAfterCollidingWithBottomBoundary();
        }        
    }
    
    @Override
    public String toString() {
        return "Rectangle";
    }
    
}
