/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factories;

import domain.Ball;
import domain.DoubleTroubleBall;
import domain.Game;
import domain.Level;
import java.util.ArrayList;
import java.util.List;
import powerUps.EffectDoubleTrouble;

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
    
    public void createExtraBallDoubleTrouble(EffectDoubleTrouble effect){
        String colorPallet = findUnusedColor();
        
        int x =  level.getGameWidth() / 2;
        int y = (level.getGameHeight()/ 10) * 6;
        
        int speed = Math.round(3 * level.getRatios().get(1).getRatio());
        
        Ball b = new DoubleTroubleBall(level, 10, speed, colorPallet, x, y, effect);
        level.getBalls().add(b);
    }
}
