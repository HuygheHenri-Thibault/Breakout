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
import be.howest.ti.breakout.domain.game.Player;
import be.howest.ti.breakout.domain.game.User;

/**
 *
 * @author micha
 */
public final class ZelfstandigNaamwoord extends Woord{
    private final Effect effect;
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
        for (BijvoegelijkNaamwoord bijvoegelijkNaamwoord : bijvoegelijkeNaamwoorden) {
            if(bijvoegelijkNaamwoord.getNaam().equals(bn.getNaam())){
                return true;
            }
        }
        return false;
    }
    
    public void setEntetiesOfEffect(Level level, Player player){
        effect.setPlayerPallet(level.getPlayerPallet(player));
        effect.setBallActivatedEffect(effect.getPlayerPallet().getLastBallTouched());
        effect.setLevelOfEffect(level);
        effect.setPlayerActivatedEffect(player);
        for (BijvoegelijkNaamwoord bijvoegelijkNaamwoord : bijvoegelijkeNaamwoorden) {
            bijvoegelijkNaamwoord.setEntetiesOfEffect(level, player);
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
