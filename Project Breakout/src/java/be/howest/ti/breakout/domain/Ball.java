/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain;

import java.awt.Image;
import java.io.Serializable;
import java.util.List;
import be.howest.ti.breakout.domain.powerUps.PowerUpOrDown;

/**
 *
 * @author micha
 */
public class Ball extends Circle implements Serializable{
    //id
    //private int id;
    //
    private final Level level;
    private Sprite s;
    private int speed;
    private float dx;
    private float dy;
    private int damage = 1;
    private final int INIT_BALL_X;
    private final int INIT_BALL_Y;
    private Pallet palletLastTouched;
    private int lastUserThatTouchedMe = 0;

    public Ball(Level level, int radius, int speed, String color, int x, int y) {
        super(level, x, y, radius);
        //this.id = id;
        this.level = level;
        this.s = new Sprite(color);
        this.INIT_BALL_X = x;
        this.INIT_BALL_Y = y;
        this.speed = speed * 2;
        //this.dx = -speed;
        //this.dy = speed;
        setAngleDirectionToNearestPallet();
    }
    
    public void setAngleDirectionToNearestPallet(){
        List<Pallet> userPallets = level.getPallets();
        int nearestPalletUserID = calculateNearestPalletUserID(userPallets);
        //System.out.println(nearestPalletUserID);
        Pallet selectedPallet = level.getUserPallet(nearestPalletUserID);
        int targetX = selectedPallet.getX() + (selectedPallet.getLength() / 2);
        int targetY = selectedPallet.getY();
        //System.out.println(targetX);
        //System.out.println(targetY);
        int x = getX();
        int y = getY();
        double angle;
        angle = (double) round(Math.toDegrees(Math.atan2(targetY - y, targetX - x)), 1);
        
        if (angle < 0) {
            angle += 360;
        }
        
        this.dx = (float) Math.round(speed * Math.cos(angle * Math.PI / 180));
        this.dy = (float) Math.round(speed * Math.sin(angle * Math.PI / 180));
        //System.out.println(dx);
        //System.out.println(dy);
    }
    
    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
    
    public int calculateNearestPalletUserID(List<Pallet> pallets){
        int palletUserID = 0;
        int nearestX = 1000;
        for (Pallet pallet : pallets) {
            int difference = Math.abs(pallet.getX() - this.getX());
            if(difference < nearestX){
                nearestX = difference;
                palletUserID = pallet.getUserID();
            }
        }
        return palletUserID;
    }

//    public int getId(){
//        return id;
//    }

    public Level getLevel() {
        return level;
    }

    public Pallet getPalletLastTouched() {
        return palletLastTouched;
    }

    public void setPalletLastTouched(Pallet palletLastTouched) {
        this.palletLastTouched = palletLastTouched;
    }
    
    public void setLastUserThatTouchedMe(int lastUserThatTouchedMe) {
        this.lastUserThatTouchedMe = lastUserThatTouchedMe;
    }
    
