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
    protected int id;
    protected String name;
    protected String type;
    protected int duration;
    protected String iconPath;
    protected String description;
    
    //status
    protected PowerUpStatus status = PowerUpStatus.INACTIVE;

    public PowerUpOrDown() {
    }

    public PowerUpOrDown(int id, String name, String type, int duration, String iconPath, String description) {
        this.name = name;
        this.type = type;
        this.duration = duration;
        this.description = description;
    }
    
    public int getId(){return id;}
    public String getName(){return name;}
    public String getType(){return type;}
    public int getDuration(){return duration;}
    public String getIconPath(){return iconPath;}
    public String getDescription(){return description;}
    public void setActive(){status = PowerUpStatus.ACTIVE;}
    public void setRunning(){status = PowerUpStatus.RUNNING;}
    public void setDeActive(){status = PowerUpStatus.INACTIVE;}
    public PowerUpStatus isActivated(){return status;}
    public Rectangle getBoundaries(){return boundaries;}
    public void show(){brick.getLevel().getPowerUpsShownOnScreen().add(this);}
    public abstract void activate();
    public abstract void deActivate();
    
    public void setBrickHiddenIn(Brick b){
        this.level = b.getLevel();
        this.brick = b;
        b.setPowerUp(this);
        setX(brick.getX() + brick.getLength() / 2);
        setY(brick.getY() + brick.getHeight() / 2);
        this.boundaries =  new Rectangle(brick.getLevel(), getX(), getY(), 10, 10);
    }
    
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
    
    @Override
    public String toString() {
        return "Powerup";
    }
}
