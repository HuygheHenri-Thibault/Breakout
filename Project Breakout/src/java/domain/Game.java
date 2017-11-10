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
    private List<Level> levels = new ArrayList<>();
    
    private FactoryLevel factoryLevels;

    private int width;
    private int height;
    
    private int score = 0;
    private int aantalSpelers;
    private int lives; 
    private List<Ratio> ratios = new ArrayList<>();
    
    private boolean gameOver = false;

    public Game(int height, int width, int lives, int aantalSpelers) {
        this.width = width;
        this.height = height;
        this.lives = lives;
        this.aantalSpelers = aantalSpelers;
        addRatiosToGame();
        this.factoryLevels = new FactoryLevel(this);
        this.factoryLevels.createLevel();
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
        factoryLevels.createLevel();
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

    public int getAantalSpelers() {
        return aantalSpelers;
    }
    
    public void decrementLife(){
        lives--;
        if(lives == 0){
            setGameOver(true);
        }
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isGameOver() {
        return gameOver;
    }
   
}
