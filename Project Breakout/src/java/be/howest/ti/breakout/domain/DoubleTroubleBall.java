/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain;

import be.howest.ti.breakout.domain.game.User;
import be.howest.ti.breakout.domain.game.Level;
import be.howest.ti.breakout.domain.effects.EffectExtraBall;

/**
 *
 * @author micha
 */
public final class DoubleTroubleBall extends Ball{
    private final EffectExtraBall effect;
    private final User user;
    
    public DoubleTroubleBall(Level level, int radius, int speed, String color, int x, int y, EffectExtraBall effect) {
        super(level, radius, speed, color, x, y);
        this.effect = effect;
        this.user = effect.getUserActivatedEffect();
        setLastUserThatTouchedMe(user);
    }
    
    @Override
    public void updateSpriteAfterCollidingWithBottomBoundary(){
        effect.setDeActive();
    }
    
    @Override
    public User giveUserActivatedSpecialBall(){
        return user;
    }
    
    
}
