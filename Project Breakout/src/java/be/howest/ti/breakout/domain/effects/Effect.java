/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.effects;

import be.howest.ti.breakout.domain.Ball;
import be.howest.ti.breakout.domain.game.Level;
import be.howest.ti.breakout.domain.Pallet;
import be.howest.ti.breakout.domain.game.User;
import java.util.Timer;

/**
 *
 * @author micha
 */
public abstract class Effect{
    protected final String name;
    protected Pallet userPallet;
    protected User userActivatedEffect;
    protected Ball BallActivatedEffect;
    protected final int originalDuration;
    protected int duration;
    protected Level LevelOfEffect;
    protected Timer TimerEffect;
    protected boolean paused = false;

    public Effect(String name, int duration) {
        this.name = name;
        this.originalDuration = duration;
        this.duration = duration;
    }
    
    //status
    protected EffectStatus status = EffectStatus.READY;
    
    public void setReady(){status = EffectStatus.READY;}
    public void setActive(){status = EffectStatus.ACTIVE;}
    public void setRunning(){status = EffectStatus.RUNNING;}
    public void setDeActive(){status = EffectStatus.INACTIVE;}
    public void setDone(){status = EffectStatus.DONE;}
    public EffectStatus isActivated(){return status;}
    
    public abstract void activate();
    public abstract void deActivate();

    public String getName() {
        return name;
    }
    
    public void setUserActivatedEffect(User u){
        this.userActivatedEffect = u;
    }
    
    public User getUserActivatedEffect(){
        return userActivatedEffect;
    }
    
    public void setUserPallet(Pallet p){
        this.userPallet = p;
    }
    
    public Pallet getUserPallet() {
        return userPallet;
    }

    public void setBallActivatedEffect(Ball BallActivatedEffect) {
        this.BallActivatedEffect = BallActivatedEffect;
    }

    public Ball getBallActivatedEffect() {
        return BallActivatedEffect;
    }

    public void setLevelOfEffect(Level level){
        this.LevelOfEffect = level;
    }
    
    public Level getLevelOfEffect() {
        return LevelOfEffect;
    }
    
    //mag later vervangen worden
    public void setDuration(int duration){
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }
    
    public void decrementDuration(){
        duration--;
    }
    
    public int getOriginalDuration(){
        return originalDuration;
    }
    
    public void setTimerEffect(Timer t){
        this.TimerEffect = t;
    }
    
    public boolean hasTimer(){
        return TimerEffect != null;
    }

    public Timer getTimerEffect() {
        return TimerEffect;
    }
    
    public void pause(){
        paused = true;
    }
    
    public void unpause(){
        paused = false;
    }
    
    public boolean isPaused(){
        return paused;
    }

//    public boolean isUserActivatedEffectAlive(){
//        return getLevelOfEffect().isUserAlive(userActivatedEffect);
//    }
//    
    @Override
    public String toString() {
        return "Effect ";
    }
}
