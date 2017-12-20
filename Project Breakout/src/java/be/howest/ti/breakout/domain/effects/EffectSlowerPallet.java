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
public final class EffectSlowerPallet extends Effect {

    public EffectSlowerPallet(String name, int duration) {
        super(name, duration);
    }

    @Override
    public void activate() {
        setRunning();

        userPallet.setSpeed(userPallet.getSpeed() - 1);

        System.out.println("activated slowed");

        TimerEffect = new Timer();
        TimerEffect.schedule(new TimerTaskEffect(this), 0, 1000);
    }

    @Override
    public void deActivate() {
        System.out.println("deactivated slowed");
        TimerEffect.cancel();
        userPallet.setSpeed(getUserPallet().getSpeed() + 1);
        setDone();
    }
    
    @Override
    public String toString() {
        return super.toString() + " slowed";
    }
}
