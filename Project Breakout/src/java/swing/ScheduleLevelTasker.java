/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import domain.Ball;
import domain.Level;
import domain.Pallet;
import domain.User;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import powerUps.Effect;
import powerUps.EffectHandeler;
import powerUps.EffectStatus;
import powerUps.NoPower;
import spells.Spell;

/**
 *
 * @author micha
 */
public class ScheduleLevelTasker extends TimerTask {

    private Level level;
    private JPanel toRepaint;
    private boolean paused = false;

    public ScheduleLevelTasker(Level level, JPanel jpanel) {
        this.level = level;
        this.toRepaint = jpanel;
    }

    @Override
    public void run() {
        if (!paused) {
//            for (Ball ball : level.getBalls()) {
//                ball.move();
//                //System.out.println(ball.getX() + " " +ball.getDamage());
//            }
            for (Pallet pallet : level.getPallets()) {
                pallet.move();
                //System.out.println(pallet.getSpeed());
            }

            //voor elke powerup, zijn effecten checken
            switch (level.getActivePowerUp().isActivated()) {
                case ACTIVE:
                    level.getActivePowerUp().activate();
                    break;
                case INACTIVE:
                    level.getActivePowerUp().deActivate();
                    break;
                case RUNNING:
                    changeStateEffect(level.getActivePowerUp().getEffects());
                    if(checkAllPowerUpEffects()){
                        level.getActivePowerUp().setDone();
                    }
                case DONE:
                    level.setPowerUpActive(new NoPower());
                    break;
                default:
                    break;
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

            SwingUtilities.invokeLater(() -> toRepaint.repaint());
            //zorgen dat je update gebeurt in de thread van gui
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

    private boolean checkAllPowerUpEffects() {
        for (Effect effect : level.getActivePowerUp().getEffects()) {
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

    public JPanel getToRepaint() {
        return toRepaint;
    }

}
