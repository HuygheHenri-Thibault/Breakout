/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factories;

import domain.Brick;
import domain.Level;
import domain.RowOfBricks;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author micha
 */
public class FactoryRowOfBricks {
    private Level level;
    private String[] colors = {"red", "green", "yellow", "blue", "purple"};

    public FactoryRowOfBricks(Level level) {
        this.level = level;
    }
    
    private List<RowOfBricks> createRowOfBricks(){
        List<RowOfBricks> rowsOfBricks = new ArrayList();
        FactoryBricks factoryB = new FactoryBricks(level);
        for (int i = 0; i < level.getMAX_ROWS_BRICKS(); i++) {
            RowOfBricks rowBricks = new RowOfBricks();
            factoryB.createBricks(rowBricks, colors[i]);
            rowsOfBricks.add(rowBricks);
        }
        return rowsOfBricks;
    }
}
