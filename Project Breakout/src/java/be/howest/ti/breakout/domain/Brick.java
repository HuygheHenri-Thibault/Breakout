/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain;

import be.howest.ti.breakout.domain.game.Level;
import be.howest.ti.breakout.domain.powerUps.NoPower;
import be.howest.ti.breakout.domain.powerUps.PowerUpOrDown;

/**
 *
 * @author micha
 */
public class Brick extends Rectangle{
    private Sprite s;
    private int hits;
    private int achievedScoreForPlayer;
    private PowerUpOrDown powerUp;

    public Brick(Level level, int lenght, int height, int hits, int achievedScoreForPlayer, String color, int x, int y) {
        this(level, lenght, height, hits, achievedScoreForPlayer, new NoPower(), color, x, y);
    }
    
    public Brick(Level level, int lenght, int height, int hits, int achievedScoreForPlayer, PowerUpOrDown power, String color, int x, int y) {
        super(level, x, y, lenght, height);
        this.s = new Sprite(color);
        this.hits = hits;
        this.achievedScoreForPlayer = achievedScoreForPlayer;
        this.powerUp = power;
    }
    
    public int getHits() {
        return hits;
    }
    
    public void decrementHits(int damage){
        hits = hits - damage;
    }
    
    public void setAcheievedScore(int acheievedScore) {
        this.achievedScoreForPlayer = acheievedScore;
    }

    public int getAchievedScore() {
        return achievedScoreForPlayer;
    }
    
    public void setPowerUp(PowerUpOrDown powerUp){
        this.powerUp = powerUp;
    }
    
    public PowerUpOrDown getPowerUP(){
        return powerUp;
    }
    
    @Override
    public void updateSpriteBall(Ball aBall) {
        aBall.updateSpriteBallAfterCollidingWithBrick(this);
    }
    
    @Override
    public String toString() {
        return "Brick " + s.getColor();
    }
}
