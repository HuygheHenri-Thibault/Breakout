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

    public EffectSlowerPallet(String name, String description, int duration) {
        super(name, description, duration);
    }

    @Override
    public void activate() {
        setRunning();

        playerPallet.setSpeed(playerPallet.getSpeed() - 1);

        System.out.println("activated slowed");

        TimerEffect = new Timer();
        TimerEffect.schedule(new TimerTaskEffect(this), 0, 1000);
    }

    @Override
    public void deActivate() {
        System.out.println("deactivated slowed");
        TimerEffect.cancel();
        playerPallet.setSpeed(getPlayerPallet().getSpeed() + 1);
        setDone();
    }
    
    @Override
    public String toString() {
        return super.toString() + " slowed";
    }
}
