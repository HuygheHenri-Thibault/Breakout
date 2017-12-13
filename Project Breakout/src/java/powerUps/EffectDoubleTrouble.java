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
public class EffectDoubleTrouble extends Effect {

    public EffectDoubleTrouble(int duration) {
        super(duration);
    }

    @Override
    public void activate() {
        int amountOfBallsInLevel = (int) Math.ceil((double) getThisLevel().getAantalSpelers() / 2);
        if (!(getThisLevel().getBalls().size() >= amountOfBallsInLevel + 1)) {
            setRunning();
            getThisLevel().createExtraBall(this);
            System.out.println("activated");
        }
    }

    @Override
    public void deActivate() {
        System.out.println("deactivated");
        getThisLevel().getBalls().remove(getThisLevel().getBalls().size() - 1);
        //getThisLevel().decrementLife();
        setDone();
    }

    @Override
    public String toString() {
        return super.toString() + " double-trouble";
    }

}
