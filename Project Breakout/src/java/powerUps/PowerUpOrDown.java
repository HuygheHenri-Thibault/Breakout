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
import domain.Pallet;
import domain.Rectangle;
import domain.Shape;
import domain.Sprite;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author micha
 */
public class PowerUpOrDown extends Shape implements EffectHandeler{
    private List<Effect> effects = new ArrayList<>(); 
    
    private Level level;
    //private Ball ballActivatedPower;
    private Sprite s;
    private Rectangle boundaries;
    private Brick brick;
    private int id;
    private String name;
    private String type;
    private int duration;
    private String iconPath;
    private String description;
    
    public PowerUpOrDown() {
    }

    public PowerUpOrDown(int id, String name, String type, int duration, String iconPath, String description) {
        this.name = name;
        this.type = type;
        this.duration = duration;
        this.iconPath = iconPath;
        this.description = description;
    }
    
    public int getId(){return id;}
    public String getName(){return name;}
    public String getType(){return type;}
    public int getDuration(){return duration;}
    public String getIconPath(){return iconPath;}
    public String getDescription(){return description;}
    
    public Rectangle getBoundaries(){return boundaries;}
    public void show(){brick.getLevel().getPowerUpsShownOnScreen().add(this);}
    
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
            effect.setLastBallActivated(ballActivated);
            effect.setLevel(level);
            effect.setDuration(duration);
        }
    }
    
    @Override
    public void activate(){
        for (Effect effect : effects) {
            effect.setActive();
        }
    }
    
    @Override
    public void deActivate(){
        for (Effect effect : effects) {
            effect.setDeActive();
        }
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
    public void updateSpritePallet(Pallet p){}
    
    @Override
    public String toString() {
        return "Powerup " + getName().toLowerCase(); // moet aangepast worden
    }
}