    public int getLastUserThatTouchedMe() {
        return lastUserThatTouchedMe;
    }
    
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed * 2;
        resetDx();
        resetDy();
    }

    public void setDx(float dx){
        this.dx = dx;
    }
    
    public void setDy(float dy){
        this.dy = dy;
    }

    public float getDx() {
        return dx;
    }

    public float getDy() {
        return dy;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
    
    public void resetDx(){
        if(dx > 0){
            dx = speed - Math.abs(dy);
        } else {
            dx = -speed + Math.abs(dy);
        }
    }
    
    public void resetDy(){
        if(dy > 0){
            dy = speed / 2;
        } else {
            dy = -speed / 2;
        }
    }
    
    public void cutDyBy(float getal){
        resetDy();
        resetDx();
        if(dx > 0){
            this.dx = (float) Math.ceil(dx /getal);
        } else {
            this.dx = (float) Math.floor(dx /getal); 
        }
        if(dy > 0){
            dy = speed - Math.abs(dx);
        } else {
            dy = -speed + Math.abs(dx);
        }
    }
    
    public void move(){
        this.setX(Math.round(this.getX() + dx));
        this.setY(Math.round(this.getY() + dy));
        Shape s = findCollidingSprite();
        if (s!=null) s.updateSpriteBall(this);
    }
    
    public void resetState(){
        this.setX(INIT_BALL_X);
        this.setY(INIT_BALL_Y);
        //dx = -speed / 2;
        //dy = speed / 2;
        setAngleDirectionToNearestPallet();
    }
    
    public Shape findCollidingSprite() {
        for (Shape s : level.getAllEntities()) {
            if(this.getX() != s.getX()){
                if(this.checkCollission(s)){
                    return s;
                }
            }  
        }
        return null;
    }
    
    public void updateSpriteBallAfterCollidingWithPallet(Pallet p){
        float rectPosX = p.getX();
        float rectPosY = p.getY();
        float ballLPosX = this.getX();
        float ballPosY = this.getY();
        
        float leftSide = rectPosX;
        float rightSide = rectPosX + p.getLength();
        
        float updside = rectPosY;
        float downSide = rectPosY + p.getHeight();
        
        if(ballLPosX < leftSide){
            setDx(-Math.abs(getDx()));
        }
        
        if(ballPosY < updside){
            updateSpriteBallAfterCollidingWithPalletUpsideOrDownside(p, rectPosX, ballLPosX, leftSide, rightSide, -1.0f);
        } 
        
        if(ballPosY > downSide){
            updateSpriteBallAfterCollidingWithPalletUpsideOrDownside(p, rectPosX, ballLPosX, leftSide, rightSide, 1.0f);
        }
        
        if(ballLPosX >= rightSide){
            setDx(Math.abs(getDx()));
        }
        
        p.setLastBallTouched(this);
        setPalletLastTouched(p);
        setLastUserThatTouchedMe(p.getUserID());
        //System.out.println(getLastUserThatTouchedMe());
    }

    private void updateSpriteBallAfterCollidingWithPalletUpsideOrDownside(Pallet p, float rectPosX, float ballLPos, float leftSide, float rightSide, float direction) {
        float first = rectPosX + (p.getLength() / 5); 
        float second = rectPosX + ((p.getLength() / 5) * 2);
        float third = rectPosX + ((p.getLength() / 5) * 3);
        float fourth = rectPosX + ((p.getLength() / 5) * 4);
        
        if (ballLPos >= leftSide && ballLPos < first) {
            cutDyBy(4);
            setDx(Math.abs(getDx()) * -Math.abs(direction));
        }

        if (ballLPos >= first && ballLPos < second) {
            cutDyBy(2);
            setDx(Math.abs(getDx()) * -Math.abs(direction));
        }
        
        if (ballLPos >= third && ballLPos < fourth) {
            cutDyBy(2);
            setDx(Math.abs(getDx()));
        }

        if (ballLPos >= fourth && ballLPos < rightSide ) {
            cutDyBy(4);
            setDx(Math.abs(getDx()));
        }
        
        setDy(Math.abs(getDy()) * direction);
    }
    
    public void updateSpriteBallAfterCollidingWithBrick(Brick b){
        float rectPosX = b.getX();
        float rectPosY = b.getY();
        float ballLPosX = this.getX();
        float ballPosY = this.getY();
        
        float leftSide = rectPosX;
        float rightSide = rectPosX + b.getLength();
        
        float updside = rectPosY;
        float downSide = rectPosY + b.getHeight();
        
        if(ballLPosX < leftSide){
            setDx(-Math.abs(getDx()));
        }
        
        if(ballPosY < updside){
            setDy(-Math.abs(getDy()));
        } 
        
        if(ballPosY > downSide){
            setDy(Math.abs(getDy()));
        }
        
        if(ballLPosX >= rightSide){
            setDx(Math.abs(getDx()));
        }
        //level.getSpellByUser(level.getPlayers().get(getLastUserThatTouchedMe() - 1)).setDeActive(); // is never replaced
        setDamage(1);
        level.lowerHitsOfBrick(this, b, getLastUserThatTouchedMe() - 1);
    }
    
    public void updateSpriteBallAfterCollidingWithPowerUp(PowerUpOrDown powerUpTouched){
        setDx(-getDx());
        setDy(-getDy());

        level.getPowerUpsShownOnScreen().remove(powerUpTouched);
        //level.resetPowerUps();
        powerUpTouched.setEntetiesOfLevel(this);
        powerUpTouched.setActive();
        level.addPowerUpActive(powerUpTouched);
    }
    
    public void updateSpriteAfterCollidingWithCircle(Circle circle){
        if(getX() < circle.getX()){
            while(checkCollissionWithCircle(circle)){
                setX(getX() - 1);
            }
        } else {
            while(checkCollissionWithCircle(circle)){
                setX(getX() + 1);
            }
        }
        setDx(-getDx());
        setDy(-getDy());
    }
    
    public void updateSpriteAfterCollidingWithLeftBoundary(){
        setDx(Math.abs(getDx()));
    }
    
    public void updateSpriteAfterCollidingWithRightBoundary(){
        setDx(-Math.abs(getDx()));
    }
    
    public void updateSpriteAfterCollidingWithTopBoundary(){
        setDy(-getDy());
    }
    
    public void updateSpriteAfterCollidingWithBottomBoundary(){
        level.decrementLife();
        level.resetStates();
    }
    
    public User giveUserActivatedSpecialBall(){
        return null;
    }
    
    @Override
    public String toString() {
        return "Ball";
    }
}
