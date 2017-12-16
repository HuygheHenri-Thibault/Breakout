/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.swing;

import be.howest.ti.breakout.domain.Ball;
import be.howest.ti.breakout.domain.Level;
import be.howest.ti.breakout.domain.Pallet;
import be.howest.ti.breakout.domain.User;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.TimerTask;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import be.howest.ti.breakout.domain.powerUps.Effect;
import be.howest.ti.breakout.domain.powerUps.EffectStatus;
import be.howest.ti.breakout.domain.powerUps.PowerUpOrDown;
import be.howest.ti.breakout.spells.Spell;

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
            
           for (ListIterator<PowerUpOrDown> iter = level.getAllActivePowerUps().listIterator(); iter.hasNext();) {
                PowerUpOrDown power = iter.next();
                switch (power.isActivated()) {
                    case ACTIVE:
                        power.activate();
                        break;
                    case INACTIVE:
                        power.deActivate();
                        break;
                    case RUNNING:
                        changeStateEffect(power.getEffects());
                        if (checkAllPowerUpEffects(power)) {
                            power.setDone();
                        }
                        break;
                    case DONE:
                        iter.remove();
                        break;
                    default:
                        break;
                }
            }

            for (Map.Entry<User, Spell> entry : level.getAllSpellsInGame().entrySet()) {
                switch (entry.getValue().isActivated()) {
                    case ACTIVE:
                        entry.getValue().cast();
                        break;
                    case DEACTIVE:
                        entry.getValue().startCooldown();
                        break;
                    default:
                        break;
                }

            }
            for (Map.Entry<User, Spell> entry : level.getAllSpellsInGame().entrySet()) {
                changeStateEffect(entry.getValue().getSpellEffects());
            }
        }
    }

    
    private void changeStateEffect(List<Effect> effects) {
        for (Effect effect : effects) {
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

    private boolean checkAllPowerUpEffects(PowerUpOrDown power) {
        for (Effect effect : power.getEffects()) {
            if (effect.isActivated() != EffectStatus.DONE) {
                return false;
            }
        }
        return true;
    }
    
    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public Level getLevel() {
        return level;
    }
}
