/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lagilabra.miinanlakaisija;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jaakkojo
 */
public class PeliTest {
    
    Peli peli;
    
    public PeliTest() {
    }
    
    @Before
    public void setUp() {
        peli = new Peli(8);
    }

    @Test
    public void helppoVaikeustasoToimii() {
        assertEquals(64, peli.getPelilauta().getRuutujenMaara());
    }
     
    @Test
    public void aloitaPeliAsettaaOikeanMaaranMiinojaHelppo() {
        peli.aloita(0, 0);
        assertEquals(10, peli.getPelilauta().getMiinojenMaara());
    }

    @Test
    public void keskivaikeaVaikeustasoToimii() {
        peli = new Peli(16);
        assertEquals(256, peli.getPelilauta().getRuutujenMaara());
    }
        
    @Test
    public void aloitaPeliAsettaaOikeanMaaranMiinojaKeskivaikea() {
        peli = new Peli(16);
        peli.aloita(0, 0);
        assertEquals(40, peli.getPelilauta().getMiinojenMaara());
    }
    
    @Test
    public void vaikeaVaikeustasoToimii() {
        peli = new Peli(24);
        assertEquals(576, peli.getPelilauta().getRuutujenMaara());
    }
        
    @Test
    public void aloitaPeliAsettaaOikeanMaaranMiinojaVaikea() {
        peli = new Peli(24);
        peli.aloita(0, 0);
        assertEquals(90, peli.getPelilauta().getMiinojenMaara());
    }
    
    @Test
    public void aloitaPeliEiAsetaMiinaaAloitusruutuun() {
        peli.aloita(0, 0);
        boolean eiMiinaa = peli.getPelilauta().getPelilauta()[0][0] != -1;
        assertEquals(true, eiMiinaa);
    }
}
