/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaTesten;

import be.howest.ti.breakout.data.Repositories;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Brecht
 */
public class MySQLTesten {
    
    public MySQLTesten() {
    }
    
    @Test
    public void firstInsert(){
        int score = 2000;
        int id = Repositories.getHighscoreRepository().insertScoreIntoMultiplayerScores(score);
        assertEquals(1, id);
    }
    
    @Test
    public void secondAndThirdInsert(){
        int score = 2000;
        int id = Repositories.getHighscoreRepository().insertScoreIntoMultiplayerScores(score);
        assertEquals(2, id);
        int score2 = 3000;
        int id2 = Repositories.getHighscoreRepository().insertScoreIntoMultiplayerScores(score2);
        assertEquals(3, id2);
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
