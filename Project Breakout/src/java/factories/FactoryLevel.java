/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factories;

import domain.Game;
import domain.Level;
import domain.Ratio;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author micha
 */
public class FactoryLevel {
    private final Game game;
    private int numberLevels;
    private int startScoreForBricksFirstLine = 50;
    private int ratioChoiceChange = 0;

    public FactoryLevel(Game game) {
        this.game = game;
        this.numberLevels = 0;
    }
     
    public Level createLevel(){
        numberLevels++;
        Level l = new Level(game, startScoreForBricksFirstLine, numberLevels);
        game.getLevels().add(l);
        changeRatio();
        startScoreForBricksFirstLine += 10;
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
