/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author micha
 */
public class MultiPlayerGame extends Game {

    public MultiPlayerGame(int height, int width, int aantalSpelers) {
        super(height, width, 2 * aantalSpelers, aantalSpelers);
    }
    
}
