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

    public void createBall() {
        int numberOfBalls = (int) Math.ceil((double)level.getAantalSpelers() / 2);
        for (int i = 0; i < numberOfBalls; i++) {
            String colorPallet = findUnusedColor();
            
            int startX = level.getGameWidth() / numberOfBalls / 2; 
            int nextXDistance = level.getGameWidth() / numberOfBalls; 
            int multiplierDistance = level.getBalls().size(); 
            
            int x =  startX + (nextXDistance * multiplierDistance);
            int y = (level.getGameHeight()/ 10) * 8;
            
            int speed = Math.round(2 * level.getRatios().get(1).getRatio());
            Ball b = new Ball(level, 15, speed, colorPallet, x, y);
            level.getBalls().add(b);
        }
    }
}
