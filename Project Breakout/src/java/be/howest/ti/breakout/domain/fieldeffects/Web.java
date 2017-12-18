/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.fieldeffects;

import be.howest.ti.breakout.domain.Ball;
import be.howest.ti.breakout.domain.Circle;
import be.howest.ti.breakout.domain.game.Level;
import java.util.Timer;

/**
 *
 * @author Fredr
 */
public class Web extends Circle{
    private final Timer timeToLive = new Timer();
    
    public Web(Level level, int x, int y, int radius) {
        super(level, x, y, radius);
        timeToLive.schedule(new TimerTaskWeb(this), 10000);
    }
    
    public void removeYourselfNow(){
       timeToLive.cancel();
       getLevel().removeShapeFromFieldEffectShapes(this);
    }
    
    @Override
    public void updateSpriteBall(Ball aBall){
       aBall.updateSpriteAfterCollidingWithWeb(this);
    }
    
}
