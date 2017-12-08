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
    //alle power ups van de database halen
    //van alle data, variabelen maken. 
    
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
    
    public PowerUpOrDown getRandomPowerUpOrDown(Level thisLevel, List<PowerUpOrDown> powerUpsAlreadyInLevel){
        Random r = new Random();
        int randomNumber = r.nextInt(powerUpsAlreadyInLevel.size() - 1 + 1) + 1;
        while(powerUpsAlreadyInLevel.contains(getAllPowerUpsAndDowns().get(randomNumber))){
            randomNumber =  r.nextInt(powerUpsAlreadyInLevel.size() - 1 + 1) + 1;
        }
        int powerUpOrDownsChance = r.nextInt(100 - 0 + 0) + 0;
        if(powerUpOrDownsChance < 50 * thisLevel.getRatios().get(2).getRatio()){
            return getRandomPowerUp(randomNumber);
        } else {
            return getRandomPowerDown(randomNumber);
        }        
    }
    
    private PowerUpOrDown getRandomPowerUp(int randomNumber){
        return powerUps.get(randomNumber);
    }
    
    private PowerUpOrDown getRandomPowerDown(int randomNumber){
        return powerDowns.get(randomNumber);
    }
//    enum AllBonuses{
//    Bonus1(BonusClass1::new),
//    // ...
//    Bonus9(BonusClass9::new);
//
//    private final Supplier<Bonus> bonus;
//    AllBonuses(Supplier<Bonus> bonus) { this.bonus = bonus; }
//    public Bonus create() { return bonus.get(); }
//}
//
//// Usage:
//int randomNumber = generateRandomNumber(AllBonuses.values().length);
//Bonus randomBonus = AllBonuses.values()[randomNumber].create();
}
