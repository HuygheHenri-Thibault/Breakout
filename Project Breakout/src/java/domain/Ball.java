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
    private float speed;
    private float dx;
    private float dy;
    
    public Ball(String color, int x, int y) {
        super(color, x, y);
    }

    public Ball(int radius, float speed, String color, int x, int y) {
        super(color, x, y);
        this.radius = radius;
        this.speed = speed;
        this.dx = speed;
        this.dy = -speed;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
        this.dx = speed; //nog veranderen
        this.dy = -speed; //nog veranderen
    }
    
    public void move(){
        x += dx;
        y += dy;
        
// collission detection
//        
//        if (x == 0) {
//            setXDir(1);
//        }
//
//        if (x == WIDTH - i_width) {
//            setXDir(-1);
//        }
//
//        if (y == 0) {
//            setYDir(1);
//        }
    }
    
}
