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
public class Sprite {
    protected Image skin;
    protected int x;
    protected int y;

    public Sprite(Image skin, int x, int y) {
        this.skin = skin;
        this.x = x;
        this.y = y;
    }

    public Image getSkin() {
        return skin;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setSkin(Image skin) {
        this.skin = skin;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
}
