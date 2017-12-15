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
public class EffectShrunk extends Effect{

    public EffectShrunk(String name, int duration) {
        super(name, duration);
    }

    @Override
    public void activate() {
        Pallet palletOfUser = getThisLevel().getUserPallet(getUserActivatedEffect().getUserId());
        setUserPallet(palletOfUser);
 
        setRunning();
        
        getUserPallet().setLength((int) (getUserPallet().getLength() - (getUserPallet().getOriginalLenght()* 0.2)));
        System.out.println("activated shrunk");
        
        setT(new Timer());
        getT().schedule(new TimerTaskEffect(this), 0, 1000);
    }

    @Override
    public void deActivate() {
        System.out.println("deactivated shrunk");
        getT().cancel();
        getUserPallet().setLength((int) (getUserPallet().getLength() + (getUserPallet().getOriginalLenght()* 0.2)));
        setDone();
    }
    
    @Override
    public String toString() {
        return super.toString() + " shrunk"; 
    }
}
