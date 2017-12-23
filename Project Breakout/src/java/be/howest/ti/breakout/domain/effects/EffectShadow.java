/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.effects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

/**
 *
 * @author Fredr
 */
public class EffectShadow extends Effect {

    private int palletIdSetInvisible;

    public EffectShadow(String name, String description, int duration) {
        super(name, description, duration);
    }

    @Override
    public void activate() {
        Random generator = new Random();
        if (LevelOfEffect.getPallets().size() == 1) {
            palletIdSetInvisible = 0;
        } else {
            palletIdSetInvisible = generator.nextInt(((getLevelOfEffect().getPallets().size() - 1) - 0) + 0) + 0;
        }
        LevelOfEffect.getPallets().get(palletIdSetInvisible).setInvisible();
        setRunning();
        TimerEffect = new Timer();
        TimerEffect.scheduleAtFixedRate(new TimerTaskEffect(this), 0, 1000);
    }

    @Override
    public void deActivate() {
        TimerEffect.cancel();
        LevelOfEffect.getPallets().get(palletIdSetInvisible).setVisible();
        setDone();
    }

    @Override
    public String toString() {
        return super.toString() + " shadow";
    }
}
