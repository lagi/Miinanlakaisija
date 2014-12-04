package lagilabra.kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import lagilabra.miinanlakaisija.Peli;

/**
 *
 * @author jaakkojo
 */
public class MiinanlakaisijaUI implements Runnable, ActionListener {

    private final Random random = new Random();
    private int rivit;
    private int sarakkeet;
    private JButton pelilauta[][];
    private JTextField pelilauta2[][];
    private JButton ruutu;
    private JTextField ruudunArvo;
    private JFrame frame;
    private Peli peli;
    private JPanel ruudukkoPaneeli;
    private JPanel laudanRuudutPaneeli;
    private final int sivunPituus = 30;
    private final Dimension dimensio = new Dimension(sivunPituus, sivunPituus);
    private int laskuri = 0;
    private final String[] irtoavat = {"jalkasi", "pääsi", "kätesi", "nenäsi", "hiuksesi", "otsasi", "varpaasi", "sormesi"};
    private final String[] irtoaminen = {"irtoamaan", "räjähtämään", "likvidoitumaan",
        "sulamaan", "palamaan poroksi", "dematerialisoitumaan", "amputoitumaan"};
    private final String[] korjaus = {"deamputoida", "rematerialisoida", "kuumaliimata kiinni", "kiinnittää"};

    /**
     * Alustetaan tarvittavat muuttujat ja luodaan uusi Peli-olio.
     *
     * @param vaikeustaso: halutun pelilaudan sivun pituus
     */
    public MiinanlakaisijaUI() {
    }

