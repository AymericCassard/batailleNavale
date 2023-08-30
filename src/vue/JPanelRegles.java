/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

/**
 *
 * @author acassard
 */
public class JPanelRegles extends javax.swing.JPanel {

    private JPanel cardContainer;
    private JButton JButtonRetour;
    private JButton JButtonSuivant;
    private JButton JButtonPrecedent;
    
    /**
     * Creates new form JPanelRegles
     */
    public JPanelRegles() {
        initComponents();
        Dimension panelSize = new Dimension(1200, 800);
        this.setPreferredSize(panelSize);
        
        JPanel JPanelTop = new JPanel();        
        FlowLayout topLayout = new FlowLayout(FlowLayout.LEFT,15, 5);
        JPanelTop.setLayout(topLayout);
        JButtonRetour = new JButton("Retour");        
        JPanelTop.add(JButtonRetour, BorderLayout.LINE_START);        
                                
        this.add(JPanelTop);        
        
        cardContainer = new JPanel();
        cardContainer.setLayout(new CardLayout());                
                
        JPanel JPanel1 = new JPanel();                
        GridBagLayout gridBag1 = new GridBagLayout();
        gridBag1.preferredLayoutSize(JPanel1);
        GridBagConstraints c1 = new GridBagConstraints();
        JPanel1.setLayout(gridBag1);
        
        c1.gridwidth = GridBagConstraints.REMAINDER;
        c1.fill = GridBagConstraints.HORIZONTAL;
        c1.weightx = 1.0;
        c1.weighty = 1.0;
        c1.anchor = GridBagConstraints.NORTH;        
        JEditorPane editorPaneHeader1 = new JEditorPane();
        editorPaneHeader1.setEditable(false);        
        try{
            editorPaneHeader1.setPage(JPanelRegles.class.getResource("/resources/url/page1Header.html"));
        } catch(IOException e){
            System.out.println(e);
        }        
        gridBag1.setConstraints(editorPaneHeader1, c1);
        JPanel1.add(editorPaneHeader1);
                        
        //c.gridx = 1;
        c1.gridwidth = 1;
        c1.gridheight = 1;
        //c.weighty = 20.0;                        
        JTextField JTextField1 = makeStepJTextField("Etape 1 - Choisir le type du bateau");        
        JTextField JTextField2 = makeStepJTextField("Etape 2 - Choisir la première extrémité");                
        JTextField JTextField3 = makeStepJTextField("Etape 3 - Choisir la seconde extrémité");        
        gridBag1.setConstraints(JTextField1, c1);
        JPanel1.add(JTextField1);
        gridBag1.setConstraints(JTextField2, c1);
        JPanel1.add(JTextField2);
        c1.gridwidth = GridBagConstraints.REMAINDER;
        gridBag1.setConstraints(JTextField3, c1);
        JPanel1.add(JTextField3);
        
        c1.gridwidth = 1;
        JLabel JLabelPlacementStep1 = makeStepJLabel("/resources/img/regles/placementStep1.png", "Ici, un croiseur de 4 cases est choisi");        
        JLabel JLabelPlacementStep2 = makeStepJLabel("/resources/img/regles/placementStep2.png", "Les cases possibles pour la 2eme extremité sont en vert");
        JLabel JLabelPlacementStep3 = makeStepJLabel("/resources/img/regles/placementStep3.png", "Le bateau est représenté par les pingouins Tux");
        gridBag1.setConstraints(JLabelPlacementStep1, c1);
        JPanel1.add(JLabelPlacementStep1);
        gridBag1.setConstraints(JLabelPlacementStep2, c1);
        JPanel1.add(JLabelPlacementStep2);
        c1.gridwidth = GridBagConstraints.REMAINDER;
        gridBag1.setConstraints(JLabelPlacementStep3, c1);
        JPanel1.add(JLabelPlacementStep3);
                
        //c.gridwidth = 1;
        c1.gridx = 2;
        c1.weightx = 0;
        c1.anchor = GridBagConstraints.EAST;
        c1.fill = GridBagConstraints.NONE;
        c1.insets = new Insets(0, 0, 0, 15);
        JButtonSuivant = new JButton("Suivant");
        gridBag1.setConstraints(JButtonSuivant, c1);
        JPanel1.add(JButtonSuivant);
        
        cardContainer.add(JPanel1);

        JPanel JPanel2 = new JPanel();
        GridBagLayout gridBag2 = new GridBagLayout();
        gridBag2.preferredLayoutSize(JPanel2);
        GridBagConstraints c2 = new GridBagConstraints();
        JPanel2.setLayout(gridBag2);
        
        c2.fill = GridBagConstraints.BOTH;
        c2.weightx = 1.0;
        c2.weighty = 1.0;
        c2.gridwidth = GridBagConstraints.REMAINDER;
        c2.anchor = GridBagConstraints.NORTH; 
        JTextField JTextFieldBataille = new JTextField("Phase de Bataille");
        JTextFieldBataille.setFont(new Font("Dialog", 0, 24));
        JTextFieldBataille.setHorizontalAlignment(JTextField.CENTER);
        JTextFieldBataille.setEditable(false);
        JTextFieldBataille.setForeground(Color.red);
        gridBag2.setConstraints(JTextFieldBataille, c2);
        JPanel2.add(JTextFieldBataille);
                
        c2.weightx = 1.0;
        c2.weighty = 1.0;
        c2.gridheight = 5;
        c2.gridwidth = 1;
        JEditorPane editorPaneHeader2 = new JEditorPane();
        editorPaneHeader2.setEditable(false);        
        try{
            editorPaneHeader2.setPage(JPanelRegles.class.getResource("/resources/url/page2Header.html"));
        } catch(IOException e){
            System.out.println(e);
        }                
        gridBag2.setConstraints(editorPaneHeader2, c2);
        JPanel2.add(editorPaneHeader2);
        
        c2.fill = GridBagConstraints.HORIZONTAL;
        c2.gridheight = 1;
        c2.gridy = 1;
        c2.anchor = GridBagConstraints.NORTH;
        JTextField JTextField4 = makeStepJTextField("Etape 1 - Choisir la case");
        JTextField JTextField5 = makeStepJTextField("Etape 2 - Tirer");
        gridBag2.setConstraints(JTextField4, c2);
        JPanel2.add(JTextField4);        
        c2.gridwidth = GridBagConstraints.REMAINDER;
        gridBag2.setConstraints(JTextField5, c2);
        JPanel2.add(JTextField5);   
        
        c2.gridy = 2;
        c2.gridwidth = 1;
        JLabel JLabelBatailleStep1 = new JLabel(new ImageIcon(this.getClass().getResource("/resources/img/regles/batailleStep1.png")));
        JLabel JLabelBatailleStep2 = new JLabel(new ImageIcon(this.getClass().getResource("/resources/img/regles/batailleStep2.png")));
        gridBag2.setConstraints(JLabelBatailleStep1, c2);
        JPanel2.add(JLabelBatailleStep1);
        c2.gridwidth = GridBagConstraints.REMAINDER;
        gridBag2.setConstraints(JLabelBatailleStep2, c2);
        JPanel2.add(JLabelBatailleStep2);
        
        c2.gridy = GridBagConstraints.RELATIVE;
        c2.gridwidth = GridBagConstraints.REMAINDER;
        JTextField JTextFieldCaseState = makeStepJTextField("Signification des symboles des cases");
        JTextFieldCaseState.setFont(new Font("Dialog",1, 20));
        gridBag2.setConstraints(JTextFieldCaseState, c2);
        JPanel2.add(JTextFieldCaseState);
        
        c2.gridwidth = 1;
        JLabel JLabelMate = makeCaseJLabel("/resources/img/mate.png", "Bateau intact");
        JLabel JLabelMissed = makeCaseJLabel("/resources/img/missed.png", "Tir raté");
        JLabel JLabelHit = makeCaseJLabel("/resources/img/explosion.png", "Bateau touché");
        JLabel JLabelDestroyed = makeCaseJLabel("/resources/img/destruction.png", "Bateau détruit");
        gridBag2.setConstraints(JLabelMate, c2);
        JPanel2.add(JLabelMate);
        c2.gridwidth = GridBagConstraints.REMAINDER;
        c2.gridy = 4;
        gridBag2.setConstraints(JLabelMissed, c2);
        JPanel2.add(JLabelMissed);
        c2.gridwidth = 1;
        c2.gridy = GridBagConstraints.RELATIVE;
        gridBag2.setConstraints(JLabelHit, c2);
        JPanel2.add(JLabelHit);
        c2.gridwidth = GridBagConstraints.REMAINDER;        
        c2.gridy = 5;
        gridBag2.setConstraints(JLabelDestroyed, c2);
        JPanel2.add(JLabelDestroyed);
        
        c2.gridwidth = 1;
        c2.fill = GridBagConstraints.NONE;
        c2.gridy = GridBagConstraints.RELATIVE;
        c2.anchor = GridBagConstraints.WEST;
        c2.insets = new Insets(0, 15, 0, 0);
        JButtonPrecedent = new JButton("Précédent");
        gridBag2.setConstraints(JButtonPrecedent, c2);
        JPanel2.add(JButtonPrecedent);
        
        
        cardContainer.add(JPanel2);
        
        this.add(cardContainer);
        
    }
    
