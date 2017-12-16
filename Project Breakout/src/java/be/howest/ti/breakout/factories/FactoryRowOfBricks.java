/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.factories;

import be.howest.ti.breakout.data.MySQLBrickRepository;
import be.howest.ti.breakout.domain.Brick;
import be.howest.ti.breakout.domain.BrickData;
import be.howest.ti.breakout.domain.game.Level;
import be.howest.ti.breakout.domain.game.Level;
import be.howest.ti.breakout.domain.BrickRow;
import be.howest.ti.breakout.domain.BrickRow;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author micha
 */
public class FactoryRowOfBricks{
    private final Level level;
    private MySQLBrickRepository BrickREPO = new MySQLBrickRepository();

    public FactoryRowOfBricks(Level level) {
        this.level = level;
    }
    
    public List<BrickRow> createRowOfBricks(){
        List<BrickData> brickDatas = BrickREPO.getAllBricks();
        List<BrickRow> rowsOfBricks = new ArrayList();
        FactoryBricks factoryB = new FactoryBricks();
        for (int i = level.getMAX_ROWS_BRICKS() - 1; i >= 0 ; i--) {
            BrickRow rowBricks = new BrickRow(level, brickDatas.get(i));
            factoryB.createBricks(rowsOfBricks, rowBricks);
            rowsOfBricks.add(rowBricks);
        }
        return rowsOfBricks;
    }
}
