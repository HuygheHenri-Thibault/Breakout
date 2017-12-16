/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.powerUps;

import java.util.TimerTask;

/**
 *
 * @author micha
 */
public class TimerTaskEffect extends TimerTask {

    private final Effect effect;

    private final long start;
    private final long end;

    public TimerTaskEffect(Effect effect) {
        this.effect = effect;
        this.start = System.currentTimeMillis();
        this.end = start + (effect.getDuration() * 1000);
    }

    @Override
    public void run() {
        if (System.currentTimeMillis() > end) {
            effect.setDeActive();
            cancel();
        }
    }

}
