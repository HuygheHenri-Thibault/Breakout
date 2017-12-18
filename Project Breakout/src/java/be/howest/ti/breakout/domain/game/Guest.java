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
public final class Guest extends User{
    //userID
    
    @Override
    public String getUsername() {
        return "guest " + getUserId();
    }

    @Override
    public String getHashPassword() {
        return null;
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public String getBio() {
        return "";
    }
    
    @Override
    public void addToTotalScore(int score) {}
    
    @Override
    public void addXP(int XP) {}
    
    @Override
    public int getXP() {
        return 0;
    }
}
