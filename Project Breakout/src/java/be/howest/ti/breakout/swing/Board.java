/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.swing;

import be.howest.ti.breakout.data.Repositories;
import be.howest.ti.breakout.domain.Ball;
import be.howest.ti.breakout.domain.Brick;
import be.howest.ti.breakout.domain.Circle;
import be.howest.ti.breakout.domain.game.Game;
import be.howest.ti.breakout.domain.game.GameDifficulty;
import be.howest.ti.breakout.domain.game.Level;
import be.howest.ti.breakout.domain.Pallet;
import be.howest.ti.breakout.domain.Shape;
import be.howest.ti.breakout.domain.game.User;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import be.howest.ti.breakout.domain.effects.Effect;
import be.howest.ti.breakout.domain.effects.EffectStatus;
import be.howest.ti.breakout.domain.game.Player;
import be.howest.ti.breakout.domain.powerUps.PowerUpOrDown;
import be.howest.ti.breakout.domain.spells.Spell;
import be.howest.ti.breakout.domain.spells.SpellStatus;
import java.util.Map;

/**
 *
 * @author micha
 */
public class Board extends JPanel{
    Player me = Repositories.getUserRepository().getUserWithId(5);
    Player me2 = Repositories.getUserRepository().getGuest(1);
    Player me3 = Repositories.getUserRepository().getGuest(2);
    Player me4 = Repositories.getUserRepository().getGuest(3);
    List<Player> users = new ArrayList<>(Arrays.asList(me, me2, me3, me4));
    List<GameDifficulty> difficulties;
    private Game game;
    private Level level;
    private ScheduleLevelTaskerSwing s;

    public Board() {
        this.difficulties = new ArrayList<>(Arrays.asList(new GameDifficulty("Easy", 0.2f, 1), new GameDifficulty("Medium", -0.2f, 3), new GameDifficulty("Hard", -0.4f, 5)));
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        addKeyListener(new TEdaper());
        addKeyListener(new TTdaper());
        setFocusable(true);
        setDoubleBuffered(true);
        showDifficulties();
    }
    
    public void showDifficulties(){
        String[] options = new String[difficulties.size()];
        for (int i = 0; i < difficulties.size(); i++) {
            options[i] = difficulties.get(i).getName();
        }
        int response = JOptionPane.showOptionDialog(null, "Choose a Difficulty", "difficulties",
        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
        null, options, options[0]);
        
        
        game = new Game(1000, 1000, 2, difficulties.get(response));
        game.createNewLevel();
        game.replaceGuestByUser(1, me);
        game.getLevelPlayedRightNow().createNewRandomSpells();
        level = game.getLevelPlayedRightNow();
        s = new ScheduleLevelTaskerSwing(level, this);
        showSpellChoices();
    }
    
