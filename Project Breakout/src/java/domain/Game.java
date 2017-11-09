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
import java.util.List;

/**
 *
 * @author micha
 */
public class Game {
    private List<Level> levels = new ArrayList<>();
    
    private FactoryLevel factoryLevels;
    private int width;
    private int height;
    
    private int score;
    private int aantalSpelers;
    private int lives; 
    
    private boolean gameOver = false;

    public Game(int score, int height, int width, int lives, int aantalSpelers) {
        this.score = score;
        this.width = width;
        this.height = height;
        this.lives = lives;
        this.aantalSpelers = aantalSpelers;
        this.factoryLevels = new FactoryLevel(this);
        this.factoryLevels.createLevel();
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
