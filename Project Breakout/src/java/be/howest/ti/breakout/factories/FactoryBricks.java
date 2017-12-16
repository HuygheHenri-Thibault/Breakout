/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.factories;

import be.howest.ti.breakout.data.MySQLPowerUpOrDownRepository;
import be.howest.ti.breakout.data.Repositories;
import be.howest.ti.breakout.domain.Brick;
import be.howest.ti.breakout.domain.BrickRow;
import java.util.List;
import java.util.Random;
import be.howest.ti.breakout.domain.powerUps.AllPowerUps;
import be.howest.ti.breakout.domain.effects.EffectQuickerPallet;
import be.howest.ti.breakout.domain.effects.EffectExtraBall;
import be.howest.ti.breakout.domain.powerUps.PowerUpOrDown;
import be.howest.ti.breakout.domain.effects.EffectBiggerPallet;

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
        //weg doen als alle testen gedaan zijn
        
        //test for powerup
        //Brick testBrick = rowBricks.getBricksOnRow().get(rowBricks.getBricksOnRow().size() - 1);
        //make a random powerup
        //get all the powerups from database
        //select a random powerup out of the list
    }   
    
    private Brick createSingleBrick(List<BrickRow> rowsMade, BrickRow rowBricks){
        String color = rowBricks.getBrickData().getColor();
        
        int height = (rowBricks.getMAX_BRICK_BORDER_Y() - rowBricks.getMIN_BRICK_BORDER_Y()) / 6;
        
        int bricksLenghtMadeSoFar = rowBricks.getSomLengteGemaakteBricks();
        int x = rowBricks.getMIN_BRICK_BORDER_X() + bricksLenghtMadeSoFar;
        int y = rowBricks.getMIN_BRICK_BORDER_Y() + (rowsMade.size() * height);
        
        Random generator = new Random();
        double ratioLengte = Math.round((generator.nextDouble() * (1.0 - 0.5) + 0.5) * 10.0) / 10.0;
//      int maxLengte = (rowBricks.getMAX_BRICK_BORDER_X() - rowBricks.getMIN_BRICK_BORDER_X()) / 10;
        int minLengte = (rowBricks.getMAX_BRICK_BORDER_X() - rowBricks.getMIN_BRICK_BORDER_X()) / 10;
//      int lengte = rand.nextInt((maxLengte-minLengte) + 1) + minLengte;
        int lengte = (int) ((rowBricks.getBrickData().getBaseLen() * ratioLengte) * minLengte);
        if(x + lengte > (rowBricks.getMAX_BRICK_BORDER_X() - minLengte / 2)){
            int spaceLeft = rowBricks.getMAX_BRICK_BORDER_X() - rowBricks.getMIN_BRICK_BORDER_X() - rowBricks.getSomLengteGemaakteBricks();
            lengte = spaceLeft;
        }
        
        int achievedScoreIfDestroyed = rowBricks.getBrickData().getBaseScore() + (10 * (rowBricks.getLevel().getNumber() - 1));
        int hits = 1; //rowBricks.getBrickData().getBaseHits();
       
        Brick b = new Brick(rowBricks, lengte, height, hits, achievedScoreIfDestroyed, color, x, y);
        
        if((generator.nextInt((10 - 1) - 1 + 1) + 1) == 1){
            PowerUpOrDown power = Repositories.getPowerUpDownRepository().getAllPowerUpsAndDowns().getRandomPowerUpOrDown(rowBricks.getLevel());
            power.setBrickHiddenIn(b);
            //System.out.println("added " + power.getName() + " in brick x:" + b.getX() + " y:" + b.getY());
        }

        return b;
    }
    
}
