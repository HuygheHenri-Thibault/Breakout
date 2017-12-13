/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package powerUps;

import domain.Ball;
import domain.Brick;
import domain.Circle;
import domain.Level;
import domain.Pallet;
import domain.Rectangle;
import domain.Sprite;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author micha
 */
public class EffectBulletTime extends Effect{

    public EffectBulletTime(int duration) {
        super(duration);
    }

    @Override
    public void activate() {
        Pallet palletOfUser = getThisLevel().getUserPallet(getLastBallActivated().getLastUserThatTouchedMe());
        setUserPallet(palletOfUser);

        setRunning();
        
        getUserPallet().setSpeed(getUserPallet().getSpeed() + 1);
        
        System.out.println("activated");
      
        setT(new Timer());
        getT().schedule(new TimerTaskEffect(this), 0, 1000);
    }
    
    @Override
    public void deActivate() {
        System.out.println("deactivated");
        getT().cancel();
        getUserPallet().setSpeed(getUserPallet().getSpeed() - 1);
        getThisLevel().setPowerUpActive(new NoPower());
        setReady();
    }

    @Override
    public String toString() {
        return super.toString() + " bullet-time"; 
    }

    

}
