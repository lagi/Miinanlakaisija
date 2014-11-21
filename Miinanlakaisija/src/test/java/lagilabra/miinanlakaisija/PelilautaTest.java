package lagilabra.miinanlakaisija;

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
        lauta.aloitaPeli(0, 0);
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
    public void ensimmainenAvattuRuutuEiVoiOllaMiina() {
        lauta.aloitaPeli(0, 0);
        boolean eiMiina;
        eiMiina = lauta.getPelilauta()[0][0] != -1;
        assertEquals(true, eiMiina);
    }

    @Test
    public void ruutujenArvotAsetetaanOikein() {
        lauta.getPelilauta()[7][7] = -1;
        lauta.getPelilauta()[4][4] = -1;
        lauta.asetaYmparoivienRuutujenArvot(7, 7);
        lauta.asetaYmparoivienRuutujenArvot(4, 4);
        boolean oikein = true;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (!(i == 0 && j == 0) && lauta.getPelilauta()[4 + i][4 + j] != 1) {
                    oikein = false;
                }
            }
        }
        assertEquals(true, oikein);
    }

    @Test
    public void vierekkaistenRuutujenArvotAsetetaanOikein() {
        lauta.getPelilauta()[0][0] = -1;
        lauta.getPelilauta()[1][0] = -1;
        lauta.getPelilauta()[2][0] = -1;
        lauta.asetaYmparoivienRuutujenArvot(0, 0);
        lauta.asetaYmparoivienRuutujenArvot(1, 0);
        lauta.asetaYmparoivienRuutujenArvot(2, 0);
        boolean apu = true;
        if (lauta.getPelilauta()[1][1] != 3 || lauta.getPelilauta()[0][1] != 2 || lauta.getPelilauta()[2][1] != 2) {
            apu = false;
        }
        assertEquals(true, apu);
    }

    @Test
    public void josRuudussaOnMiinaSenArvoEiMuutu() {
        lauta.getPelilauta()[1][1] = -1;
        lauta.getPelilauta()[1][2] = -1;
        lauta.asetaYmparoivienRuutujenArvot(1, 1);
        lauta.asetaYmparoivienRuutujenArvot(1, 2);
        boolean oikein = true;
        if (lauta.getPelilauta()[1][1] != -1 || lauta.getPelilauta()[1][2] != -1) {
            oikein = false;
        }
        assertEquals(true, oikein);
    }

    @Test
    public void rajojenSisallaToimii() {
        assertEquals(false, lauta.rajojenSisalla(8, 8));
    }
}
