package lagilabra.kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import lagilabra.miinanlakaisija.Peli;

/**
 *
 * @author jaakkojo
 */
public class MiinanlakaisijaUI implements Runnable, ActionListener {

    private int rivit;
    private int sarakkeet;
    private int miinojenMaara;
    private JButton pelilauta[][];
    private JTextField pelilauta2[][];
    private JButton ruutu;
    private JTextField ruutu2;
    private JFrame frame;
    private Peli peli;
    private final int dimensio = 50;
    private final Dimension d = new Dimension(dimensio, dimensio);

    public MiinanlakaisijaUI(int vaikeustaso) {
        this.rivit = vaikeustaso;
        this.sarakkeet = vaikeustaso;
        peli = new Peli(vaikeustaso);
    }

    @Override
    public void run() {
        frame = new JFrame("Miinanlakaisija");
        frame.setPreferredSize(new Dimension(rivit * dimensio + 10, sarakkeet * dimensio + 31));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setResizable(true);
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        pelilauta = new JButton[rivit][sarakkeet];
        pelilauta2 = new JTextField[rivit][sarakkeet];
        JLayeredPane lp = new JLayeredPane();
        lp.add(luoPaneeli(), 1);
        lp.add(luoPaneeli2(), 2);
        container.add(lp);
    }

    private JPanel luoPaneeli() {
        JPanel panel = new JPanel(new GridLayout(rivit, sarakkeet));
        panel.setSize(new Dimension(rivit * dimensio, sarakkeet * dimensio));
        peli.aloita(0, 0);
        for (int x = 0; x < pelilauta.length; x++) {
            for (int y = 0; y < pelilauta[x].length; y++) {
                pelilauta[x][y] = new JButton();
                ruutu = pelilauta[x][y];
                ruutu.setSize(d);
                ruutu.setLocation(x * dimensio, y * dimensio);
                panel.add(ruutu);
                ruutu.addActionListener(this);
                ruutu.setName(x + "," + y);
                ruutu.setVisible(true);
            }
        }
        panel.setOpaque(false);
        return panel;
    }

    private JPanel luoPaneeli2() {
        JPanel panel = new JPanel(new GridLayout(rivit, sarakkeet));
        panel.setSize(new Dimension(rivit * dimensio, sarakkeet * dimensio));
        peli.aloita(0, 0);
        for (int x = 0; x < pelilauta.length; x++) {
            for (int y = 0; y < pelilauta[x].length; y++) {
                pelilauta2[x][y] = new JTextField();
                ruutu2 = pelilauta2[x][y];
                ruutu2.setHighlighter(null);
                ruutu2.setEditable(false);
                ruutu2.setHorizontalAlignment(JTextField.CENTER);
                Dimension d = new Dimension(dimensio, dimensio);
                if (peli.getPelilauta().getPelilauta()[x][y] != -1) {
                    ruutu2.setText(Integer.toString(peli.getPelilauta().getPelilauta()[x][y]));
                } else {
                    ruutu2.setText("●");
                }
                ruutu2.setSize(d);
                ruutu2.setLocation(x * dimensio, y * dimensio);
                panel.add(ruutu2);
            }
        }
        return panel;
    }

    public int getRivi(String koordinaatti) {
        String out = koordinaatti.substring(0, koordinaatti.indexOf(','));
        return Integer.parseInt(out);
    }

    public int getSarake(String koordinaatti) {
        String out = koordinaatti.substring(koordinaatti.indexOf(',') + 1, koordinaatti.length());
        return Integer.parseInt(out);
    }

    @Override
    public void actionPerformed(ActionEvent toiminto) {
        JButton apuRuutu = (JButton) toiminto.getSource();
        apuRuutu.setVisible(false);
        System.out.println(painoitkoMiinaa(apuRuutu));
        System.out.println(getRivi(apuRuutu.getName()) + "," + getSarake(apuRuutu.getName()));
    }

    public boolean painoitkoMiinaa(JButton button) {
        return peli.getPelilauta().getPelilauta()[getRivi(button.getName())][getSarake(button.getName())] == -1;
    }

}
