/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lagilabra.kayttoliittyma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author jaakkojo
 */
public class MiinanlakaisijaUI extends JPanel {

    private final int rivit;
    private final int sarakkeet;
    private int miinojenMaara;
    private JButton pelilauta[][];
    private int curRow = 0;
    private int curCol = 0;

    public MiinanlakaisijaUI(int x, int y) {
        this.rivit = x;
        this.sarakkeet = y;
        setLayout(new GridLayout(rivit, sarakkeet));
        pelilauta = new JButton[rivit][sarakkeet];
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}