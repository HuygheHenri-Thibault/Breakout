/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.game;

import be.howest.ti.breakout.factories.FactoryLevel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author micha
 */
public class Game{
    //hardcoded Users
    User me = new User(1, "coolboi", "blabla", "hipitiehoppitie", 99, "pepe");
    User me1 = new User(2, "coolboi2", "blabla2", "hipitiehoppitie", 99, "pepe");
    User me2 = new User(3, "coolboi3", "blabla3", "hipitiehoppitie", 99, "pepe");
    User me3 = new User(4, "coolboi4", "blabla4", "hipitiehoppitie", 99, "pepe");
    //
    
    private List<User> players;
    private final int numberOfPlayers; // just take size of players
        
    private List<Level> levels = new ArrayList<>();
    private Level levelPlayedRightNow;
    private final FactoryLevel factoryLevels;

    private final int width;
    private final int height;
    
    private final GameDifficulty difficulty;
    private List<Ratio> ratios = new ArrayList<>();
    
    //private int TotalGameScore = 0; // can just get this by taking total score of scoreperUser;
    private Map<User, Integer> scorePerUser = new HashMap<User, Integer>();
    
    private int livesLeftOriginally; // for sudden death powerdown
    private int lives; 
    private boolean gameOver = false;
    
    //hardcoded constructor
    public Game(int height, int width, int lives, int numberOfPlayers, GameDifficulty difficulty){
        switch(numberOfPlayers){
            case 1:
                this.players = new ArrayList<>(Arrays.asList(me));
                break;
            case 2:
                this.players = new ArrayList<>(Arrays.asList(me, me1));
                break;
            case 3:
                this.players = new ArrayList<>(Arrays.asList(me, me1, me2));
                break;
            case 4:
                this.players = new ArrayList<>(Arrays.asList(me, me1, me2, me3));
                break;
        }
        initializeUserScores();
        this.width = width;
        this.height = height;
        this.lives = 3 * players.size();
        this.livesLeftOriginally = lives;
        this.numberOfPlayers = players.size();
        this.difficulty = difficulty;
        addRatiosToGame(difficulty);
        this.factoryLevels = new FactoryLevel(this);
        //createNewLevel();
    }
    //
    

    public Game(List<User> players, int height, int width, int lives, GameDifficulty difficulty) {
        this.players = players;
        initializeUserScores();
        this.width = width;
        this.height = height;
        this.lives = 3 * players.size();
        this.livesLeftOriginally = this.lives;
        this.numberOfPlayers = players.size();
        this.difficulty = difficulty;
        addRatiosToGame(difficulty);
        this.factoryLevels = new FactoryLevel(this);
        //createNewLevel();
    }
    
    public List<User> getPlayers() {
        return players;
    }
    
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
    
    public List<Level> getLevels() {
        return levels;
    }
    
    public final void createNewLevel(){
        levelPlayedRightNow = factoryLevels.createLevel();
    }

    public Level getLevelPlayedRightNow() {
        return levelPlayedRightNow;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public GameDifficulty getDifficulty() {
        return difficulty;
    }

    

//    public void setLevelPlayedRightNow(Level levelPlayedRightNow) {
//        this.levelPlayedRightNow = levelPlayedRightNow;
//    }

//    public FactoryLevel getFactoryLevels() {
//        return factoryLevels;
//    }

//    public void setFactoryLevels(FactoryLevel factoryLevels) {
//        this.factoryLevels = factoryLevels;
//    }
    
    public List<Ratio> getRatios() {
        return ratios;
    }
    
    public final void addRatiosToGame(GameDifficulty difficulty){
       ratios.add(new Ratio("Pallet", -0.01f, difficulty));
       ratios.add(new Ratio("Ball", +0.1f, difficulty));
       ratios.add(new Ratio("Power up And Down", -0.1f, difficulty));
       //ratios.add(new Ratio("Power down", +0.01f));
    }
    
    public final void initializeUserScores(){
        for (User player : players) {
            scorePerUser.put(player, 0);
        }
    }
    
    public Map<User, Integer> getUserScores(){
        return scorePerUser;
    }

    public int getTotalGameScore() {
        int sum = 0;
        for (Map.Entry<User, Integer> entry : scorePerUser.entrySet()) {
            sum += entry.getValue();
        }
        return sum;
    }
    
    public int getTotalScoreOfUser(User user){
        return scorePerUser.get(user);
    }

    public void addToTotalScoreDuringGame(User user, int TotalGameScore) {
        scorePerUser.merge(user, TotalGameScore, Integer::sum);
    }
    
//    public final HashMap generateLives(){
//        HashMap livesForUsers = new HashMap();
//        for (User player : players) {
//            livesForUsers.put(player, 3);
//        }
//        return livesForUsers;
//    }
    
//    public final HashMap copyLives(){
//        HashMap copiedLives = new HashMap();
//        for (Map.Entry<User, Integer> entry : lives.entrySet()) {
//            copiedLives.put(entry.getKey(), entry.getValue());
//        }
//        return copiedLives;
//    }
    
    public void setLives(int lives) {
        this.lives = lives;
    }
    
    public int getLives() {
        return lives;
    }
     
    public int getLivesLeftOriginally() {
        return livesLeftOriginally;
    }

    public void decrementLife(){
        lives--;
        livesLeftOriginally--;
        if(lives <= 0){
            stopGame();
        }
    }
//    public void decrementLifeOfPlayers(List<User> players){
//        for (User player : players) {
//            int currentLivesOfPlayer = lives.get(player);
//            lives.put(player, currentLivesOfPlayer - 1);
//            int currentOriginalLivesOfPlayer = livesLeftOriginally.get(player);
//            livesLeftOriginally.put(player, currentOriginalLivesOfPlayer-1);
//            if(userLostAllLives(player)){
//                getLevelPlayedRightNow().removePalletFromLevelOfUser(player);
//            }
//        }
//        if(allLivesOfPlayersLost()){
//            stopGame();
//        }
//    }
    
//    public boolean userLostAllLives(User player){
//        return lives.get(player) <= 0;
//    }
//    
//    public boolean allLivesOfPlayersLost(){
//        for (User player : players) {
//            if(lives.get(player) > 0){
//                return false;
//            }
//        }
//        return true;
//    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isGameOver() {
        return gameOver;
    }
    
    public void stopGame() {
        levelPlayedRightNow.endLevel();
        setGameOver(true);
        for (Map.Entry<User, Integer> entry : scorePerUser.entrySet()) {
            entry.getKey().addToTotalScore(entry.getValue());
        }
        for (Map.Entry<User, Integer> entry : scorePerUser.entrySet()) {
            entry.getKey().addXP(entry.getValue() / 2);
        }
        //add Highscores here
        //check players to see which type of highscore
    }
}
