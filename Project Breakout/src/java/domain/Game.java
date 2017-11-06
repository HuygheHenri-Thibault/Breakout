/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

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
    private int score;

    public Game(int score) {
        this.score = score;
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
}
