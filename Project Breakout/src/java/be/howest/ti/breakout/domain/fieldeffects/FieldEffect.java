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
    private final String name;
    private final Effect effect;
    private final int interval;
    private final Timer timerFieldEffect;

    public FieldEffect(String name, Effect effect, int interval) {
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
      
    public void doEffect(Level level)
    {
        effect.setLevelOfEffect(level);
        timerFieldEffect.scheduleAtFixedRate(new TimerTaskFieldEffect(effect), 0, interval * 1000);
    }
    
    public void cancel(){
        this.timerFieldEffect.cancel();
    }
}
