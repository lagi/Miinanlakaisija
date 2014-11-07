package lagilabra.miinanlakaisija;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
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
    public void miinojenAsettajaListaLuotuOikein() {
        lauta.luoMiinojenAsettajalista();
        assertEquals(64, lauta.getMiinojenAsettajalista().size());
    }

//    @Test
//    public void miinojaAsetetaanOikeaMaara() {
//        lauta.asetaMiinat();
//        ArrayList<Integer> miinat = new ArrayList<>();
//        ArrayList<Ruutu> apu = lauta.getPelilauta();
//        for (int i = 0; i < lauta.getRuutujenMaara(); i++) {
//            if (apu.get(i).ruudussaMiina()) {
//                miinat.add(i);
//            }
//        }
//        assertEquals(10, miinat.size());
//    }

    @Test
    public void konstruktoriTekeePelilaudanOikein() {
        lauta = new Pelilauta(2);
        assertEquals("[x: 0, y: 0, miina: false, x: 0, y: 1, miina: false, x: 1, y: 0, miina: false, x: 1, y: 1, miina: false]", lauta.getPelilaudanSisalto());
    }
}
