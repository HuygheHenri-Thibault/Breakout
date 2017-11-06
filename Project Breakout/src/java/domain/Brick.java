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
public class Brick extends Sprite{
    private float lenght;
    private float height;
    private int hits;
    private boolean destroyed;
    
    public Brick(Image skin, int x, int y) {
        super(skin, x, y);
    }

    public Brick(float lenght, float height, int hits, boolean destroyed, Image skin, int x, int y) {
        super(skin, x, y);
        this.lenght = lenght;
        this.height = height;
        this.hits = hits;
        this.destroyed = destroyed;
    }

    public float getLenght() {
        return lenght;
    }

    public float getHeight() {
        return height;
    }

    public int getHits() {
        return hits;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setLenght(float lenght) {
        this.lenght = lenght;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }
    
}
