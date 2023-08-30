/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlleur;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import modele.Bataille;
import modele.Bateau;
import modele.Case;
import modele.Cpu;
import modele.CreationBateauException;
import modele.Grille;
import modele.Joueur;
import modele.TypeBateau;
import vue.JPanelBataille;
import vue.JPanelFin;
import vue.composants.DisplayCase;
import vue.composants.JPanelGrille;
import vue.composants.JTableGrille;
import vue.composants.Sound;

/**
 *
 * @author acassard
 */
public class CtrlBataille implements ActionListener, MouseListener {

    private CtrlPrincipal ctrlP;
    private JPanelBataille vue;
    private JPanelGrille JPanelGrilleJ1;
    private JPanelGrille JPanelGrilleJ2;
    private Bataille bataille;
    private TypeBateau typeChoisi;
    private Case caseChoisie;
    private Bateau bateauChoisi;
    //sert à compter les bateaux (à placer, ou Survivants)
    private HashMap<TypeBateau, Integer> nombreBateauxJ1;
    private HashMap<TypeBateau, Integer> nombreBateauxJ2;

    private enum Phase {
        PLACEMENT,
        BATAILLE
    }
    private Phase phaseActuelle;

    public CtrlBataille(CtrlPrincipal ctrlP) {
        this.ctrlP = ctrlP;
        Joueur j1 = new Joueur("ahii");
        Cpu cpu = new Cpu("gpt8");
        this.bataille = new Bataille(j1, cpu);
        this.phaseActuelle = Phase.PLACEMENT;
        this.nombreBateauxJ1 = new HashMap<>();
        this.nombreBateauxJ2 = new HashMap<>();
        generateBateauxRestantFromJoueur(nombreBateauxJ1);
        this.bataille.phaseDePlacementJ2();
        this.JPanelGrilleJ1 = generateJPanelGrilleFromJoueur1();
        this.JPanelGrilleJ2 = generateJPanelGrilleFromJoueur2();
        this.vue = new JPanelBataille(JPanelGrilleJ1, JPanelGrilleJ2);
        //colore la grille j2 en gris pour indiquer qu'elle n'a pas d'importance
        JPanelGrilleJ2.setBackgroundEnabled(false);
        JPanelGrilleJ2.getJTable().setEnabled(false);

        this.typeChoisi = null;
        this.caseChoisie = null;
        this.bateauChoisi = null;

        for (HashMap.Entry<TypeBateau, Integer> set : this.nombreBateauxJ1.entrySet()) {
            TypeBateau type = set.getKey();
            int nombreBateau = set.getValue();
            this.vue.attributeJPanelToLeftBar(type.name(), type.cases, nombreBateau);
        }
        //ajout des actionsListener
        for (JComponent aJComponent : vue.getJButtonTypesLeft().keySet()) {
            JButton aJButton = (JButton) aJComponent;
            aJButton.addActionListener(this);
        }
        JPanelGrilleJ1.getJTable().addMouseListener(this);
        JPanelGrilleJ2.getJTable().addMouseListener(this);
        vue.getJButtonRetirer().addActionListener(this);
        vue.getJButtonValider().addActionListener(this);

        this.vue.appendToOutput("Début de la phase de Placement");
    }

    private JPanelGrille generateJPanelGrilleFromJoueur1() {
        Grille grille = bataille.getJoueur1().getGrille();
        DisplayCase[][] data = new DisplayCase[10][10];
        for (Case uneCase : grille.getLesCases()) {
            data[uneCase.getY()][uneCase.getX()] = new DisplayCase(uneCase);
        }
        return new JPanelGrille(data);
    }

    private JPanelGrille generateJPanelGrilleFromJoueur2() {
        Grille grille = bataille.getJoueur2().getGrille();
        DisplayCase[][] data = new DisplayCase[10][10];
        for (Case uneCase : grille.getLesCases()) {
            DisplayCase uneDisplayCase = new DisplayCase(uneCase);
            uneDisplayCase.setEtat(DisplayCase.Etat.EMPTY);
            data[uneCase.getY()][uneCase.getX()] = uneDisplayCase;
        }
        return new JPanelGrille(data);
    }

