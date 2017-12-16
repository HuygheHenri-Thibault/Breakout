/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.data;

import domain.Highscore;
import domain.User;
import java.util.List;

/**
 *
 * @author Henri
 */
public interface HighscoreRepository {
    public List<Highscore> getAllHighscores();
    public Highscore getUserHighscore(User u);
    public void addHighscore(Highscore h);
    public void removeHighscore(Highscore h);
}
