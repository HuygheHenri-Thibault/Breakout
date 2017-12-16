/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.powerUps;

import be.howest.ti.breakout.domain.Ball;
import be.howest.ti.breakout.domain.Brick;
import be.howest.ti.breakout.domain.Circle;
import be.howest.ti.breakout.domain.Level;
import be.howest.ti.breakout.domain.Pallet;
import be.howest.ti.breakout.domain.Rectangle;
import be.howest.ti.breakout.domain.Sprite;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author micha
 */
public class EffectQuickerPallet extends Effect{

    public EffectQuickerPallet(String name, int duration) {
        super(name, duration);
    }

    @Override
    public void activate() {
        Pallet palletOfUser = getThisLevel().getUserPallet(getUserActivatedEffect().getUserId());
        setUserPallet(palletOfUser);
        
        setRunning();
        
        getUserPallet().setSpeed(getUserPallet().getSpeed() + 1);
        
        System.out.println("activated bullet time");
      
        setT(new Timer());
        getT().schedule(new TimerTaskEffect(this), 0, 1000);
    }
    
    @Override
    public void deActivate() {
        System.out.println("deactivated bullet time");
        getT().cancel();
        getUserPallet().setSpeed(getUserPallet().getSpeed() - 1);
        setDone();
    }

    @Override
    public String toString() {
        return super.toString() + " bullet-time"; 
    }

    

}
