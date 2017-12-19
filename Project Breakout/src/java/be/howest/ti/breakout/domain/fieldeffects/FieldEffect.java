/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.fieldeffects;

import be.howest.ti.breakout.domain.effects.Effect;
import be.howest.ti.breakout.domain.effects.TimerTaskEffect;
import be.howest.ti.breakout.domain.game.Level;
import java.util.Timer;

/**
 *
 * @author Fredr
 */
public class FieldEffect {
    private final Level level;
    private final String name;
    private final Effect effect;
    private final int interval;
    private Timer timerFieldEffect;
    private boolean paused = false;

    public FieldEffect(Level level, String name, Effect effect, int interval) {
        this.level = level;
        this.name = name;
        this.effect = effect;
        this.interval = interval;
        this.timerFieldEffect = new Timer();
    }

    public String getName() {
        return name;
    }

    public Effect getEffect() {
        return effect;
    }

    public int getInterval() {
        return interval;
    }
    
   
      
    public void doEffect()
    {
        effect.setLevelOfEffect(level);
        timerFieldEffect.scheduleAtFixedRate(new TimerTaskFieldEffect(effect), 2000, interval * 1000);
    }
    
    public void pause(){
        paused = true;
        effect.setDeActive();
        timerFieldEffect.cancel();
    }
    
    public boolean IsPaused(){
        return paused;
    }
    
    public void resume(){
        paused = false;
        timerFieldEffect = new Timer();
        doEffect();
    }
    
    public void cancel(){
        this.timerFieldEffect.cancel();
    }

    @Override
    public String toString() {
        return "FieldEffect";
    }
    
}
