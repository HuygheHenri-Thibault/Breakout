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
import java.util.List;

/**
 *
 * @author micha
 */
public class Level {
    private Game game;
    
//    private int aantalSpelers;
//    private int lives; 
    
    private FactoryRowOfBricks factoryBrick;
    private FactoryPallet factoryPallet;
    private FactoryBall factoryBall;
    
    private List<BrickRow> rowsOfBricks;
    private List<Pallet> pallets = new ArrayList<>();
    private List<Ball> balls = new ArrayList<>();
    
    private final int number;
    private int score;
    private final static int MAX_ROWS_BRICKS = 5;
    
    private boolean completed;
    
    private final Rectangle TOP_BOUNDARY = new Rectangle(this, null, 0, -10, 1000, 10);
    private final Rectangle LEFT_BOUNDARY = new Rectangle(this, null, -10, 0, 10, 1000);
    private final Rectangle RIGHT_BOUNDARY = new Rectangle(this, null, 1000, 0, 10, 1000);
    private final Rectangle BOTTOM_BOUNDARY = new Rectangle(this, null, 0, 1000, 1000, 10);

    public Level(Game game, int number, int score) {
        if(game != null){ this.game = game; } else {throw new NullPointerException("Game may not be null");}
        this.factoryBrick = new FactoryRowOfBricks(this);
        this.rowsOfBricks = factoryBrick.createRowOfBricks();
        this.factoryPallet = new FactoryPallet(this);
        this.factoryPallet.createPallets();
        this.factoryBall = new FactoryBall(this);
        this.factoryBall.createBall();
        
        this.number = number;
        this.score = score;
        this.completed = false;
    }
    
    public List<Pallet> getPallets() {
        return pallets;
    }

    public List<Ball> getBalls() {
        return balls;
    }
    
    public List<BrickRow> getRowOfBricks() {
        return rowsOfBricks;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public int getNumber() {
        return number;
    }

    public int getScore() {
        return score;
    }

    public int getGameWidth() {
        return game.getWidth();
    }

    public int getGameHeight() {
        return game.getHeight();
    }
    
    public int getAantalSpelers(){
        return game.getAantalSpelers();
    }
    
    public void decrementLife(){
        game.decrementLife();
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
    
    public List<Sprite> getAllEntities(){
        List<Sprite> allEntities = new ArrayList<>(pallets);
        allEntities.addAll(balls);
        for (BrickRow br : rowsOfBricks) {
            allEntities.addAll(br.getBricksOnRow());
        }
        allEntities.add(TOP_BOUNDARY);
        allEntities.add(LEFT_BOUNDARY);
        allEntities.add(RIGHT_BOUNDARY);
        allEntities.add(BOTTOM_BOUNDARY);
        return allEntities;
    }
    
    public void deleteBrick(Brick b){
        BrickRow brickLine = searchBrickThroughRows(b);
        brickLine.deleteBrick(b);
        if(brickLine.getBricksOnRow().isEmpty()){
            getRowOfBricks().remove(brickLine);
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
        if(this.getRowOfBricks().isEmpty()){
            setCompleted(true);
        }
    }
}
