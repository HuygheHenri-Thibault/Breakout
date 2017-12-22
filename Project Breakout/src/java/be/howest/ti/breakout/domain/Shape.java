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
public abstract class Shape implements Collidable{
    private int x;
    private int y;
    
    public Shape(){
        
    }

    public Shape(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    
    @Override
    public boolean checkCollissionWithRect(Rectangle rect){
        return rect.checkCollission(this);
    }
    @Override
    public boolean checkCollissionWithCircle(Circle c){
        return c.checkCollission(this);
    }
    @Override
    public boolean checkCollission(Shape s){
        return s.checkCollission(s);
    }
    @Override
    public abstract void updateSpriteBall(Ball aBall);
    
    @Override
    public abstract void updateSpritePallet(Pallet p);
}
