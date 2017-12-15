/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author micha
 */
public class GameDifficulty {
    private String Name;
    private float ratioChange;

    public GameDifficulty(String Name, float ratioChange) {
        this.Name = Name;
        this.ratioChange = ratioChange;
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

    @Override
    public String toString() {
        return "GameDifficulty{" + "Name=" + Name + '}';
    }
}
