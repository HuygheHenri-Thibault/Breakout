/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.awt.Image;

/**
 *
 * @author micha
 */
public class Ball extends Circle{
    private final Level level;
    private int speed;
    private float dx;
    private float dy;
    private final int INIT_BALL_X;
    private final int INIT_BALL_Y;

    public Ball(Level level, int radius, int speed, String color, int x, int y) {
        super(level, color, x, y, radius);
        this.level = level;
        this.INIT_BALL_X = x;
        this.INIT_BALL_Y = y;
        this.speed = speed * 2;
        this.dx = speed;
        this.dy = -speed;
    }
    
    public float getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed * 2;
        resetDx();
        resetDy();
    }

    public void setDx(int dx){
        this.dx = dx;
    }
    
    public void setDy(int dy){
        this.dy = dy;
    }
    
    public void toggleDx(){
        dx = -dx;
    }
    
    public void toggleDy(){
        dy = -dy;
    }
    
    public void resetDx(){
        if(dx > 0){
            dx = speed - Math.abs(dy);
        } else {
            dx = -speed + Math.abs(dy);
        }
    }
    
    public void resetDy(){
        if(dy > 0){
            dy = speed / 2;
        } else {
            dy = -speed / 2;
        }
    }
    
    public void cutDyBy(int getal){
        resetDx();
        resetDy();
        this.dx = Math.round(dx / getal);
        if(dy > 0){
            dy = speed - Math.abs(dx);
        } else {
            dy = -speed + Math.abs(dx);
        }
        toggleDy();
    }
    
    public void move(){
        this.setX(Math.round(this.getX() + dx));
        this.setY(Math.round(this.getY() + dy));
        Sprite s = findCollidingSprite();
        if (s!=null) s.updateSpriteBall(this);
    }
    
    public void resetState(){
        this.setX(INIT_BALL_X);
        this.setY(INIT_BALL_Y);
        dx = speed / 2;
        dy = -speed / 2;
    }
    
    public Sprite findCollidingSprite() {
        for (Sprite s : level.getAllEntities()) {
            if(this.getX() != s.getX()){
                if(this.checkCollission(s)){
                    return s;
                }
            }  
        }
        return null;
    }
    
    public void updateSpriteBallAfterCollidingWithRectangle(Rectangle r){
        float rectPos = r.getX();
        float ballLPos = this.getX();
        
        float leftSide = rectPos;
        float first = rectPos + (r.getLength() / 5); 
        float second = rectPos + ((r.getLength() / 5) * 2);
        float third = rectPos + ((r.getLength() / 5) * 3);
        float fourth = rectPos + ((r.getLength() / 5) * 4);
        float rightSide = rectPos + r.getLength();
        
        if(ballLPos < leftSide){
            toggleDx();
        }

        if (ballLPos >= leftSide && ballLPos < first) {
            cutDyBy(4);
        }

        if (ballLPos >= first && ballLPos < second) {
            cutDyBy(2);
        }
        
        if(ballLPos >= second && ballLPos < third){
            toggleDy();
        }

        if (ballLPos >= third && ballLPos < fourth) {
            cutDyBy(2);
        }

        if (ballLPos >= fourth && ballLPos < rightSide ) {
            cutDyBy(4);
        }
        
        if(ballLPos >= rightSide){
            toggleDx();
        }
    }
    
    public void updateSpriteBallAfterCollidingWithBrick(Brick b){
        updateSpriteBallAfterCollidingWithRectangle(b);
        //b.setDestroyed(true);
        level.deleteBrick(b);
    }
    
    public void updateSpriteAfterCollidingWithCircle(){
        toggleDx();
        toggleDy();
    }
    
    public void updateSpriteAfterCollidingWithLeftORRighBoundary(){
        toggleDx();
    }
    
    public void updateSpriteAfterCollidingWithTopBoundary(){
        toggleDy();
    }
    
    public void updateSpriteAfterCollidingWithBottomBoundary(){
        resetState();
        level.decrementLife();
    }
}
