package lagilabra.miinanlakaisija;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Collections;
import lagilabra.miinanlakaisija.Pelilauta;
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
public class PelilautaTest {

    Pelilauta lauta;

    public PelilautaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        lauta = new Pelilauta(8);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void ruutujaLuodaanOikeaMaara() {
        assertEquals(64, lauta.getRuutujenMaara());
    }

    @Test
    public void miinojaLuodaanOikeaMaara() {
        assertEquals(10, lauta.getMiinojenMaara());
    }

    @Test
    public void miinatAsetetaanOikein() {
        lauta.asetaMiinat();
        int laskuri = 0;
        for (int i = 0; i < lauta.getSivunPituus(); i++) {
            for (int j = 0; j < lauta.getSivunPituus(); j++) {
                if (lauta.getPelilauta()[i][j] == -1) {
                    laskuri++;
                }
            }
        }
        assertEquals(10, laskuri);
    }

//    @Test
//    public void konstruktoriTekeePelilaudanOikein() {
//        lauta = new Pelilauta(2);
//        assertEquals("[[0,0], [0,1], [1,0], [1,1]]", lauta.getPelilaudanSisalto());
//    }
    
}
