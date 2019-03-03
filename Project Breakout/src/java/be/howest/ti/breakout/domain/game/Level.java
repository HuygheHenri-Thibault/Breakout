/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain.game;

import be.howest.ti.breakout.data.Repositories;
import be.howest.ti.breakout.domain.Ball;
import be.howest.ti.breakout.domain.Brick;
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
import be.howest.ti.breakout.domain.effects.EffectStatus;
import be.howest.ti.breakout.domain.fieldeffects.FieldEffect;
import be.howest.ti.breakout.domain.fieldeffects.Web;
import be.howest.ti.breakout.domain.powerUps.PowerUpOrDown;
import be.howest.ti.breakout.domain.spells.Spell;
import be.howest.ti.breakout.domain.spells.SpellStatus;
import be.howest.ti.breakout.factories.FactoryBricks;
import java.util.Random;
import java.util.TreeMap;

/**
 *
 * @author micha
 */
public final class Level{
    private Game game;
    private Timer timer;
    private LevelTasker taskForLevel;
   
    private final FactoryBricks factoryBrick;
    private final FactoryPallet factoryPallet;
    private final FactoryBall factoryBall;
    
    private final List<Brick> bricks;
    private final List<Pallet> pallets;
    private final List<Ball> balls = new ArrayList<>();
    private final List<Ball> extraBallCreatedByEffects = new ArrayList<>();
    
    private final List<PowerUpOrDown> powerUpsOnScreen = new ArrayList<>();
    private final List<PowerUpOrDown> powerupsActive = new ArrayList<>();
    
    private final Map<Player, List<Spell>> spellsChoices = new HashMap<>();
    private final Map<Player, Spell> spellsInGame = new HashMap<>();
    
    private FieldEffect fieldEffect;
    private final List<Web> websMadeByFieldEffect = new ArrayList<>();
    
    private final int number;
    private final Map<Player, Integer> scorePerPlayer = new TreeMap<>((Player p1, Player p2) -> p1.getPlayerID()- p2.getPlayerID());
    
    private boolean completed;
    
    private final Rectangle TOP_BOUNDARY;
    private final Rectangle LEFT_BOUNDARY;
    private final Rectangle RIGHT_BOUNDARY;
    private final Rectangle BOTTOM_BOUNDARY;
    
    public Level(Game game, int number) {
        this(game, number, null);
        this.fieldEffect = new FieldEffect(this, "dragon", "hello", new EffectDragonFireBall("help", "hello", 0), 10);
    }
    
    public Level(Game game, int number, FieldEffect fieldEffect) {
        if(game != null){ this.game = game; } else {throw new NullPointerException("Game may not be null");}
        this.number = number;
        this.completed = false;
        initializePlayerScores();
        
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
        this.fieldEffect = fieldEffect;
    }
    
    public int getNumber() {
        return number;
    }
    
    public boolean hasLevelStarted(){
        return timer != null;
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
        this.timer.cancel();
        cancelTimersOfEffects();
    }
        
