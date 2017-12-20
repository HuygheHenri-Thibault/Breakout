/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.game;

import be.howest.ti.breakout.domain.Ball;
import be.howest.ti.breakout.domain.Brick;
import be.howest.ti.breakout.domain.Circle;
import be.howest.ti.breakout.domain.DoubleTroubleBall;
import be.howest.ti.breakout.domain.Fireball;
import be.howest.ti.breakout.domain.Pallet;
import be.howest.ti.breakout.domain.Rectangle;
import be.howest.ti.breakout.domain.Shape;
import be.howest.ti.breakout.factories.FactoryBall;
import be.howest.ti.breakout.factories.FactoryPallet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import be.howest.ti.breakout.domain.effects.Effect;
import be.howest.ti.breakout.domain.effects.EffectExtraBall;
import be.howest.ti.breakout.domain.effects.EffectDragonFireBall;
import be.howest.ti.breakout.domain.effects.EffectShadow;
import be.howest.ti.breakout.domain.effects.EffectStatus;
import be.howest.ti.breakout.domain.effects.EffectWebs;
import be.howest.ti.breakout.domain.fieldeffects.FieldEffect;
import be.howest.ti.breakout.domain.fieldeffects.Web;
import be.howest.ti.breakout.domain.powerUps.PowerUpOrDown;
import be.howest.ti.breakout.domain.spells.Spell;
import be.howest.ti.breakout.domain.spells.SpellStatus;
import be.howest.ti.breakout.factories.FactoryBricks;
import be.howest.ti.breakout.swing.ScheduleLevelTaskerSwing;
import java.awt.event.KeyEvent;

/**
 *
 * @author micha
 */
public class Level{
    private Game game;
    private Timer timer;
    private LevelTasker taskForLevel;
    //swing
    private ScheduleLevelTaskerSwing taskForLevelSwing;
    //
   
    private final FactoryBricks factoryBrick;
    private final FactoryPallet factoryPallet;
    private final FactoryBall factoryBall;
    
    private final List<Brick> bricks;
    private final List<Pallet> pallets;
    private final List<Ball> balls = new ArrayList<>();
    private final List<Ball> extraBallCreatedByEffects = new ArrayList<>();
    
    private final List<PowerUpOrDown> powerUpsOnScreen = new ArrayList<>();
    private final List<PowerUpOrDown> powerupsActive = new ArrayList<>();
    
    private final Map<User, List<Spell>> spellsChoices = new HashMap<>();
    private final Map<User, Spell> spellsInGame = new HashMap<>();
    
    private final FieldEffect fieldEffect;
    private final List<Web> websMadeByFieldEffect = new ArrayList<>();
    
    private final int number;
    private final Map<User, Integer> scorePerUser = new HashMap<>();
    
    private boolean completed;
    
    private final Rectangle TOP_BOUNDARY;
    private final Rectangle LEFT_BOUNDARY;
    private final Rectangle RIGHT_BOUNDARY;
    private final Rectangle BOTTOM_BOUNDARY;
    
    public Level(Game game, int number) {
        if(game != null){ this.game = game; } else {throw new NullPointerException("Game may not be null");}
        this.number = number;
        this.completed = false;
        initializeUserScores();
        
        this.factoryBrick = new FactoryBricks(this);
        this.bricks = factoryBrick.createBricks();
        this.factoryPallet = new FactoryPallet(this);
        this.pallets = this.factoryPallet.createPallets();
        this.factoryBall = new FactoryBall(this);
        this.factoryBall.createBalls();
        
        this.TOP_BOUNDARY = new Rectangle(this, 0, -10, getGameWidth(), 10);
        this.LEFT_BOUNDARY = new Rectangle(this, -10, 0, 10, getGameHeight());
        this.RIGHT_BOUNDARY = new Rectangle(this, getGameWidth(), 0, 10, getGameHeight());
        this.BOTTOM_BOUNDARY = new Rectangle(this, 0, getGameHeight(), getGameWidth(), 10);
        
        createNewRandomSpells();
        fieldEffect = new FieldEffect(this, "shadow", new EffectShadow("shadow", 3), 10);
        
    }
    
    public int getNumber() {
        return number;
    }
    
    public void startLevel(){
        timer = new Timer();
        taskForLevel = new LevelTasker(this);
        timer.scheduleAtFixedRate(taskForLevel, 1000, 20);
        fieldEffect.doEffect();
    }
    
     public void pauseLevel(){
        this.taskForLevel.setPaused(true);
         pauseEffects();
    }
    
    public void unpauseLevel(){
        this.taskForLevel.setPaused(false);
        resumeEffects();
    }
    
    public boolean isPaused(){
        return taskForLevel.isPaused();
    }
    
    public void endLevel(){
        cancelTimersOfEffects();
        this.timer.cancel();
    }
        
    private void resumeEffects(){
        for (PowerUpOrDown power : powerupsActive) {
            power.resume();
        }
        for (Map.Entry<User, Spell> entry : spellsInGame.entrySet()) {
            entry.getValue().resume();
        }
        for (Web web : websMadeByFieldEffect) {
            web.resume();
        }
        this.fieldEffect.resume();
    }
    
