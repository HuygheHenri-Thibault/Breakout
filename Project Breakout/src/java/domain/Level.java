/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import factories.FactoryBall;
import factories.FactoryPallet;
import factories.FactoryRowOfBricks;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import powerUps.Effect;
import powerUps.EffectDoubleTrouble;
import powerUps.EffectStatus;
import powerUps.NoPower;
import powerUps.PowerUpOrDown;
import spells.Spell;
import spells.SpellStatus;
import swing.ScheduleLevelTasker;
import swing.ScheduleLevelTaskerJavascript;

/**
 *
 * @author micha
 */
public class Level{
    private Game game;
    private Timer timer;
    private ScheduleLevelTasker taskForLevel;
    
    private FactoryRowOfBricks factoryBrick;
    private FactoryPallet factoryPallet;
    private FactoryBall factoryBall;
    
    private List<BrickRow> rowsOfBricks;
    private List<Pallet> pallets = new ArrayList<>();
    private List<Ball> balls = new ArrayList<>();
    
    private List<PowerUpOrDown> powerUps = new ArrayList<>();
    private List<PowerUpOrDown> powerupsActive = new ArrayList<>();
    
    private List<Spell> spells = new ArrayList<>();
    private Map<User, Spell> spellsInGame = new HashMap<User, Spell>();
    
    private final int number;
    private int score = 0;
    private final int startScoreForBricks;

    private final static int MAX_ROWS_BRICKS = 5;
    
    private boolean completed;
    
    private final Rectangle TOP_BOUNDARY;
    private final Rectangle LEFT_BOUNDARY;
    private final Rectangle RIGHT_BOUNDARY;
    private final Rectangle BOTTOM_BOUNDARY;
    
    public Level(Game game, int startScoreForBricks, int number) {
        if(game != null){ this.game = game; } else {throw new NullPointerException("Game may not be null");}
        this.number = number;
        this.startScoreForBricks = startScoreForBricks;
        this.completed = false;
        
        this.factoryBrick = new FactoryRowOfBricks(this);
        this.rowsOfBricks = factoryBrick.createRowOfBricks();
        this.factoryPallet = new FactoryPallet(this);
        this.factoryPallet.createPallets();
        this.factoryBall = new FactoryBall(this);
        this.factoryBall.createBalls();
        this.TOP_BOUNDARY = new Rectangle(this, 0, -10, getGameWidth(), 10);
        this.LEFT_BOUNDARY = new Rectangle(this, -10, 0, 10, getGameHeight());
        this.RIGHT_BOUNDARY = new Rectangle(this, getGameWidth(), 0, 10, getGameHeight());
        this.BOTTOM_BOUNDARY = new Rectangle(this, 0, getGameHeight(), getGameWidth(), 10);
        createNewRandomSpells();
    }
    
    //spells
    private void createNewRandomSpells(){
        for (int i = 0; i < 3; i++) {
            Spell newSpell = new Spell(this);
            if(!LevelAlreadyContainsSpell(newSpell)){
                spells.add(newSpell);
            } else {
                i--;
            }
        }
    }
    
    public boolean LevelAlreadyContainsSpell(Spell s){
        for (Spell spell : spells) {
            if(spell.getName() == null ? s.getName() == null : spell.getName().equals(s.getName())){
                return true;
            }
        }
        return false;
    }
    
    public void setUserSpell(User u, Spell s){
        u.setSpell(s);
        spellsInGame.put(u, s);
    }
    
    public List<Spell> getAllSpells(){
        return spells;
    }
    
//    public void addActiavtedSpelltoLevel(Spell spell){
//        activatedSpells.add(spell);
//    }
//    
//    public void removeActivatedSpellFromLevel(Spell spell){
//        activatedSpells.remove(spell);
//    }
    
    public Spell getSpellByUser(User u){
        return spellsInGame.get(u);
    }
    
    public void updateSpellOfUser(User u, Spell spell){
        spellsInGame.replace(u, spell);
    }
    
    public Map<User, Spell> getAllSpellsInGame(){
        return spellsInGame;
    }
    //
    
    //voor swing
    public void startLevel(ScheduleLevelTasker s){
        timer = new Timer();
        taskForLevel = s;
        timer.scheduleAtFixedRate(s, 1000, 10);
    }
    //

    public void startLevel(){
        timer = new Timer();
        ScheduleLevelTaskerJavascript taskForLevelNow = new ScheduleLevelTaskerJavascript(this);
        timer.scheduleAtFixedRate(taskForLevelNow, 1000, 15);
    }
    
    public void pause(){
        this.taskForLevel.setPaused(true);
    }
    
    public void unpause(){
        this.taskForLevel.setPaused(false);
    }
    
