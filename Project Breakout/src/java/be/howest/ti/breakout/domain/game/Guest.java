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
public final class Guest implements Player{
    
    private int playerID;
    private String placeHolderName;

    public Guest(String placeHolderName) {
        this.placeHolderName = placeHolderName;
    }
    
    public Guest(int playerID, String placeHolderName) {
        this.playerID = playerID;
        this.placeHolderName = placeHolderName;
    }
    
    @Override
    public String getName() {
        return getPlaceHolderName();
    }


    public String getPlaceHolderName() {
        return placeHolderName;
    }

    public void setPlaceHolderName(String placeHolderName) {
        this.placeHolderName = placeHolderName;
    }
        
    @Override
    public void addToTotalScore(int score) {}
    
    @Override
    public int getTotalScore() {return 0;}
    
    @Override
    public void addXP(int XP) {}

    @Override
    public int getXP() {return 0;}

    @Override
    public void setPlayerID(int playerid) {
        this.playerID = playerid;
    }

    @Override
    public int getPlayerID() {
        return playerID;
    }

    @Override
    public void addToSinglePlayerHighScore(SinglePlayerHighscore sp) {}

    @Override
    public boolean isGuest() {
        return true;
    }

}
