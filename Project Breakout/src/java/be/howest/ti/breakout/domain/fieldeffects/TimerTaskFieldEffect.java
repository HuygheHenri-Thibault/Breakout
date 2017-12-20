/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.fieldeffects;

import be.howest.ti.breakout.domain.effects.Effect;
import java.util.TimerTask;

/**
 *
 * @author Fredr
 */
public class TimerTaskFieldEffect extends TimerTask{

    private final FieldEffect fieldEffect;
    private final Effect effect;
    
    public TimerTaskFieldEffect(FieldEffect fieldEffect, Effect effect) {
        this.fieldEffect = fieldEffect;
        this.effect = effect;
    }

    @Override
    public void run() {
        if(!fieldEffect.IsPaused()){
            effect.setActive();
        }
    }
    
}
