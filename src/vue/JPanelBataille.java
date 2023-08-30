/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;
import modele.TypeBateau;
import vue.composants.DisplayCase;
import vue.composants.JPanelGrille;

/**
 *
 * @author acassard
 */
public class JPanelBataille extends javax.swing.JPanel {

    //différents panels de la frame
    private JPanel JPanelLeftBar;
    private JPanel JPanelTopBar;
    private JPanel JPanelRightBar;
    private JPanelGrille grilleJ1;
    private JPanelGrille grilleJ2;
    private JPanel JPanelBotBar;

    //JButton des différents types de bateaux    
    private HashMap<JComponent, JLabel> JButtonTypesLeft;
    private HashMap<JComponent, JLabel> JButtonTypesRight;
    private JTextArea JTextAreaOutput;
    private JLabel typeChoisi;

    private JButton JButtonRetirer;
    private JButton JButtonValider;

    private JLabel JLabelCompteurJ1;
    private JLabel JLabelCompteurJ2;

    /**
     * Creates new form JPanelBataille
     */
    public JPanelBataille(JPanelGrille grilleJ1, JPanelGrille grilleJ2) {
        initComponents();
        this.JButtonTypesLeft = new HashMap<>();
        this.JButtonTypesRight = new HashMap<>();
        GridBagLayout gridBag = new GridBagLayout();
        gridBag.preferredLayoutSize(this);
        GridBagConstraints c = new GridBagConstraints();
        this.setLayout(gridBag);
        //FlowLayout layout = (FlowLayout) this.getLayout();

        setupJPanelLeftBar();
        //c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridheight = GridBagConstraints.REMAINDER;
        //c.anchor = GridBagConstraints.NORTHWEST;
        //c.insets = new Insets(0, 5, 0, 0);
        gridBag.setConstraints(this.JPanelLeftBar, c);
        this.add(this.JPanelLeftBar);

        setupJPanelTopBar();
        //c.weightx = 0.0;
        c.weighty = 0;
        //c.anchor = GridBagConstraints.NORTH;
        c.gridy = 0;
        c.gridx = 1;
        c.gridheight = 1;
        //c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(5, 0, 0, 0);
        gridBag.setConstraints(this.JPanelTopBar, c);
        this.add(this.JPanelTopBar);

        setupJPanelRightBar();
        //c.weighty = 0;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = GridBagConstraints.RELATIVE;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.gridheight = GridBagConstraints.REMAINDER;
        //c.anchor = GridBagConstraints.NORTHEAST;
        //c.insets = new Insets(0, 0, 0, 5);
        gridBag.setConstraints(this.JPanelRightBar, c);
        this.add(this.JPanelRightBar);

        this.grilleJ1 = grilleJ1;
        //c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 1;
        c.gridy = 1;
        //c.weighty = 1.0;        
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.WEST;
        gridBag.setConstraints(this.grilleJ1, c);
        this.add(this.grilleJ1);

        this.grilleJ2 = grilleJ2;
        //c.anchor = GridBagConstraints.BASELINE_TRAILING;
        //c.gridwidth = GridBagConstraints.REMAINDER;

        c.anchor = GridBagConstraints.EAST;
        gridBag.setConstraints(this.grilleJ2, c);
        this.add(this.grilleJ2);

        setupJPanelBotBar();
        c.gridy = GridBagConstraints.RELATIVE;
        c.anchor = GridBagConstraints.CENTER;
        gridBag.setConstraints(this.JPanelBotBar, c);
        this.add(this.JPanelBotBar);

        //layout.setAlignment(FlowLayout.LEADING);
        //this.add(JPanelLeftBar);        
    }

