package lagilabra.miinanlakaisija;

import java.util.ArrayList;
import java.util.Collections;

public class Pelilauta {

    ArrayList<Ruutu> pelilauta = new ArrayList<>();
    ArrayList<Integer> satunnaislukulista = new ArrayList<>();
    int ruutujenMaara;
    int miinojenMaara;

    public Pelilauta(int laudanSivunPituus) {
        this.ruutujenMaara = laudanSivunPituus*laudanSivunPituus;
        this.miinojenMaara = (int)((this.ruutujenMaara)/6.4);
        for (int i = 0; i < laudanSivunPituus; i++) {
            for (int j = 0; j < laudanSivunPituus; j++) {
                pelilauta.add(new Ruutu(i, j));
            }
        }
    }
    
    public void luoMiinojenAsettajalista() {
        for (int i = 0; i < ruutujenMaara; i++) {
            satunnaislukulista.add(i);
        }
        Collections.shuffle(satunnaislukulista);
    }
    
    public ArrayList getMiinojenAsettajalista() {
        return satunnaislukulista;
    }

    public void asetaMiinat() {
        for (int i = 0; i < miinojenMaara; i++) {
            pelilauta.get(satunnaislukulista.get(i)).setMiina();
        }
    }
    
    public ArrayList getPelilauta() {
        return pelilauta;
    }
    
    public String getPelilaudanSisalto() {
        return pelilauta.toString();
    }
    
    public int getRuutujenMaara() {
        return ruutujenMaara;
    }
    
    public int getMiinojenMaara() {
        return miinojenMaara;
    }
}
