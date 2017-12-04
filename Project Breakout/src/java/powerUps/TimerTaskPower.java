/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package powerUps;

import java.util.TimerTask;

/**
 *
 * @author micha
 */
public class TimerTaskPower extends TimerTask {

    private final PowerUpOrDown powerUp;

    private final long start;
    private final long end;

    public TimerTaskPower(PowerUpOrDown powerUp) {
        this.powerUp = powerUp;
        this.start = System.currentTimeMillis();
        this.end = start + (powerUp.duration * 1000);
    }

    @Override
    public void run() {
        if (System.currentTimeMillis() > end) {
            powerUp.setDeActive();
            cancel();
        }
    }

}
