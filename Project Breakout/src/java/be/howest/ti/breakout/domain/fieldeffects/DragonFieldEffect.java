/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.fieldeffects;

import be.howest.ti.breakout.domain.effects.Effect;

/**
 *
 * @author Fredr
 */
public class DragonFieldEffect extends FieldEffect {
    private String pathToSprite;
    
    public DragonFieldEffect(String name, Effect effect, int interval) {
        super(name, effect, interval);
    }
    
}
