/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.spells;

import be.howest.ti.breakout.domain.game.Level;
import java.util.ArrayList;
import java.util.List;
import be.howest.ti.breakout.domain.effects.Effect;

/**
 *
 * @author micha
 */
public class ZelfstandigNaamwoord extends Woord{
    private Effect effect;
    private final List<BijvoegelijkNaamwoord> bijvoegelijkeNaamwoorden = new ArrayList<>();
    
    public ZelfstandigNaamwoord(String naam, int amountOfDamage, String typeOfDamage, Effect effect) {
        super(naam, amountOfDamage, typeOfDamage);
        this.effect = effect;
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
    
    public void setEntetiesOfEffect(Level level, int userId){
        effect.setUserPallet(level.getUserPallet(userId));
        effect.setLastBallActivated(effect.getUserPallet().getLastBallTouched());
        effect.setLevel(level);
        effect.setUserActivatedEffect(level.getPlayers().get(userId - 1));
        for (BijvoegelijkNaamwoord bijvoegelijkNaamwoord : bijvoegelijkeNaamwoorden) {
            bijvoegelijkNaamwoord.setEntetiesOfEffect(level, userId);
        }
    }
    
    public String combineNames(){
        String completeName = getNaam();
        for (BijvoegelijkNaamwoord bijvoegelijkNaamwoord : bijvoegelijkeNaamwoorden) {
            completeName = bijvoegelijkNaamwoord.combineName(completeName);
        }
        return completeName;
    }
    
    public List<Effect> getEffects(){
        List<Effect> spellEffects = new ArrayList<>();
        spellEffects.add(effect);
        for (BijvoegelijkNaamwoord bijvoegelijkNaamwoord : bijvoegelijkeNaamwoorden) {
            spellEffects.add(bijvoegelijkNaamwoord.getEffect());
        }
        return spellEffects;
    }
    
//    public void resetEffect(){
//        effect.setReady();
//        for (BijvoegelijkNaamwoord bijvoegelijkNaamwoord : bijvoegelijkeNaamwoorden) {
//            bijvoegelijkNaamwoord.resetEffect();
//        }
//    }
    
    public int cast(){
        effect.setActive();
        for (BijvoegelijkNaamwoord bijvoegelijkNaamwoord : bijvoegelijkeNaamwoorden) {
            bijvoegelijkNaamwoord.cast();
        }
        
        int totalDamage = getAmountOfDamage();
        for (BijvoegelijkNaamwoord bijvoegelijkNaamwoord : bijvoegelijkeNaamwoorden) {
            totalDamage = bijvoegelijkNaamwoord.combineDamage(totalDamage);
        }
        return totalDamage;
    }
    
}
