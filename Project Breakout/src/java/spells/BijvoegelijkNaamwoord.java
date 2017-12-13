/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spells;

import domain.Level;
import powerUps.Effect;

/**
 *
 * @author micha
 */
public class BijvoegelijkNaamwoord extends Woord{
    private Effect effect;
    
    public BijvoegelijkNaamwoord(String naam, int amountOfDamage, String typeOfDamage, Effect effect) {
        super(naam, amountOfDamage, typeOfDamage);
        this.effect = effect;
    }
    
    public String combineName(String combinedName){
        return getNaam() + " " + combinedName;
    }
    
    public void setEntetiesOfEffect(Level level, int userId){
        effect.setUserPallet(level.getUserPallet(userId));
        effect.setLastBallActivated(effect.getUserPallet().getLastBallTouched());
        effect.setLevel(level);
    }
    
    public void cast(){
        effect.setActive();
    }
    
    public int combineDamage(int totalDamageSoFar){
        return totalDamageSoFar += getAmountOfDamage();
    }
    
    public Effect getEffect(){
        return effect;
    }

    @Override
    public String toString() {
        return "BijvoegelijkNaamwoord{" + "effect=" + effect.toString() + '}';
    }
  
}
