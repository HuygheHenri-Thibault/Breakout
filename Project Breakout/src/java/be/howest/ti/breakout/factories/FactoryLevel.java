/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.factories;

import be.howest.ti.breakout.domain.game.Game;
import be.howest.ti.breakout.domain.game.Level;
import be.howest.ti.breakout.domain.game.Ratio;

/**
 *
 * @author micha
 */
public class FactoryLevel {
    private final Game game;
    private int numberLevels;
    private int ratioChoiceChange = 0;

    public FactoryLevel(Game game) {
        this.game = game;
        this.numberLevels = 0;
    }
     
    public Level createLevel(){
        numberLevels++;
        Level l = new Level(game, numberLevels);
        game.getLevels().add(l);
        changeRatio();
        return l;
    } 
    
    public void changeRatio(){
        Ratio ratio = game.getRatios().get(ratioChoiceChange);
        ratio.changeRatio();
        ratioChoiceChange++;
        if(ratioChoiceChange > game.getRatios().size() - 1){
            ratioChoiceChange = 0;
        }
    }
}
