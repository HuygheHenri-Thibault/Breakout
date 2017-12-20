/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.effects;

import java.util.TimerTask;

/**
 *
 * @author micha
 */
public final class TimerTaskEffect extends TimerTask {

    private final Effect effect;

    public TimerTaskEffect(Effect effect) {
        this.effect = effect;
    }

    @Override
    public void run() {
        if (!effect.isPaused()) {
            effect.decrementDuration();
            if (effect.getDuration() <= 0) {
                effect.setDuration(effect.getOriginalDuration());
                effect.setDeActive();
                cancel();
            }
        }
    }

}
