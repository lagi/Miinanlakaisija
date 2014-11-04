package lagilabra.miinanlakaisija;

import java.util.ArrayList;
import java.util.Random;

public class Pelilauta {

    ArrayList<Ruutu> pelilauta = new ArrayList<Ruutu>();
    Random random = new Random();

    public Pelilauta(int laudanKoko) {
        for (int i = 0; i < laudanKoko; i++) {
            for (int j = 0; j < laudanKoko; j++) {
                pelilauta.add(new Ruutu(i,j));
            }
        }
        
    }

}
