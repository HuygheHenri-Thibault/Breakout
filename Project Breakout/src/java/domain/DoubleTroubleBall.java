/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import powerUps.EffectDoubleTrouble;

/**
 *
 * @author micha
 */
public class DoubleTroubleBall extends Ball{
    private EffectDoubleTrouble effect;
    private User user;
    
    public DoubleTroubleBall(Level level, int radius, int speed, String color, int x, int y, EffectDoubleTrouble effect) {
        super(level, radius, speed, color, x, y);
        this.effect = effect;
        this.user = effect.getUserActivatedEffect();
        setLastUserThatTouchedMe(user.getUserId());
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
