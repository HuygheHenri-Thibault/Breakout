/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.awt.Image;
import java.awt.event.KeyEvent;

/**
 *
 * @author micha
 */
public class Pallet extends Rectangle {

    private Sprite s;
    private final int userID;
    private final Level level;
    private float speed;
    private float dx;
    private final int INIT_PALLET_X;
    private final int INIT_PALLET_Y;

    public Pallet(int userID, String color, Level level, int x, int y, int length, float speed) {
        super(level, x, y, length, 10);
        this.userID = userID;
        this.level = level;
        this.s = new Sprite(color);
        this.speed = speed;
        this.INIT_PALLET_X = x;
        this.INIT_PALLET_Y = y;
    }

    public int getUserID() {
        return userID;
    }

    public Level getLevel() {
        return level;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
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

    public void toggleDx() {
        if (dx > 0) {
            dx = -speed;
        } else {
            dx = speed;
        }
    }

    public void move() {
        this.setX((int) (this.getX() + dx));
        Shape shape = collidesWithOtherRectangleOrBoundaries();
        if (shape != null) {
            shape.updateSpritePallet(this);
        }
    }

    //kan dit veranderen, ipv shape terug te geven, geef gew de functie terug, bv -> checkCollission voor left boundary roept direct updateSpritePalletAfterCollission with left boundary op
    public Shape collidesWithOtherRectangleOrBoundaries() {
        for (Rectangle r : level.getPallets()) {
            if (this.getX() != r.getX()) {
                if (this.checkCollission(r)) {
                    return r;
                }
            }
        }
        if (this.checkCollission(level.getLEFT_BOUNDARY())) {
            return level.getLEFT_BOUNDARY();
        }
        if (this.checkCollission(level.getRIGHT_BOUNDARY())) {
            return level.getRIGHT_BOUNDARY();
        }
        return null;
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

    public boolean collidesWithRightSide(Rectangle p) {
        return this.getX() + this.getLength() > p.getX() && this.getX() + this.getLength() < p.getX() + p.getLength();
    }

    public boolean collidesWithLeftSide(Rectangle p) {
        return this.getX() > p.getX() && this.getX() < p.getX() + p.getLength();
    }

    public void updateSpriteAfterCollidingWithLeftBoundary() {
        moveRight();
        while (collidesWithOtherRectangleOrBoundaries() != null) {
            int xToBoundary = (int) (getX() + dx);
            this.setX(xToBoundary);
        }
        moveLeft();
    }

    public void updateSpriteAfterCollidingWithRightBoundary() {
        moveLeft();
        while (collidesWithOtherRectangleOrBoundaries() != null) {
            int xToBoundary = (int) (getX() + dx);
            this.setX(xToBoundary);
        }
        moveRight();
    }

    public void setDx(float dx) {
        this.dx = dx;
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
