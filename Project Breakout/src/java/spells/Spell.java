/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spells;

import data.Repositories;
import domain.Ball;
import domain.Level;
import domain.User;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import powerUps.Effect;

/**
 *
 * @author micha
 */
public class Spell{
    private User user;
    private Level level;
    private int originalCooldown;
    private int cooldown;
    private Timer CooldownTimer = new Timer();
    private ZelfstandigNaamwoord zelfstandigNaamwoord;
    private String name;
    
    //status
    private SpellStatus status = SpellStatus.READY;

    public Spell(Level level) {
        this.level = level;
        randomizeWords();
        this.originalCooldown = generateCooldown();
        this.cooldown = originalCooldown;
    }
    
    public void setUserID(User user){
        this.user = user;
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
    
    private int generateCooldown(){
        int spellCooldown = 10;
        for (int i = 0; i < getSpellEffects().size(); i++) {
            spellCooldown += 2;
        }
        return spellCooldown;
    }
    
    private void randomizeWords(){
        Random generator = new Random(); 
        int numberBetween1And3 = generator.nextInt((3 - 1) + 1) + 1;
        for (int i = 1; i <= numberBetween1And3; i++) {
            if(i == 1){
                this.zelfstandigNaamwoord = fetchNewZelfstandigNaamwoord(generator);
            } else {
                BijvoegelijkNaamwoord bn = fetchNewBijvoegelijkNaamwoord(generator);
                while(zelfstandigNaamwoord.getAllBijvoegelijkNaamwoorden().contains(bn)){
                    bn = fetchNewBijvoegelijkNaamwoord(generator);
                }
                this.zelfstandigNaamwoord.addBijvoegelijkNaamwoord(bn);
            }
        }
        //System.out.println(Arrays.toString(this.zelfstandigNaamwoord.getAllBijvoegelijkNaamwoorden().toArray()));
        this.name = this.zelfstandigNaamwoord.combineNames();
    }
    
    private ZelfstandigNaamwoord fetchNewZelfstandigNaamwoord(Random generator){
        Repositories repos = new Repositories(); //nadat database is gefixt mag dit weg;
        List<ZelfstandigNaamwoord> zelfstandigeNaamwoorden = repos.getSpellRepository().getHardcodedZelfstandigeNaamwoorden();
        int max = (zelfstandigeNaamwoorden.size() - 1);
        int min = 0;
        int randomIndex = generator.nextInt((max - min) + 1) + min;
        return zelfstandigeNaamwoorden.get(randomIndex);
    }
    
    private BijvoegelijkNaamwoord fetchNewBijvoegelijkNaamwoord(Random generator){
        Repositories repos = new Repositories(); //nadat database is gefixt mag dit weg;
        List<BijvoegelijkNaamwoord> bijvoegelijkeNaamwoorden = repos.getSpellRepository().getHardCodedBijvoegelijkeNaamwoorden();
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
    

    
    public void setReady(){status = SpellStatus.READY; level.updateSpellOfUser(user, this); }
    public void setActive(){status = SpellStatus.ACTIVE; level.updateSpellOfUser(user, this);}
    public void setDeActive(){status = SpellStatus.DEACTIVE; level.updateSpellOfUser(user, this);}
    public void setCoolDown(){status = SpellStatus.COOLDOWN; level.updateSpellOfUser(user, this);}
    public SpellStatus isActivated(){return status;}
    
    public void setEntetiesOfEffect(){
        this.zelfstandigNaamwoord.setEntetiesOfEffect(level, user.getUserId());
    }
    
    public void setReadyToCast(){
        setActive();
    }
    
    public void cast(){
        setEntetiesOfEffect();
        List<Ball> allBallsInLevel = level.getBalls();
        for (Ball ball : allBallsInLevel) {
            ball.setDamage(this.zelfstandigNaamwoord.cast());
        }
        setDeActive();
    } 
    
    public void startCooldown(){
        setCoolDown();
        CooldownTimer = new Timer();
        CooldownTimer.schedule(new TimerTaskSpell(this), 0, 1000);
    }
    
    public void stopCooldown(){
        CooldownTimer.cancel();
    }
    
//    public void resetSpell(){
//        zelfstandigNaamwoord.resetEffect();
//    }
    
    public List<Effect> getSpellEffects(){
        return this.zelfstandigNaamwoord.getEffects();
    } 
    
    //voor swing
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            if(status == SpellStatus.READY){
                setReadyToCast();
            }
        }
    }
    //
}
