/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.data;

import be.howest.ti.breakout.domain.game.GameDifficulty;
import java.util.List;

/**
 *
 * @author micha
 */
public interface DifficultyRepository {
    public List<GameDifficulty> getAllDifficulties();
    public GameDifficulty getDifficultyByID(int id);
    public GameDifficulty getDifficultyByName(String name);
}
