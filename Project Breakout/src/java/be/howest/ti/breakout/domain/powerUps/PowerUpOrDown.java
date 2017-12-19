/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.powerUps;

import be.howest.ti.breakout.domain.Ball;
import be.howest.ti.breakout.domain.Brick;
import be.howest.ti.breakout.domain.Circle;
import be.howest.ti.breakout.domain.game.Level;
import be.howest.ti.breakout.domain.Pallet;
import be.howest.ti.breakout.domain.Rectangle;
import be.howest.ti.breakout.domain.Shape;
import be.howest.ti.breakout.domain.effects.Effect;
import be.howest.ti.breakout.domain.effects.EffectHandeler;
import be.howest.ti.breakout.domain.effects.EffectStatus;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author micha
 */
public class PowerUpOrDown extends Shape implements EffectHandeler{
    private final List<Effect> effects = new ArrayList<>(); 
    
    private Level level;
    private Rectangle boundaries;
    private Brick brick;
    private int id;
    private String name;
    private String type;
    private int duration;
    private String iconPath;
    private String description;
    
     //status
    private EffectStatus status = EffectStatus.READY;
    
    public PowerUpOrDown() {
    }

    public PowerUpOrDown(int id, String name, String type, int duration, String iconPath, String description) {
        this.name = name;
        this.type = type;
        this.duration = duration;
        this.iconPath = iconPath;
        this.description = description;
    }
    
    public void setReady(){status = EffectStatus.READY;}
    public void setActive(){status = EffectStatus.ACTIVE;}
    public void setRunning(){status = EffectStatus.RUNNING;}
    public void setDeActive(){status = EffectStatus.INACTIVE;}
    public void setDone(){status = EffectStatus.DONE;}
    public EffectStatus isActivated(){return status;}
    
    public int getId(){return id;}
    public String getName(){return name;}
    public String getType(){return type;}
    public int getDuration(){return duration;}
    public String getIconPath(){return iconPath;}
    public String getDescription(){return description;}
    
    public Rectangle getBoundaries(){return boundaries;}
    public void show(){level.getPowerUpsShownOnScreen().add(this);}
    
    public void setBrickHiddenIn(Brick b){
        this.level = b.getLevel();
        this.brick = b;
        b.setPowerUp(this);
        setX(brick.getX() + brick.getLength() / 2);
        setY(brick.getY());
        this.boundaries =  new Rectangle(brick.getLevel(), getX(), getY(), 10, 10);
    }
    
    public void addEffect(Effect effect){
        effects.add(effect);
    }

    public List<Effect> getEffects() {
        return effects;
    }
    
    public void setEntetiesOfLevel(Ball ballActivated){
        for (Effect effect : effects) {
            effect.setBallActivatedEffect(ballActivated);
            effect.setLevelOfEffect(level);
            effect.setDuration(duration);
            effect.setUserPallet(level.getUserPallet(ballActivated.getLastUserThatTouchedMe()));
            if(ballActivated.getLastUserThatTouchedMe().getUserId() > 0){
                effect.setUserActivatedEffect(ballActivated.getLastUserThatTouchedMe());
            }
        }
    }
    
    @Override
    public void activate(){
        for (Effect effect : effects) {
            effect.setActive();
        }
        setRunning();
    }
    
    @Override
    public void deActivate(){
        for (Effect effect : effects) {
            effect.setDeActive();
        }
        setRunning();
    }
    
    @Override
    public boolean checkCollissionWithCircle(Circle c){
        return c.checkCollissionWithRect(boundaries);
    }
    
    @Override
    public boolean checkCollissionWithRect(Rectangle rect){
        return rect.checkCollissionWithRect(boundaries);
    }
    
    @Override
    public void updateSpriteBall(Ball aBall) {
        aBall.updateSpriteBallAfterCollidingWithPowerUp(this);
    }
    
    @Override
    public void updateSpritePallet(Pallet p){}
    
    @Override
    public String toString() {
        return "Powerup " + getIconPath(); // moet aangepast worden
    }
}
