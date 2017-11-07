/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import collissionDetectors.CollissionDetectorPallet;
import java.awt.Image;
import java.awt.event.KeyEvent;

/**
 *
 * @author micha
 */
public class Pallet extends Sprite{
    private float length;
    private final float height = 35;
    private float speed;
    private float dx;
    
    private CollissionDetectorPallet cdp = new CollissionDetectorPallet(this); 
    
    private final int MIN_PALLET_BORDER;
    private final int MAX_PALLET_BORDER;

    public Pallet(String color, int x, int y, int min_pallet_border, int max_pallet_border) {
        super(color, x, y);
        this.MIN_PALLET_BORDER = min_pallet_border;
        this.MAX_PALLET_BORDER = max_pallet_border;
    }

    public Pallet(String color, int x, int y, float length, float speed, int min_pallet_border, int max_pallet_border) {
        super(color, x, y);
        this.length = length;
        this.speed = speed;
        this.MIN_PALLET_BORDER = min_pallet_border;
        this.MAX_PALLET_BORDER = max_pallet_border;
    }

    public float getLength() {
        return length;
    }

    public float getHeight() {
        return height;
    }

    public float getSpeed() {
        return speed;
    }
    
    public void setLength(float length) {
        this.length = length;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getMIN_PALLET_BORDER() {
        return MIN_PALLET_BORDER;
    }

    public int getMAX_PALLET_BORDER() {
        return MAX_PALLET_BORDER;
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
    
    public void move(){
        x += dx;
        cdp.detect();
    }

    public void setDx(float dx) {
        this.dx = dx;
    }
    
}
