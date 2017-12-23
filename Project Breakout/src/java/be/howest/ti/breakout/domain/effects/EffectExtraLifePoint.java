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

    public EffectExtraLifePoint(String name, String description, int duration) {
        super(name, description, duration);
    }

    @Override
    public void activate() {
        setRunning();
        LevelOfEffect.getGame().setLives(LevelOfEffect.getGame().getLives() + 1);
        setDeActive();
    }

    @Override
    public void deActivate() {
        setDone();
    }

    @Override
    public String toString() {
        return super.toString() + " LifePoint";
    }
    
}
