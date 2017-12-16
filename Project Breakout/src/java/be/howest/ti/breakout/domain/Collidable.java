/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain;

/**
 *
 * @author micha
 */
public interface Collidable {
    public boolean checkCollissionWithRect(Rectangle rect);
    public boolean checkCollissionWithCircle(Circle c);
    public boolean checkCollission(Shape s);
    public void updateSpriteBall(Ball aBall);
    public void updateSpritePallet(Pallet p);
}
