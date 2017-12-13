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
    private int cooldown;
    private ZelfstandigNaamwoord zelfstandigNaamwoord;
    private String name;
    
    //status
    private SpellStatus status = SpellStatus.READY;

    public Spell(Level level) {
        this.level = level;
        randomizeWords();
    }
    
    public void setUserID(User user){
        this.user = user;
    }

    public int getCooldown() {
        return cooldown;
    }
    
    private void randomizeWords(){
        Random generator = new Random(); 
        int numberBetween1And3 = generator.nextInt((3 - 1) + 1) + 1;
        for (int i = 1; i <= numberBetween1And3; i++) {
            if(i == 1){
                zelfstandigNaamwoord = fetchNewZelfstandigNaamwoord(generator);
            } else {
                zelfstandigNaamwoord.addBijvoegelijkNaamwoord(fetchNewBijvoegelijkNaamwoord(generator));
            }
        }
        System.out.println(Arrays.toString(zelfstandigNaamwoord.getAllBijvoegelijkNaamwoorden().toArray()));
        this.name = zelfstandigNaamwoord.combineNames();
    }
    
    private ZelfstandigNaamwoord fetchNewZelfstandigNaamwoord(Random generator){
        List<ZelfstandigNaamwoord> zelfstandigeNaamwoorden = Repositories.getSpellRepository().getHardcodedZelfstandigeNaamwoorden();
        int max = (zelfstandigeNaamwoorden.size() - 1);
        int min = 0;
        int randomIndex = generator.nextInt((max - min) + 1) + min;
        return zelfstandigeNaamwoorden.get(randomIndex);
    }
    
    private BijvoegelijkNaamwoord fetchNewBijvoegelijkNaamwoord(Random generator){
        List<BijvoegelijkNaamwoord> bijvoegelijkeNaamwoorden = Repositories.getSpellRepository().getHardCodedBijvoegelijkeNaamwoorden();
        int max = (bijvoegelijkeNaamwoorden.size() - 1);
        int min = 0;
        int randomIndex = generator.nextInt((max - min) + 1) + min;
        while(zelfstandigNaamwoord.hasBijvoegelijkNaamwoord(bijvoegelijkeNaamwoorden.get(randomIndex))){
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
        zelfstandigNaamwoord.setEntetiesOfEffect(level, user.getUserId());
    }
    
    public void setReadyToCast(){
        setActive();
    }
    
    public void cast(){
        setEntetiesOfEffect();
        List<Ball> allBallsInLevel = level.getBalls();
        for (Ball ball : allBallsInLevel) {
            ball.setDamage(zelfstandigNaamwoord.cast());
        }
        setDeActive();
    }
    
    public void startCooldown(){
        setCoolDown();
        Timer t = new Timer();
        t.schedule(new TimerTaskSpell(this), 0, 1000);
    }
    
    public void resetSpell(){
        zelfstandigNaamwoord.resetEffect();
    }
    
    public List<Effect> getSpellEffects(){
        return zelfstandigNaamwoord.getEffects();
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
