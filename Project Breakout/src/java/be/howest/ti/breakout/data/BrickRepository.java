/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.data;

import be.howest.ti.breakout.domain.BrickData;
import java.util.List;

/**
 *
 * @author Brecht
 */
public interface BrickRepository {
    public List<BrickData> getAllBricks();
    public void addBrick(BrickData b);
    public void deleteBrick(BrickData b);
}
