/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain;

import java.awt.Image;

/**
 *
 * @author micha
 */
public class Sprite {
   private String color;
  
    public Sprite(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

   
    public void setColor(String color) {
        this.color = color;
    }
}
