/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain;

import be.howest.ti.breakout.domain.game.Level;
import be.howest.ti.breakout.domain.effects.EffectExtraBall;
import be.howest.ti.breakout.domain.game.Player;

/**
 *
 * @author micha
 */
public final class DoubleTroubleBall extends Ball{
    private final EffectExtraBall effect;
    private final Player player;
    
    public DoubleTroubleBall(Level level, int radius, int speed, String color, int x, int y, EffectExtraBall effect) {
        super(level, radius, speed, color, x, y);
        this.effect = effect;
        this.player = effect.getPlayerActivatedEffect();
        setLastPlayerThatTouchedMe(player);
    }
    
    @Override
    public void updateSpriteAfterColldingWithFireBall(Fireball fireBall){}
    
    @Override
    public void updateSpriteAfterCollidingWithBottomBoundary(){
        effect.setDeActive();
    }
    
    @Override
    public Player givePlayerActivatedSpecialBall(){
        return player;
    }
    
    
}
