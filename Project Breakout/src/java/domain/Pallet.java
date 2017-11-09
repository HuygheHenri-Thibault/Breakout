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
    private final Level level;
    private float speed;
    private float dx;

    public Pallet(String color, Level level, int x, int y, int lenght, int height) {
        super(level, color, x, y, lenght, height);
        this.level = level;
    }

    public Pallet(String color, Level level, int x, int y, int length, int height, float speed) {
        super(level, color, x, y, length, height);
        this.level = level;
        this.speed = speed;
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

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            setDx(-speed);
        }

        if (key == KeyEvent.VK_RIGHT) {
            setDx(Math.abs(speed));
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            setDx(0);
        }

        if (key == KeyEvent.VK_RIGHT) {
            setDx(0);
        }
    }

    public void toggleDx() {
        if (dx > 0) {
            dx = -speed;
        } else {
            dx = speed;
        }
    }

    public void move() {
        this.setX((int) (this.getX() + dx));
        if (collidesWithOtherRectangleOrBoundaries()) {
            updateSpriteAfterCollidingWithRectangle();
        }
    }

    public boolean collidesWithOtherRectangleOrBoundaries() {
        for (Rectangle r : level.getPallets()) {
            if (this.getX() != r.getX()) {
                if (this.checkCollission(r)) {
                    return true;
                }
            }
        }
        if (this.checkCollission(level.getLEFT_BOUNDARY())) { return true;}
        if (this.checkCollission(level.getRIGHT_BOUNDARY())) { return true;}
        return false;
    }

    public void updateSpriteAfterCollidingWithRectangle() {
        toggleDx();
        int xBeforeCollission = (int) (this.getX() + dx);
        this.setX(xBeforeCollission);
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    @Override
    public void updateSpriteBall(Ball aBall) {
        aBall.updateSpriteBallAfterCollidingWithRectangle(this);
    }
    
}
