/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spells;

import data.Repositories;
import java.util.List;
import java.util.Random;

/**
 *
 * @author micha
 */
public class Spell{
    private ZelfstandigNaamwoord zelfstandigNaamwoord;
    private String name;

    public Spell() {
        randomizeWords();
    }
    
    private void randomizeWords(){
        Random generator = new Random(); 
        int numberBetween1And3 = generator.nextInt((3 - 1) + 1) + 1;
        for (int i = 1; i <= numberBetween1And3; i++) {
            if(i == 1){
                zelfstandigNaamwoord = fetchNewZelfstandigNaamwoord(generator);
            } else {
                zelfstandigNaamwoord.addBijvoegelijkNaamwoord(fetchNewBijvoegelijkNaamwoord(generator));
            }
        }
        this.name = zelfstandigNaamwoord.combineNames();
    }
    
    private ZelfstandigNaamwoord fetchNewZelfstandigNaamwoord(Random generator){
        List<ZelfstandigNaamwoord> zelfstandigeNaamwoorden = Repositories.getSpellRepository().getHardcodedZelfstandigeNaamwoorden();
        int max = (zelfstandigeNaamwoorden.size() - 1);
        int min = 0;
        int randomIndex = generator.nextInt((max - min) + 1) + min;
        return zelfstandigeNaamwoorden.get(randomIndex);
    }
    
    private BijvoegelijkNaamwoord fetchNewBijvoegelijkNaamwoord(Random generator){
        List<BijvoegelijkNaamwoord> bijvoegelijkeNaamwoorden = Repositories.getSpellRepository().getHardCodedBijvoegelijkeNaamwoorden();
        int max = (bijvoegelijkeNaamwoorden.size() - 1);
        int min = 0;
        int randomIndex = generator.nextInt((max - min) + 1) + min;
        while(zelfstandigNaamwoord.hasBijvoegelijkNaamwoord(bijvoegelijkeNaamwoorden.get(randomIndex))){
            randomIndex = generator.nextInt((max - min) + 1) + min;
        }
        return bijvoegelijkeNaamwoorden.get(randomIndex);
    }

    public String getName() {
        return name;
    }
    
    public int cast(){
        return zelfstandigNaamwoord.combineDamage();
    }
}