    private void setupJPanelLeftBar() {
        Dimension leftBarSize = new Dimension(150, 700);
        this.JPanelLeftBar = new JPanel();
        this.JPanelLeftBar.setPreferredSize(leftBarSize);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        TitledBorder titled = BorderFactory.createTitledBorder(blackline, "Bateaux à placer");
        titled.setTitleJustification(TitledBorder.CENTER);
        this.JPanelLeftBar.setBorder(titled);
        BoxLayout leftBarLayout = new BoxLayout(this.JPanelLeftBar, BoxLayout.Y_AXIS);
        this.JPanelLeftBar.setLayout(leftBarLayout);

        JLabel typeHeader = new JLabel("Type Choisi :", JLabel.CENTER);
        typeHeader.setPreferredSize(new Dimension(leftBarSize.width, 75));
        typeHeader.setAlignmentX(0.5f);

        this.typeChoisi = new JLabel("AUCUN", JLabel.CENTER);
        this.typeChoisi.setPreferredSize(new Dimension(leftBarSize.width, 75));
        this.typeChoisi.setAlignmentX(0.5f);

        this.JPanelLeftBar.add(typeHeader);
        this.JPanelLeftBar.add(this.typeChoisi);

    }

    private void setupJPanelRightBar() {
        Dimension leftBarSize = new Dimension(150, 700);
        this.JPanelRightBar = new JPanel();
        this.JPanelRightBar.setPreferredSize(leftBarSize);
        this.JPanelRightBar.setBorder(BorderFactory.createLineBorder(Color.black));
        BoxLayout leftBarLayout = new BoxLayout(this.JPanelRightBar, BoxLayout.Y_AXIS);
        this.JPanelRightBar.setLayout(leftBarLayout);
    }

    private JPanel generateJPanelBateau(Dimension containerSize, String titre, int tileCount, int number, boolean allie) {
        JPanel JPanelBateau = new JPanel();
        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        JPanelBateau.setLayout(gridBag);
        JPanelBateau.setMaximumSize(new Dimension(containerSize.width, containerSize.height / 5));
        //JPanelBateau.setBackground(Color.red);

        JLabel JLabelCount = new JLabel(String.valueOf(number));
        JLabelCount.setFont(new Font("Dialog", 0, 32));
        JLabelCount.setPreferredSize(new Dimension(80, 80));
        c.gridwidth = GridBagConstraints.REMAINDER;
        gridBag.setConstraints(JLabelCount, c);
        JPanelBateau.add(JLabelCount);

        c.gridwidth = 1;
        c.weightx = 1;
        Dimension mateSize = new Dimension(30, 30);
        JLabel mate;
        //Calcul des dimensions restantes pour centrer les mates
        int extraWidth = containerSize.width - mateSize.width * tileCount;
        int individualPadding = 0;
        if (extraWidth != 0) {
            individualPadding = extraWidth / 2;
        }
        for (int i = 0; i < tileCount; i++) {
            mate = makeJLabelMate(mateSize);
            mate.setPreferredSize(mateSize);
            int leftPadding = 0;
            int rightPadding = 0;
            if (i == 0) {
                leftPadding = individualPadding;
            } else if (i == tileCount - 1) {
                rightPadding = individualPadding;
            }
            c.insets = new Insets(0, leftPadding, 0, rightPadding);
            gridBag.setConstraints(mate, c);
            JPanelBateau.add(mate);
        }

        c.insets = new Insets(0, 0, 0, 0);
        c.gridwidth = GridBagConstraints.REMAINDER; //end row
        c.weightx = 0;
        c.gridy = 2;
        //le booleen allie change la couleur du Jpanel
        JComponent componentTitre;
        if (allie) {
            componentTitre = makeButton(titre, gridBag, c);
            this.JButtonTypesLeft.put((JButton) componentTitre, JLabelCount);
            JPanelBateau.setBackground(Color.blue);
            JLabelCount.setForeground(Color.white);
        } else {
            componentTitre = new JLabel(titre);
            giveJLabelTitreStyle((JLabel)componentTitre);
            componentTitre.setOpaque(true);
            gridBag.setConstraints(componentTitre, c);
            this.JButtonTypesRight.put((JLabel) componentTitre, JLabelCount);
            JPanelBateau.setBackground(Color.red);
        }
        JPanelBateau.add(componentTitre);
        JPanelBateau.setBorder(BorderFactory.createLineBorder(Color.black));
        return JPanelBateau;
    }

