/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lagilabra.kayttoliittyma;

/**
 *
 * @author jaakkojo
 */
public class Sovellus {

    public static void main(String[] args) {
        MiinanlakaisijaUI sovellus = new MiinanlakaisijaUI(8, 8);
        sovellus.run();
    }
}