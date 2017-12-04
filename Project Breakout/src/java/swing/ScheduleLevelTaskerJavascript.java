/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import domain.Ball;
import domain.Level;
import domain.Pallet;
import java.util.TimerTask;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author micha
 */
public class ScheduleLevelTaskerJavascript extends TimerTask {
    private Level level;
    private boolean paused = false;

    public ScheduleLevelTaskerJavascript(Level level) {
        this.level = level;
    }

    @Override
    public void run() {
        if (!paused) {
            for (Ball ball : level.getBalls()) {
                ball.move();
                System.out.println(ball.getX());
            }
            for (Pallet pallet : level.getPallets()) {
                pallet.move();
            }
            
            switch (level.getActivePowerUp().isActivated()) {
                case ACTIVE:
                    level.getActivePowerUp().activate();
                    break;
                case INACTIVE:
                    level.getActivePowerUp().deActivate();
                    break;
                default:
                    break;
            }
        }
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public Level getLevel() {
        return level;
    }
}
