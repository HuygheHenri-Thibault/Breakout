/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factories;

import domain.Brick;
import domain.Game;
import domain.Pallet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author micha
 */
public class FactoryPallet extends FactoryBreakoutUtilities{
    private final Game game;
    private final int gameBoardRatioSplit;

    public FactoryPallet(Game game) {
        this.game = game;
        this.gameBoardRatioSplit = game.getMAX_GAME_BORDER_X() / game.getAantalSpelers();
    }

    public void createPallets() {
        for (int i = 0; i < game.getAantalSpelers(); i++) {
            String colorPallet = findUnusedColor();
            int lenght = game.getMAX_GAME_BORDER_X() / 4 - (game.getAantalSpelers() - 1) * 50;
            
            int min_pallet_border = (gameBoardRatioSplit + getGameBoardSplitPerPallet()) - gameBoardRatioSplit;
            int max_pallet_border = gameBoardRatioSplit + getGameBoardSplitPerPallet();
            
            int x = (gameBoardRatioSplit + getGameBoardSplitPerPallet()) - (lenght / 2);
            int y = game.getMAX_GAME_BORDER_Y() - (game.getMAX_GAME_BORDER_Y()/ 10);
         
            Pallet p = new Pallet(colorPallet, x, y, lenght, 5, min_pallet_border, max_pallet_border);
            game.getPallets().add(p);
        }
    }

    private int getGameBoardSplitPerPallet() {
        if(game.getPallets().size() > 0){
            return game.getPallets().get(game.getPallets().size() - 1).getMAX_PALLET_BORDER();    
        } else {
            return 0;
        }
    }
}
