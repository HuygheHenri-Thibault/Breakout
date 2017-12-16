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
public class GameDifficulty {
    private String Name;
    private float ratioChange;
    private int hitChangeBricks;

    public GameDifficulty(String Name, float ratioChange, int hitChangeBricks) {
        this.Name = Name;
        this.ratioChange = ratioChange;
        this.hitChangeBricks = hitChangeBricks;
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

    public int getHitChangeBricks() {
        return hitChangeBricks;
    }

    @Override
    public String toString() {
        return "GameDifficulty{" + "Name=" + Name + '}';
    }
}
