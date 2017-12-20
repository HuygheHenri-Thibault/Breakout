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
public final class EffectQuickerPallet extends Effect{

    public EffectQuickerPallet(String name, int duration) {
        super(name, duration);
    }

    @Override
    public void activate() {
        setRunning();
        
        userPallet.setSpeed(getUserPallet().getSpeed() + 1);
        
        System.out.println("activated bullet time");
      
        TimerEffect = new Timer();
        TimerEffect.schedule(new TimerTaskEffect(this), 0, 1000);
    }
    
    @Override
    public void deActivate() {
        System.out.println("deactivated bullet time");
        TimerEffect.cancel();
        userPallet.setSpeed(getUserPallet().getSpeed() - 1);
        setDone();
    }
    
    @Override
    public String toString() {
        return super.toString() + " bullet-time"; 
    }
    
}
