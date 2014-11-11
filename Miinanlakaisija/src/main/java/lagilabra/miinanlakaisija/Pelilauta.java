package lagilabra.miinanlakaisija;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Pelilauta {

    Random random = new Random();
    int[][] pelilauta;
    int ruutujenMaara;
    int miinojenMaara;
    int laudanSivunPituus;

    public Pelilauta(int laudanSivunPituus) {
        this.pelilauta = new int[laudanSivunPituus][laudanSivunPituus];     // luodaan pelilauta-taulukko oikean kokoiseksi
        this.laudanSivunPituus = laudanSivunPituus;
        this.ruutujenMaara = laudanSivunPituus * laudanSivunPituus;         // lasketaan ruutujen lkm
        this.miinojenMaara = (int) ((this.ruutujenMaara) / 6.4);            // lasketaan miinojen lkm
        for (int i = 0; i < laudanSivunPituus; i++) {
            for (int j = 0; j < laudanSivunPituus; j++) {
                pelilauta[i][j] = 0;                            // asetetaan pelilaudan jokaiseen ruutuun oletusarvo 0 (tyhjä ruutu)
            }
        }
    }

    public void asetaMiinat() {
        int laskuri = 0;                    
        int x;
        int y;
        while (laskuri < miinojenMaara) {                       // laskuri ja while varmistavat, että asetamme oikean määrän miinoja 
            x = random.nextInt(laudanSivunPituus);              // satunnaiset ruudut
            y = random.nextInt(laudanSivunPituus);
            if (pelilauta[x][y] != -1) {                    // if-lauseen ehto varmistaa, ettemme luo samaan ruutuun miinaa kahdesti
                pelilauta[x][y] = -1;
                laskuri++;
            }
        }
    }

    public int[][] getPelilauta() {
        return pelilauta;
    }

//    public String getPelilaudanSisalto() {
//        return Arrays.toString(pelilauta);
//    } 
    
    public int getRuutujenMaara() {
        return ruutujenMaara;
    }

    public int getMiinojenMaara() {
        return miinojenMaara;
    }

    public int getSivunPituus() {
        return laudanSivunPituus;
    }
}