    private void pauseEffects(){
        for (PowerUpOrDown power : powerupsActive) {
            power.pause();
        }
        for (Map.Entry<User, Spell> entry : spellsInGame.entrySet()) {
            entry.getValue().pause();
        }
        for (Web web : websMadeByFieldEffect) {
            web.pause();
        }
        this.fieldEffect.pause();
    }
    
    private void cancelTimersOfEffects(){
        for (PowerUpOrDown power : powerupsActive) {
            power.cancel();
        }
        for (Map.Entry<User, Spell> entry : spellsInGame.entrySet()) {
            entry.getValue().cancel();
        }
        for (Web web : websMadeByFieldEffect) {
            web.removeYourselfNow();
        }
        this.fieldEffect.stopFieldEffect();
    }
    
    public List<Pallet> getPallets() {
        return pallets;
    }
 
    public Pallet getUserPallet(int userID){
        for (Pallet pallet : pallets) {
            if(pallet.getUser().getUserId() == userID){
                return pallet;
            }
        }
        return null;
    }
     
    public Pallet getUserPallet(User user){
        for (Pallet pallet : pallets) {
            if(pallet.getUser().getUserId() == user.getUserId()){
                return pallet;
            }
        }
        return null;
    }

    public List<Ball> getBalls() {
        return balls;
    }
    
    public List<Ball> getExtraBallCreatedByEffects(){
        return extraBallCreatedByEffects;
    }
    
    public List<Ball> getAllBallsInLevel(){
        List<Ball> allBallsInLevel = new ArrayList<>();
        allBallsInLevel.addAll(balls);
        allBallsInLevel.addAll(extraBallCreatedByEffects);
        return allBallsInLevel;
    }
    
    public DoubleTroubleBall createExtraBall(EffectExtraBall effect){
        return factoryBall.createExtraBallDoubleTrouble(effect);
    }
    
    public Fireball createExtraFireBall(EffectDragonFireBall effect){
        return factoryBall.createExtraFireball(effect);
    }
    
    public List<Brick> getBricks() {
        return bricks;
    }
    
    public void lowerHitsOfBrick(Ball ball, Brick b, User playerThatDestroyedBrick){
        b.decrementHits(ball.getDamage());
        if(b.getHits() <= 0){
            deleteBrick(b, playerThatDestroyedBrick);
        }
    }
    
    public void deleteBrick(Brick b, User playerThatDestroyedBrick){
        b.getPowerUP().show();
        bricks.remove(b);

        //User player = game.getPlayers().get(playerThatDestroyedBrick);
        addScoreToUser(playerThatDestroyedBrick, b.getAchievedScore());
        
        //XP nog aan toevoegen
        //als gameover -> XP behouden
        //als gameover -> total score wordt toegevegd aan score users
        //als succes -> XP awarden 
        //als succes -> level CollectiveScore aan totale CollectiveScore toevoegen.

        checkForCompletion();
    }
    
    public List<PowerUpOrDown> getPowerUpsShownOnScreen(){
        return powerUpsOnScreen;
    }
    
    public void addPowerUpActive(PowerUpOrDown powerUp){
        powerupsActive.add(powerUp);
    }
    
    public List<PowerUpOrDown> getAllActivePowerUps(){
        return powerupsActive;
    }
    
    //spells
    public final void createNewRandomSpells(){
        for (User player : game.getPlayers()) {
            spellsChoices.put(player, new ArrayList<>());
        }
        for (Map.Entry<User, List<Spell>> entry : spellsChoices.entrySet()) {
            for (int i = 0; i < 3; i++) {
                Spell newSpell = new Spell(this);
                if(!LevelAlreadyContainsSpell(entry.getValue(), newSpell)){
                    entry.getValue().add(newSpell);
                } else {
                    i--;
                }
            }
        }
    }
    
    public boolean LevelAlreadyContainsSpell(List<Spell> spellChoicesOfUser, Spell s){
        for (Spell spell : spellChoicesOfUser) {
            if(spell.getName().equals(s.getName())){
                return true;
            }
        }
        return false;
    }
    
    public boolean areAllSpellsSelected(){
        return spellsInGame.size() == spellsChoices.size();
    }
    
    public void setUserSpell(User u, Spell s){
        //u.setSpell(s);
        spellsInGame.put(u, s);
    }
    
    public Spell getSpellByUser(User u){
        return spellsInGame.get(u);
    }
    
    public void updateSpellOfUser(User u, Spell spell){
        spellsInGame.replace(u, spell);
    }
    
    public List<Spell> getAllSpellChoicesOfUser(User user){
        return spellsChoices.get(user);
    }
    
    public Spell getSpellofUserChoices(User user, String spellName){
        for (Spell spell : spellsChoices.get(user)) {
            if(spell.getName().equals(spellName)){
                return spell;
            }
        }
        return null;
    }
    
    public Map<User, List<Spell>> getAllSpellsChoices(){
        return spellsChoices;
    }
    
