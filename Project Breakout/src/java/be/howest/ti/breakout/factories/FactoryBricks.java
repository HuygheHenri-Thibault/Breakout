/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.factories;

import be.howest.ti.breakout.data.Repositories;
import be.howest.ti.breakout.domain.Brick;
import be.howest.ti.breakout.domain.BrickData;
import be.howest.ti.breakout.domain.game.Level;
import java.util.List;
import java.util.Random;
import be.howest.ti.breakout.domain.powerUps.PowerUpOrDown;
import java.util.ArrayList;

/**
 *
 * @author micha
 */
public class FactoryBricks extends FactoryBreakoutUtilities{
    //testing
    private Level level;
    private int row;
    private List<BrickData> brickDatas = new ArrayList<>();
    
    private final int MIN_BRICK_BORDER_X;
    private final int MAX_BRICK_BORDER_X;
    private final int MIN_BRICK_BORDER_Y;
    private final int MAX_BRICK_BORDER_Y;
    
    public FactoryBricks(Level level) {
        this.level = level;
        this.row = 5;
        this.MIN_BRICK_BORDER_X = 0;
        this.MAX_BRICK_BORDER_X = level.getGameWidth();
        this.MIN_BRICK_BORDER_Y = level.getGameHeight() / 8;
        this.MAX_BRICK_BORDER_Y = level.getGameHeight() / 2;
    }
    
    public List<Brick> createBricks(){
        brickDatas = Repositories.getBrickRepository().getAllBricks();
        List<Brick> bricks = new ArrayList<>();
        for (BrickData brickData : brickDatas) {
            bricks.addAll(createRowBricks(brickData));
        }
        return bricks;
    }   
    
    private List<Brick> createRowBricks(BrickData brickData){
        List<Brick> bricksOnRow = new ArrayList<>();
        int somMadeBricks = getSomLengteGemaakteBricks(bricksOnRow);
        while(MIN_BRICK_BORDER_X + somMadeBricks != MAX_BRICK_BORDER_X){
            bricksOnRow.add(createSingleBrick(bricksOnRow, brickData));
            somMadeBricks = getSomLengteGemaakteBricks(bricksOnRow);
        }
        row--;
        return bricksOnRow;
    }
    
    
    private Brick createSingleBrick(List<Brick> bricksSoFar, BrickData brickData){
        String color = brickData.getColor();
        
        int height = (MAX_BRICK_BORDER_Y - MIN_BRICK_BORDER_Y) / 6;
        
        int bricksLenghtMadeSoFar = getSomLengteGemaakteBricks(bricksSoFar);
        int x = MIN_BRICK_BORDER_X + bricksLenghtMadeSoFar;
        int y = MIN_BRICK_BORDER_Y + ((row - 1) * height);
        
        Random generator = new Random();
        double ratioLengte = Math.round((generator.nextDouble() * (1.0 - 0.5) + 0.5) * 10.0) / 10.0;
        int minLengte = (MAX_BRICK_BORDER_X - MIN_BRICK_BORDER_X) / 10;
        
        int lengte = (int) ((brickData.getBaseLen() * ratioLengte) * minLengte);
        if(x + lengte > (MAX_BRICK_BORDER_X - minLengte / 2)){
            int spaceLeft = MAX_BRICK_BORDER_X - MIN_BRICK_BORDER_X - bricksLenghtMadeSoFar;
            lengte = spaceLeft;
        }
        
        int achievedScoreIfDestroyed = (brickData.getBaseScore() * level.getGame().getDifficulty().getChangeForBricks()) + (10 * (level.getNumber() - 1));
        
        int hits = brickData.getBaseHits() * level.getGame().getDifficulty().getChangeForBricks();
        
        if((generator.nextInt((10 - 1) - 1 + 1) + 1) == 1){
            hits += (2 * (level.getNumber() - 1));
        }
       
        Brick b = new Brick(level, lengte, height, hits, achievedScoreIfDestroyed, color, x, y);
        
        if((generator.nextInt((10 - 1) - 1 + 1) + 1) == 1){
            PowerUpOrDown power = Repositories.getPowerUpDownRepository().getAllPowerUpsAndDowns().getRandomPowerUpOrDown(level);
            power.setBrickHiddenIn(b);
        }

        return b;
    }
    
    private int getSomLengteGemaakteBricks(List<Brick> bricksOnRow){
        int som = 0;
        if(bricksOnRow.size() > 0){
            for (Brick brick : bricksOnRow) {
                som += brick.getLength();
            }
        }
       return som; 
    }
    
}