    public void showSpellChoices(){
        Map<Player, List<Spell>> spellsChoices = level.getAllSpellsChoices();
        int j = 0;
        for (Map.Entry<Player, List<Spell>> entry : spellsChoices.entrySet()) {
            j++;
            List<Spell> spells = entry.getValue();
            String[] options = new String[spells.size()];
            for (int i = 0; i < spells.size(); i++) {
                options[i] = spells.get(i).getName();
            }
            int response = JOptionPane.showOptionDialog(null, "Choose a Spell", "Spells",
            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
            null, options, options[0]);
            level.setPlayerSpell(users.get(j - 1), spells.get(response));
        }
        level.startLevel(s);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        if (!game.isGameOver()) {
            drawObjects(g2d);
            drawTexts(g2d);
            if(level.isCompleted()){
                level = game.getLevelPlayedRightNow();
                s = new ScheduleLevelTaskerSwing(level, this);
                showSpellChoices();
            }
        } else {
            gameFinished(g2d);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics2D g2d) {
        Level level = game.getLevelPlayedRightNow();
        g2d.setColor(Color.BLUE);
        
        for (Pallet pallet : level.getPallets()) {
            if(pallet.IsVisible()){
                g2d.fillRect(pallet.getX(), pallet.getY(), pallet.getLength(), pallet.getHeight());
            }
        }
        
        
        //for (BrickRow rowsOfBrick : level.getBricks()) {
            for (Brick brick : level.getBricks()) {
                g2d.setColor(Color.RED);
                g2d.fillRect(brick.getX(), brick.getY(), brick.getLength(), brick.getHeight());
                g2d.setColor(Color.BLACK);
                g2d.drawRect(brick.getX(), brick.getY(), brick.getLength(), brick.getHeight());
                g2d.drawString(""+brick.getHits(), (brick.getX() + (brick.getLength() / 2)), (brick.getY() + (brick.getHeight() / 2)));
            }
        //}
        
        g2d.setColor(Color.BLACK);
        for (Ball ball : level.getBalls()) {
            g2d.fillOval(ball.getX() - (ball.getRadius() / 2), ball.getY() - (ball.getRadius() / 2), ball.getRadius(), ball.getRadius());
        }
        
        g2d.setColor(Color.BLUE);
        for (Ball ball : level.getExtraBallCreatedByEffects()) {
            g2d.fillOval(ball.getX(), ball.getY(), ball.getRadius(), ball.getRadius());
        }
        
        for (Circle circle : level.getAllShapesCreatedByFieldEffect()) {
            g2d.drawOval(circle.getX() - (circle.getRadius() / 2), circle.getY() - (circle.getRadius() / 2), circle.getRadius(), circle.getRadius());
        }
        for (PowerUpOrDown powerUp : level.getPowerUpsShownOnScreen()) {
            g2d.drawRect(powerUp.getBoundaries().getX(), powerUp.getBoundaries().getY(), powerUp.getBoundaries().getLength(), powerUp.getBoundaries().getHeight());
        }
    }
    
    private void drawTexts(Graphics2D g2d) {
        Font font = new Font("Verdana", Font.BOLD, 18);
        String levelNumber = "Level " + game.getLevels().size();
        String lives = "Lives x " + game.getLives();
        String score = "Score " + game.getLevelPlayedRightNow().getPlayerScore(me.getPlayerID());
        String scoreTotal = "Total Score " + game.getTotalGameScore();
        String powerup = "PowerUp Active: ";
        for (PowerUpOrDown powerUp : level.getAllActivePowerUps()) {
            powerup += powerUp.toString();
        }
        String spell = "effects of spell active: ";
        for (Effect effect :  level.getAllSpellsInGame().get(me).getSpellEffects()) {
            if(!level.getAllSpellsInGame().get(me).getSpellEffects().isEmpty()){
                if(effect.isActivated() == EffectStatus.RUNNING){
                    spell += effect.toString() + ", ";
                }
            }
        }
        String spellCooldown = "Cooldown " + level.getAllSpellsInGame().get(me).getCooldown();
        

        g2d.setColor(Color.BLACK);
        g2d.setFont(font);
        g2d.drawString(levelNumber, 5, 20);
        g2d.drawString(lives, 5, 50);
        g2d.drawString(score, 5, 80);
        g2d.drawString(scoreTotal, 5, 110);
        g2d.drawString(powerup, 100, 20);
        g2d.drawString(spell, 100, 50);
        if(level.getAllSpellsInGame().get(me).isActivated() == SpellStatus.COOLDOWN){
            g2d.drawString(spellCooldown, 100, 80);
        }
    }
    
    private void gameFinished(Graphics2D g2d) {
        Font font = new Font("Verdana", Font.BOLD, 18);
        String gameover = "Game over";
        String scoreThisLevel = "Your Score this Level " + game.getLevels().get(game.getLevels().size() - 1).getCollectiveScore();
        String totalScore = "Your Total Score " + game.getTotalGameScore();
        String thankyou = "Thank you for playing!";

        g2d.setColor(Color.BLACK);
        g2d.setFont(font);
        g2d.drawString(gameover, 410, 450);
        g2d.drawString(scoreThisLevel, 340, 480);
        g2d.drawString(totalScore, 365, 510);
        g2d.drawString(thankyou, 360, 540);
    }

    private class TAdapter extends KeyAdapter {
        
        @Override
        public void keyReleased(KeyEvent e) {
            level.getPallets().get(0).keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            level.getPallets().get(0).keyPressed(e);
        }
    }
    
    private class TEdaper extends KeyAdapter {
        
        @Override
        public void keyPressed(KeyEvent e) {
            Spell spell = level.getSpellByPlayer(me); 
            spell.keyPressed(e); 
        }
    }
    
    private class TTdaper extends KeyAdapter {
        
        @Override
        public void keyPressed(KeyEvent e) {
            level.keyPressed(e);
        }
        
        @Override
        public void keyReleased(KeyEvent e) {
            level.keyReleased(e);
        }
    }

}
