/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.domain;

/**
 *
 * @author Henri
 */
public final class BrickData {
    private final int id;
    private final String color;
    private final int baseLen;
    private final int baseHits;
    private final int baseScore;
    
    public BrickData(int id, String color, int baseLen, int baseHits, int baseScore) {
        this.id = id;
        this.color = color;
        this.baseLen = baseLen;
        this.baseHits = baseHits;
        this.baseScore = baseScore;
    }
    
    public BrickData(String color, int baseLen, int baseHits, int baseScore) {
        this(-1, color, baseLen, baseHits, baseScore);
    }
    
    public int getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public int getBaseLen() {
        return baseLen;
    }

    public int getBaseHits() {
        return baseHits;
    }

    public int getBaseScore() {
        return baseScore;
    }
}
