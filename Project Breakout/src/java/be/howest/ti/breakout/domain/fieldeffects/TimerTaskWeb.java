/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.fieldeffects;

import be.howest.ti.breakout.domain.Ball;
import be.howest.ti.breakout.domain.game.Level;
import java.util.TimerTask;

/**
 *
 * @author Fredr
 */
public class TimerTaskWeb extends TimerTask{
    
    private final Level level;
    private final Web web;

    public TimerTaskWeb(Web web) {
        this.web = web;
        this.level = web.getLevel();
    }

    @Override
    public void run() {
        for (Ball ball : web.getLevel().getBalls()) {
            if(ball.checkCollissionWithCircle(web)){
                ball.setSpeed(ball.getOriginalSpeed());
                ball.cutDirectionBy(ball.getNumberOfDirectionBeingCutBy());
            }
        }
        level.removeShapeFromFieldEffectShapes(web);
        cancel();
    }
    
}
