/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.factories;

import be.howest.ti.breakout.domain.Ball;
import be.howest.ti.breakout.domain.DoubleTroubleBall;
import be.howest.ti.breakout.domain.Fireball;
import be.howest.ti.breakout.domain.game.Game;
import be.howest.ti.breakout.domain.game.Level;
import java.util.ArrayList;
import java.util.List;
import be.howest.ti.breakout.domain.effects.EffectExtraBall;
import be.howest.ti.breakout.domain.effects.EffectFireBall;

/**
 *
 * @author micha
 */
public class FactoryBall extends FactoryBreakoutUtilities {
    private final Level level;

    public FactoryBall(Level level) {
        this.level = level;
    }

    public void createBalls() {
        int numberOfBalls = (int) Math.ceil((double)level.getAantalSpelers() / 2);
        for (int i = 0; i < numberOfBalls; i++) {
            createBall(numberOfBalls);
        }
    }

    public void createBall(int numberOfBalls) {
        String colorPallet = findUnusedColor();
        
        int startX = (level.getGameWidth() / numberOfBalls) / 2;
        int nextXDistance = level.getGameWidth() / numberOfBalls;
        int multiplierDistance = level.getBalls().size();
        
        int x =  startX + (nextXDistance * multiplierDistance);
        int y = (level.getGameHeight()/ 10) * 6;
        
        int speed = 3 * Math.round(level.getRatios().get(1).getRatio());//original 2
        //System.out.println(speed);
        
        Ball b = new Ball(level, 10, speed, colorPallet, x, y);
        level.getBalls().add(b);
    }
    
    public DoubleTroubleBall createExtraBallDoubleTrouble(EffectExtraBall effect){
        String colorPallet = findUnusedColor();
        
        int x =  level.getGameWidth() / 2;
        int y = (level.getGameHeight()/ 10) * 6;
        
        int speed = 3 * Math.round(level.getRatios().get(1).getRatio());
        
        DoubleTroubleBall b = new DoubleTroubleBall(level, 10, speed, colorPallet, x, y, effect);
        level.getExtraBallCreatedByEffects().add(b);
        return b;
    }
    
    public Fireball createExtraFireball(EffectFireBall effect){
        String colorPallet = findUnusedColor();
        
        int x = level.getGameWidth() / 2;
        int y = 15;
        
        int speed = 5 * Math.round(level.getRatios().get(1).getRatio());
        
        Fireball b = new Fireball(level, 10, speed, colorPallet, x, y, effect);
        level.getExtraBallCreatedByEffects().add(b);
        return b;  
    }
}
