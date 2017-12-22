/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.game;

import be.howest.ti.breakout.domain.spells.Spell;

/**
 *
 * @author Henri
 */
public class User implements Player{
    private int userId;
    private int playerID;
    private String email;
    private String username;
    private String hashPassword;
    private int XP;
    private int level;
    private final int XPtoNextLevel = 500 * level;
    private String bio;
    private int spScore;
    private int totalScore;
    private int amountOfGems;
    private int amountOfCoins;
    
    public User(int id, String username, String password, String email, int lvl, String bio, int spHighscore, int totalScore, int gems, int coins) {
        this.userId = id;
        this.email = email;
        this.username = username;
        this.hashPassword = password;
        this.level = lvl;
        this.bio = bio;
        this.totalScore = 0;
        this.spScore = spHighscore;
        this.totalScore = totalScore;
        this.amountOfGems = gems;
        this.amountOfCoins = coins;
    }
    
    public User(int id, String username, String password, String email, int lvl, String bio, int spScore) {
        this(id, username, password, email, lvl, bio, spScore, 0, 0, 0);
    }
    
    public User(String username, String password, String email, int lvl, String bio) {
        this(-1, username, password, email, lvl, bio, 0);
    }
    public User(String username, String password, String email, int lvl) {
        this(username, password, email, lvl, null);
    }
    public User(String username, String password, String email) {
        this(username, password, email, -1);
    }
    public User(String username, String password) {
        this(username, password, null);
    }
    
    public User(){
        this(null, null);
    }
    
    public int getUserId() {
        return userId;
    }
    
    @Override
    public void setPlayerID(int playerid) {
        this.playerID = playerid;
    }

    @Override
    public int getPlayerID() {
        return playerID;
    }

    public String getUsername() {
        return username;
    }
    
    @Override
    public String getName() {
        return getUsername();
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public String getEmail() {
        return email;
    }

    public int getLevel() {
        return level;
    }

    public String getBio() {
        return bio;
    }
    
    public int getSinglePlayerScore() {
        return spScore;
    }

    public int getAmountOfGems() {
        return amountOfGems;
    }

    public int getAmountOfCoins() {
        return amountOfCoins;
    }

    @Override
    public int getXP() {
        return XP;
    }

    @Override
    public void addXP(int XP) {
        this.XP += XP;
        if(XP >= XPtoNextLevel){
            level += 1;
        }
    }

    @Override
    public int getTotalScore() {
        return totalScore;
    }

    @Override
    public void addToTotalScore(int score) {
        this.totalScore += score;
    }

    @Override
    public void addToSinglePlayerHighScore(SinglePlayerHighscore sp) {
        sp.setUser(this);
    }

    @Override
    public boolean isGuest() {
        return false;
    }

}
