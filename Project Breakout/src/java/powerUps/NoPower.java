/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package powerUps;

import domain.Ball;
import domain.Brick;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author micha
 */
public class NoPower extends PowerUpOrDown{

    public NoPower() {
    }

    @Override
    public void show() {
    }
    
    @Override
     public List<Effect> getEffects() {
        return new ArrayList<>();
    }
     
      @Override
    public void activate(){
       
    }
    
    @Override
    public void deActivate(){
       
    }
}
