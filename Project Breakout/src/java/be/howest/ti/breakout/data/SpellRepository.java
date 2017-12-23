/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.data;

import java.util.List;
import be.howest.ti.breakout.domain.spells.BijvoegelijkNaamwoord;
import be.howest.ti.breakout.domain.spells.Woord;
import be.howest.ti.breakout.domain.spells.ZelfstandigNaamwoord;

/**
 *
 * @author micha
 */
public interface SpellRepository {
    public List<Woord> getAllWords();
    public List<ZelfstandigNaamwoord> getAllZelfstandigeNaamwoorden(); 
    public List<BijvoegelijkNaamwoord> getAllBijvoegelijkeNaamwoorden();
}
