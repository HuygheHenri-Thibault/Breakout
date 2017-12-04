/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package powerUps;

import domain.Ball;
import domain.Brick;
import domain.Circle;
import domain.Level;
import domain.Rectangle;
import domain.Shape;
import domain.Sprite;

/**
 *
 * @author micha
 */
public abstract class PowerUpOrDown extends Shape{
    
    protected Level level;
    protected Ball ballActivatedPower;
    protected Sprite s;
    protected Rectangle boundaries;
    protected Brick brick;
    protected String name;
    protected int duration;
    protected String description;
    
    //status
    protected PowerUpStatus type = PowerUpStatus.INACTIVE;

    public PowerUpOrDown() {
    }

    public PowerUpOrDown(Level level, Brick theBrick, String name, int duration, String description) {
        this.level = level;
        this.brick = theBrick;
        theBrick.setPowerUp(this);
        this.name = name;
        this.duration = duration;
        this.description = description;
        this.boundaries =  new Rectangle(brick.getLevel(), theBrick.getX() + theBrick.getLength() / 2, theBrick.getY() + theBrick.getHeight() / 2, 10, 10);
    }
    
    public void setActive(){type = PowerUpStatus.ACTIVE;}
    public void setRunning(){type = PowerUpStatus.RUNNING;}
    public void setDeActive(){type = PowerUpStatus.INACTIVE;}
    public PowerUpStatus isActivated(){return type;}
    public Rectangle getBoundaries(){return boundaries;}
    public void show(){brick.getLevel().getPowerUpsShownOnScreen().add(this);}
    public abstract void activate();
    public abstract void deActivate();
    
    public void setBallActivatedPower(Ball ballActivated){
        this.ballActivatedPower = ballActivated;
    }
    
    @Override
    public boolean checkCollissionWithCircle(Circle c){
        return c.checkCollissionWithRect(boundaries);
    }
    
    @Override
    public void updateSpriteBall(Ball aBall) {
        aBall.updateSpriteBallAfterCollidingWithPowerUp(this);
    }
}
