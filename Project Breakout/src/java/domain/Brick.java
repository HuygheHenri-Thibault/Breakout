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
public class Brick extends Rectangle{
    private BrickRow br;
    private int hits;
    private int achievedScore;
    //private boolean destroyed;

    public Brick(BrickRow br, int lenght, int height, int hits, int achievedScore, String color, int x, int y) {
        super(br.getLevel(), color, x, y, lenght, height);
        this.br = br;
        this.hits = hits;
        this.achievedScore = achievedScore;
        //this.destroyed = destroyed;
    }

    public BrickRow getBr() {
        return br;
    }
    

    public int getHits() {
        return hits;
    }
    
    public void decrementHits(){
        hits--;
    }
    
    public void setAcheievedScore(int acheievedScore) {
        this.achievedScore = acheievedScore;
    }

    public int getAchievedScore() {
        return achievedScore;
    }

//    public void setDestroyed(boolean destroyed) {
//        this.destroyed = destroyed;
//    }
//    
//    public boolean isDestroyed() {
//        return destroyed;
//    }
    
    @Override
    public void updateSpriteBall(Ball aBall) {
        aBall.updateSpriteBallAfterCollidingWithBrick(this);
    }
}
