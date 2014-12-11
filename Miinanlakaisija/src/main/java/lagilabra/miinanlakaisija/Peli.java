package lagilabra.miinanlakaisija;

import java.text.DecimalFormat;

public class Peli {

    private Pelilauta lauta;
    private long aloitusAika;
    private long lopetusAika;

    /**
     * Luodaan uusi Peli-olio.
     *
     * @param vaikeustaso, joka on sama kuin pelilaudan sivun pituus.
     */
    public Peli(int vaikeustaso) {
        if (vaikeustaso == 8) {
            lauta = new Pelilauta(8);
        } else if (vaikeustaso == 16) {
            lauta = new Pelilauta(16);
        } else if (vaikeustaso == 24) {
            lauta = new Pelilauta(24);
        }
    }

    /**
     * Aloittaa pelin.
     *
     * @param x: ensimmäisen klikkauksen x-koordinaatti
     * @param y: ensimmäisen klikkauksen y-koordinaatti
     *
     * @see Pelilauta.aloitaPeli(int, int)
     */
    public void aloita(int x, int y) {
        lauta.aloitaPeli(x, y);
    }

    /**
     * Aloittaa ajanoton.
     */
    public void aloitaAjanOtto() {
        aloitusAika = System.currentTimeMillis();
    }

    /**
     * Lopettaa ajanoton.
     */
    public void lopetaAjanOtto() {
        lopetusAika = System.currentTimeMillis();
    }

    public Pelilauta getPelilauta() {
        return lauta;
    }

    /**
     * Laskee pelin ratkaisemiseen käytetyn ajan ja muuntaa sen mukavaan
     * desimaalimuotoon.
     *
     * @return Ratkaisemiseen käytetty aika
     */
    public String kaytettyAika() {
        DecimalFormat format = new DecimalFormat("#.#");
        return format.format(((double) (lopetusAika) - (double) (aloitusAika)) / 1000);
    }

}
