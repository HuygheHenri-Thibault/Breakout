/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.game;

import java.util.List;

/**
 *
 * @author micha
 */
public final class MultiPlayerGame extends Game { // mss overbodige klasse

    //hardcoded constructor
    public MultiPlayerGame(int height, int width, int numberOfPlayers, GameDifficulty difficulty){
        super(height, width, 6, numberOfPlayers, difficulty);
    }
    
    public MultiPlayerGame(List<User> players, int height, int width, GameDifficulty difficulty) {
        super(players, height, width, 2 * players.size(), difficulty);
    }
    
}
