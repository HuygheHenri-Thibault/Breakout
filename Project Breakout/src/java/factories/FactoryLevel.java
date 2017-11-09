/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factories;

import domain.Game;
import domain.Level;

/**
 *
 * @author micha
 */
public class FactoryLevel {
    private final Game game;
    private int numberLevels;

    public FactoryLevel(Game game) {
        this.game = game;
        this.numberLevels = 0;
    }
     
    public void createLevel(){
        numberLevels++;
        Level l = new Level(game, numberLevels, 0);
        game.getLevels().add(l);
    } 
}
