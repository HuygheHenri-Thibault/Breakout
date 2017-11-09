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
import domain.BrickRow;
import domain.BrickRow;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author micha
 */
public class FactoryBricks extends FactoryBreakoutUtilities{
    
    public FactoryBricks() {
    }
    
    public void createBricks(List<BrickRow> rowsMade, BrickRow rowBricks){
        while(rowBricks.getMIN_BRICK_BORDER_X() + rowBricks.getSomLengteGemaakteBricks()!= rowBricks.getMAX_BRICK_BORDER_X()){
            Brick b = createSingleBrick(rowsMade, rowBricks);
            rowBricks.addBrickToRow(b);
        }
    }   
    
    private Brick createSingleBrick(List<BrickRow> rowsMade, BrickRow rowBricks){
        String color = findUnusedColor();
        
        int x = 250 + rowBricks.getSomLengteGemaakteBricks();
        int y = 250 + rowsMade.size() * 50;
        
        Random rand = new Random();
        int lengte = rand.nextInt((50-20) + 1) + 20;
        if(x + lengte > rowBricks.getMAX_BRICK_BORDER_X()){
            lengte = rowBricks.getMAX_BRICK_BORDER_X() - rowBricks.getMIN_BRICK_BORDER_X() - rowBricks.getSomLengteGemaakteBricks();
        }
        
        int achievedScoreIfDestroyed = rowBricks.getAchievedScoreIfDestroyedForBrickOnRow();
       
        Brick b = new Brick(rowBricks, lengte, 50, 1, achievedScoreIfDestroyed, color, x, y);
        return b;
    }
    
}
