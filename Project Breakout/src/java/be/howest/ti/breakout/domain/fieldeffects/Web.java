/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.fieldeffects;

import be.howest.ti.breakout.domain.Ball;
import be.howest.ti.breakout.domain.Circle;
import be.howest.ti.breakout.domain.Pallet;
import be.howest.ti.breakout.domain.game.Level;
import java.util.Timer;

/**
 *
 * @author Fredr
 */
public final class Web extends Circle{
    private int timeLeft =  5;
    private Timer timeToLiveTimer = new Timer();
    private boolean pausedTimer = false;
    
    public Web(Level level, int x, int y, int radius) {
        super(level, x, y, radius);
        timeToLiveTimer.scheduleAtFixedRate(new TimerTaskWeb(this), 0, 1000);
    }
    
    public void resume(){
        pausedTimer = false;
    }
    
    public void pause(){
        pausedTimer = true;
    }
    
    public boolean isPaused(){
        return pausedTimer;
    }
    
    public int getTimeToLive(){
        return timeLeft;
    }
    
    public void cancel(){
        timeToLiveTimer.cancel();
    }
    
    public void decrementTimeToLive(){
        timeLeft--;
    }
    
    public void removeYourselfNow(){
       timeToLiveTimer.cancel();
       getLevel().removeShapeFromFieldEffectShapes(this);
    }
    
    @Override
    public void updateSpriteBall(Ball aBall){
       aBall.updateSpriteAfterCollidingWithWeb(this);
    }
    
    @Override
    public void updateSpritePallet(Pallet pallet){
        pallet.updateSpriteAfterCollidingWithWeb(this);
    }
    
}