    private JTextField makeStepJTextField(String text){
        JTextField textField = new JTextField(text);
        textField.setFont(new Font("Dialog", 0, 16));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);
        return textField;
    }
    
    //imgPath est l'image choisie en partant de la classe actuelle(JPanelRegles)
    private JLabel makeStepJLabel(String imgPath, String text){
        JLabel JLabelPlacementStep = new JLabel(new ImageIcon(this.getClass().getResource(imgPath)));
        JLabelPlacementStep.setText(text);
        JLabelPlacementStep.setVerticalTextPosition(JLabel.BOTTOM);
        JLabelPlacementStep.setHorizontalTextPosition(JLabel.CENTER);
        return JLabelPlacementStep;
    }
    
    //imgPath est l'image choisie en partant de la classe actuelle(JPanelRegles)
    private JLabel makeCaseJLabel(String imgPath, String text){
        ImageIcon JLabelImg = new ImageIcon(this.getClass().getResource(imgPath));
        Image scaledImg = JLabelImg.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel JLabelPlacementStep = new JLabel(new ImageIcon(scaledImg));
        JLabelPlacementStep.setText(text);
        JLabelPlacementStep.setVerticalTextPosition(JLabel.TOP);
        JLabelPlacementStep.setHorizontalTextPosition(JLabel.CENTER);
        return JLabelPlacementStep;
    }

    public JButton getJButtonRetour() {
        return JButtonRetour;
    }

    public JButton getJButtonSuivant() {
        return JButtonSuivant;
    }

    public JButton getJButtonPrecedent() {
        return JButtonPrecedent;
    }

    public JPanel getCardContainer() {
        return cardContainer;
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
