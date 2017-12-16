/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.game;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author micha
 */
public class SinglePlayerGame extends Game{
    
    //hardcoded constructor
    public SinglePlayerGame(int height, int width, int aantalspelers, GameDifficulty difficulty){
        super(height, width, 3, 1, difficulty);
    }
    
    public SinglePlayerGame(User player, int height, int width, GameDifficulty difficulty) {
        super(new ArrayList<User>(Arrays.asList(player)), height, width, 3, 1, difficulty);
    }
    
    
}
