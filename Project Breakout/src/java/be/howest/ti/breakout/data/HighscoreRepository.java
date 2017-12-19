/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.data;

import be.howest.ti.breakout.domain.game.Highscore;
import be.howest.ti.breakout.domain.game.SinglePlayerHighscore;
import be.howest.ti.breakout.domain.game.User;
import java.util.List;

/**
 *
 * @author Henri
 */
public interface HighscoreRepository {
    public List<SinglePlayerHighscore> getAllSingleplayerHighscores();
    public SinglePlayerHighscore getUserHighscore(User u);
    public void updateSinglePlayerHighscore(SinglePlayerHighscore sph);
    public void removeHighscore(Highscore h);
}
