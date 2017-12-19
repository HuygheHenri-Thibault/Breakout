/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.swing;

import be.howest.ti.breakout.domain.Ball;
import be.howest.ti.breakout.domain.game.Level;
import be.howest.ti.breakout.domain.Pallet;
import be.howest.ti.breakout.domain.game.User;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.TimerTask;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import be.howest.ti.breakout.domain.effects.Effect;
import be.howest.ti.breakout.domain.effects.EffectHandeler;
import be.howest.ti.breakout.domain.effects.EffectStatus;
import be.howest.ti.breakout.domain.powerUps.NoPower;
import be.howest.ti.breakout.domain.powerUps.PowerUpOrDown;
import be.howest.ti.breakout.domain.spells.Spell;
import java.util.ArrayList;

/**
 *
 * @author micha
 */
public class ScheduleLevelTaskerSwing extends TimerTask {

    private Level level;
    private JPanel toRepaint;
    private boolean paused = false;

    public ScheduleLevelTaskerSwing(Level level, JPanel jpanel) {
        this.level = level;
        this.toRepaint = jpanel;
    }

    @Override
    public void run() {
        if (!paused) {
            for (ListIterator<Ball> iter = level.getBalls().listIterator(); iter.hasNext();) {
                Ball ball = iter.next();
                ball.move();
                if(!ball.isOnScreen()){
                    iter.remove();
                }
                //System.out.println(ball.getDx());
                //System.out.println(ball.getDy());
            }
            if(level.getBalls().isEmpty()){
                level.resetStates();
            }
            for (ListIterator<Ball> iter = level.getExtraBallCreatedByEffects().listIterator(); iter.hasNext();) {
                Ball ball = iter.next();
                ball.move();
                if(!ball.isOnScreen()){
                    iter.remove();
                }
            }
            for (Pallet pallet : level.getPallets()) {
                pallet.move();
                System.out.println(pallet.getSpeed());
            }

            //voor elke powerup, zijn effecten checken
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
            
            changeStateEffect(new ArrayList<>(Arrays.asList(level.getFieldEffect().getEffect())));
            
            if(level.getFieldEffect().IsPaused()){
                level.getFieldEffect().resume();
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
                    System.out.println("here");
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

    public JPanel getToRepaint() {
        return toRepaint;
    }

}
