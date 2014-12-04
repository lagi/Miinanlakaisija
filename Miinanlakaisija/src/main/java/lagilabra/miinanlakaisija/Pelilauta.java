package lagilabra.miinanlakaisija;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Pelilauta {

    private Random random = new Random();
    private int[][] pelilauta;
    private int ruutujenMaara;
    private int miinojenMaara;
    private int laudanSivunPituus;

    /**
     * Konstruktorissa luodaan pelilauta-taulukko oikean kokoiseksi, lasketaan
     * ruutujen ja miinojen lukumäärä sekä asetetaan pelilaudan jokaiseen
     * ruutuun oletusarvo 0.
     *
     * @param laudanSivunPituus, joka tullaan antamaan metodille pelin alussa
     */
    public Pelilauta(int laudanSivunPituus) {
        this.pelilauta = new int[laudanSivunPituus][laudanSivunPituus];
        this.laudanSivunPituus = laudanSivunPituus;
        this.ruutujenMaara = laudanSivunPituus * laudanSivunPituus;
        this.miinojenMaara = (int) ((this.ruutujenMaara) / 6.4);
        for (int i = 0; i < laudanSivunPituus; i++) {
            for (int j = 0; j < laudanSivunPituus; j++) {
                pelilauta[i][j] = 0;
            }
        }
    }

    /**
     * Metodi asettaa pelilaudalle miinat satunnaisiin ruutuihin. While-silmukka
     * varmistaa, että miinoja asetetaan oikea määrä ja if-lause sen, ettemme
     * luo kahdesti miinaa samaan ruutuun. Varmistetaan myös, että ensimmäisenä
     * klikatussa ruudussa ei voi olla miinaa.
     *
     * @param ekaX: ensimmäisenä klikatun ruudun x-koordinaatti
     * @param ekaY: ensimmäisenä klikatun ruudun y-koordinaatti
     *
     * @see Pelilauta#asetaYmparoivienRuutujenArvot(int, int)
     */
    public void aloitaPeli(int ekaX, int ekaY) {
        int laskuri = 0;
        int x;
        int y;
        while (laskuri < miinojenMaara) {
            x = random.nextInt(laudanSivunPituus);
            y = random.nextInt(laudanSivunPituus);
            if (pelilauta[x][y] != -1 && x != ekaX && y != ekaY) {
                pelilauta[x][y] = -1;
                laskuri++;
                asetaYmparoivienRuutujenArvot(x, y);
            }
        }
    }

    /**
     * Asetetaan annetun ruudun ympäröiville ruuduille oikeat arvot sen mukaan,
     * kuinka monta miinaa ruudun vieressä on. If-lause varmistaa, ettemme muuta
     * itse ruudun arvoa emmekä sellaisen ruudun arvoa, jossa on jo miina.
     *
     * @param x
     * @param y
     */
    public void asetaYmparoivienRuutujenArvot(int x, int y) {
        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                if (!(i == x && j == y) && rajojenSisalla(i, j) && pelilauta[i][j] != -1) {
                    pelilauta[i][j]++;
                }
            }
        }
    }

    /**
     * Tarkistetaan, että annetut koordinaatit ovat pelilaudan rajojen
     * sisäpuolella.
     *
     * @param x
     * @param y
     * @return false, jos koordinaatit ovat rajojen ulkopuolella
     */
    public boolean rajojenSisalla(int x, int y) {
        return !(x >= laudanSivunPituus || x < 0 || y >= laudanSivunPituus || y < 0);
    }

    public int[][] getPelilauta() {
        return pelilauta;
    }

    public int getRuutujenMaara() {
        return ruutujenMaara;
    }

    public int getMiinojenMaara() {
        return miinojenMaara;
    }

    public int getSivunPituus() {
        return laudanSivunPituus;
    }

    public boolean lopetus() {
        return true;
    }
}
