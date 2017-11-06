/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import factories.FactoryRowOfBricks;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author micha
 */
public class Level {
    private FactoryRowOfBricks factoryBrick;
    private List<BrickRow> rowsOfBricks;
    private int number;
    private int levens;
    private int score;
    private final static int MAX_ROWS_BRICKS = 5;

    public Level(int number, int levens, int score) {
        this.factoryBrick = new FactoryRowOfBricks(this);
        this.rowsOfBricks = factoryBrick.createRowOfBricks();
        this.number = number;
        this.levens = levens;
        this.score = score;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setLevens(int levens) {
        this.levens = levens;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public int getNumber() {
        return number;
    }

    public int getLevens() {
        return levens;
    }

    public int getScore() {
        return score;
    }
    
     public List<BrickRow> getRowOfBricks() {
        return rowsOfBricks;
    }

    public int getMAX_ROWS_BRICKS() {
        return MAX_ROWS_BRICKS;
    }
    
}
