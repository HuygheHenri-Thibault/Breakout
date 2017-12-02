/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import factories.FactoryBall;
import factories.FactoryLevel;
import factories.FactoryPallet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author micha
 */
public class Game{
    private List<User> players;
    private List<Level> levels = new ArrayList<>();
    private Level levelPlayedRightNow;
    
    private FactoryLevel factoryLevels;

    private int width;
    private int height;
    
    private int score = 0;
    private int aantalSpelers;
    private int startLives;
    private int lives; 
    private List<Ratio> ratios = new ArrayList<>();
    
    private boolean gameOver = false;

    public Game(List<User> players, int height, int width, int lives, int aantalSpelers) {
        this.players = players;
        this.width = width;
        this.height = height;
        this.startLives = lives;
        this.lives = startLives;
        this.aantalSpelers = aantalSpelers;
        addRatiosToGame();
        this.factoryLevels = new FactoryLevel(this);
        createNewLevel();
    }
    
    public List<User> getPlayers() {
        return players;
    }
    
    public void stopGame() {
        gameOver = true;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Level> getLevels() {
        return levels;
    }
    
    public void createNewLevel(){
        levelPlayedRightNow = factoryLevels.createLevel();
    }
    
    public Level getLevelPlayedRightNow() {
        return levelPlayedRightNow;
    }

    public void setLevelPlayedRightNow(Level levelPlayedRightNow) {
        this.levelPlayedRightNow = levelPlayedRightNow;
    }

    public FactoryLevel getFactoryLevels() {
        return factoryLevels;
    }

    public void setFactoryLevels(FactoryLevel factoryLevels) {
        this.factoryLevels = factoryLevels;
    }
    
    public List<Ratio> getRatios() {
        return ratios;
    }
    
    public final void addRatiosToGame(){
       ratios.add(new Ratio("Pallet", -0.01f));
       ratios.add(new Ratio("Ball", +0.1f));
       ratios.add(new Ratio("Power up", -0.01f));
       ratios.add(new Ratio("Power down", +0.01f));
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public void setLives(int lives) {
        this.lives = lives;
    }
     
    public int getLives() {
        return lives;
    }
    
    public int getStartLives() {
        return startLives;
    }

    public int getAantalSpelers() {
        return aantalSpelers;
    }
    
    public void decrementLife(){
        lives--;
        if(lives == 0){
            stopGame();
        }
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isGameOver() {
        return gameOver;
    }
   
}
