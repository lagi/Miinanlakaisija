package lagilabra.kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import lagilabra.miinanlakaisija.Peli;

/**
 *
 * @author jaakkojo
 */
public class MiinanlakaisijaUI implements Runnable {

    private int rivit;
    private int sarakkeet;
    private int miinojenMaara;
    private JButton pelilauta[][];
    private JFrame frame;
    private Peli peli;

    public MiinanlakaisijaUI(int x, int y) {
        this.rivit = x;
        this.sarakkeet = y;
    }

    @Override
    public void run() {
        frame = new JFrame("Miinanlakaisija");
        frame.setPreferredSize(new Dimension(300, 300));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        pelilauta = new JButton[rivit][sarakkeet];
        container.add(luoPaneeli());
    }

    private JPanel luoPaneeli() {
        JPanel panel = new JPanel(new GridLayout(rivit, sarakkeet));
        for (int x = 0; x < pelilauta.length; x++) {
            for (int y = 0; y < pelilauta[x].length; y++) {
                pelilauta[x][y] = new JButton();
                JButton button = pelilauta[x][y];
                button.setPreferredSize(new Dimension(30, 30));
                button.setLocation(x * 30 + 1, y * 30 + 1);
                panel.add(button);
            }
        }
        return panel;
    }

}
