/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.factories;

import be.howest.ti.breakout.domain.game.Level;
import be.howest.ti.breakout.domain.Pallet;
import be.howest.ti.breakout.domain.game.Player;
import be.howest.ti.breakout.domain.game.User;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author micha
 */
public class FactoryPallet extends FactoryBreakoutUtilities{
   private final Level level;

    public FactoryPallet(Level level) {
        this.level = level;
    }

    public List<Pallet> createPallets() {
        List<Pallet> pallets = new ArrayList<>();
        for (int i = 0; i < level.getAantalSpelers(); i++) {
            String colorPallet = findUnusedColor();
            int lenght = Math.round((level.getGameWidth() / 8));
            lenght -= ((lenght / 20) * level.getAantalSpelers());
            lenght = Math.round(lenght * level.getRatios().get(0).getRatio());
                                                                                
            int startX = level.getGameWidth() / level.getAantalSpelers() / 2; 
            int nextXDistance = level.getGameWidth() / level.getAantalSpelers(); 
            int multiplierDistance = pallets.size(); 
            
            int x =  startX + (nextXDistance * multiplierDistance) - (lenght / 2);
            int y = (level.getGameHeight()/ 10) * 9;
            
            Player player = level.getPlayers().get(i);
         
            Pallet p = new Pallet(player, colorPallet, level, x, y, lenght, 5);
            pallets.add(p);
        }
        return pallets;
    }
}
