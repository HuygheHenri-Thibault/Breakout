/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.game;

/**
 *
 * @author micha
 */
public final class GameDifficulty {
    private String Name;
    private float ratioChange;
    private final int changeForBricks;

    public GameDifficulty(String Name, float ratioChange, int changeForBricks) {
        this.Name = Name;
        this.ratioChange = ratioChange;
        this.changeForBricks = changeForBricks;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public float getRatioChange() {
        return ratioChange;
    }

    public void setRatioChange(float ratioChange) {
        this.ratioChange = ratioChange;
    }

    public int getChangeForBricks() {
        return changeForBricks;
    }

    @Override
    public String toString() {
        return "GameDifficulty{" + "Name=" + Name + '}';
    }
}
