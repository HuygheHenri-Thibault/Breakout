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

    public EffectGravity(String name, int duration) {
        super(name, duration);
    }
    
    @Override
    public void activate() {       
        setRunning();
        for (Ball ball : getThisLevel().getBalls()) {
            ball.setDx(0);
            ball.setDy(ball.getSpeed() / 2);           
        }
        System.out.println("activated gravity");
        setDeActive();
    }

    @Override
    public void deActivate() {
        System.out.println("deactivated gravity");
        setDone();
    }
    
    @Override
    public String toString() {
        return super.toString() + " gravity"; 
    }
}
