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
    private final static int HEIGHT = 50;
    private int hits;
    private int achievedScore;
    private boolean destroyed;
    
    public Brick(String color, int x, int y) {
        super(color, x, y);
    }

    public Brick(float lenght, int hits, int achievedScore, boolean destroyed, String color, int x, int y) {
        super(color, x, y);
        this.lenght = lenght;
        this.hits = hits;
        this.achievedScore = achievedScore;
        this.destroyed = destroyed;
    }

    public float getLenght() {
        return lenght;
    }

    public float getHeight() {
        return HEIGHT;
    }

    public int getHits() {
        return hits;
    }

    public int getAcheievedScore() {
        return achievedScore;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setLenght(float lenght) {
        this.lenght = lenght;
    }
    
    public void setAcheievedScore(int acheievedScore) {
        this.achievedScore = acheievedScore;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }
}
