/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain;

import be.howest.ti.breakout.domain.effects.EffectFireBall;
import be.howest.ti.breakout.domain.game.Level;

/**
 *
 * @author Fredr
 */
public class Fireball extends Ball {
    private final EffectFireBall effect;
    
    public Fireball(Level level, int radius, int speed, String color, int x, int y, EffectFireBall effect) {
        super(level, radius, speed, color, x, y);
        this.effect = effect;
    }

    @Override
    public void updateSpriteAfterCollidingWithBall(Ball ball) {}
    
    @Override
    public void updateSpriteBallAfterCollidingWithPallet(Pallet p){
        effect.getLevelOfEffect().decrementLife();
        goneFromScreen();
    }
  
    @Override
    public void updateSpriteBallAfterCollidingWithBrick(Brick b){
        
    }
    @Override
    public void updateSpriteAfterCollidingWithBottomBoundary(){
        goneFromScreen();
    }

    @Override
    public String toString() {
        return "Fireball";
    }
    
    
}
