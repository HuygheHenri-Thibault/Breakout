/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.fieldeffects;

import be.howest.ti.breakout.domain.effects.Effect;
import java.util.Timer;

/**
 *
 * @author Fredr
 */
public class FieldEffect {
    private String name;
    private Effect effect;
    private int interval;
    private Timer timerFieldEffect;

    public FieldEffect(String name, Effect effect, int interval) {
        this.name = name;
        this.effect = effect;
        this.interval = interval;
        this.timerFieldEffect = new Timer();
    }
      
    private void doEffect()
    {
        
    }
}
