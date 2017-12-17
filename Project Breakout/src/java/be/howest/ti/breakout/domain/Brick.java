/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain;

import be.howest.ti.breakout.domain.game.Level;
import java.awt.Image;
import be.howest.ti.breakout.domain.powerUps.NoPower;
import be.howest.ti.breakout.domain.powerUps.PowerUpOrDown;

/**
 *
 * @author micha
 */
public class Brick extends Rectangle{
    private Sprite s;
    //private BrickRow br;
    private int hits;
    private int achievedScoreForUser;
    private PowerUpOrDown powerUp;

    public Brick(Level level, int lenght, int height, int hits, int achievedScoreForUser, String color, int x, int y) {
        this(level, lenght, height, hits, achievedScoreForUser, new NoPower(), color, x, y);
    }
    
    public Brick(Level level, int lenght, int height, int hits, int achievedScoreForUser, PowerUpOrDown power, String color, int x, int y) {
        super(level, x, y, lenght, height);
        this.s = new Sprite(color);
        //this.br = br;
        this.hits = hits;
        this.achievedScoreForUser = achievedScoreForUser;
        this.powerUp = power;
    }
   
//    public BrickRow getBr() {
//        return br;
//    }
    
//    public Level getLevel(){
//        return ;
//    }
    

    public int getHits() {
        return hits;
    }
    
    public void decrementHits(Ball ball){
        hits = hits - ball.getDamage();
    }
    
    public void setAcheievedScore(int acheievedScore) {
        this.achievedScoreForUser = acheievedScore;
    }

    public int getAchievedScore() {
        return achievedScoreForUser;
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
        return "Brick " + s.getColor(); // kleur ophalen moet naar shape gebracht worden
    }
}
