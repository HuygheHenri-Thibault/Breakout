/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collissionDetectors;

import domain.Game;
import domain.Pallet;

/**
 *
 * @author micha
 */
public class CollissionDetectorPallet implements CollissionDetector{
    private final Pallet pallet;

    public CollissionDetectorPallet(Pallet pallet) {
        this.pallet = pallet;
    }
    
    @Override
    public void detect() {
        if(crossesLeftBorder()){
            pallet.setX(pallet.getMIN_PALLET_BORDER());
        } else if(crossesRightBorder()){
            pallet.setX(pallet.getMAX_PALLET_BORDER() - Math.round(pallet.getLength()));
        }
    }
    
    private boolean crossesLeftBorder(){
        return pallet.getX() < pallet.getMIN_PALLET_BORDER();
    }
    
    private boolean crossesRightBorder(){
        return pallet.getX() + pallet.getLength() > pallet.getMAX_PALLET_BORDER();
    }
}
