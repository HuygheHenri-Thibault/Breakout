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
    private BrickRow br;
    private int hits;
    private int achievedScore;
    private PowerUpOrDown powerUp = new NoPower();

    public Brick(BrickRow br, int lenght, int height, int hits, int achievedScore, String color, int x, int y) {
        super(br.getLevel(), x, y, lenght, height);
        this.s = new Sprite(color);
        this.br = br;
        this.hits = hits;
        this.achievedScore = achievedScore;
    }
    
    public Brick(BrickRow br, int lenght, int height, int hits, int achievedScore, PowerUpOrDown power, String color, int x, int y) {
        super(br.getLevel(), x, y, lenght, height);
        this.s = new Sprite(color);
        this.br = br;
        this.hits = hits;
        this.achievedScore = achievedScore;
        this.powerUp = power;
    }
    
    public void setPowerUp(PowerUpOrDown powerUp){
        this.powerUp = powerUp;
    }
    
    public PowerUpOrDown getPowerUP(){
        return powerUp;
    }

    public BrickRow getBr() {
        return br;
    }
    
    public Level getLevel(){
        return br.getLevel();
    }
    

    public int getHits() {
        return hits;
    }
    
    public void decrementHits(Ball ball){
        hits = hits - ball.getDamage();
    }
    
    public void setAcheievedScore(int acheievedScore) {
        this.achievedScore = acheievedScore;
    }

    public int getAchievedScore() {
        return achievedScore;
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
