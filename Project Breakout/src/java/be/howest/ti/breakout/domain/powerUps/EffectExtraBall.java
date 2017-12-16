/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.powerUps;

import be.howest.ti.breakout.domain.Ball;
import be.howest.ti.breakout.domain.Brick;
import be.howest.ti.breakout.domain.Level;
import be.howest.ti.breakout.domain.User;
import java.util.Map;
import java.util.Timer;
import be.howest.ti.breakout.spells.Spell;

/**
 *
 * @author micha
 */
public class EffectExtraBall extends Effect {
 
    public EffectExtraBall(String name, int duration) {
        super(name, duration);
    }

    @Override
    public void activate() {
        //int amountOfBallsInLevel = (int) Math.ceil((double) getThisLevel().getAantalSpelers() / 2);
        //if (!(getThisLevel().getBalls().size() >= amountOfBallsInLevel + 1)) {
        if(!hasUserAlreadyActivatedThisEffect()){
            setRunning();
            getThisLevel().createExtraBall(this);
            System.out.println("activated");
        } else {
            setDone();
        }
    }

    @Override
    public void deActivate() {
        System.out.println("deactivated double trouble");
        getThisLevel().getBalls().remove(getThisLevel().getBalls().size() - 1);
        //getThisLevel().decrementLife();
        setDone();
    }

    @Override
    public String toString() {
        return super.toString() + " double-trouble";
    }
    
    public boolean hasUserAlreadyActivatedThisEffect(){
        for (Ball ball : getThisLevel().getBalls()) {
            User u = ball.giveUserActivatedSpecialBall();
            if(u != null){
                if(u.getUserId() == getUserActivatedEffect().getUserId()){
                    return true;
                }
            }
        }
        return false;
    }

}
