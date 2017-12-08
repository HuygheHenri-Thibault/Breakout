/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package powerUps;

import domain.Game;
import domain.Level;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author micha
 */
public class AllPowerUps {
    List<PowerUpOrDown> powerUps = new ArrayList<>();
    List<PowerUpOrDown> powerDowns = new ArrayList<>();
    
    public void addToPowerUpList(PowerUpOrDown powerup){
        powerUps.add(powerup);
    }
    
    public void addToPowerDownList(PowerUpOrDown powerup){
        powerDowns.add(powerup);
    }
    
    public List<PowerUpOrDown> getAllPowerUpsAndDowns(){
        List<PowerUpOrDown> allPowerUps = new ArrayList<>();
        allPowerUps.addAll(powerUps);
        allPowerUps.addAll(powerDowns);
        return allPowerUps;
    }
    
    public PowerUpOrDown getRandomPowerUpOrDown(Level thisLevel){
        
//        int randomNumber = r.nextInt(powerUpsAlreadyInLevel.size() - 1 + 1) + 1;
//        while(powerUpsAlreadyInLevel.contains(getAllPowerUpsAndDowns().get(randomNumber))){
//            randomNumber =  r.nextInt(powerUpsAlreadyInLevel.size() - 1 + 1) + 1;
//        }
        Random r = new Random();
        int powerUpOrDownsChance = r.nextInt(100 - 0 + 0) + 0;
        if(powerUpOrDownsChance < 50 * thisLevel.getRatios().get(2).getRatio()){
            return getRandomPowerUp(r, 0, powerUps.size() - 1);
        } else {
            return getRandomPowerDown(r, 0, powerDowns.size() - 1);
        }        
    }
    
    private PowerUpOrDown getRandomPowerUp(Random r, int min, int max){
        //((max-min) + 1) + min 
        int randomNumber = r.nextInt((max - min) + 1) + min;
        System.out.println(randomNumber);
        return powerUps.get(randomNumber);
    }
    
    private PowerUpOrDown getRandomPowerDown(Random r, int min, int max){
        int randomNumber = r.nextInt((max - min) + 1) + min;
        System.out.println(randomNumber);
        return powerDowns.get(randomNumber);
    }
}
