/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factories;

import domain.Brick;
import domain.BrickRow;
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
        
        int height = (rowBricks.getMAX_BRICK_BORDER_Y() - rowBricks.getMIN_BRICK_BORDER_Y()) / 10;
        
        int x = rowBricks.getMIN_BRICK_BORDER_X() + rowBricks.getSomLengteGemaakteBricks();
        int y = rowBricks.getMIN_BRICK_BORDER_Y() + (rowsMade.size() * height);
        
        Random rand = new Random();
        int maxLengte = (rowBricks.getMAX_BRICK_BORDER_X() - rowBricks.getMIN_BRICK_BORDER_X()) / 10;
        int minLengte = (rowBricks.getMAX_BRICK_BORDER_X() - rowBricks.getMIN_BRICK_BORDER_X()) / 25;
        int lengte = rand.nextInt((maxLengte-minLengte) + 1) + minLengte;
        if(x + lengte > rowBricks.getMAX_BRICK_BORDER_X()){
            lengte = rowBricks.getMAX_BRICK_BORDER_X() - rowBricks.getMIN_BRICK_BORDER_X() - rowBricks.getSomLengteGemaakteBricks();
        }
        
        int achievedScoreIfDestroyed = rowBricks.getAchievedScoreIfDestroyedForBrickOnRow();
       
        Brick b = new Brick(rowBricks, lengte, height, 1, achievedScoreIfDestroyed, color, x, y);
        return b;
    }
    
}
