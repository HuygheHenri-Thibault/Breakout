/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package powerUps;

import domain.Ball;
import domain.Brick;
import domain.Level;
import domain.Pallet;
import java.util.Timer;

/**
 *
 * @author micha
 */
public class EffectSlowed extends Effect {

    public EffectSlowed(String name, int duration) {
        super(name, duration);
    }

    @Override
    public void activate() {
        Pallet palletOfUser = getThisLevel().getUserPallet(getUserActivatedEffect().getUserId());
        setUserPallet(palletOfUser);
        setRunning();

        getUserPallet().setSpeed(getUserPallet().getSpeed() - 1);

        System.out.println("activated slowed");

        setT(new Timer());
        getT().schedule(new TimerTaskEffect(this), 0, 1000);
    }

    @Override
    public void deActivate() {
        System.out.println("deactivated slowed");
        getT().cancel();
        getUserPallet().setSpeed(getUserPallet().getSpeed() + 1);
        setDone();
    }

    @Override
    public String toString() {
        return super.toString() + " slowed";
    }
}