    private void initEmptyNombreBateau(HashMap<TypeBateau, Integer> map) {
        map.clear();
        for (TypeBateau type : TypeBateau.values()) {
            map.put(type, 0);
        }
    }

    //génère les bateaux restants à placer pour le joueur concerné
    private void generateBateauxRestantFromJoueur(HashMap<TypeBateau, Integer> mapConcerne) {
        initEmptyNombreBateau(mapConcerne);
        ArrayList<TypeBateau> arrayBateauxRestants;
        if (mapConcerne == this.nombreBateauxJ1) {
            arrayBateauxRestants = bataille.getJoueur1().getGrille().getBateauxRestants();
            for (TypeBateau type : arrayBateauxRestants) {
                this.nombreBateauxJ1.put(type, this.nombreBateauxJ1.get(type) + 1);
            }
        }
        if (mapConcerne == this.nombreBateauxJ2) {
            arrayBateauxRestants = bataille.getJoueur2().getGrille().getBateauxRestants();
            for (TypeBateau type : arrayBateauxRestants) {
                this.nombreBateauxJ2.put(type, this.nombreBateauxJ2.get(type) + 1);
            }
        }
    }

    private void generateBateauxSurvivantsFromJoueur(HashMap<TypeBateau, Integer> mapConcerne) {
        initEmptyNombreBateau(mapConcerne);
        ArrayList<TypeBateau> arrayBateauxSurvivants;
        if (mapConcerne == this.nombreBateauxJ1) {
            arrayBateauxSurvivants = bataille.getJoueur1().getGrille().getBateauxSurvivants();
            for (TypeBateau type : arrayBateauxSurvivants) {
                this.nombreBateauxJ1.put(type, this.nombreBateauxJ1.get(type) + 1);
            }
        }
        if (mapConcerne == this.nombreBateauxJ2) {
            arrayBateauxSurvivants = bataille.getJoueur2().getGrille().getBateauxSurvivants();
            for (TypeBateau type : arrayBateauxSurvivants) {
                this.nombreBateauxJ2.put(type, this.nombreBateauxJ2.get(type) + 1);
            }
        }
    }

    private void attributeVueJButtonFromNbBateaux(HashMap<JComponent, JLabel> vueHashMap, HashMap<TypeBateau, Integer> nbBateauxCtrl) {

        for (HashMap.Entry<TypeBateau, Integer> setNbBateaux : nbBateauxCtrl.entrySet()) {
            TypeBateau type = setNbBateaux.getKey();
            int nombreBateau = setNbBateaux.getValue();
            for (HashMap.Entry<JComponent, JLabel> setJButtonType : vueHashMap.entrySet()) {
                JButton JButtonC = (JButton) setJButtonType.getKey();
                JLabel JLabelC = setJButtonType.getValue();
                if (JButtonC.getText().equals(type.name())) {
                    JLabelC.setText(String.valueOf(nombreBateau));
                    JButtonC.setEnabled(true);
                    if (nombreBateau == 0) {
                        JButtonC.setEnabled(false);
                    }
                }
            }
        }

    }

