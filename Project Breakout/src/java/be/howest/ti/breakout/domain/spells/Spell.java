/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.spells;

import be.howest.ti.breakout.data.Repositories;
import be.howest.ti.breakout.domain.Ball;
import be.howest.ti.breakout.domain.game.Level;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import be.howest.ti.breakout.domain.effects.Effect;
import be.howest.ti.breakout.domain.game.Player;

/**
 *
 * @author micha
 */
public class Spell{
    private Player player;
    private final Level level;
    
    private String name;
    private ZelfstandigNaamwoord zelfstandigNaamwoord;
    
    private final int originalCooldown;
    private int cooldown;
    private Timer CooldownTimer = new Timer();
    private boolean paused = false;
    
    private static final int CHANCEFORONEWORD = 60;
    private static final int CHANCEFORTWOWORDS = CHANCEFORONEWORD + 30;
    private static final int CHANCEFORTHREEWORDS = CHANCEFORONEWORD + CHANCEFORTWOWORDS + 10;
   
    private SpellStatus status = SpellStatus.READY;

    public Spell(Level level) {
        this.level = level;
        randomizeWords();
        this.originalCooldown = generateCooldown();
        this.cooldown = originalCooldown;
    }
    
    public void setPlayer(Player player){
        this.player = player;
    }
    
    private int generateCooldown(){
        int spellCooldown = 10;
        for (int i = 0; i < getSpellEffects().size(); i++) {
            spellCooldown += 2;
        }
        return spellCooldown;
    }
    
    public void setCoolDown(int cooldown){
        this.cooldown = cooldown;
    }

    public int getCooldown() {
        return cooldown;
    }
    
    public int getOriginalCoolDown(){
        return originalCooldown;
    }
    
    private void randomizeWords(){
        Random generator = new Random(); 
        int numberBetween1And3 = generateNumberOfWords(generator);
        for (int i = 1; i <= numberBetween1And3; i++) {
            if(i == 1){
                this.zelfstandigNaamwoord = fetchNewZelfstandigNaamwoord(generator);
            } else {
                BijvoegelijkNaamwoord bn = fetchNewBijvoegelijkNaamwoord(generator);
                this.zelfstandigNaamwoord.addBijvoegelijkNaamwoord(bn);
            }
        }
        this.name = this.zelfstandigNaamwoord.combineNames();
    }
    
    private int generateNumberOfWords(Random generator){
        int randomNumber = generator.nextInt((100 - 1) + 1) + 1;
        if(randomNumber > 0 && randomNumber <= CHANCEFORONEWORD){
            return 1;
        } else if(randomNumber > CHANCEFORONEWORD && randomNumber <= CHANCEFORTWOWORDS){
            return 2;
        } else if(randomNumber > CHANCEFORTWOWORDS && randomNumber <= CHANCEFORTHREEWORDS){
            return 3;
        }
        return 0;
    }
    
    private ZelfstandigNaamwoord fetchNewZelfstandigNaamwoord(Random generator){
        List<ZelfstandigNaamwoord> zelfstandigeNaamwoorden = Repositories.getSpellRepository().getAllZelfstandigeNaamwoorden();
        int max = (zelfstandigeNaamwoorden.size() - 1);
        int min = 0;
        int randomIndex = generator.nextInt((max - min) + 1) + min;
        return zelfstandigeNaamwoorden.get(randomIndex);
    }
    
    private BijvoegelijkNaamwoord fetchNewBijvoegelijkNaamwoord(Random generator){
        List<BijvoegelijkNaamwoord> bijvoegelijkeNaamwoorden = Repositories.getSpellRepository().getAllBijvoegelijkeNaamwoorden();
        int max = (bijvoegelijkeNaamwoorden.size() - 1);
        int min = 0;
        int randomIndex = generator.nextInt((max - min) + 1) + min;
        while(this.zelfstandigNaamwoord.hasBijvoegelijkNaamwoord(bijvoegelijkeNaamwoorden.get(randomIndex))){
            randomIndex = generator.nextInt((max - min) + 1) + min;
        }
        return bijvoegelijkeNaamwoorden.get(randomIndex);
    }

    public String getName() {
        return name;
    }
    

    public void setReady(){status = SpellStatus.READY; level.updateSpellOfPlayer(player, this); }
    public void setActive(){status = SpellStatus.ACTIVE; level.updateSpellOfPlayer(player, this);}
    public void setDeActive(){status = SpellStatus.DEACTIVE; level.updateSpellOfPlayer(player, this);}
    public void setCoolDown(){status = SpellStatus.COOLDOWN; level.updateSpellOfPlayer(player, this);}
    public SpellStatus getStatus(){return status;}
    
    public void setReadyToCast(){
        if(status == SpellStatus.READY){
            setActive();
        }
    }
    
    public void cast(){
        setEntetiesOfEffect();
        List<Ball> allBallsInLevel = level.getBalls();
        for (Ball ball : allBallsInLevel) {
            ball.setDamage(this.zelfstandigNaamwoord.cast());
        }
        setDeActive();
    } 
    
    public void setEntetiesOfEffect(){
        this.zelfstandigNaamwoord.setEntetiesOfEffect(level, player);
    }
    
    public List<Effect> getSpellEffects(){
        return this.zelfstandigNaamwoord.getEffects();
    }
    
    public void startCooldown(){
        setCoolDown();
        CooldownTimer = new Timer();
        CooldownTimer.scheduleAtFixedRate(new TimerTaskSpell(this), 0, 1000);
    }
    
    public void stopCooldown(){
        CooldownTimer.cancel();
    }
    
    public boolean isPaused(){
        return paused;
    }
    
    public void pause(){
        paused = true;
        for (Effect spellEffect : getSpellEffects()) {
            spellEffect.pause();
        }
    }
    
    public void resume(){
        paused = false;
        for (Effect spellEffect : getSpellEffects()) {
            spellEffect.unpause();
        }
    }
    
    public void cancel(){
        for (Effect spellEffect : getSpellEffects()) {
            if(spellEffect.hasTimer()){
                spellEffect.getTimerEffect().cancel();
            }
        }
    }
}
