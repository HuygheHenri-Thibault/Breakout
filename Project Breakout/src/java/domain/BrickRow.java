/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author micha
 */
public class BrickRow {
    private final Level level;
    private List<Brick> bricksOnRow = new ArrayList<>();
    private final int MIN_BRICK_BORDER_X;
    private final int MAX_BRICK_BORDER_X;
    private final int MIN_BRICK_BORDER_Y;
    private final int MAX_BRICK_BORDER_Y;

    public BrickRow(Level level) {
        if(level != null){ this.level = level; } else {throw new NullPointerException("Level may not be null");}
        this.MIN_BRICK_BORDER_X = level.getGameWidth() / 4;
        this.MAX_BRICK_BORDER_X = (level.getGameWidth() / 4) * 3;
        this.MIN_BRICK_BORDER_Y = level.getGameHeight()/ 4;
        this.MAX_BRICK_BORDER_Y = (level.getGameHeight()/ 4) * 3;
    }

    public Level getLevel() {
        return level;
    }
    
    public int getMIN_BRICK_BORDER_X() {
        return MIN_BRICK_BORDER_X;
    }

    public int getMAX_BRICK_BORDER_X() {
        return MAX_BRICK_BORDER_X;
    }

    public int getMIN_BRICK_BORDER_Y() {
        return MIN_BRICK_BORDER_Y;
    }

    public int getMAX_BRICK_BORDER_Y() {
        return MAX_BRICK_BORDER_Y;
    }
    
    public List<Brick> getBricksOnRow() {
        return bricksOnRow;
    }
    
    public void addBrickToRow(Brick b){
        bricksOnRow.add(b);
    }
    
    public int getSomLengteGemaakteBricks(){
        int som = 0;
        if(bricksOnRow.size() > 0){
            for (Brick brick : bricksOnRow) {
                som += brick.getLength();
            }
        }
       return som; 
    }
    
    public void deleteBrick(Brick b){
        bricksOnRow.remove(b);
    }
    
}
