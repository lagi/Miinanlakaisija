package lagilabra.miinanlakaisija;

import java.util.HashSet;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Pelilauta {

    private final Random random = new Random();
    private final int[][] pelilauta;
    private final int ruutujenMaara;
    private final int miinojenMaara;
    private final int laudanSivunPituus;
    private int laskuri;

    private final Koordinaatti koordinaatti = new Koordinaatti(0, 0);
    private HashSet<Koordinaatti> tyhjatNaapuritListaApu = new HashSet<>();
    private HashSet<Koordinaatti> avattavatRuudut = new HashSet<>();

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
        int apuLaskuri = 0;
        int x;
        int y;
        while (apuLaskuri < miinojenMaara) {
            x = random.nextInt(laudanSivunPituus);
            y = random.nextInt(laudanSivunPituus);
            if (pelilauta[x][y] != -1 && x != ekaX && y != ekaY) {
                pelilauta[x][y] = -1;
                apuLaskuri++;
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

    /**
     * Luo listan tyhjistä ruuduista.
     *
     * @param x
     * @param y
     */
    private void tyhjatRuudut(int x, int y) {
        if (!tyhjaRuutu(x, y)) {
            return;
        }
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                Koordinaatti tx = new Koordinaatti(x + i, y + j);
                if (tyhjaRuutu(x + i, y + j) && !tyhjatNaapuritListaApu.contains(tx)) {
                    tyhjatNaapuritListaApu.add(tx);
                    tyhjatRuudut(x + i, y + j);
                }
            }
        }
    }

    /**
     * Lisää tyhjien ruutujen naapurit listaan.
     *
     * @param x
     * @param y
     */
    private void tyhjatNaapuritListanViimeistely(int x, int y) {
        Koordinaatti apu = new Koordinaatti(x, y);
        if (tyhjatNaapuritListaApu.contains(apu)) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (rajojenSisalla(x + i, y + j)) {
                        avattavatRuudut.add(new Koordinaatti(x + i, y + j));
                    }
                }
            }
        }
    }

    /**
     * Käy kaikki ruudut läpi ja tekee tarvittavan toiminnon, jos ruutu on
     * tyhjä.
     *
     * @param x
     * @param y
     * @see
     * lagilabra.kayttoliittyma.MiinanlakaisijaUI#actionPerformed(java.awt.event.ActionEvent)
     */
    private void laskeKaikkiNaapurit(int x, int y) {
        tyhjatRuudut(x, y);
        for (int i = 0; i < laudanSivunPituus; i++) {
            for (int j = 0; j < laudanSivunPituus; j++) {
                tyhjatNaapuritListanViimeistely(i, j);
            }
        }
    }

    /**
     * Laskee avattujen ruutujen määrän jokaisen klikkauksen jälkeen.
     *
     * @param laudanRuudutPaneeli
     * @see
     * lagilabra.kayttoliittyma.MiinanlakaisijaUI#actionPerformed(java.awt.event.ActionEvent)
     */
    public void laskeAvatutRuudut(JPanel laudanRuudutPaneeli) {
        laskuri = 0;
        for (int i = 0; i < laudanRuudutPaneeli.getComponentCount(); i++) {
            if (!laudanRuudutPaneeli.getComponent(i).isVisible()) {
                laskuri++;
            }
        }
    }

    /**
     * Kertoo oliko viimeinen klikattu ruutu miinan sisältävä ruutu.
     *
     * @param button: Klikattu ruutu
     * @return true, jos ruudussa oli miina, muuten false
     */
    public boolean painoitkoMiinaa(JButton button) {
        String koordinaatit = button.getName();
        return pelilauta[koordinaatti.getRivi(koordinaatit)][koordinaatti.getSarake(koordinaatit)] == -1;
    }

    /**
     * Kertoo ovatko koordinaatit pelilaudalla ja onko ruudun arvo 0.
     *
     * @param x
     * @param y
     * @return true, jos koordinaatit pelilaudalla ja arvo 0, muuten false
     */
    private boolean tyhjaRuutu(int x, int y) {
        return rajojenSisalla(x, y) && pelilauta[x][y] == 0;
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

    public int getLaskuri() {
        return laskuri;
    }

    /**
     * Selvittää annetun ruudun tyhjät naapurit ja laskee niiden tyhjät naapurit
     * rekursiivisesti.
     *
     * @param x
     * @param y
     * @return Lista avattavista ruuduista.
     */
    public HashSet<Koordinaatti> getTyhjatNaapurit(int x, int y) {
        laskeKaikkiNaapurit(x, y);
        return avattavatRuudut;
    }
}
