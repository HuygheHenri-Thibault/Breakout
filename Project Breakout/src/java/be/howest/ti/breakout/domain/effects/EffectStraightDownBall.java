/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.effects;

import be.howest.ti.breakout.domain.Ball;

/**
 *
 * @author micha
 */
public final class EffectStraightDownBall extends Effect{

    public EffectStraightDownBall(String name, String description, int duration) {
        super(name, description, duration);
    }
    
    @Override
    public void activate() {       
        setRunning();
        for (Ball ball : LevelOfEffect.getBalls()) {
            ball.setDx(0);
            ball.setDy(ball.getSpeed() / 2);           
        }
        for (Ball extraBallCreatedByEffect : LevelOfEffect.getExtraBallCreatedByEffects()) {
            extraBallCreatedByEffect.setDx(0);
            extraBallCreatedByEffect.setDy(extraBallCreatedByEffect.getSpeed() / 2);    
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
