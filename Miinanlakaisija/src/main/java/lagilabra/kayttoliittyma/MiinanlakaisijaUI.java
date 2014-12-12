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

    private JFrame frame;
    private JPanel ruudukkoPaneeli;
    private JPanel painikkeet;
    private boolean peliAlkanut = false;

    private JButton ruutuGrid[][];
    private JTextField ruutujenArvot[][];
    private JButton ruutu;
    private JTextField ruudunArvo;

    private Peli peli;
    private final Random random = new Random();

    private int rivit;
    private int sarakkeet;
    private final int sivunPituus = 30;
    private final Dimension dimensio = new Dimension(sivunPituus, sivunPituus);

    private final String[] irtoavat = {"jalkasi", "pääsi", "kätesi", "nenäsi", "hiuksesi", "otsasi", "varpaasi", "sormesi"};
    private final String[] irtoaminen = {"irtoamaan", "räjähtämään", "likvidoitumaan",
        "sulamaan", "palamaan poroksi", "dematerialisoitumaan", "amputoitumaan"};
    private final String[] korjaus = {"deamputoida", "rematerialisoida", "kuumaliimata kiinni", "kiinnittää"};
    private final String[] vaihtoehdot = {"Khyl!", "No en tod."};
    private final Koordinaatti koordinaatti = new Koordinaatti(0, 0);

    /**
     * Kysytään käyttäjältä haluttu vaikeustaso ja luodaan sen jälkeen ikkuna
     * pelilautaa varten.
     */
    @Override
    public void run() {
        vaikeusAsteKysely();
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
        ruutuGrid = new JButton[rivit][sarakkeet];
        ruutujenArvot = new JTextField[rivit][sarakkeet];
        JLayeredPane kerrosPane = new JLayeredPane();

        luoRuudukkoPaneeli();
        luoLaudanRuudutPaneeli();
        kerrosPane.add(painikkeet);
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
        painikkeet = new JPanel(new GridLayout(rivit, sarakkeet));
        painikkeet.setSize(new Dimension(rivit * sivunPituus, sarakkeet * sivunPituus));
        luoLaudanRuudut();
        painikkeet.setOpaque(false);
        painikkeet.setVisible(true);

    }

    /**
     * Luodaan ruudut ruudukkoon, annetaan nimeksi napin koordinaatit ja
     * lisätään ne paneeliin.
     */
    private void luoLaudanRuudut() {
        for (int x = 0; x < ruutuGrid.length; x++) {
            for (int y = 0; y < ruutuGrid[x].length; y++) {
                ruutuGrid[x][y] = new JButton();
                ruutu = ruutuGrid[x][y];
                ruutu.setSize(dimensio);
                ruutu.setLocation(x * sivunPituus, y * sivunPituus);
                painikkeet.add(ruutu);
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
        luoRuutujenArvotGrid();
    }

    /**
     * Luo ruudukon jo generoidun pelilaudan mukaiseksi.
     */
    private void luoRuutujenArvotGrid() {
        for (int x = 0; x < rivit; x++) {
            for (int y = 0; y < sarakkeet; y++) {
                ruutujenArvot[x][y] = new JTextField();
                ruudunArvo = ruutujenArvot[x][y];
                ruudunArvo.setHighlighter(null);
                ruudunArvo.setEditable(false);
                ruudunArvo.setHorizontalAlignment(JTextField.CENTER);
                ruudunArvo.setSize(dimensio);
                ruudunArvo.setLocation(x * sivunPituus, y * sivunPituus);
                ruudukkoPaneeli.add(ruudunArvo);
            }
        }
    }

    /**
     * Asetetaan ruuduille arvot kun ensimmäinen klikkaus on suoritettu.
     *
     * @param x
     * @param y
     */
    private void asetaRuutujenArvot(int x, int y) {
        peli.aloita(x, y);
        for (int i = 0; i < rivit; i++) {
            for (int j = 0; j < sarakkeet; j++) {
                if (peli.getPelilauta().getPelilauta()[i][j] == -1) {
                    ruutujenArvot[i][j].setText("●");
                } else if (peli.getPelilauta().getPelilauta()[i][j] == 0) {
                    ruutujenArvot[i][j].setText("");
                } else {
                    ruutujenArvot[i][j].setText(Integer.toString(peli.getPelilauta().getPelilauta()[i][j]));
                }
            }
        }

    }

    /**
     * Asettaa ruudun näkymättömäksi sitä painettaessa. Tarkistaa myös mitä
     * ruudussa on: jos ruudussa on miina, niin ilmoitetaan häviöstä.
     *
     * @param toiminto
     * @see lagilabra.kayttoliittyma.MiinanlakaisijaUI#pelinHavio(JButton)
     */
    @Override
    public void actionPerformed(ActionEvent toiminto) {
        JButton apuRuutu = (JButton) toiminto.getSource();
        apuRuutu.setVisible(false);

        int x = koordinaatti.getRivi(apuRuutu.getName());
        int y = koordinaatti.getSarake(apuRuutu.getName());
        if (!peliAlkanut) {
            asetaRuutujenArvot(x, y);
            peliAlkanut = true;
        }

        if (peli.getPelilauta().painoitkoMiinaa(apuRuutu)) {
            pelinHavio();
        } else {
            HashSet<Koordinaatti> viereiset = peli.getPelilauta().getTyhjatNaapurit(x, y);
            avaaViereisetRuudut(viereiset);
            peli.getPelilauta().laskeAvatutRuudut(painikkeet);
            peliEtenee();
        }

    }

    /**
     * Asettaa listan painikkeet näkymättömiksi.
     *
     * @param viereiset
     */
    private void avaaViereisetRuudut(HashSet<Koordinaatti> viereiset) {
        int x, y;
        for (int i = 0; i < painikkeet.getComponentCount(); i++) {
            x = koordinaatti.getRivi(painikkeet.getComponent(i).getName());
            y = koordinaatti.getSarake(painikkeet.getComponent(i).getName());
            Koordinaatti apu = new Koordinaatti(x, y);
            if (viereiset.contains(apu)) {
                painikkeet.getComponent(i).setVisible(false);
            }
        }
    }

    /**
     * Jos avattuja ruutuja laskeva laskuri saavuttaa ei-miinallisten ruutujen
     * määrän, niin peli on läpäisty ja peli ilmoittaa pelaajalle voitosta.
     */
    private void peliEtenee() {
        if (peli.getPelilauta().getLaskuri() == peli.getPelilauta().getRuutujenMaara() - peli.getPelilauta().getMiinojenMaara()) {
            peli.lopeta();
            int n = JOptionPane.showOptionDialog(frame.getContentPane(), "Käytit tähän vain " + peli.kaytettyAika()
                    + " sekuntia!\nHaluatko pelata uudelleen?", "Voitit pelin!", 0, JOptionPane.YES_NO_OPTION, null, vaihtoehdot, null);
            if (n == JOptionPane.NO_OPTION) {
                System.exit(0);
            } else if (n == JOptionPane.YES_OPTION) {
                frame.removeAll();
                frame.dispose();
                peliAlkanut = false;
                run();
            } else if (n == JOptionPane.CLOSED_OPTION) {
                System.exit(0);
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
            peliAlkanut = false;
            run();
        } else if (m == JOptionPane.CLOSED_OPTION) {
            System.exit(0);
        }

    }

}
