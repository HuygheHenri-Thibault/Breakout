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
public final class Ratio {
    private String elementForChange;
    private float ratio = 1.00f;
    private float changeAfterLevel;

    public Ratio(String elementForChange, float changeAfterLevel, GameDifficulty difficuly) {
        this.elementForChange = elementForChange;
        this.changeAfterLevel = changeAfterLevel;
        changeRatioFromDifficulty(difficuly);
    }

    public String getElementForChange() {
        return elementForChange;
    }

    public float getRatio() {
        return ratio;
    }

    public void qetChangeAfterLevel(float changeAfterLevel) {
        this.changeAfterLevel = changeAfterLevel;
    }
    
    public void changeRatio(){
        ratio = ratio + changeAfterLevel;
    }
    
    public void changeRatioFromDifficulty(GameDifficulty difficulty){
        float ratioChangeDifficulty = difficulty.getRatioChange();
        if(elementForChange.equals("Ball")){
            ratioChangeDifficulty = -ratioChangeDifficulty;
        }
        this.ratio += ratioChangeDifficulty;
    }
    
}
