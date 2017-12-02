/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author micha
 */
public class SinglePlayerGame extends Game{
    
    public SinglePlayerGame(User player, int height, int width) {
        super(new ArrayList<User>(Arrays.asList(player)), height, width, 3, 1);
    }
    
    
}