    private void resumeEffects(){
        for (PowerUpOrDown power : powerupsActive) {
            power.resume();
        }
        for (Map.Entry<Player, Spell> entry : spellsInGame.entrySet()) {
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
        for (Map.Entry<Player, Spell> entry : spellsInGame.entrySet()) {
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
        for (Map.Entry<Player, Spell> entry : spellsInGame.entrySet()) {
            entry.getValue().cancel();
        }
        for (Web web : websMadeByFieldEffect) {
            web.cancel();
        }
        this.fieldEffect.stopFieldEffect();
    }
    
    public List<Pallet> getPallets() {
        return pallets;
    }
 
    public Pallet getPlayerPallet(int playerID){
        for (Pallet pallet : pallets) {
            if(pallet.getPlayer().getPlayerID()== playerID){
                return pallet;
            }
        }
        return null;
    }
     
    public Pallet getPlayerPallet(Player player){
        for (Pallet pallet : pallets) {
            if(pallet.getPlayer().getPlayerID()== player.getPlayerID()){
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
    
    public void lowerHitsOfBrick(Ball ball, Brick b, Player playerThatDestroyedBrick){
        b.decrementHits(ball.getDamage());
        if(b.getHits() <= 0){
            deleteBrick(b, playerThatDestroyedBrick);
        }
    }
    
    public void deleteBrick(Brick b, Player playerThatDestroyedBrick){
        b.getPowerUP().show();
        bricks.remove(b);

        addScoreToPlayer(playerThatDestroyedBrick, b.getAchievedScore());
        
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
    
    public final void createNewRandomSpells(){
        for (Player player : game.getPlayers()) {
            spellsChoices.put(player, new ArrayList<>());
        }
        for (Map.Entry<Player, List<Spell>> entry : spellsChoices.entrySet()) {
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
    
    public boolean LevelAlreadyContainsSpell(List<Spell> spellChoicesOfPlayer, Spell s){
        for (Spell spell : spellChoicesOfPlayer) {
            if(spell.getName().equals(s.getName())){
                return true;
            }
        }
        return false;
    }
    
    public boolean areAllSpellsSelected(){
        return spellsInGame.size() == spellsChoices.size();
    }
    
    public void replacePlayer(int spelerID, Player player){
        Player playerBeingReplaced = getPlayerFromUserSpells(spelerID);
        List<Spell> spellChoices = spellsChoices.remove(playerBeingReplaced);
        spellsChoices.put(player, spellChoices);
        int scoreOfUser = scorePerPlayer.remove(playerBeingReplaced);
        scorePerPlayer.put(player, scoreOfUser);
    }
    
    private Player getPlayerFromUserSpells(int spelerID){
        for (Map.Entry<Player, List<Spell>> entry : spellsChoices.entrySet()) {
            if(entry.getKey().getPlayerID() == spelerID){
                return entry.getKey();
            }
        }
        return null;
    }
    
    public void setPlayerSpell(Player u, Spell s){
        s.setPlayer(u);
        spellsInGame.put(u, s);
    }
    
    public Spell getSpellByPlayer(Player player){
        return spellsInGame.get(player);
    }
    
    public void updateSpellOfPlayer(Player player, Spell spell){
        spellsInGame.replace(player, spell);
    }
    
    public List<Spell> getAllSpellChoicesOfPlayer(Player player){
        return spellsChoices.get(player);
    }
    
    public Spell getSpellofPlayerChoices(Player player, String spellName){
        for (Spell spell : spellsChoices.get(player)) {
            if(spell.getName().equals(spellName)){
                return spell;
            }
        }
        return null;
    }
    
    public Map<Player, List<Spell>> getAllSpellsChoices(){
        return spellsChoices;
    }
    
    public Map<Player, Spell> getAllSpellsInGame(){
        return spellsInGame;
    }
    
    public FieldEffect randomizeFieldEffect(){
        List<FieldEffect> allFieldEffects = Repositories.getFieldEffectRepository().getAllFieldEffects();
        Random generator = new Random();
        int max = (allFieldEffects.size() - 1);
        int min = 0;
        int randomIndex = generator.nextInt((max - min) + 1) - min;
        while(randomIndex == 1){ //webs niet kunnen helemaal uitwerken dus deze wordt niet gekozen
            randomIndex = generator.nextInt((max - min) + 1) - min;
        }
        FieldEffect fieldEffectForThisLevel = allFieldEffects.get(randomIndex);
        fieldEffectForThisLevel.setLevel(this);
        return fieldEffectForThisLevel;
    }
    
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
    
    public final void initializePlayerScores(){
        for (Player player : game.getPlayers()) {
            scorePerPlayer.put(player, 0);
        }
    }
    
    private void addScoreToPlayer(Player player, int score){
        scorePerPlayer.merge(player, score, Integer::sum);
    }
    
    public Map<Player, Integer> getScoresPerUser(){
        return scorePerPlayer;
    }
    
    public int getPlayerScore(int playerID){
        return scorePerPlayer.get(game.getPlayers().get(playerID - 1));
    }
    
    public int getCollectiveScore() {
        int sum = 0;
        for (Map.Entry<Player, Integer> entry : scorePerPlayer.entrySet()) {
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
    
    public List<Player> getPlayers(){
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
        if(getGameOver()){
            addPlayerScoresToTotalGame();
            game.stopGame();
        }
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
         for (Map.Entry<Player, Spell> entry : spellsInGame.entrySet()) {
             for (Effect spellEffect : entry.getValue().getSpellEffects()) {
                 if(spellEffect.getStatus() == EffectStatus.RUNNING){
                    spellEffect.setDeActive();
                 }
             }
             if(entry.getValue().getStatus() != SpellStatus.READY){
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
            addPlayerScoresToTotalGame();
        }
    }
    
    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
   
    private void addPlayerScoresToTotalGame(){
        for (Map.Entry<Player, Integer> entry : scorePerPlayer.entrySet()) {
            game.addToTotalScoreDuringGame(entry.getKey(), entry.getValue());
        }
    }
    
}
