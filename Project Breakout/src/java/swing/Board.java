/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import domain.Ball;
import domain.Brick;
import domain.BrickRow;
import domain.Game;
import domain.Level;
import domain.Pallet;
import domain.SinglePlayerGame;
import domain.User;
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
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;
import powerUps.PowerUpOrDown;

/**
 *
 * @author micha
 */
public class Board extends JPanel{
    private Game game;
    private Level level;
    private ScheduleLevelTasker s;

    public Board() {
        initBoard();
    }

    private void initBoard() {
        User me = new User(1, "coolboi", "blabla", "hipitiehoppitie", 99, "pepe");
        game = new SinglePlayerGame(me, 1000, 1000);
        addKeyListener(new TAdapter());
        setFocusable(true);
        setDoubleBuffered(true);
        level = game.getLevelPlayedRightNow();
        s = new ScheduleLevelTasker(level, this);
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
                s = new ScheduleLevelTasker(level, this);
                level.startLevel(s);
            }
        } else {
            gameFinished(g2d);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics2D g2d) {
        Level level = game.getLevels().get(game.getLevels().size() - 1);
        g2d.setColor(Color.BLUE);
        for (Pallet pallet : level.getPallets()) {
            g2d.fillRect(pallet.getX(), pallet.getY(), pallet.getLength(), pallet.getHeight());
        }
        g2d.setColor(Color.BLACK);
        for (Ball ball : level.getBalls()) {
            g2d.fillOval(ball.getX(), ball.getY(), ball.getRadius(), ball.getRadius());
        }
        
        for (BrickRow rowsOfBrick : level.getRowsOfBricks()) {
            for (Brick brick : rowsOfBrick.getBricksOnRow()) {
                g2d.setColor(Color.RED);
                g2d.fillRect(brick.getX(), brick.getY(), brick.getLength(), brick.getHeight());
                g2d.setColor(Color.BLACK);
                g2d.drawRect(brick.getX(), brick.getY(), brick.getLength(), brick.getHeight());
            }
        }
        for (PowerUpOrDown powerUp : level.getPowerUpsShownOnScreen()) {
            g2d.drawRect(powerUp.getBoundaries().getX(), powerUp.getBoundaries().getY(), powerUp.getBoundaries().getLength(), powerUp.getBoundaries().getHeight());
        }
    }
    
    private void drawTexts(Graphics2D g2d) {
        Font font = new Font("Verdana", Font.BOLD, 18);
        String levelNumber = "Level " + game.getLevels().size();
        String lives = "Lives x " + game.getLives();
        String score = "Score " + game.getPlayers().get(0).getScore();
        String scoreTotal = "Total Score " + game.getScore();

        g2d.setColor(Color.BLACK);
        g2d.setFont(font);
        g2d.drawString(levelNumber, 5, 20);
        g2d.drawString(lives, 5, 50);
        g2d.drawString(score, 5, 80);
        g2d.drawString(scoreTotal, 5, 110);
    }
    
    private void gameFinished(Graphics2D g2d) {
        Font font = new Font("Verdana", Font.BOLD, 18);
        String gameover = "Game over";
        String scoreThisLevel = "Your Score this Level " + game.getLevels().get(game.getLevels().size() - 1).getScore();
        String totalScore = "Your Total Score " + game.getScore();
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

}
