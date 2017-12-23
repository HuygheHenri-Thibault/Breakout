/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.collissionDetectors;

import be.howest.ti.breakout.domain.Circle;
import be.howest.ti.breakout.domain.Rectangle;

/**
 *
 * @author micha
 */
public class CollissionDetectorUtilities {
    
    public boolean checkCollissionBetweenRectangleAndCircle(Rectangle r, Circle c) {
        float distX = Math.abs(c.getX() - (r.getX()+r.getLength()/2)); 
        float distY = Math.abs(c.getY() - (r.getY()+r.getHeight()/2));
        
        if (distX > (r.getLength()/2 + c.getRadius())) { return false; }
        if (distY > (r.getHeight()/2 + c.getRadius())) { return false; }

        if (distX <= (r.getLength()/2)) { return true; } 
        if (distY <= (r.getHeight()/2)) { return true; }

        float dx=distX-r.getLength()/2; 
        float dy=distY-r.getHeight()/2;
        return (dx*dx+dy*dy<=(c.getRadius()*c.getRadius()));
    }
}