    /**
     * Kysytään käyttäjältä haluttu vaikeustaso ja
     * luodaan sen jälkeen ikkuna pelilautaa varten.
     */
    @Override
    public void run() {
        vaikeusAsteKysely();

        frame = new JFrame("Miinanlakaisija");
        frame.setPreferredSize(new Dimension(rivit * sivunPituus + 10, sarakkeet * sivunPituus + 31));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void vaikeusAsteKysely() {
        Object[] options = {"Helppo: 8x8 ruutua",
            "Keskivaikea: 16x16 ruutua",
            "Vaikea: 24x24 ruutua"};
        int n = JOptionPane.showOptionDialog(frame,
                "Tervetuloa Miinanlakaisija-peliin. Kuinka hankalaa "
                + "haluat miinojen lakaisemisen olevan?",
                "Miinanlakaisija",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[2]);
        if (n == JOptionPane.YES_OPTION) {
            peli = new Peli(8);
            rivit = sarakkeet = 8;
        } else if (n == JOptionPane.NO_OPTION) {
            peli = new Peli(16);
            rivit = sarakkeet = 16;
        } else if (n == JOptionPane.CANCEL_OPTION) {
            peli = new Peli(24);
            rivit = sarakkeet = 24;
        } else if (n == JOptionPane.CLOSED_OPTION) {
            System.exit(0);
        }
    }

    /**
     * Lisätään klikattavat ruudut ja niiden alle ruutujen arvot JLayeredPanen
     * avulla.
     *
     * @param container
     */
    private void luoKomponentit(Container container) {
        pelilauta = new JButton[rivit][sarakkeet];
        pelilauta2 = new JTextField[rivit][sarakkeet];
        JLayeredPane kerrosPane = new JLayeredPane();

        luoRuudukkoPaneeli();
        luoLaudanRuudutPaneeli();
        kerrosPane.add(laudanRuudutPaneeli);
        kerrosPane.add(ruudukkoPaneeli);
        container.add(kerrosPane);
    }

    /**
     * Luodaan paneeli, johon lisätään klikattavat ruudut ruudukkomuodossa.
     * Ruuduille annetaan myös nimeksi sen koordinaatit pelilaudalla.
     *
     * @return Paneeli, jossa klikattavat ruudut.
     */
    private void luoLaudanRuudutPaneeli() {
        laudanRuudutPaneeli = new JPanel(new GridLayout(rivit, sarakkeet));
        laudanRuudutPaneeli.setSize(new Dimension(rivit * sivunPituus, sarakkeet * sivunPituus));
        luoLaudanRuudut();
        laudanRuudutPaneeli.setOpaque(false);
        laudanRuudutPaneeli.setVisible(true);
    }

    private void luoLaudanRuudut() {
        for (int x = 0; x < pelilauta.length; x++) {
            for (int y = 0; y < pelilauta[x].length; y++) {
                pelilauta[x][y] = new JButton();
                ruutu = pelilauta[x][y];
                ruutu.setSize(dimensio);
                ruutu.setLocation(x * sivunPituus, y * sivunPituus);
                laudanRuudutPaneeli.add(ruutu);
                ruutu.addActionListener(this);
                ruutu.setName(x + "," + y);
                ruutu.setVisible(true);
                ruutu.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
            }
        }
    }

    /**
     * Luodaan ruutujen alle JTextField-ruudukko, joka sisältää tiedot ruutujen
     * arvoista.
     *
     * @return Paneeli, jossa ruutujen arvot.
     * @see luoRuudukko()
     */
    private void luoRuudukkoPaneeli() {
        ruudukkoPaneeli = new JPanel(new GridLayout(rivit, sarakkeet));
        ruudukkoPaneeli.setSize(new Dimension(rivit * sivunPituus, sarakkeet * sivunPituus));
        peli.aloita(random.nextInt(rivit), random.nextInt(sarakkeet));
        luoRuudukko();
    }

    /**
     * Luo ruudukon jo generoidun pelilaudan mukaiseksi.
     */
    private void luoRuudukko() {
        for (int x = 0; x < rivit; x++) {
            for (int y = 0; y < sarakkeet; y++) {
                pelilauta2[x][y] = new JTextField();
                ruudunArvo = pelilauta2[x][y];
                ruudunArvo.setHighlighter(null);
                ruudunArvo.setEditable(false);
                ruudunArvo.setHorizontalAlignment(JTextField.CENTER);
                if (peli.getPelilauta().getPelilauta()[x][y] == -1) {
                    ruudunArvo.setText("●");
                } else if (peli.getPelilauta().getPelilauta()[x][y] == 0) {
                    ruudunArvo.setText("");
                } else {
                    ruudunArvo.setText(Integer.toString(peli.getPelilauta().getPelilauta()[x][y]));
                }

                ruudunArvo.setSize(dimensio);
                ruudunArvo.setLocation(x * sivunPituus, y * sivunPituus);
                ruudukkoPaneeli.add(ruudunArvo);
            }
        }
    }

    /**
     * Selvittää ruudun x-koordinaatin.
     *
     * @param koordinaatti
     * @return Ruudun x-koordinaatti eli rivi.
     */
    private int getRivi(String koordinaatti) {
        String out = koordinaatti.substring(0, koordinaatti.indexOf(','));
        return Integer.parseInt(out);
    }

    /**
     * Selvittää ruudun y-koordinaatin.
     *
     * @param koordinaatti
     * @return Ruudun y-koordinaatti eli sarake.
     */
    private int getSarake(String koordinaatti) {
        String out = koordinaatti.substring(koordinaatti.indexOf(',') + 1, koordinaatti.length());
        return Integer.parseInt(out);
    }

    /**
     * Asettaa ruudun näkymättömäksi sitä painettaessa. Tarkistaa myös mitä
     * ruudussa on: jos ruudussa on miina, niin ilmoittaa häviöstä.
     *
     * @param toiminto
     * @see pelinHavio(JButton)
     */
    @Override
    public void actionPerformed(ActionEvent toiminto) {
        JButton apuRuutu = (JButton) toiminto.getSource();
        apuRuutu.setVisible(false);
        laskuri++;
        if (painoitkoMiinaa(apuRuutu)) {
            pelinHavio();
        } else {
            pelinVoitto();
        }
//        avaaViereisetRuudut(apuRuutu);
    }

    /**
     * Kertoo oliko viimeinen klikattu ruutu miinan sisältävä ruutu.
     *
     * @param button: Klikattu ruutu
     * @return true, jos ruudussa oli miina, muuten false
     */
    private boolean painoitkoMiinaa(JButton button) {
        return peli.getPelilauta().getPelilauta()[getRivi(button.getName())][getSarake(button.getName())] == -1;
    }

//    private void avaaViereisetRuudut(JButton ruutu) { // ei toimi vielä
//        int apuX = getRivi(ruutu.getName());
//        int apuY = getSarake(ruutu.getName());
//        for (int i = -1; i < 2; i++) {
//            for (int j = -1; j < 2; j++) {
//                if (peli.getPelilauta().rajojenSisalla(apuX + i, apuY + j) && peli.getPelilauta().getPelilauta()[apuX + i][apuY + j] == 0) {
//                    for (int k = 0; k < peli.getPelilauta().getRuutujenMaara(); k++) {
//                        if (getRivi(ruudukkoPaneeli.getComponent(k).getName()) == getRivi(ruutu.getName()) + i
//                                && getSarake(ruudukkoPaneeli.getComponent(k).getName()) == getSarake(ruutu.getName()) + j) {
//                            ruudukkoPaneeli.getComponent(k).setVisible(false);
//                        }
//                    }
//                }
//            }
//        }
//    }
    private void pelinVoitto() {
        if (laskuri == peli.getPelilauta().getRuutujenMaara() - peli.getPelilauta().getMiinojenMaara()) {
            int n = JOptionPane.showConfirmDialog(frame, "Haluatko pelata uudelleen?", "Voitit pelin!", JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.NO_OPTION) {
                System.exit(0);
            } else if (n == JOptionPane.YES_OPTION) {
                frame.dispose();
                run();
            }
        }
    }

    private void pelinHavio() {
        int m = JOptionPane.showConfirmDialog(frame, "Räjähdys sai " + irtoavat[random.nextInt(irtoavat.length)] + " "
                + irtoaminen[random.nextInt(irtoaminen.length)] + " ja lakaisijasi hajosi!"
                + " Haluatko " + korjaus[random.nextInt(korjaus.length)] + "\nirroneet osasesi, korjata lakaisijasi ja pelata uudelleen?",
                "Hävisit pelin!", JOptionPane.YES_NO_OPTION);
        if (m == JOptionPane.NO_OPTION) {
            System.exit(0);
        } else if (m == JOptionPane.YES_OPTION) {
            frame.removeAll();
            frame.dispose();
            run();
        }

    }

}
