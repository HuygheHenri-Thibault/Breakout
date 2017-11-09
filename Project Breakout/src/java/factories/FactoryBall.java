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
public class FactoryBall extends FactoryBreakoutUtilities{
    private final Level level;

    public FactoryBall(Level level) {
        this.level = level;
    }
    
    public void createBall(){
        String colorPallet = findUnusedColor();
        int x = (int) (level.getGameWidth()/ 2);
        int y = (int) ((level.getGameHeight()/ 10) * 8); 
        Ball b = new Ball(level, 15, 5, colorPallet, x, y);
        level.getBalls().add(b);
    }
}
