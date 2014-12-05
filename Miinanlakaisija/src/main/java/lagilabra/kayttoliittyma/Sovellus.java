
package lagilabra.kayttoliittyma;

/**
 *
 * @author Jaakko Jokinen
 */
public class Sovellus {

    /**
     * Luo uuden Miinanlakaisija-käyttöliittymän ja käynnistää sen.
     * @param args 
     */
    public static void main(String[] args) {
        MiinanlakaisijaUI sovellus = new MiinanlakaisijaUI();
        sovellus.run();
    }
}
