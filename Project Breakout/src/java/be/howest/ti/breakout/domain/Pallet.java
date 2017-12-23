/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain;

import be.howest.ti.breakout.domain.fieldeffects.Web;
import be.howest.ti.breakout.domain.game.Level;
import be.howest.ti.breakout.domain.game.Player;

/**
 *
 * @author micha
 */
public final class Pallet extends Rectangle {
   
    private Sprite s;
    private final Player player;
    
    private final int originalLenght; 
   
    private final int INIT_PALLET_X;
    private final int INIT_PALLET_Y;
    private final float originalSpeed;
    private float speed;
    private float dx;

    private Ball lastBallTouched;
    
    private boolean visible = true;

    public Pallet(Player player, String color, Level level, int x, int y, int length, float speed) {
        super(level, x, y, length, 10);
        this.player = player;
        this.s = new Sprite(color);
        this.originalSpeed = speed;
        this.speed = speed;
        this.originalLenght = length;
        this.INIT_PALLET_X = x;
        this.INIT_PALLET_Y = y;
    }

    public Player getPlayer() {
        return player;
    }
    
    public int getOriginalLenght() {
        return originalLenght;
    }

    public float getSpeed() {
        return speed;
    }
    
    public float getOriginalSpeed(){
        return originalSpeed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Ball getLastBallTouched() {
        return lastBallTouched;
    }

    public void setLastBallTouched(Ball lastBallTouched) {
        this.lastBallTouched = lastBallTouched;
    }
    
    public void setInvisible(){
        this.visible = false;
    }
    
    public void setVisible(){
        this.visible = true;
    }
    
    public boolean IsVisible(){
        return visible;
    }
    
    public void resetState() {
        setX(INIT_PALLET_X);
        setY(INIT_PALLET_Y);
    }
    
    public void move() {
        this.setX((int) (this.getX() + dx));
        Shape shape = collidesWithOtherRectangleOrBoundaries();
        if (shape != null) {
            shape.updateSpritePallet(this);
        }
    }
    
    public void moveLeft() {
        setDx(-speed);
    }

    public void moveRight() {
        setDx(speed);
    }

    public void stopMoving() {
        setDx(0);
    }
    
    public void setDx(float dx) {
        this.dx = dx;
    }

    private Shape collidesWithOtherRectangleOrBoundaries() {
        for (Shape s : getLevel().getAllEntities()) {
            if (this.getX() != s.getX()) {
                if (this.checkCollission(s)) {
                    return s;
                }
            }
        }
        return null;
    }

    @Override
    public void updateSpritePallet(Pallet OurPallet) {
        if (OurPallet.collidesWithRightSide(this)) {
            OurPallet.updateSpriteAfterCollidingWithRightBoundary();
        } else if (OurPallet.collidesWithLeftSide(this)) {
                OurPallet.updateSpriteAfterCollidingWithLeftBoundary();
        }
    }

    private boolean collidesWithRightSide(Rectangle p) {
        return this.getX() + this.getLength() > p.getX() && this.getX() + this.getLength() < p.getX() + p.getLength();
    }

    private boolean collidesWithLeftSide(Rectangle p) {
        return this.getX() > p.getX() && this.getX() < p.getX() + p.getLength();
    }

    public void updateSpriteAfterCollidingWithLeftBoundary() {
        moveRight();
        while (collidesWithOtherRectangleOrBoundaries() != null) {
            int newXAwayFromBoundary = (int) (getX() + dx);
            this.setX(newXAwayFromBoundary);
        }
        moveLeft();
    }

    public void updateSpriteAfterCollidingWithRightBoundary() {
        moveLeft();
        while (collidesWithOtherRectangleOrBoundaries() != null) {
            int newXAwayFromBoundary = (int) (getX() + dx);
            this.setX(newXAwayFromBoundary);
        }
        moveRight();
    }

    public void updateSpriteAfterCollidingWithWeb(Web web){
        speed = Math.round(originalSpeed / 2);
        this.setX((int) (this.getX() + dx));
        if(checkCollissionWithCircle(web)){
            this.setX((int) (this.getX() - dx));
        } else {
            this.setX((int) (this.getX() - dx));
            speed = originalSpeed;
        }
    }
    

    @Override
    public void updateSpriteBall(Ball aBall) {
        aBall.updateSpriteBallAfterCollidingWithPallet(this);
    }

    @Override
    public String toString() {
        return "Pallet";
    }
}
