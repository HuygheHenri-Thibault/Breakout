/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaTesten;

import domain.Game;
import domain.Level;
import domain.Pallet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author micha
 */
public class FactoriesTesten {
    
    public FactoriesTesten() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testRowOfBlocksForLevel(){
        Level l1 = new Level(1, 3, 0);
        int som = 0;
        for (int i = 0; i < l1.getRowOfBricks().size(); i++) {
            for (int j = 0; j < l1.getRowOfBricks().get(i).getBricksOnRow().size() ; j++) {
                som += l1.getRowOfBricks().get(i).getBricksOnRow().get(j).getLenght();
            }
        }
        assertEquals(500 * 5, som);
    }
    
    @Test
    public void testEenPalletjeMaken(){
        Game g = new Game(0, 1);
        int som = 0;
        for (int i = 0; i < g.getPallets().size(); i++) {
           som += g.getPallets().get(i).getMAX_PALLET_BORDER();
        }
        assertTrue(g.getPallets().get(0) instanceof Pallet);
        assertEquals(g.getMAX_GAME_BORDER_X(), som);
    }
    
    @Test
    public void testMeerderePalletjeMaken(){
        Game g = new Game(0, 4);
        int som = 0;
        for (int i = 0; i < g.getPallets().size(); i++) {
           som += g.getPallets().get(i).getMAX_PALLET_BORDER();
        }
        assertTrue(g.getPallets().get(0) instanceof Pallet);
        assertEquals(2500, som);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
