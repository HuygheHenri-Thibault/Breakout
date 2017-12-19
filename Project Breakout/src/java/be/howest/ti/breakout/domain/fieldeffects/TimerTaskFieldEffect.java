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

    private final Effect effect;
    
    public TimerTaskFieldEffect(Effect effect) {
        this.effect = effect;
    }

    @Override
    public void run() {
        effect.setActive();
    }
    
}
