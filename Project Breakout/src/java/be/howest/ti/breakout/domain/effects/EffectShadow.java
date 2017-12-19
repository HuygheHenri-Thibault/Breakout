/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.effects;

import be.howest.ti.breakout.domain.Brick;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

/**
 *
 * @author Fredr
 */
public class EffectShadow extends Effect{
    private int palletIdSetInvisible;

    public EffectShadow(String name, int duration) {
        super(name, duration);
    }

    @Override
    public void activate() {
        System.out.println("activated shadow");
        Random generator = new Random();
        int chance = generator.nextInt((20 - 1) + 0) + 1;
        List<Integer> bricksNumbers = generateBrickNumbers(10, 20);
        if(chance == 20){
            for (Integer brickIndex : bricksNumbers) {
                int randomDamage = generator.nextInt((5 - 1) + 1) + 1;
                getLevelOfEffect().getBricks().get(brickIndex).decrementHits(randomDamage);
            }
        } else {
            if(getLevelOfEffect().getPallets().size() == 1){
                palletIdSetInvisible = 0;
            } else {
                palletIdSetInvisible = generator.nextInt(((getLevelOfEffect().getPallets().size() - 1) - 0) + 0) + 0;
            }
            getLevelOfEffect().getPallets().get(palletIdSetInvisible).setInvisible();
            setTimerEffect(new Timer());
            getTimerEffect().scheduleAtFixedRate(new TimerTaskEffect(this), 0, 1000);
        }
    }

    @Override
    public void deActivate() {
        System.out.println("deactivated shadow");
        getLevelOfEffect().getPallets().get(palletIdSetInvisible).setVisible();
        setDone();
    }
    
    
    private List<Integer> generateBrickNumbers(int min, int max){
        List<Integer> randomIndexOfBricks = new ArrayList<>();
        Random generator = new Random();
        int numberOfBrickIndexChoices = generator.nextInt((max - min) + 0) + min;
        for (int i = 0; i < numberOfBrickIndexChoices; i++) {
            int randomChoiceOfBrickIndex = generator.nextInt((getLevelOfEffect().getBricks().size() - 0) + 0) + 0;
            if(!randomIndexOfBricks.contains(randomChoiceOfBrickIndex)){
                randomIndexOfBricks.add(randomChoiceOfBrickIndex);
            }
        }
        return randomIndexOfBricks;
    }
}
