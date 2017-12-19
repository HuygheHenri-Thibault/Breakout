/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain;

import be.howest.ti.breakout.domain.fieldeffects.Web;
import be.howest.ti.breakout.domain.game.Level;
import be.howest.ti.breakout.domain.game.User;
import java.awt.Image;
import java.awt.event.KeyEvent;

/**
 *
 * @author micha
 */
public final class Pallet extends Rectangle {
   
    private Sprite s;
    private final User user;
    //private final Level level;
    
    private final int originalLenght; //check this later
   
    private final int INIT_PALLET_X;
    private final int INIT_PALLET_Y;
    private final float originalSpeed;
    private float speed;
    private float dx;

    private Ball lastBallTouched;
    
    private boolean visible = true;

    public Pallet(User user, String color, Level level, int x, int y, int length, float speed) {
        super(level, x, y, length, 10);
        this.user = user;
        //this.level = level;
        this.s = new Sprite(color);
        this.originalSpeed = speed;
        this.speed = speed;
        this.originalLenght = length;
        this.INIT_PALLET_X = x;
        this.INIT_PALLET_Y = y;
    }

    public User getUser() {
        return user;
    }
    
    public int getOriginalLenght() {
        return originalLenght;
    }

//    @Override
//    public Level getLevel() {
//        return level;
//    }

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

    //voor swing
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            moveLeft();
        }

        if (key == KeyEvent.VK_RIGHT) {
            moveRight();
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            stopMoving();
        }

        if (key == KeyEvent.VK_RIGHT) {
            stopMoving();
        }
    }
    //

//    public void toggleDx() {
//        if (dx > 0) {
//            dx = -speed;
//        } else {
//            dx = speed;
//        }
//    }
    
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

    //kan dit veranderen, ipv shape terug te geven, geef gew de functie terug, bv -> checkCollission voor left boundary roept direct updateSpritePalletAfterCollission with left boundary op
    private Shape collidesWithOtherRectangleOrBoundaries() {
        for (Shape s : getLevel().getAllEntities()) {
            if (this.getX() != s.getX()) {
                if (this.checkCollission(s)) {
                    return s;
                }
            }
        }
        return null;
//        for (Pallet pallet : getLevel().getPallets()) {
//            if (this.getX() != pallet.getX()) {
//                if (this.checkCollission(pallet)) {
//                    return pallet;
//                }
//            }
//        }
//        for (Circle circle : getLevel().getAllShapesCreatedByFieldEffect()) {
//            if (this.checkCollission(circle)) {
//                return circle;
//            }
//        }
//        if (this.checkCollission(getLevel().getLEFT_BOUNDARY())) {
//            return getLevel().getLEFT_BOUNDARY();
//        }
//        if (this.checkCollission(getLevel().getRIGHT_BOUNDARY())) {
//            return getLevel().getRIGHT_BOUNDARY();
//        }
    }

    @Override
    public void updateSpritePallet(Pallet OurPallet) {
        if (OurPallet.collidesWithRightSide(this)) {
            OurPallet.updateSpriteAfterCollidingWithRightBoundary();
//           if(this.dx == 0){
//              this.moveRight();
//              
//              if(OurPallet.dx != speed){
//                  this.stopMoving();
//              }
//            }
        } else if (OurPallet.collidesWithLeftSide(this)) {
                OurPallet.updateSpriteAfterCollidingWithLeftBoundary();
//            if(this.dx == 0){
//                this.moveLeft();
//                if(OurPallet.dx != -speed){
//                    this.stopMoving();
//                }
//            }
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
