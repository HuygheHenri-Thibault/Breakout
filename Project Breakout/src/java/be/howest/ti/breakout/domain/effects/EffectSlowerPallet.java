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
        //Pallet palletOfUser = getUserPallet();
        //setUserPallet(palletOfUser);
        setRunning();

        getUserPallet().setSpeed(getUserPallet().getSpeed() - 1);

        System.out.println("activated slowed");

        setTimerEffect(new Timer());
        getTimerEffect().schedule(new TimerTaskEffect(this), 0, 1000);
    }

    @Override
    public void deActivate() {
        System.out.println("deactivated slowed");
        getTimerEffect().cancel();
        getUserPallet().setSpeed(getUserPallet().getSpeed() + 1);
        setDone();
    }

    @Override
    public String toString() {
        return super.toString() + " slowed";
    }
}
