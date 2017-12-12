/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spells;

/**
 *
 * @author micha
 */
public class BijvoegelijkNaamwoord extends Woord{
    
    public BijvoegelijkNaamwoord(String naam, int amountOfDamage, String typeOfDamage) {
        super(naam, amountOfDamage, typeOfDamage);
    }
    
    public String combineName(String combinedName){
        return getNaam() + " " + combinedName;
    }
    
    public int combineDamage(int totalDamageSoFar){
        return totalDamageSoFar += getAmountOfDamage();
    }
}
