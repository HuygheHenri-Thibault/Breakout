/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import factories.FactoryBall;
import factories.FactoryPallet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author micha
 */
public class Game {
    private List<Level> levels = new ArrayList<>();
    private List<Pallet> pallets = new ArrayList<>();
    private List<Ball> balls = new ArrayList<>();
    private FactoryPallet factoryPallet;
    private FactoryBall factoryBall;
    private int score;
    private int aantalSpelers;
    private final static int MIN_GAME_BORDER_X = 0;
    private final static int MAX_GAME_BORDER_X = 1000;
    private final static int MIN_GAME_BORDER_Y = 0;
    private final static int MAX_GAME_BORDER_Y = 1000;

    public Game(int score, int aantalSpelers) {
        this.score = score;
        this.aantalSpelers = aantalSpelers;
        this.factoryPallet = new FactoryPallet(this);
        this.factoryPallet.createPallets();
        this.factoryBall = new FactoryBall(this);
        this.factoryBall.createBall();
    }

    public List<Level> getLevels() {
        return levels;
    }

    public List<Pallet> getPallets() {
        return pallets;
    }

    public List<Ball> getBalls() {
        return balls;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAantalSpelers() {
        return aantalSpelers;
    }

    public int getMIN_GAME_BORDER_X() {
        return MIN_GAME_BORDER_X;
    }

    public int getMAX_GAME_BORDER_X() {
        return MAX_GAME_BORDER_X;
    }

    public int getMIN_GAME_BORDER_Y() {
        return MIN_GAME_BORDER_Y;
    }

    public int getMAX_GAME_BORDER_Y() {
        return MAX_GAME_BORDER_Y;
    }
   
}
