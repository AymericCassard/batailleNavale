/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package test;

import java.awt.Dimension;
import java.util.HashMap;
import modele.Bataille;
import modele.Case;
import modele.Grille;
import modele.Joueur;
import modele.TypeBateau;
import vue.JPanelBataille;
import vue.JFrameMain;
import vue.composants.DisplayCase;
import vue.composants.JPanelGrille;

/**
 *
 * @author acassard
 */
public class testJPanelBataille {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrameMain JFrame = new JFrameMain();
//        Joueur j1 = new Joueur("j1");
//        Joueur j2 = new Joueur("j2");
//        Bataille bataille = new Bataille(j1, j2);
        Grille grille = new Grille();
        DisplayCase[][] data = new DisplayCase[10][10];
        for(Case uneCase : grille.getLesCases()){
            data[uneCase.getX()][uneCase.getY()] = new DisplayCase(uneCase);
        }
        JPanelGrille JPanelgrilleJ1 = new JPanelGrille(data,new Dimension(440, 440));
        JPanelGrille JPanelgrilleJ2 = new JPanelGrille(data,new Dimension(440, 440));
        JPanelBataille JPanel = new JPanelBataille(JPanelgrilleJ1, JPanelgrilleJ2);
        HashMap <TypeBateau, Integer> nombreBateau = new HashMap<>();
        for (TypeBateau type : TypeBateau.values()){
            nombreBateau.put(type, 0);
            JPanel.attributeJPanelToLeftBar(type.name(), type.cases, 0);
        }               
        for(int i = 0; i < 10; i++){
            JPanel.appendToOutput("test");
        }        
        JFrame.setjPanelActive(JPanel);
        JFrame.setVisible(true);
        System.out.println(JPanelgrilleJ1.getJTable().getTableHeader().getSize().height);
        System.out.println(JPanelgrilleJ1.getJTable().getSize().height);
        System.out.println(JPanelgrilleJ1.getSize());
    }
    
}
