/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.game;

import be.howest.ti.breakout.data.Repositories;


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
    private int XPtoNextLevel;
    private String bio;
    private int spScore;
    private int totalScore;
    private int amountOfGems;
    private int amountOfCoins;
    private String profilePic;
    
    public User(int id, String username, String password, String email, int XP, int lvl, String bio, int spHighscore, int totalScore, int gems, int coins, String pp) {
        this.userId = id;
        this.email = email;
        this.username = username;
        this.hashPassword = password;
        this.level = lvl;
        this.XP = XP;
        this.XPtoNextLevel = 500 * level;
        this.bio = bio;
        this.spScore = spHighscore;
        this.totalScore = totalScore;
        this.amountOfGems = gems;
        this.amountOfCoins = coins;
        this.profilePic = pp;
    }
    
    public User(int id, String username, String password, String email, int XP, int lvl, String bio, int spScore) {
        this(id, username, password, email, XP, lvl, bio, spScore, 0, 0, 0, null);
    }
    
    public User(String username, String password, String email, int XP, int lvl, String bio) {
        this(-1, username, password, email, XP, lvl, bio, 0);
    }
    public User(String username, String password, String email, int XP, int lvl) {
        this(username, password, email, XP, lvl, null);
    }
    public User(String username, String password, String email) {
        this(username, password, email, 0, -1);
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
        Repositories.getUserRepository().updateUserXP(this);
        if(this.XP >= XPtoNextLevel){
            level += 1;
            XPtoNextLevel = 500 * level;
            Repositories.getUserRepository().updateUserLevel(this);
        }
    }

    @Override
    public int getTotalScore() {
        return totalScore;
    }

    @Override
    public void addToTotalScore(int score) {
        this.totalScore += score;
        Repositories.getUserRepository().updateUserTotalScore(this);
    }

    @Override
    public void addToSinglePlayerHighScore(SinglePlayerHighscore sp) {
        sp.setUser(this);
    }

    @Override
    public boolean isGuest() {
        return false;
    }

    public String getProfilePic() {
        return profilePic;
    }
}
