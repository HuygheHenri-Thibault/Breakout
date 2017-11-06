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
public class Ball extends Sprite{
    private int radius;
    private float snelheid;
    
    public Ball(Image skin, int x, int y) {
        super(skin, x, y);
    }

    public Ball(int radius, float snelheid, Image skin, int x, int y) {
        super(skin, x, y);
        this.radius = radius;
        this.snelheid = snelheid;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public float getSnelheid() {
        return snelheid;
    }

    public void setSnelheid(float snelheid) {
        this.snelheid = snelheid;
    }
    
    
    
}
