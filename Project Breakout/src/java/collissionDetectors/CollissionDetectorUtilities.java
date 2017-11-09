/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collissionDetectors;

import domain.Circle;
import domain.Rectangle;

/**
 *
 * @author micha
 */
public class CollissionDetectorUtilities {
    
    public boolean checkCollissionBetweenRectangleAndCircle(Rectangle r, Circle c) {
         // Find the vertical & horizontal (distX/distY) distances between the circle’s center and the rectangle’s center
        float distX = Math.abs(c.getX() - (r.getX()+r.getLength()/2)); 
        float distY = Math.abs(c.getY() - (r.getY()+r.getHeight()/2));
        
        //If the distance is greater than halfCircle + halfRect, then they are too far apart to be colliding
        if (distX > (r.getLength()/2 + c.getRadius())) { return false; }
        if (distY > (r.getHeight()/2 + c.getRadius())) { return false; } 

        //If the distance is less than halfRect then they are definitely colliding
        if (distX <= (r.getLength()/2)) { return true; } 
        if (distY <= (r.getHeight()/2)) { return true; }

        //Test for collision at rect corner.
        //Think of a line from the rect center to any rect corner
        //Now extend that line by the radius of the circle
        //If the circle’s center is on that line they are colliding at exactly that rect corner
        float dx=distX-r.getLength()/2; 
        float dy=distY-r.getHeight()/2;
        return (dx*dx+dy*dy<=(c.getRadius()*c.getRadius()));
    }
}
