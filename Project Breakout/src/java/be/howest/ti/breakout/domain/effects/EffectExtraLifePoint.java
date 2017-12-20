/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.effects;

/**
 *
 * @author micha
 */
public class EffectExtraLifePoint extends Effect{

    public EffectExtraLifePoint(String name, int duration) {
        super(name, duration);
    }

    @Override
    public void activate() {
        setRunning();
        System.out.println("activated extraLife");
        getLevelOfEffect().getGame().setLives(getLevelOfEffect().getGame().getLives() + 1);
        setDeActive();
    }

    @Override
    public void deActivate() {
        System.out.println("added Extra Life");
        setDone();
    }

    @Override
    public String toString() {
        return super.toString() + " LifePoint";
    }
    
}
