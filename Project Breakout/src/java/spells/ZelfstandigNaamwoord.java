/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spells;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author micha
 */
public class ZelfstandigNaamwoord extends Woord{
    private final List<BijvoegelijkNaamwoord> bijvoegelijkeNaamwoorden = new ArrayList<>();
    
    public ZelfstandigNaamwoord(String naam, int amountOfDamage, String typeOfDamage) {
        super(naam, amountOfDamage, typeOfDamage);
    }
    
    public void addBijvoegelijkNaamwoord(BijvoegelijkNaamwoord bn){
        bijvoegelijkeNaamwoorden.add(bn);
    }
    
    public List<BijvoegelijkNaamwoord> getAllBijvoegelijkNaamwoorden(){
        return bijvoegelijkeNaamwoorden;
    }
    
    public boolean hasBijvoegelijkNaamwoord(BijvoegelijkNaamwoord bn){
        return bijvoegelijkeNaamwoorden.contains(bn);
    }
    
    public String combineNames(){
        String completeName = getNaam();
        for (BijvoegelijkNaamwoord bijvoegelijkNaamwoord : bijvoegelijkeNaamwoorden) {
            completeName = bijvoegelijkNaamwoord.combineName(completeName);
        }
        return completeName;
    }
    
    public int combineDamage(){
        int totalDamage = getAmountOfDamage();
        for (BijvoegelijkNaamwoord bijvoegelijkNaamwoord : bijvoegelijkeNaamwoorden) {
            totalDamage = bijvoegelijkNaamwoord.combineDamage(totalDamage);
        }
        return totalDamage;
    }
    
}
