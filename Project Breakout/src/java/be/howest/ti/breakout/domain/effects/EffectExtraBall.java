/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.effects;

import be.howest.ti.breakout.domain.Ball;
import be.howest.ti.breakout.domain.DoubleTroubleBall;
import be.howest.ti.breakout.domain.game.Player;

/**
 *
 * @author micha
 */
public final class EffectExtraBall extends Effect {
    DoubleTroubleBall extraBallCreated;
 
    public EffectExtraBall(String name, String description, int duration) {
        super(name, description, duration);
    }

    @Override
    public void activate() {
        if(!hasUserAlreadyActivatedThisEffect()){
            setRunning();
            extraBallCreated = LevelOfEffect.createExtraBall(this);
            System.out.println("activated");
        } else {
            setDone();
        }
    }

    @Override
    public void deActivate() {
        System.out.println("deactivated double trouble");
        extraBallCreated.removeFromScreen();
        setDone();
    }
    
    public boolean hasUserAlreadyActivatedThisEffect(){
        for (Ball ball : getLevelOfEffect().getExtraBallCreatedByEffects()) {
            Player u = ball.givePlayerActivatedSpecialBall();
            if(u != null){
                if(u.getPlayerID()== getPlayerActivatedEffect().getPlayerID()){
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        return super.toString() + " double-trouble";
    }
}
