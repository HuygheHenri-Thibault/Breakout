/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.fieldeffects;

import java.util.TimerTask;

/**
 *
 * @author Fredr
 */
public class TimerTaskWeb extends TimerTask {

    private final Web web;

    public TimerTaskWeb(Web web) {
        this.web = web;
    }

    @Override
    public void run() {
        if (!web.isPaused()) {
            web.decrementTimeToLive();
            if (web.getTimeToLive() <= 0) {
                web.removeYourselfNow();
            }
        }
    }

}
