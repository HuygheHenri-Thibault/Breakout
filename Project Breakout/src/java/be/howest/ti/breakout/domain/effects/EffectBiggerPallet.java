/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.effects;

import be.howest.ti.breakout.domain.Ball;
import be.howest.ti.breakout.domain.Brick;
import be.howest.ti.breakout.domain.game.Level;
import be.howest.ti.breakout.domain.Pallet;
import java.util.Timer;

/**
 *
 * @author micha
 */
public class EffectBiggerPallet extends Effect{

    public EffectBiggerPallet(String name, int duration) {
        super(name, duration);
    }

    @Override
    public void activate() {
        Pallet palletOfUser = getThisLevel().getUserPallet(getUserActivatedEffect().getUserId());
        setUserPallet(palletOfUser);
        setRunning();
        
        palletOfUser.setLength((int) (palletOfUser.getLength() + (palletOfUser.getOriginalLenght()* 0.2)));
        
        System.out.println("activated scaffolds");
      
        setT(new Timer());
        getT().schedule(new TimerTaskEffect(this), 0, 1000);
    }

    @Override
    public void deActivate() {
        System.out.println("deactivated scaffolds");
        getT().cancel();
        getUserPallet().setLength((int) (getUserPallet().getLength() - (getUserPallet().getOriginalLenght()* 0.2)));
        setDone();
    }
    
    @Override
    public String toString() {
        return super.toString() + " scaffolds"; 
    }
}