    public void endLevel(){
        this.timer.cancel();
    }
    
    public List<User> getPlayers(){
        return game.getPlayers();
    }
    
    public List<BrickRow> getRowsOfBricks() {
        return rowsOfBricks;
    }
    
    public List<Pallet> getPallets() {
        return pallets;
    }

//    public Spell getSpell() {
//        return spell;
//    }
    
    public void addPowerUpActive(PowerUpOrDown powerUp){
        powerupsActive.add(powerUp);
    }
    
    public List<PowerUpOrDown> getAllActivePowerUps(){
        return powerupsActive;
    }
    
    public Pallet getUserPallet(int userID){
        for (Pallet pallet : pallets) {
            if(pallet.getUserID() == userID){
                return pallet;
            }
        }
        return null;
    }

    public List<Ball> getBalls() {
        return balls;
    }
    
//    public void addBallOnScreen(){
//        //ballsonScreen++;
//    }
//    
//    public void decrementBallsOnScreen(Ball ball){
//        ballsonScreen--;
//        System.out.println("ball " + ball.getId() + "activated balls " +ballsonScreen);
//        if(ballsonScreen == 0){
//            resetStates();
//            ballsonScreen = balls.size() + 1;
//        }
//    }
    
    public void createExtraBall(EffectDoubleTrouble effect){
        //addBallOnScreen();
        factoryBall.createExtraBallDoubleTrouble(effect);
    }
    
     
    public List<PowerUpOrDown> getPowerUpsShownOnScreen(){
        return powerUps;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public int getStartScoreForBricks() {
        return startScoreForBricks;
    }
    
    public int getNumber() {
        return number;
    }

    public int getScore() {
        return score;
    }
       
    public List<Ratio> getRatios(){
        return game.getRatios();
    }
    
    public Game getGame(){
        return this.game;
    }

    public int getGameWidth() {
        return this.game.getWidth();
    }

    public int getGameHeight() {
        return this.game.getHeight();
    }
    
    public int getAantalSpelers(){
        return game.getAantalSpelers();
    }
    
    public void decrementLife(){
        game.decrementLife();
        if(game.isGameOver()){
            endLevel();
        }
    }
    
    public void resetStates(){
        for (Ball ball : balls) {
            ball.resetState();
        }
        for (Pallet pallet : pallets) {
            pallet.resetState();
        }
        resetPowerUps();
        resetSpellEffects();
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
                entry.getValue().setCoolDown(entry.getValue().getOriginalCoolDown());
             }
         }
    }
    
    public boolean getGameOver(){
        return game.isGameOver();
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
   
    public int getMAX_ROWS_BRICKS() {
        return MAX_ROWS_BRICKS;
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
    
    public List<Shape> getAllEntities(){
        List<Shape> allEntities = new ArrayList<>(pallets);
        allEntities.addAll(balls);
        for (PowerUpOrDown powerUp : powerUps) {
            allEntities.add(powerUp);
        }
        for (BrickRow br : rowsOfBricks) {
            allEntities.addAll(br.getBricksOnRow());
        }
        allEntities.add(TOP_BOUNDARY);
        allEntities.add(LEFT_BOUNDARY);
        allEntities.add(RIGHT_BOUNDARY);
        allEntities.add(BOTTOM_BOUNDARY);
        return allEntities;
    }
    public void lowerHitsOfBrick(Ball ball, Brick b, int playerIDThatDestroyedBrick){
        b.decrementHits(ball);
        if(b.getHits() <= 0){
            deleteBrick(b, playerIDThatDestroyedBrick);
        }
    }
    
    public void deleteBrick(Brick b, int playerIDThatDestroyedBrick){
        BrickRow brickLine = searchBrickThroughRows(b);
        b.getPowerUP().show();
        brickLine.deleteBrick(b);
        score += b.getAchievedScore();
        User player = game.getPlayers().get(playerIDThatDestroyedBrick);
        player.setScore(player.getScore() + b.getAchievedScore());
        game.setScore(game.getScore() + b.getAchievedScore());
        if(brickLine.getBricksOnRow().isEmpty()){
            getRowsOfBricks().remove(brickLine);
        }
        checkForCompletion();
    }
    
    private BrickRow searchBrickThroughRows(Brick b){
        for (BrickRow rowsOfBrick : rowsOfBricks) {
            if(rowsOfBrick.getBricksOnRow().contains(b)){
                return rowsOfBrick;
            }
        }
        return null;
    }
    
    private void checkForCompletion(){
        if(this.getRowsOfBricks().isEmpty()){
            setCompleted(true);
            endLevel();
            game.createNewLevel();
        }
    }
}
