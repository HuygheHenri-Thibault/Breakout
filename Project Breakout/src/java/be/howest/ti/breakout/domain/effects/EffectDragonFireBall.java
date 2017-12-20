/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.effects;

import be.howest.ti.breakout.domain.Fireball;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fredr
 */
public class EffectDragonFireBall extends Effect{
    private final List<Fireball> fireBallsCreated = new ArrayList<>();
    
    public EffectDragonFireBall(String name, int duration) {
        super(name, duration);
    }

    @Override
    public void activate() {
        setRunning();
        fireBallsCreated.add(LevelOfEffect.createExtraFireBall(this));
        System.out.println("activated fireBall");
    }

    @Override
    public void deActivate() {
        for (Fireball fireBall : fireBallsCreated) {
            fireBall.removeFromScreen();
        }
        System.out.println("deactivated fireball");
        setDone();
    }
    
}
