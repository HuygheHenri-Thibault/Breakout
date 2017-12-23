/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.effects;

import java.util.Timer;

/**
 *
 * @author micha
 */
public final class EffectOneLifeLeft extends Effect{
    private int originalLivesLeft;
    
    public EffectOneLifeLeft(String name, String description, int duration) {
        super(name, description, duration);
    }
    
    @Override
    public void activate() {
        setRunning();
        originalLivesLeft = LevelOfEffect.getGame().getLivesLeftOriginally();
        LevelOfEffect.getGame().setLives(1);
        TimerEffect = new Timer();
        TimerEffect.schedule(new TimerTaskEffect(this), 0, 1000);
    }

    @Override
    public void deActivate() {
        TimerEffect.cancel();
        LevelOfEffect.getGame().setLives(originalLivesLeft);
        setDone();
    }
    
    @Override
    public String toString() {
        return super.toString() + " sudden-death"; 
    }
}
