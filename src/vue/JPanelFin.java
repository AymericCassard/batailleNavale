/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vue;

import java.awt.Component;
import java.awt.image.BufferedImage;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Box;

/**
 *
 * @author acassard
 */

public class JPanelFin extends javax.swing.JPanel {
    
    public enum Resultat{
    VICTOIRE,
    DEFAITE
    }
    private JButton JButtonRetourAccueil;
    
    /**
     * Creates new form JPanelFin
     */
    public JPanelFin() {
        initComponents();                                
        JButtonRetourAccueil = new JButton("Retour à l'accueil");
        JButtonRetourAccueil.setAlignmentX(Component.CENTER_ALIGNMENT);                       
    }
    
    public void generateJpanelFromResult(Resultat resultat){
        switch(resultat){
            case VICTOIRE:
                JLabel JLabelVictoryHeader = new JLabel("Bravo, vous avez détruit tous les bateaux de l'adversaire !");
                JLabelVictoryHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
                ImageIcon trophy = new ImageIcon(this.getClass().getResource("/resources/img/trophy.png"));
                JLabel JLabelTrophy = new JLabel(trophy);
                JLabelTrophy.setAlignmentX(Component.CENTER_ALIGNMENT);                

                this.add(Box.createVerticalGlue());
                this.add(JLabelVictoryHeader);
                this.add(Box.createVerticalGlue());
                this.add(JLabelTrophy);
                this.add(Box.createVerticalGlue());
                this.add(JButtonRetourAccueil);
                this.add(Box.createVerticalGlue());
                break;
            case DEFAITE:
                JLabel JLabelDefeatHeader = new JLabel("Vous avez perdu... \n Je ne pensais pas que c'était possible");
                JLabelDefeatHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
                ImageIcon tuxDefeat = new ImageIcon(this.getClass().getResource("/resources/img/defeatedTux.png"));
                JLabel JLabelTux = new JLabel(tuxDefeat);
                JLabelTux.setAlignmentX(Component.CENTER_ALIGNMENT);
                this.add(Box.createVerticalGlue());
                this.add(JLabelDefeatHeader);
                this.add(Box.createVerticalGlue());
                this.add(JLabelTux);
                this.add(Box.createVerticalGlue());
                this.add(JButtonRetourAccueil);
                this.add(Box.createVerticalGlue());
                break;
        }
    }
    
    public JButton getJButtonRetourAccueil(){
        return JButtonRetourAccueil;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(800, 600));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
