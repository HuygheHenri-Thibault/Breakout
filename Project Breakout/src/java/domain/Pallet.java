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
public class Pallet extends Sprite{
    private float length;
    private float height;
    private float speed;

    public Pallet(Image skin, int x, int y) {
        super(skin, x, y);
    }

    public Pallet(Image skin, int x, int y, float length, float height, float speed) {
        super(skin, x, y);
        this.length = length;
        this.height = height;
        this.speed = speed;
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
}
