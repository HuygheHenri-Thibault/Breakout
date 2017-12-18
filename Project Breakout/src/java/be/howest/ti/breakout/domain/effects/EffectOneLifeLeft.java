/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.effects;

import be.howest.ti.breakout.domain.Ball;
import be.howest.ti.breakout.domain.Brick;
import be.howest.ti.breakout.domain.game.Level;
import java.util.Timer;

/**
 *
 * @author micha
 */
public final class EffectOneLifeLeft extends Effect{
    private int originalLivesLeft;
    
    public EffectOneLifeLeft(String name, int duration) {
        super(name, duration);
    }
    
    @Override
    public void activate() {
        System.out.println("activated sudden death");
        setRunning();
        originalLivesLeft = getLevelOfEffect().getGame().getLivesLeftOriginally();
        getLevelOfEffect().getGame().setLives(1);
        setTimerEffect(new Timer());
        getTimerEffect().schedule(new TimerTaskEffect(this), 0, 1000);
    }

    @Override
    public void deActivate() {
        System.out.println("deactivated sudden death");
        getTimerEffect().cancel();
        getLevelOfEffect().getGame().setLives(originalLivesLeft);
        setDone();
    }
    
    @Override
    public String toString() {
        return super.toString() + " sudden-death"; 
    }
}
