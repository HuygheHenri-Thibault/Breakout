/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain;

import java.util.List;

/**
 *
 * @author micha
 */
public class MultiPlayerGame extends Game {

    //hardcoded constructor
    public MultiPlayerGame(int height, int width, int aantalspelers, GameDifficulty difficulty){
        super(height, width, 2 * aantalspelers, aantalspelers, difficulty);
    }
    
    public MultiPlayerGame(List<User> players, int height, int width, int aantalSpelers, GameDifficulty difficulty) {
        super(players, height, width, 2 * aantalSpelers, aantalSpelers, difficulty);
    }
    
}
