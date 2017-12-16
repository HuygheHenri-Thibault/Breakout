/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.data;

import be.howest.ti.breakout.data.util.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import be.howest.ti.breakout.domain.powerUps.EffectQuickerPallet;
import be.howest.ti.breakout.domain.powerUps.EffectExtraBall;
import be.howest.ti.breakout.domain.powerUps.EffectStraightDownBall;
import be.howest.ti.breakout.domain.powerUps.EffectBiggerPallet;
import be.howest.ti.breakout.domain.powerUps.EffectSmallerPallet;
import be.howest.ti.breakout.domain.powerUps.EffectSlowerPallet;
import be.howest.ti.breakout.domain.powerUps.EffectOneLifeLeft;
import be.howest.ti.breakout.domain.powerUps.NoEffect;
import be.howest.ti.breakout.spells.BijvoegelijkNaamwoord;
import be.howest.ti.breakout.spells.Woord;
import be.howest.ti.breakout.spells.ZelfstandigNaamwoord;
import be.howest.ti.breakout.util.BreakoutException;

/**
 *
 * @author micha
 */
public class MySQLSpellRepository implements SpellRepository{
    
    private static final String SELECT_ALL_WORDS = "select * from spell";
    
    //voor test
    private List<ZelfstandigNaamwoord> zelfstandigeNaamWoorden;
    private List<BijvoegelijkNaamwoord> bijvoegelijkeNaamwoorden;

    public MySQLSpellRepository() {
        zelfstandigeNaamWoorden = Arrays.asList(new ZelfstandigNaamwoord("chicken", 1, "fire", new EffectSlowerPallet("slowerPallet", 5)),
                new ZelfstandigNaamwoord("tsunami", 2, "water", new EffectSlowerPallet("slowerPallet", 5))
        );
        
        bijvoegelijkeNaamwoorden = Arrays.asList(new BijvoegelijkNaamwoord("fire", 2, "fire", new EffectQuickerPallet("bullet time", 5)),
                new BijvoegelijkNaamwoord("roasted", 3, "fire", new EffectQuickerPallet("bullet time", 5)),
                new BijvoegelijkNaamwoord("stormy", 2, "water", new EffectQuickerPallet("bullet time", 5)),
                new BijvoegelijkNaamwoord("dangerous", 5, "darkness", new EffectQuickerPallet("bullet time", 5))
        );
    }
    //
    

    @Override
    public List<Woord> getAllWords() {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SELECT_ALL_WORDS);
            ResultSet rs = prep.executeQuery()){
            
            List<Woord> woorden = new ArrayList<>();
            while(rs.next()){
                
            }
            return woorden;
        } catch (SQLException ex) {
            throw new BreakoutException("couldn't get all woorden", ex);
        }
    }

    //voor test
    @Override
    public List<ZelfstandigNaamwoord> getHardcodedZelfstandigeNaamwoorden() {
        return Collections.unmodifiableList(zelfstandigeNaamWoorden);
    }
    
    @Override
    public List<BijvoegelijkNaamwoord> getHardCodedBijvoegelijkeNaamwoorden() {
        return Collections.unmodifiableList(bijvoegelijkeNaamwoorden);
    }
    //
    
}
