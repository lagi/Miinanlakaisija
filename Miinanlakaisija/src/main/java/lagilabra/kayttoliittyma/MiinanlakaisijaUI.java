package lagilabra.kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import lagilabra.miinanlakaisija.Koordinaatti;
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
    private int laskuri;
    private final String[] irtoavat = {"jalkasi", "pääsi", "kätesi", "nenäsi", "hiuksesi", "otsasi", "varpaasi", "sormesi"};
    private final String[] irtoaminen = {"irtoamaan", "räjähtämään", "likvidoitumaan",
        "sulamaan", "palamaan poroksi", "dematerialisoitumaan", "amputoitumaan"};
    private final String[] korjaus = {"deamputoida", "rematerialisoida", "kuumaliimata kiinni", "kiinnittää"};
    private final String[] vaihtoehdot = {"Khyl!", "No en tod."};

    /**
     * Kysytään käyttäjältä haluttu vaikeustaso ja luodaan sen jälkeen ikkuna
     * pelilautaa varten.
     */
    @Override
    public void run() {
        vaikeusAsteKysely();
        laskuri = 0;
        frame = new JFrame("Miinanlakaisija");
        frame.setPreferredSize(new Dimension(rivit * sivunPituus + 10, sarakkeet * sivunPituus + 31));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    /**
     * Luo vaikeustason tiedusteluikkunan ja aloittaa uuden pelin halutulla
     * vaikeustasolla.
     */
    private void vaikeusAsteKysely() {
        Object[] vaikeusAsteet = {"Helppo: 8x8 ruutua",
            "Keskivaikea: 16x16 ruutua",
            "Vaikea: 24x24 ruutua"};
        int n = JOptionPane.showOptionDialog(frame,
                "Tervetuloa Miinanlakaisija-peliin. Kuinka hankalaa "
                + "haluat miinojen lakaisemisen olevan?",
                "Miinanlakaisija",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                vaikeusAsteet,
                vaikeusAsteet[2]);

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

    /**
     * Luodaan ruudut ruudukkoon, annetaan nimeksi napin koordinaatit ja
     * lisätään ne paneeliin.
     */
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
        int x = getRivi(apuRuutu.getName());
        int y = getSarake(apuRuutu.getName());

        if (painoitkoMiinaa(apuRuutu)) {
            pelinHavio();
        } else {
            HashSet<Koordinaatti> testi = peli.getPelilauta().getTyhjatNaapurit(x, y);
            avaaViereisetRuudut(testi);
            pelinVoitto();
        }
    }

    public void avaaViereisetRuudut(HashSet<Koordinaatti> viereiset) {
        int x, y = 0;
        for (int i = 0; i < laudanRuudutPaneeli.getComponentCount(); i++) {
            x = getRivi(laudanRuudutPaneeli.getComponent(i).getName());
            y = getSarake(laudanRuudutPaneeli.getComponent(i).getName());
            Koordinaatti apu = new Koordinaatti(x, y);
            if (viereiset.contains(apu)) {
                laudanRuudutPaneeli.getComponent(i).setVisible(false);
            }
        }
    }

    /**
     * Kertoo oliko viimeinen klikattu ruutu miinan sisältävä ruutu.
     *
     * @param button: Klikattu ruutu
     * @return true, jos ruudussa oli miina, muuten false
     */
    private boolean painoitkoMiinaa(JButton button) {
        String koordinaatit = button.getName();
        return peli.getPelilauta().getPelilauta()[getRivi(koordinaatit)][getSarake(koordinaatit)] == -1;

        // return peli.getPelilauta().miina(button.getName());
    }

    /**
     * Kasvattaa laskuria aina, kun klikattu ruutu ei ole miina. Kun laskuri
     * saavuttaa miinattomien ruutujen lukumäärän eli kun kaikki miinattomat
     * ruudut on avattu, ilmoittaa pelaajalle voitosta ja kysyy haluaako tämä
     * pelata uudelleen.
     */
    private void pelinVoitto() {
        laskuri++;
        if (laskuri == peli.getPelilauta().getRuutujenMaara() - peli.getPelilauta().getMiinojenMaara()) {
            int n = JOptionPane.showOptionDialog(frame.getContentPane(), "Haluatko pelata uudelleen?", "Voitit pelin!", 0, JOptionPane.YES_NO_OPTION, null, vaihtoehdot, null);
            if (n == JOptionPane.NO_OPTION) {
                System.exit(0);
            } else if (n == JOptionPane.YES_OPTION) {
                frame.removeAll();
                frame.dispose();
                run();
            }
        }
    }

    /**
     * Ilmoittaa pelin häviämisestä satunnaisesti vaihtuvalla ilmoituksella ja
     * kysyy, haluaako pelaaja pelata uudelleen.
     */
    private void pelinHavio() {
        int m = JOptionPane.showOptionDialog(frame.getContentPane(), "Räjähdys sai " + irtoavat[random.nextInt(irtoavat.length)] + " "
                + irtoaminen[random.nextInt(irtoaminen.length)] + " ja lakaisijasi hajosi!"
                + " Haluatko " + korjaus[random.nextInt(korjaus.length)] + "\nirroneet osasesi, korjata lakaisijasi ja pelata uudelleen?",
                "Hävisit pelin!", 0, JOptionPane.YES_NO_OPTION, null, vaihtoehdot, null);
        if (m == JOptionPane.NO_OPTION) {
            System.exit(0);
        } else if (m == JOptionPane.YES_OPTION) {
            frame.removeAll();
            frame.dispose();
            run();
        }

    }

}
