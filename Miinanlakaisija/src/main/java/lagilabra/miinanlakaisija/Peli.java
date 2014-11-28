package lagilabra.miinanlakaisija;

public class Peli {

    private Pelilauta lauta;
    private long aloitusAika;
    private long lopetusAika;

    public Peli(int vaikeustaso) {
        if (vaikeustaso == 8) {
            lauta = new Pelilauta(8);
        } else if (vaikeustaso == 16) {
            lauta = new Pelilauta(16);
        } else if (vaikeustaso == 24) {
            lauta = new Pelilauta(24);
        }
    }

    public void aloita(int x, int y) {
        lauta.aloitaPeli(x, y);
        aloitusAika = System.currentTimeMillis();
    }
    
    public void lopeta() {
        
        if(lauta.lopetus()) {
            lopetusAika = System.currentTimeMillis();
        }
    }
    
    public Pelilauta getPelilauta() {
        return lauta;
    }
    
    
}