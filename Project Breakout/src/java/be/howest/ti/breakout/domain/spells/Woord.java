/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.spells;

/**
 *
 * @author micha
 */
public class Woord {
    private final String naam;
    private final int amountOfDamage;
    private final String typeOfDamage;

    public Woord(String naam, int amountOfDamage, String typeOfDamage) {
        this.naam = naam;
        this.amountOfDamage = amountOfDamage;
        this.typeOfDamage = typeOfDamage;
    }

    public String getNaam() {
        return naam;
    }
    
    public int getAmountOfDamage() {
        return amountOfDamage;
    }

    public String getTypeOfDamage() {
        return typeOfDamage;
    }
    
}
