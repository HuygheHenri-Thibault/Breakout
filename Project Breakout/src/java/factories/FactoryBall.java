/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factories;

import domain.Ball;
import domain.Game;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author micha
 */
public class FactoryBall extends FactoryBreakoutUtilities{
    private final Game game;

    public FactoryBall(Game game) {
        this.game = game;
    }
    
    public void createBall(){
        String colorPallet = findUnusedColor();
        int x = game.getMAX_GAME_BORDER_X() / 2;
        int y = (game.getMAX_GAME_BORDER_Y() / 10) * 8; 
        Ball b = new Ball(15, 5, colorPallet, x, y);
        game.getBalls().add(b);
    }
}
