package lagilabra.miinanlakaisija;

import java.util.ArrayList;
import lagilabra.miinanlakaisija.Pelilauta;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PelilautaTest {

    Pelilauta lauta;

    public PelilautaTest() {
    }

    @Before
    public void setUp() {
        lauta = new Pelilauta(8);
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

    @Test
    public void ruutujenArvotAsetetaanOikein() {
        lauta.pelilauta[7][7] = -1;
        lauta.pelilauta[4][4] = -1;
        lauta.alustaLauta();
        boolean oikein = true;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (!(i == 0 && j == 0) && lauta.pelilauta[4 + i][4 + j] != 1) {
                    oikein = false;
                }
            }
        }
        assertEquals(true, oikein);
    }

    @Test
    public void vierekkaistenRuutujenArvotAsetetaanOikein() {
        lauta.pelilauta[0][0] = -1;
        lauta.pelilauta[1][0] = -1;
        lauta.pelilauta[2][0] = -1;
        lauta.alustaLauta();
        boolean apu = true;
        if (lauta.pelilauta[1][1] != 3 || lauta.pelilauta[0][1] != 2 || lauta.pelilauta[2][1] != 2) {
            apu = false;
        }
        assertEquals(true, apu);
    }

    @Test
    public void josRuudussaOnMiinaSenArvoEiMuutu() {
        lauta.pelilauta[1][1] = -1;
        lauta.pelilauta[1][2] = -1;
        lauta.alustaLauta();
        boolean oikein = true;
        if (lauta.pelilauta[1][1] != -1 || lauta.pelilauta[1][2] != -1) {
            oikein = false;
        }
        assertEquals(true, oikein);
    }

    @Test
    public void rajojenSisallaToimii() {
        assertEquals(false, lauta.rajojenSisalla(8, 8));
    }
}