    private JButton makeButton(String text, GridBagLayout gridbag, GridBagConstraints c) {
        JButton button = new JButton(text);
        gridbag.setConstraints(button, c);
        return button;
    }

    private JLabel makeJLabelMate(Dimension size) {
        DisplayCase mate = new DisplayCase(DisplayCase.Etat.MATE);
        Image mateImg = mate.getImg().getImage().getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
        ImageIcon JLabelImg = new ImageIcon(mateImg);
        return new JLabel(JLabelImg);
    }

    private void setupJPanelTopBar() {
        Dimension topBarSize = new Dimension(900, 100);
        this.JPanelTopBar = new JPanel();
        this.JPanelTopBar.setLayout(new BoxLayout(JPanelTopBar, BoxLayout.X_AXIS));
        JLabel JLabelTopBar = new JLabel("PHASE DE PLACEMENT");
        this.JPanelTopBar.setPreferredSize(topBarSize);
        this.JPanelTopBar.setBorder(BorderFactory.createLineBorder(Color.black));
        this.JPanelTopBar.add(Box.createVerticalGlue());
        this.JPanelTopBar.add(Box.createHorizontalGlue());
        this.JPanelTopBar.add(JLabelTopBar);
        this.JPanelTopBar.add(Box.createHorizontalGlue());
    }

    private void setupJPanelBotBar() {
        Dimension botBarSize = new Dimension(900, 100);
        this.JPanelBotBar = new JPanel();
        this.JPanelBotBar.setPreferredSize(botBarSize);
        this.JPanelBotBar.setBorder(BorderFactory.createLineBorder(Color.black));

        GridBagLayout gridBag = new GridBagLayout();
        gridBag.preferredLayoutSize(this.JPanelBotBar);
        GridBagConstraints c = new GridBagConstraints();
        this.JPanelBotBar.setLayout(gridBag);

        this.JTextAreaOutput = new JTextArea();
        //l'update policy ALWAYS UPDATE du caret permet au viewPort de toujours suivre la dernière ligne ajoutée
        DefaultCaret caret = (DefaultCaret) JTextAreaOutput.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        JScrollPane areaScrollPane = new JScrollPane(this.JTextAreaOutput);
        areaScrollPane.setPreferredSize(new Dimension(botBarSize.width / 2, botBarSize.height - 2));
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.WEST;
        gridBag.setConstraints(areaScrollPane, c);
        this.JPanelBotBar.add(areaScrollPane);

        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1;
        c.weighty = 0;
        this.JButtonRetirer = new JButton("Retirer le bateau");
        JButtonRetirer.setEnabled(false);
        gridBag.setConstraints(JButtonRetirer, c);
        this.JPanelBotBar.add(JButtonRetirer);

        //c.insets = new Insets(0,0, 0, botBarSize.width / 4);
        this.JButtonValider = new JButton("Valider la grille");
        JButtonValider.setEnabled(false);
        gridBag.setConstraints(JButtonValider, c);
        this.JPanelBotBar.add(JButtonValider);

    }

    //trie les components de JpanelLeftBar pour mettre les plus grands types en haut
    //component[0] = plus grande valeur
    private void sortJPanel(JPanel JPanelToSort) {
        Component[] componentArray = JPanelToSort.getComponents();
        int[] componentCounts = new int[componentArray.length];
        for (int i = 0; i < componentArray.length; i++) {
            if (componentArray[i].getClass() == JPanel.class) {
                JPanel currentJPanel = (JPanel) componentArray[i];
                componentCounts[i] = currentJPanel.getComponentCount();
                //le composant n'est pas un Jpanel, il ne contient donc que lui meme
            } else {
                componentCounts[i] = 1;
            }

        }
        int[] sortedIndexes = getSortedIndex(componentCounts);
        JPanelToSort.removeAll();
        for (int i = 0; i < componentArray.length; i++) {
            JPanelToSort.add(componentArray[sortedIndexes[i]]);
        }
    }

