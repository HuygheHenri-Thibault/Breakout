/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.effects;

/**
 *
 * @author Fredr
 */
public class EffectFireBall extends Effect{

    public EffectFireBall(String name, int duration) {
        super(name, duration);
    }

    @Override
    public void activate() {
        setRunning();
        getLevelOfEffect().createExtraFireBall(this);
        System.out.println("activated fireBall");
    }

    @Override
    public void deActivate() {
        System.out.println("deactivated fireball");
        setDone();
    }
    
}
