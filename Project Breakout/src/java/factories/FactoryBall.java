/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factories;

import domain.Ball;
import domain.Game;
import domain.Level;
import java.util.ArrayList;
import java.util.List;

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
        
        int speed = Math.round(2 * level.getRatios().get(1).getRatio());
        
        Ball b = new Ball(level, 10, speed, colorPallet, x, y);
        level.getBalls().add(b);
    }
    
    public void createExtraBall(){
        String colorPallet = findUnusedColor();
        
        int x =  level.getGameWidth() / 2;
        int y = (level.getGameHeight()/ 10) * 6;
        
        int speed = Math.round(2 * level.getRatios().get(1).getRatio());
        
        Ball b = new Ball(level, 10, speed, colorPallet, x, y);
        //level.getBallIterator().add(b);
        level.getBalls().add(b);
    }
}