    private void attributeVueJLabelFromNbBateaux(HashMap<JComponent, JLabel> vueHashMap, HashMap<TypeBateau, Integer> nbBateauxCtrl) {
        for (HashMap.Entry<TypeBateau, Integer> setNbBateaux : nbBateauxCtrl.entrySet()) {
            TypeBateau type = setNbBateaux.getKey();
            int nombreBateau = setNbBateaux.getValue();
            for (HashMap.Entry<JComponent, JLabel> setJButtonType : vueHashMap.entrySet()) {
                JLabel JLabelT = (JLabel) setJButtonType.getKey();
                JLabel JLabelC = setJButtonType.getValue();
                if (JLabelT.getText().equals(type.name())) {
                    JLabelC.setText(String.valueOf(nombreBateau));
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (this.phaseActuelle) {
            case PLACEMENT:
                if (e.getSource().getClass() == JButton.class) {
                    this.caseChoisie = null;
                    JButton clickedButton = (JButton) e.getSource();

                    if (vue.getJButtonTypesLeft().containsKey(clickedButton)) {
                        switch (clickedButton.getText()) {
                            case "PORTEAVION":
                                this.typeChoisi = TypeBateau.PORTEAVION;
                                break;
                            case "CROISEUR":
                                this.typeChoisi = TypeBateau.CROISEUR;
                                break;
                            case "SOUSMARIN":
                                this.typeChoisi = TypeBateau.SOUSMARIN;
                                break;
                            case "TORPILLEUR":
                                this.typeChoisi = TypeBateau.TORPILLEUR;
                                break;
                        }
                        if (this.typeChoisi != null) {
                            vue.getTypeChoisi().setText(this.typeChoisi.name());
                        } else {
                            vue.getTypeChoisi().setText("AUCUN");
                        }                        
                    }

                    if (clickedButton == vue.getJButtonRetirer()) {
                        Grille grilleJ1 = this.bataille.getJoueur1().getGrille();
                        grilleJ1.retirerBateau(bateauChoisi);
                        this.bateauChoisi = null;
                        generateBateauxRestantFromJoueur(nombreBateauxJ1);
                        attributeVueJButtonFromNbBateaux(vue.getJButtonTypesLeft(), nombreBateauxJ1);
                        vue.getJButtonRetirer().setEnabled(false);
                        vue.getJButtonValider().setEnabled(false);
                    }

                    if (clickedButton == vue.getJButtonValider()) {
                        setPhaseToBataille();                                                
                    }

                    resetAllBackgrounds(JPanelGrilleJ1.getJTable().getGrilleTableModel().getData());
                    updateTableModel(JPanelGrilleJ1.getJTable(), JPanelGrilleJ1.getJTable().getGrilleTableModel().getData());
                }
                break;
            case BATAILLE:
                if (e.getSource().getClass() == JButton.class) {
                    if (e.getSource() == vue.getJButtonValider()) {
                        tourBatailleJ1();
                        if (bataille.getJoueur2().getGrille().isEtat()) {                            
                            ctrlP.afficherVueFin(JPanelFin.Resultat.VICTOIRE);
                            return;
                        }
                        vue.getJButtonValider().setEnabled(false);
                        JPanelGrilleJ2.getJTable().setEnabled(false);
                        //permet d'introduire un delai avant le tour j2
                        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
                        Runnable run = new Runnable() {
                            @Override
                            public void run() {
                                CtrlBataille.this.tourBatailleJ2();
                                JPanelGrilleJ2.getJTable().setEnabled(true);
                            }
                        };
                        executorService.schedule(run, 1, TimeUnit.SECONDS);
                        
                    }
                }
                break;
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().getClass() == JTableGrille.class) {
            JTableGrille JTable = (JTableGrille) e.getSource();
            if (!JTable.isEnabled()) {
                return;
            }
            int row = JTable.getSelectedRow();
            int column = JTable.getSelectedColumn() - 1;
            if (column == -1) {
                return;
            }
            DisplayCase[][] data = JTable.getGrilleTableModel().getData();
            switch (this.phaseActuelle) {
                case PLACEMENT:
                    Grille grilleJ1 = this.bataille.getJoueur1().getGrille();
                    Case caseCliquee = grilleJ1.getCaseByCoord(column, row);                    

                    if (this.caseChoisie != null) {
                        JTableSecondClickAction(caseCliquee, grilleJ1, data);
                        //le bateau a été placé, on peut arreter la fonction ici
                        if (this.caseChoisie == null) {
                            return;
                        }
                        //sinon, on laisse la fonction continuer pour réafficher les emplacements bateaux
                    }

                    JTableFirstClickAction(caseCliquee, grilleJ1, data);

                    updateTableModel(JTable, data);
                    break;
                case BATAILLE:
                    Grille grilleJ2 = this.bataille.getJoueur2().getGrille();
                    Case caseCliqueeBataille = grilleJ2.getCaseByCoord(column, row);
                    ArrayList<Case> tirsJ1 = bataille.getJoueur1().getTirs();
                    if (tirsJ1.contains(caseCliqueeBataille)) {
                        vue.appendToOutput("Vous avez déjà tiré sur cette case");
                        setDisplayCaseBGColorFromCase(caseCliqueeBataille, null, data);
                        updateTableModel(JTable, data);
                    } else {
                        caseChoisie = caseCliqueeBataille;
                        vue.getJButtonValider().setEnabled(true);
                    }
                    break;
            }

        }
    }

    private void JTableFirstClickAction(Case caseCliquee, Grille grilleJ1, DisplayCase[][] data) {
        resetAllBackgrounds(data);
        this.bateauChoisi = null;
        vue.getJButtonRetirer().setEnabled(false);
        if (caseCliquee.getBateauProprio() != null) {
            actionClickBateau(caseCliquee, data);
            return;
        }
        setDisplayCaseBGColorFromCase(caseCliquee, DisplayCase.backgroundColors.SELECTED.color, data);
        if (this.typeChoisi == null) {
            this.vue.appendToOutput("Choisissez un type avant de placer un bateau");
            return;
        }
        this.caseChoisie = caseCliquee;        
        ArrayList<Case> casesSecondeExtremite = grilleJ1.getSecondeExtremite(this.typeChoisi, this.caseChoisie);
        for (Case uneCase : casesSecondeExtremite) {
            try {
                //getCaseBateau renvoie CreationBateauException si il y a un bateau entre les 2 extrémités
                grilleJ1.getCasesBateau(this.caseChoisie, uneCase);
                setDisplayCaseBGColorFromCase(uneCase, DisplayCase.backgroundColors.POTENTIALSHIP.color, data);
            } catch (CreationBateauException errMsg) {
                //empeche data d'ajouter une background color
            }
        }

    }

    private void actionClickBateau(Case caseCliquee, DisplayCase[][] data) {
        Bateau bateauSurligne = caseCliquee.getBateauProprio();        
        for (Case uneCase : bateauSurligne.getCasesDuBateau()) {
            setDisplayCaseBGColorFromCase(uneCase, DisplayCase.backgroundColors.SELECTED.color, data);
        }
        this.bateauChoisi = bateauSurligne;
        vue.getJButtonRetirer().setEnabled(true);
    }

    private void setDisplayCaseBGColorFromCase(Case uneCase, Color bgColor, DisplayCase[][] data) {
        data[uneCase.getY()][uneCase.getX()].setBackgroundColor(bgColor);
    }

    private void JTableSecondClickAction(Case caseCliquee, Grille grilleJ1, DisplayCase[][] data) {
        for (Case uneCase : grilleJ1.getSecondeExtremite(typeChoisi, caseChoisie)) {
            try {
                //getCaseBateau renvoie CreationBateauException si il y a un bateau entre les 2 extrémités
                ArrayList<Case> casesDuBateau = grilleJ1.getCasesBateau(this.caseChoisie, caseCliquee);
                if (caseCliquee == uneCase) {
                    Bateau bateauAPlacer = new Bateau(casesDuBateau, this.typeChoisi);
                    grilleJ1.placerBateau(bateauAPlacer);
                    setDisplayCaseBorderFromBateau(bateauAPlacer, data);
                    generateBateauxRestantFromJoueur(nombreBateauxJ1);
                    attributeVueJButtonFromNbBateaux(vue.getJButtonTypesLeft(), nombreBateauxJ1);
                    this.caseChoisie = null;
                    this.typeChoisi = null;
                    vue.getTypeChoisi().setText("AUCUN");
                    vue.getJButtonValider().setEnabled(checkIfNbBateauxIsEmpty());
                    break;
                }
            } catch (CreationBateauException errMsg) {
                //empeche le joueur d'ajouter un bateau impossible
            }

        }

        resetAllBackgrounds(data);
        updateTableModel(JPanelGrilleJ1.getJTable(), data);
    }

    //vérifie si tous les bateaux de nbBateaux sont vides(à 0)
    private boolean checkIfNbBateauxIsEmpty() {
        for (int i : nombreBateauxJ1.values()) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    private void setPhaseToBataille() {
        this.phaseActuelle = Phase.BATAILLE;
        vue.appendToOutput("Début de la bataille");
        generateBateauxSurvivantsFromJoueur(nombreBateauxJ1);
        generateBateauxSurvivantsFromJoueur(nombreBateauxJ2);

        vue.changeJPanelTop();

        TitledBorder leftBarBorder = (TitledBorder) vue.getJPanelLeftBar().getBorder();
        leftBarBorder.setTitle("Vos bateaux");
        vue.changeJPanelLeft();
        vue.getJPanelLeftBar().repaint();

        Border blackline = BorderFactory.createLineBorder(Color.black);
        TitledBorder rightBarBorder = BorderFactory.createTitledBorder(blackline, "Bateaux ennemis");
        rightBarBorder.setTitleJustification(TitledBorder.CENTER);
        vue.getJPanelRightBar().setBorder(rightBarBorder);
        for (HashMap.Entry<TypeBateau, Integer> set : this.nombreBateauxJ2.entrySet()) {
            TypeBateau type = set.getKey();
            int nombreBateau = set.getValue();
            this.vue.attributeJPanelToRightBar(type.name(), type.cases, nombreBateau);
        }
        JPanelGrilleJ1.getJTable().setEnabled(false);
        JPanelGrilleJ2.setBackgroundEnabled(true);
        JPanelGrilleJ2.getJTable().setEnabled(true);

        attributeVueJLabelFromNbBateaux(vue.getJButtonTypesLeft(), nombreBateauxJ1);
        attributeVueJLabelFromNbBateaux(vue.getJButtonTypesRight(), nombreBateauxJ2);

        vue.getJPanelBotBar().remove(vue.getJButtonRetirer());
        vue.getJButtonValider().setText("Tirer");
        vue.getJButtonValider().setEnabled(false);
        vue.getJPanelBotBar().repaint();
    }

    public void tourBatailleJ1() {
        bataille.j1TireSurJ2(caseChoisie);
        updateCompteurFromHit(caseChoisie, vue.getJLabelCompteurJ1());
        setBorderSiBateauDetruit(caseChoisie, JPanelGrilleJ2.getJTable().getGrilleTableModel().getData());
        playSoundFromCase(caseChoisie);
        vue.appendToOutput("Vous tirez sur " + caseChoisie.getDisplayName() + "!");
        resetAllBackgrounds(JPanelGrilleJ2.getJTable().getGrilleTableModel().getData());
        updateTableModel(JPanelGrilleJ2.getJTable(), JPanelGrilleJ2.getJTable().getGrilleTableModel().getData());
        //JPanelGrilleJ2.getJTable().repaint();
        generateBateauxSurvivantsFromJoueur(nombreBateauxJ2);
        attributeVueJLabelFromNbBateaux(vue.getJButtonTypesRight(), nombreBateauxJ2);
    }

    public void tourBatailleJ2() {
        Case caseTireeOrdi = bataille.getJoueur2().inputToTir();
        caseTireeOrdi = bataille.j2TireSurJ1(caseTireeOrdi);
        if (bataille.getJoueur1().getGrille().isEtat()) {
            //faire l'écran de victoire pour l'ordi(il faudrait avoir un qi a 2 chiffres pour perdre)
            ctrlP.afficherVueFin(JPanelFin.Resultat.DEFAITE);
            return;
        }
        updateCompteurFromHit(caseTireeOrdi, vue.getJLabelCompteurJ2());
        playSoundFromCase(caseTireeOrdi);
        vue.appendToOutput("L'adversaire tire sur " + caseTireeOrdi.getDisplayName() + "!");
        generateBateauxSurvivantsFromJoueur(nombreBateauxJ1);
        attributeVueJLabelFromNbBateaux(vue.getJButtonTypesLeft(), nombreBateauxJ1);
        updateTableModel(JPanelGrilleJ1.getJTable(), JPanelGrilleJ1.getJTable().getGrilleTableModel().getData());
    }

    //attribue une couleur de bordure aléatoire pour un bateau de la grille
    private void setDisplayCaseBorderFromBateau(Bateau bateau, DisplayCase[][] data) {
        //case de la première extrémité 
        //voir Grille.getCasesBateau pour comprendre pourquoi les 2 premières cases sont les extrémités
        Case case1 = bateau.getCasesDuBateau().get(0);
        //case de la deuxième extrémité
        Case case2 = bateau.getCasesDuBateau().get(1);
        int[] innerCase = new int[4];
        Random rand = new Random();
        Color randColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        if (case1.getX() == case2.getX()) {
            if (case1.getY() > case2.getY()) {
                data[case1.getY()][case1.getX()].setInnerBorder(0, 3, 3, 3, randColor);
                data[case2.getY()][case2.getX()].setInnerBorder(3, 3, 0, 3, randColor);
            } else {
                data[case1.getY()][case1.getX()].setInnerBorder(3, 3, 0, 3, randColor);
                data[case2.getY()][case2.getX()].setInnerBorder(0, 3, 3, 3, randColor);
            }

            innerCase[0] = 0;
            innerCase[1] = 3;
            innerCase[2] = 0;
            innerCase[3] = 3;

        } else {
            if (case1.getX() > case2.getX()) {
                data[case1.getY()][case1.getX()].setInnerBorder(3, 0, 3, 3, randColor);
                data[case2.getY()][case2.getX()].setInnerBorder(3, 3, 3, 0, randColor);
            } else {
                data[case1.getY()][case1.getX()].setInnerBorder(3, 3, 3, 0, randColor);
                data[case2.getY()][case2.getX()].setInnerBorder(3, 0, 3, 3, randColor);
            }
            innerCase[0] = 3;
            innerCase[1] = 0;
            innerCase[2] = 3;
            innerCase[3] = 0;
        }

        for (int i = 2; i < bateau.getCasesDuBateau().size(); i++) {
            Case uneCase = bateau.getCasesDuBateau().get(i);
            data[uneCase.getY()][uneCase.getX()].setInnerBorder(innerCase[0], innerCase[1], innerCase[2], innerCase[3], randColor);
        }
    }

    private void setBorderSiBateauDetruit(Case caseTouchee, DisplayCase[][] data) {
        if (caseTouchee.getBateauProprio() != null) {
            if (caseTouchee.getBateauProprio().isEtat()) {
                setDisplayCaseBorderFromBateau(caseTouchee.getBateauProprio(), data);
            }
        }
    }

    private void playSoundFromCase(Case caseTouche) {
        if (caseTouche.getBateauProprio() != null) {
            Sound.playExplosion();
            return;
        }
        Sound.playSplash();
    }

    private void updateCompteurFromHit(Case caseTiree, JLabel compteur) {
        if (caseTiree.getBateauProprio() != null) {
            int oldCount = Integer.parseInt(compteur.getText());
            compteur.setText(String.valueOf(oldCount + 1));
        }
    }

    private void resetAllBackgrounds(DisplayCase[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                data[i][j].setBackgroundColor(null);
            }
        }

    }

    private void updateTableModel(JTableGrille table, DisplayCase[][] data) {
        JTableGrille.GrilleTableModel tableModel = table.getGrilleTableModel();
        tableModel.setData(data);
        tableModel.fireTableDataChanged();
        table.setModel(tableModel);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); 
    }

    public JPanelBataille getVue() {
        return vue;
    }

    public Bataille getBataille() {
        return bataille;
    }

}
