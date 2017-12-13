/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package powerUps;

import domain.Ball;
import domain.Brick;
import domain.Level;

/**
 *
 * @author micha
 */
public class EffectGravity extends Effect{

    public EffectGravity(int duration) {
        super(duration);
    }
    
    @Override
    public void activate() {       
        setRunning();
        getLastBallActivated().setDx(0);
        getLastBallActivated().setDy(getLastBallActivated().getSpeed() / 2);
        System.out.println("activated");
        setDeActive();
    }

    @Override
    public void deActivate() {
        System.out.println("deactivated");
        getThisLevel().setPowerUpActive(new NoPower());
        setReady();
    }
    
    @Override
    public String toString() {
        return super.toString() + " gravity"; 
    }
}
