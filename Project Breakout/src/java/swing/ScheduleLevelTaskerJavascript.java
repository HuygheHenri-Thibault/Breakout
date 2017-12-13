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
import powerUps.Effect;

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
            }
            for (Pallet pallet : level.getPallets()) {
                pallet.move();
            }
            
             //voor elke powerup, zijn effecten checken
            for (Effect effect : level.getActivePowerUp().getEffects()) {
                switch (effect.isActivated()) {
                    case ACTIVE:
                        effect.activate();
                        break;
                    case INACTIVE:
                        effect.deActivate();
                        break;
                    default:
                        break;
                }
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
