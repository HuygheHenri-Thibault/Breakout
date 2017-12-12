/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package powerUps;

import domain.Ball;
import domain.Level;
import domain.Pallet;
import java.util.List;
import java.util.Timer;

/**
 *
 * @author micha
 */
public abstract class DummyEffect{
    private Pallet userPallet;
    private Ball lastBallActivated;
    private int duration;
    private Level thisLevel;
    private Timer t;

    public DummyEffect(int duration) {
        this.duration = duration;
    }
    
    //status
    private EffectStatus status = EffectStatus.INACTIVE;
    
    public void setActive(){status = EffectStatus.ACTIVE;}
    public void setRunning(){status = EffectStatus.RUNNING;}
    public void setDeActive(){status = EffectStatus.INACTIVE;}
    public EffectStatus isActivated(){return status;}
    
    public abstract void activate();
    public abstract void deActivate();
    
    public void setUserPallet(Pallet p){
        this.userPallet = p;
    }
    
    public Pallet getUserPallet() {
        return userPallet;
    }

    public void setLastBallActivated(Ball lastBallActivated) {
        this.lastBallActivated = lastBallActivated;
    }

    public Ball getLastBallActivated() {
        return lastBallActivated;
    }

    public void setLevel(Level level){
        this.thisLevel = level;
    }
    
    public Level getThisLevel() {
        return thisLevel;
    }
    
    //mag later vervangen worden
    public void setDuration(int duration){
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }
    
    public void setT(Timer t){
        this.t = t;
    }

    public Timer getT() {
        return t;
    }
    
}
