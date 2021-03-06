/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.fieldeffects;

import be.howest.ti.breakout.domain.effects.Effect;
import be.howest.ti.breakout.domain.game.Level;
import java.util.Timer;

/**
 *
 * @author Fredr
 */
public class FieldEffect {
    private Level level;
    private final String name;
    private final String description;
    private final Effect effect;
    private final int interval;
    private Timer timerFieldEffect;
    private boolean paused = false;

    public FieldEffect(String name, String description, Effect effect, int interval) {
        this(null, name, description, effect, interval);
    }
    
    public FieldEffect(Level level, String name, String description, Effect effect, int interval) {
        this.level = level;
        this.name = name;
        this.description = description;
        this.effect = effect;
        this.interval = interval;
        this.timerFieldEffect = new Timer();
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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
        timerFieldEffect.scheduleAtFixedRate(new TimerTaskFieldEffect(this, effect), 2000, interval * 1000);
    }
    
    public void stopFieldEffect(){
        if(effect.hasTimer()){
            effect.getTimerEffect().cancel();
        }
        timerFieldEffect.cancel();
    }
    
    public void pause(){
        paused = true;
        effect.pause();
    }
    
    public boolean IsPaused(){
        return paused;
    }
    
    public void resume(){
        paused = false;
        effect.unpause();
    }
    

    @Override
    public String toString() {
        return "FieldEffect";
    }
    
}
