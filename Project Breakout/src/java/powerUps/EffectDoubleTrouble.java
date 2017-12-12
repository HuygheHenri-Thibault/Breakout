/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package powerUps;

import domain.Ball;
import domain.Brick;
import domain.Level;
import java.util.Timer;

/**
 *
 * @author micha
 */
public class EffectDoubleTrouble extends DummyEffect{

    public EffectDoubleTrouble(int duration) {
        super(duration);
    }

    @Override
    public void activate() {
        setRunning();
        getThisLevel().createExtraBall();
        System.out.println("activated");
    }

    @Override
    public void deActivate() {
        System.out.println("deactivated");
        getThisLevel().getBalls().remove(getThisLevel().getBalls().size() - 1);
        //getThisLevel().decrementLife();
        getThisLevel().setPowerUpActive(new NoPower());
    }
    
    @Override
    public String toString() {
        return super.toString() + " double-trouble"; 
    }
}
