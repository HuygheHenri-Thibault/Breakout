/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.powerUps;

import be.howest.ti.breakout.domain.effects.Effect;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author micha
 */
public final class NoPower extends PowerUpOrDown{

    public NoPower() {
    }

    @Override
    public void show() {
    }
    
    @Override
     public List<Effect> getEffects() {
        return new ArrayList<>();
    }
     
      @Override
    public void activate(){
       
    }
    
    @Override
    public void deActivate(){
       
    }

    @Override
    public String toString() {
        return "NoPower";
    }
}
