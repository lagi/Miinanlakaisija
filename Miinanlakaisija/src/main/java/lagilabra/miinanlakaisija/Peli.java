package lagilabra.miinanlakaisija;

public class Peli {

    Pelilauta lauta;

    public Peli(String vaikeustaso) {
        if (vaikeustaso.equals("helppo")) {
            lauta = new Pelilauta(8);
        } else if (vaikeustaso.equals("keskivaikea")) {
            lauta = new Pelilauta(16);
        } else if (vaikeustaso.equals("vaikea")) {
            lauta = new Pelilauta(24);
        }
    }

    public void aloita(int x, int y) {
        lauta.aloitaPeli(x, y);
    }
    
    public Pelilauta getPelilauta() {
        return lauta;
    }
}