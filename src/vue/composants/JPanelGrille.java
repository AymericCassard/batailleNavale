/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vue.composants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URLConnection;
import java.util.Enumeration;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellRenderer;
import modele.Case;
import modele.Grille;

/**
 *
 * @author acassard
 */
public class JPanelGrille extends JPanel {

    private BufferedImage background;
    private JTableGrille JTable;
    private Dimension prefSize;

    /**
     * Creates new form JPanelGrilleTest
     */
    //créé le JPanelGrille avec une taille par défaut 440x440px
    public JPanelGrille(DisplayCase[][] data) {
        try {
            this.background = ImageIO.read(this.getClass().getResource("/resources/img/jtableBackground.png"));
        } catch (IOException e) {
            System.out.println(e);
        }
        initComponents();
        this.prefSize = new Dimension(440, 440);
        this.JTable = new JTableGrille(data, this.prefSize);
        this.add(JTable.getTableHeader());
        this.add(JTable);
        this.prefSize = new Dimension(this.prefSize.width, this.prefSize.height);
        this.setPreferredSize(prefSize);
    }

    //etant donné qu'il y a 11 lignes dans la table
    //si size.height n'est pas un multiple de 11, elle sera rapportée au multiple de 11 le plus proche
    public JPanelGrille(DisplayCase[][] data, Dimension size) {
        try {
            this.background = ImageIO.read(this.getClass().getResource("/resources/img/jtableBackground.png"));
        } catch (IOException e) {
            System.out.println(e);
        }
        initComponents();
        this.prefSize = size;
        this.JTable = new JTableGrille(data, this.prefSize);
        this.add(JTable.getTableHeader());
        this.add(JTable);
        this.prefSize = new Dimension(this.prefSize.width, this.prefSize.height);
        this.setPreferredSize(prefSize);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, JTable.getX(), JTable.getY(), JTable.getWidth(), JTable.getHeight(), this);
    }
    
    //ajoute un background gris pour signifier que la JTable est disabled
    public void setBackgroundEnabled(boolean enabled) {
        if (!enabled) {
            Color disabled = new Color(128, 128, 128, 125);
            this.JTable.setBackground(disabled);
            this.JTable.getTableHeader().setForeground(disabled);
            return;
        }
        Color enabledColor = new Color(255, 255, 255, 0);
        this.JTable.setBackground(enabledColor);
        this.JTable.getTableHeader().setForeground(Color.BLACK);
    }

    public JTableGrille getJTable() {
        return this.JTable;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
