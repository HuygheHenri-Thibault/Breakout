/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.TimerTask;

/**
 *
 * @author micha
 */
public class ScheduleLevelTasker extends TimerTask{
    Level level;

    public ScheduleLevelTasker(Level level) {
        this.level = level;
    }
    
    @Override
    public void run() {
        while(!level.isCompleted() || !level.getGameOver()){
            for (Ball ball : level.getBalls()) {
                ball.move();
            }
            for (Pallet pallet : level.getPallets()) {
                pallet.move();
            }
        }
    }
    
}
