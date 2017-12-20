/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.game;

import be.howest.ti.breakout.data.Repositories;
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
    User me = new User(1, "coolboi", "blabla", "hipitiehoppitie", 99, "pepe", 0);
    User me1 = new User(2, "coolboi2", "blabla2", "hipitiehoppitie", 99, "pepe", 0);
    User me2 = new User(3, "coolboi3", "blabla3", "hipitiehoppitie", 99, "pepe", 0);
    User me3 = new User(4, "coolboi4", "blabla4", "hipitiehoppitie", 99, "pepe", 0);
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
    private Map<User, Integer> scorePerUser;
    
    private int livesLeftOriginally; // for sudden death powerdown
    private int lives; 
    private boolean gameOver = false;
    
//    //hardcoded constructor
//    public Game(int height, int width, int lives, int numberOfPlayers, GameDifficulty difficulty){
//        switch(numberOfPlayers){
//            case 1:
//                this.players = new ArrayList<>(Arrays.asList(me));
//                break;
//            case 2:
//                this.players = new ArrayList<>(Arrays.asList(me, me1));
//                break;
//            case 3:
//                this.players = new ArrayList<>(Arrays.asList(me, me1, me2));
//                break;
//            case 4:
//                this.players = new ArrayList<>(Arrays.asList(me, me1, me2, me3));
//                break;
//        }
//        initializeUserScores();
//        this.width = width;
//        this.height = height;
//        this.lives = 3 * players.size();
//        this.livesLeftOriginally = lives;
//        this.numberOfPlayers = players.size();
//        this.difficulty = difficulty;
//        addRatiosToGame(difficulty);
//        this.factoryLevels = new FactoryLevel(this);
//    }
    

    public Game(int height, int width, int aantalSpelers, GameDifficulty difficulty) {
        this.players = initializeUsers(aantalSpelers);
        initializeUserScores();
        this.width = width;
        this.height = height;
        this.lives = 3 * players.size();
        this.livesLeftOriginally = this.lives;
        this.numberOfPlayers = players.size();
        this.difficulty = difficulty;
        addRatiosToGame(difficulty);
        this.factoryLevels = new FactoryLevel(this);
    }
    
    public final List<User> initializeUsers(int aantalSpelers){
        List<User> users = new ArrayList<>();
        for (int i = 0; i < aantalSpelers; i++) {
            users.add(new Guest());
        }
        return users;
    }
    
    public void replaceGuestByUser(int spelerID, User u){
        players.set(spelerID, u);
        initializeUserScores();
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

    
    public List<Ratio> getRatios() {
        return ratios;
    }
    
    public final void addRatiosToGame(GameDifficulty difficulty){
       ratios.add(new Ratio("Pallet", -0.01f, difficulty));
       ratios.add(new Ratio("Ball", +0.1f, difficulty));
       ratios.add(new Ratio("Power up And Down", -0.1f, difficulty));
    }
    
    public final void initializeUserScores(){
        scorePerUser = new HashMap<>();
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
        insertHighscore();
    }
    
    public void insertHighscore(){
        if (this.getNumberOfPlayers() == 1){
            User thePlayer = players.get(0);
            int scoreOfTheUser = scorePerUser.get(thePlayer);
            SinglePlayerHighscore sph = new SinglePlayerHighscore(thePlayer, scoreOfTheUser);
        }else{
            MultiPlayerHighscore mph = new MultiPlayerHighscore(scorePerUser);
        }
    }
}
