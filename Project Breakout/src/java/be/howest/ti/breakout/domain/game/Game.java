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
import java.util.ListIterator;
import java.util.Map;

/**
 *
 * @author micha
 */
public class Game{
    
    private List<Player> players;
    private final int numberOfPlayers; 
        
    private List<Level> levels = new ArrayList<>();
    private Level levelPlayedRightNow;
    private final FactoryLevel factoryLevels;

    private final int width;
    private final int height;
    
    private final GameDifficulty difficulty;
    private List<Ratio> ratios = new ArrayList<>();
    
    private Map<Player, Integer> scorePerPlayer;
    
    private int livesLeftOriginally;
    private int lives; 
    private boolean gameOver = false;
    

    public Game(int height, int width, int aantalSpelers, GameDifficulty difficulty) {
        this.players = initializePlayers(aantalSpelers);
        initializePlayerScores();
        this.width = width;
        this.height = height;
        this.lives = 3 * players.size();
        this.livesLeftOriginally = this.lives;
        this.numberOfPlayers = players.size();
        this.difficulty = difficulty;
        addRatiosToGame(difficulty);
        this.factoryLevels = new FactoryLevel(this);
    }
    
    public final List<Player> initializePlayers(int aantalSpelers){
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < aantalSpelers; i++) {
            Player guest = Repositories.getUserRepository().getGuest((i + 1));
            guest.setPlayerID((i + 1));
            players.add(guest);
        }
        return players;
    }
    
    public void replaceGuestByUser(int spelerID, Player player){
        int newPlayerID = players.get(spelerID - 1).getPlayerID();
        player.setPlayerID(newPlayerID);
        players.set(spelerID - 1, player);
        initializePlayerScores();
        levelPlayedRightNow.replacePlayer(spelerID, player);
        levelPlayedRightNow.initializePlayerScores();
    }
    
    public List<Player> getPlayers() {
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
    
    public final void initializePlayerScores(){
        scorePerPlayer = new HashMap<>();
        for (Player player : players) {
            scorePerPlayer.put(player, 0);
        }
    }
    
    public Map<Player, Integer> getPlayerScores(){
        return scorePerPlayer;
    }

    public int getTotalGameScore() {
        int sum = 0;
        for (Map.Entry<Player, Integer> entry : scorePerPlayer.entrySet()) {
            sum += entry.getValue();
        }
        return sum;
    }
    
    public int getTotalScoreOfPlayer(Player player){
        return scorePerPlayer.get(player);
    }

    public void addToTotalScoreDuringGame(Player player, int TotalGameScore) {
        scorePerPlayer.merge(player, TotalGameScore, Integer::sum);
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
            setGameOver(true);
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
        for (Map.Entry<Player, Integer> entry : scorePerPlayer.entrySet()) {
            entry.getKey().addToTotalScore(entry.getValue());
        }
        for (Map.Entry<Player, Integer> entry : scorePerPlayer.entrySet()) {
            entry.getKey().addXP(entry.getValue() / 2);
        }
        insertHighscore();
    }
    
    public void insertHighscore(){
        if (this.getNumberOfPlayers() == 1){
            Player thePlayer = players.get(0);
            int scoreOfThePlayer = scorePerPlayer.get(thePlayer);
            SinglePlayerHighscore sph = new SinglePlayerHighscore();
            thePlayer.addToSinglePlayerHighScore(sph);
            sph.setScore(scoreOfThePlayer);
            Repositories.getHighscoreRepository().updateSinglePlayerHighscore(sph);
        }else{
            MultiPlayerHighscore mph = new MultiPlayerHighscore(scorePerPlayer);
            if(allPlayerAreGuest()){
                int mphGeneratedID = Repositories.getHighscoreRepository().insertScoreIntoMultiplayerScores(mph.getTotalScore());
                for (Map.Entry<Player, Integer> entry : scorePerPlayer.entrySet()) {
                    Repositories.getHighscoreRepository().insertPlayerScoresForMultiplayer(entry.getKey(), mphGeneratedID, entry.getValue());
                }
            }
        }
    }
    
    public boolean allPlayerAreGuest(){
        for (Player player : players) {
            if(!player.isGuest()){
                return false;
            }
        }
        return true;
    }
}
