/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain;

import be.howest.ti.breakout.domain.effects.EffectDragonFireBall;
import be.howest.ti.breakout.domain.game.Level;
import be.howest.ti.breakout.domain.powerUps.PowerUpOrDown;

/**
 *
 * @author Fredr
 */
public class Fireball extends Ball {
    private final EffectDragonFireBall effect;
    
    public Fireball(Level level, int radius, int speed, String color, int x, int y, EffectDragonFireBall effect) {
        super(level, radius, speed, color, x, y);
        this.effect = effect;
    }

    @Override
    public void updateSpriteAfterCollidingWithBall(Ball ball) {}
    
    @Override
    public void updateSpriteBallAfterCollidingWithPallet(Pallet p){
        effect.getLevelOfEffect().decrementLife();
        removeFromScreen();
    }
  
    @Override
    public void updateSpriteBallAfterCollidingWithBrick(Brick b){}
    
    @Override
    public void updateSpriteBallAfterCollidingWithPowerUp(PowerUpOrDown p){}
    
    @Override
    public void updateSpriteAfterCollidingWithBottomBoundary(){
        removeFromScreen();
    }

    @Override
    public String toString() {
        return super.toString() + " fireball";
    }
    
    @Override
    public void updateSpriteBall(Ball aBall) {
        aBall.updateSpriteAfterColldingWithFireBall(this);
    }
}