    //prend un tableau indiquant le nombre de composants des Jpanels selon leur index dans le container
    //renvoie un tableau triant les index des differents JPanel selon leur nombre de composants décroissants
    private int[] getSortedIndex(int[] arrayToSort) {
        if (arrayToSort.length < 2) {
            arrayToSort[0] = 0;
            return arrayToSort;
        }
        int[] returnArray = new int[arrayToSort.length];
        int currentValue;
        int comparedValue;
        for (int i = 0; i < arrayToSort.length; i++) {
            currentValue = arrayToSort[i];
            returnArray[i] = i;
            for (int j = 0; j < arrayToSort.length; j++) {
                comparedValue = arrayToSort[j];
                if (comparedValue > currentValue) {
                    currentValue = comparedValue;
                    returnArray[i] = j;
                }
            }
            arrayToSort[returnArray[i]] = -1;
        }
        return returnArray;
    }

    public void appendToOutput(String text) {
        String[] timeArray = new String[3];
        timeArray[0] = String.valueOf(LocalTime.now().getHour());
        timeArray[1] = String.valueOf(LocalTime.now().getMinute());
        timeArray[2] = String.valueOf(LocalTime.now().getSecond());
        for (int i = 0; i < timeArray.length; i++) {
            if (timeArray[i].length() == 1) {
                timeArray[i] = "0" + timeArray[i];
            }
        }
        String timeNow = timeArray[0] + ":" + timeArray[1] + ":" + timeArray[2];
        this.JTextAreaOutput.append("[" + timeNow + "] " + text + "\n");
    }

    //ajoute un jpanel au jpanelLeftBar, contenant:
    //-un JLabel contenant un chiffre (number)
    //-un JButton nommé après (titre)
    //-autant de display case que (tileCount)
    //-un theme différent selon que le JPanel soit allie ou non
    public void attributeJPanelToLeftBar(String titrePanel, int tileCount, int number) {
        JPanel JPanelBateau = generateJPanelBateau(this.JPanelLeftBar.getPreferredSize(), titrePanel, tileCount, number, true);
        this.JPanelLeftBar.add(JPanelBateau);
        sortJPanel(JPanelLeftBar);
    }

    public void attributeJPanelToRightBar(String titrePanel, int tileCount, int number) {
        JPanel JPanelBateau = generateJPanelBateau(this.JPanelRightBar.getPreferredSize(), titrePanel, tileCount, number, false);
        this.JPanelRightBar.add(JPanelBateau);
        sortJPanel(JPanelRightBar);
    }

