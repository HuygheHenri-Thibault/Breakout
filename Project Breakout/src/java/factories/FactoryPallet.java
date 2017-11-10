/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factories;

import domain.Level;
import domain.Pallet;


/**
 *
 * @author micha
 */
public class FactoryPallet extends FactoryBreakoutUtilities{
    private final Level level;

    public FactoryPallet(Level level) {
        this.level = level;
    }

    public void createPallets() {
        for (int i = 0; i < level.getAantalSpelers(); i++) {
            String colorPallet = findUnusedColor();
            int lenght = Math.round((level.getGameWidth() / 8) * level.getRatios().get(0).getRatio());
                                                                                
            int startX = level.getGameWidth() / level.getAantalSpelers() / 2; 
            int nextXDistance = level.getGameWidth() / level.getAantalSpelers(); 
            int multiplierDistance = level.getPallets().size(); 
            
            int x =  startX + (nextXDistance * multiplierDistance) - (lenght / 2);
            int y = (level.getGameHeight()/ 10) * 9;
         
            Pallet p = new Pallet(colorPallet, level, x, y, lenght, 1);
            level.getPallets().add(p);
        }
    }
}
