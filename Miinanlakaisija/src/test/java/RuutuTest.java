/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import lagilabra.miinanlakaisija.Ruutu;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author jaakkojo
 */
public class RuutuTest {
    
    Ruutu ruutu;
    
    public RuutuTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ruutu = new Ruutu(0, 1);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void konstruktoriAsettaaXKoordinaatinOikein() {
        assertEquals(0, ruutu.getX());
    }
    
    @Test
    public void konstruktoriAsettaaYKoordinaatinOikein() {
        assertEquals(1, ruutu.getY());
    }
    
    @Test
    public void ilmanSettaustaRuudussaEiMiinaa() {
        assertEquals(false, ruutu.ruudussaMiina());
    }
    
    @Test
    public void miinanAsettaminenToimii() {
        ruutu.setMiina();
        assertEquals(true, ruutu.ruudussaMiina());
    }
    
}
