/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.game;

/**
 *
 * @author micha
 */
public interface Player {
    public String getName();
    public void addToSinglePlayerHighScore(SinglePlayerHighscore sp);
    public void setPlayerID(int playerid);
    public boolean isGuest();
    public int getPlayerID();
    public void addXP(int XP);
    public int getXP();
    public void addToTotalScore(int score);
    public int getTotalScore();
}
