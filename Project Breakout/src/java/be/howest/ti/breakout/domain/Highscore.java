/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain;

/**
 *
 * @author Henri
 */
public class Highscore {
    private final int highscore;
    private final User userWithHighscore;
    
    public Highscore(User user, int highscore) {
        this.userWithHighscore = user;
        this.highscore = highscore;
    }

    public int getHighscore() {
        return highscore;
    }

    public User getUserWithHighscore() {
        return userWithHighscore;
    }
}