    public Map<User, Spell> getAllSpellsInGame(){
        return spellsInGame;
    }
    //
    
    public FieldEffect getFieldEffect(){
        return fieldEffect;
    }
    
    public List<Web> getAllShapesCreatedByFieldEffect(){
        return websMadeByFieldEffect;
    }
    
    public void addShapeToFieldEffectShapes(Web web){
        websMadeByFieldEffect.add(web);
    }
    
    public void removeShapeFromFieldEffectShapes(Web web){
        websMadeByFieldEffect.remove(web);
    }
    
    public final void initializeUserScores(){
        for (User player : game.getPlayers()) {
            scorePerUser.put(player, 0);
        }
    }
    
    private void addScoreToUser(User u, int score){
        scorePerUser.merge(u, score, Integer::sum);
    }
    
    public int getUserScore(int userID){
        return scorePerUser.get(game.getPlayers().get(userID - 1));
    }
    
    public int getCollectiveScore() {
        int sum = 0;
        for (Map.Entry<User, Integer> entry : scorePerUser.entrySet()) {
            sum += entry.getValue();
        }
        return sum;
    }
    
    public Game getGame(){
        return this.game;
    }
    
    public final int getGameWidth() {
        return this.game.getWidth();
    }

    public final int getGameHeight() {
        return this.game.getHeight();
    }
    
    public List<User> getPlayers(){
        return game.getPlayers();
    }
    
    public int getAantalSpelers(){
        return game.getNumberOfPlayers();
    }
    
    public List<Ratio> getRatios(){
        return game.getRatios();
    }
    
    public void decrementLife(){
        game.decrementLife();
    }
    
    public boolean getGameOver(){
        return game.isGameOver();
    }
    
    public List<Shape> getAllEntities(){
        List<Shape> allEntities = new ArrayList<>(pallets);
        allEntities.addAll(balls);
        allEntities.addAll(extraBallCreatedByEffects);
        for (PowerUpOrDown powerUp : powerUpsOnScreen) {
            allEntities.add(powerUp);
        }
        allEntities.addAll(bricks);
        allEntities.addAll(websMadeByFieldEffect);
        allEntities.add(TOP_BOUNDARY);
        allEntities.add(LEFT_BOUNDARY);
        allEntities.add(RIGHT_BOUNDARY);
        allEntities.add(BOTTOM_BOUNDARY);
        return allEntities;
    }
    
     public Rectangle getTOP_BOUNDARY() {
        return TOP_BOUNDARY;
    }

    public Rectangle getLEFT_BOUNDARY() {
        return LEFT_BOUNDARY;
    }

    public Rectangle getRIGHT_BOUNDARY() {
        return RIGHT_BOUNDARY;
    }

    public Rectangle getBOTTOM_BOUNDARY() {
        return BOTTOM_BOUNDARY;
    }
    
    public void resetStates(){
        fieldEffect.pause();
        for (Pallet pallet : pallets) {
            pallet.resetState();
            pallet.setVisible();
        }
        this.factoryBall.createBalls();
        resetPowerUps();
        resetSpellEffects();
        for (Ball extraBallCreatedByEffect : extraBallCreatedByEffects) {
            extraBallCreatedByEffect.removeFromScreen();
        }
    }
    
    public void resetPowerUps(){
        for (PowerUpOrDown powerUpOrDown : powerupsActive) {
            powerUpOrDown.setDeActive();
        }
    }
    
    public void resetSpellEffects(){
         for (Map.Entry<User, Spell> entry : spellsInGame.entrySet()) {
             for (Effect spellEffect : entry.getValue().getSpellEffects()) {
                 if(spellEffect.isActivated() == EffectStatus.RUNNING){
                    spellEffect.setDeActive();
                 }
             }
             if(entry.getValue().isActivated() != SpellStatus.READY){
                entry.getValue().setReady();
                entry.getValue().stopCooldown();
                entry.getValue().setCoolDown(entry.getValue().getOriginalCoolDown());
             }
         }
    }
    
    private void checkForCompletion(){
        if(this.getBricks().isEmpty()){
            setCompleted(true);
            endLevel();
            addUserScoresToTotalGame();
            game.createNewLevel();
        }
    }
    
    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
   
    private void addUserScoresToTotalGame(){
        for (Map.Entry<User, Integer> entry : scorePerUser.entrySet()) {
            game.addToTotalScoreDuringGame(entry.getKey(), entry.getValue());
        }
    }
    
    
    
     //voor swing
    public void startLevel(ScheduleLevelTaskerSwing s){
        timer = new Timer();
        taskForLevelSwing = s;
        timer.scheduleAtFixedRate(s, 1000, 15);
        fieldEffect.doEffect();
    }
    
    public void pauseLevelSwing(){
        this.taskForLevelSwing.setPaused(true);
        pauseEffects();
    }
    
    public void unpauseLevelSwing(){
        this.taskForLevelSwing.setPaused(false);
        resumeEffects();
    }
    
    //voor swing
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) {
            pauseLevelSwing();
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_W) {
            unpauseLevelSwing();
        }
    }
    //
    
}