    public void changeJPanelTop() {
        JPanelTopBar.removeAll();

        Border paddingBorder = BorderFactory.createEmptyBorder(0, 14, 0, 14);

        JPanel JPanelCompteurJ1 = new JPanel();
        JPanelCompteurJ1.setLayout(new BoxLayout(JPanelCompteurJ1, BoxLayout.Y_AXIS));
        JLabelCompteurJ1 = new JLabel("0");
        JLabelCompteurJ1.setFont(new Font("Dialog", 0, 32));
        JLabelCompteurJ1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.blue), paddingBorder));
        JLabelCompteurJ1.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel JLabelDescriptionJ1 = new JLabel("Tirs alliés réussis");
        JLabelDescriptionJ1.setFont(new Font("Dialog", 0, 16));
        JLabelDescriptionJ1.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanelCompteurJ1.add(JLabelCompteurJ1);
        JPanelCompteurJ1.add(JLabelDescriptionJ1);

        JPanel JPanelCompteurJ2 = new JPanel();
        JPanelCompteurJ2.setLayout(new BoxLayout(JPanelCompteurJ2, BoxLayout.Y_AXIS));
        JLabelCompteurJ2 = new JLabel("0");
        JLabelCompteurJ2.setFont(new Font("Dialog", 0, 32));
        JLabelCompteurJ2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red), paddingBorder));
        JLabelCompteurJ2.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel JLabelDescriptionJ2 = new JLabel("Tirs ennemis réussis");
        JLabelDescriptionJ2.setFont(new Font("Dialog", 0, 16));
        JLabelDescriptionJ2.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanelCompteurJ2.add(JLabelCompteurJ2);
        JPanelCompteurJ2.add(JLabelDescriptionJ2);

        JPanelTopBar.add(Box.createHorizontalGlue());
        JPanelTopBar.add(JPanelCompteurJ1);
        JPanelTopBar.add(Box.createHorizontalGlue());

        JPanelTopBar.add(Box.createHorizontalGlue());
        JPanelTopBar.add(JPanelCompteurJ2);
        JPanelTopBar.add(Box.createHorizontalGlue());

        JPanelTopBar.repaint();
    }

    public void changeJPanelLeft() {
        JButtonTypesLeft.clear();
        for (Component c : JPanelLeftBar.getComponents()) {
            if (c.getClass() == JLabel.class) {
                JPanelLeftBar.remove(c);
            }
            if (c.getClass() == JPanel.class) {
                JPanel JPanelC = (JPanel) c;
                JLabel key = null;
                JLabel value = null;
                for (Component c1 : JPanelC.getComponents()) {
                    if (c1.getClass() == JLabel.class) {
                        JLabel JLabelC1 = (JLabel) c1;
                        if (JLabelC1.getText() != null) {
                            char[] charsJLabel = JLabelC1.getText().toCharArray();
                            for (char cJLabel : charsJLabel) {
                                if (Character.isDigit(cJLabel)) {
                                    value = JLabelC1;
                                }
                            }
                        }

                    }
                    if (c1.getClass() == JButton.class) {
                        AbstractButton toBeReplaced = (AbstractButton) c1;
                        JLabel replacement = new JLabel(toBeReplaced.getText());
                        giveJLabelTitreStyle(replacement);
                        replacement.setOpaque(true);
                        GridBagLayout layout = (GridBagLayout) JPanelC.getLayout();
                        GridBagConstraints constraint = layout.getConstraints(c1);
                        JPanelC.remove(c1);
                        layout.setConstraints(replacement, constraint);
                        JPanelC.add(replacement);
                        key = replacement;
                    }
                }
                JButtonTypesLeft.put(key, value);
            }
        }
    }
    
    public void giveJLabelTitreStyle(JLabel label){
        Border paddingBorder = BorderFactory.createEmptyBorder(3, 9, 3, 9);
        label.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black), paddingBorder));
        label.setBackground(new Color(237, 237, 237));
    }

    public HashMap<JComponent, JLabel> getJButtonTypesLeft() {
        return this.JButtonTypesLeft;
    }

    public HashMap<JComponent, JLabel> getJButtonTypesRight() {
        return JButtonTypesRight;
    }

    public JPanelGrille getGrilleJ1() {
        return grilleJ1;
    }

    public JPanelGrille getGrilleJ2() {
        return grilleJ2;
    }

    public JLabel getTypeChoisi() {
        return typeChoisi;
    }

    public void setTypeChoisi(JLabel typeChoisi) {
        this.typeChoisi = typeChoisi;
    }

    public JButton getJButtonRetirer() {
        return JButtonRetirer;
    }

    public void setJButtonRetirer(JButton JButtonRetirer) {
        this.JButtonRetirer = JButtonRetirer;
    }

    public JButton getJButtonValider() {
        return JButtonValider;
    }

    public void setJButtonValider(JButton JButtonValider) {
        this.JButtonValider = JButtonValider;
    }

    public JPanel getJPanelTopBar() {
        return JPanelTopBar;
    }

    public JPanel getJPanelBotBar() {
        return JPanelBotBar;
    }

    public JPanel getJPanelLeftBar() {
        return JPanelLeftBar;
    }

    public JPanel getJPanelRightBar() {
        return JPanelRightBar;
    }

    public JLabel getJLabelCompteurJ1() {
        return JLabelCompteurJ1;
    }

    public JLabel getJLabelCompteurJ2() {
        return JLabelCompteurJ2;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(1200, 800));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
