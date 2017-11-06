/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factories;

import domain.Brick;
import domain.Brick;
import domain.Level;
import domain.Level;
import domain.RowOfBricks;
import domain.RowOfBricks;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author micha
 */
public class FactoryBricks {
    private Level level;
    
    public FactoryBricks(Level level) {
        this.level = level;
    }
    
    public void createBricks(RowOfBricks rowBricks, String color){
        while(rowBricks.getMIN_BRICK_BORDER_X() + rowBricks.getSomAllBricksLenghts()!= rowBricks.getMAX_BRICK_BORDER_X()){
            Brick b = createSingleBrick(rowBricks, color);
            rowBricks.addBrickToRow(b);
        }
    }   
    
    private Brick createSingleBrick(RowOfBricks rowBricks, String color){
        Random rand = new Random();
        int x = 250 + rowBricks.getSomAllBricksLenghts();
        int y = 250 + rowBricks.getBricksOnRow().size() * 50;
        
        float lengte = (float) rand.nextInt((50-20) + 1) + 20;
        if(x + lengte > rowBricks.getMAX_BRICK_BORDER_X()){
            lengte = rowBricks.getMAX_BRICK_BORDER_X() - rowBricks.getMIN_BRICK_BORDER_X() - rowBricks.getSomAllBricksLenghts();
        }
       
        Brick b = new Brick(lengte, 1, 10, false, color, x, y);
        return b;
    }
    
}
