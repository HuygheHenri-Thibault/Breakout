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
            for (Ball ball : level.getBalls()) {
                ball.move();
            }
            for (Pallet pallet : level.getPallets()) {
                pallet.move();
            }
            

            
            SwingUtilities.invokeLater(()->toRepaint.repaint());
            //zorgen dat je update gebeurt in de thread van gui
        }
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
