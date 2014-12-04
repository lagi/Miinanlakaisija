package lagilabra.miinanlakaisija;

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
     * @param x: ensimmäisen klikkauksen x-koordinaatti
     * @param y: ensimmäisen klikkauksen y-koordinaatti
     * 
     * @see Pelilauta.aloitaPeli(int, int)
     */
    public void aloita(int x, int y) {
        lauta.aloitaPeli(x, y);
        aloitusAika = System.currentTimeMillis();
    }

    /**
     * Lopettaa pelin.
     */
    public void lopeta() {

        if (lauta.lopetus()) {
            lopetusAika = System.currentTimeMillis();
        }
    }

    public Pelilauta getPelilauta() {
        return lauta;
    }

}
