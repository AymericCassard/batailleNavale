/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;
import java.util.ArrayList;
import modele.Bateau;
import modele.Grille;
import modele.Case;
import modele.CreationBateauException;
import modele.TypeBateau;
import vue.composants.JPanelGrille;
import vue.composants.DisplayCase;
import vue.*;

/**
 *
 * @author acassard
 */
public class testJTableGrille {
    
    public static void main(String[] args) {
        JFrameMain jFrame = new JFrameMain();
        jFrame.setResizable(false);
        Grille grille = new Grille();
        DisplayCase[][] data = new DisplayCase[10][10];
        ArrayList<Case> lesCases = new ArrayList<>();
        Case c1 = new Case(0,1);
        Case c2 = new Case(0,2);
        Case c3 = new Case(0,3);
        lesCases.add(c1);
        lesCases.add(c2);
        lesCases.add(c3);
        try{
        Bateau bateauTest = new Bateau(lesCases,TypeBateau.SOUSMARIN);
        grille.placerBateau(bateauTest);
        grille.tirer(6, 6);
        grille.tirer(0, 1);
        grille.tirer(0, 2);
        Bateau bateauRecu = grille.tirer(0, 3);
        System.out.println(bateauTest.toString());
        } catch(CreationBateauException e) {
            System.out.println(e);
        }        
        for(Case uneCase : grille.getLesCases()){
            data[uneCase.getX()][uneCase.getY()] = new DisplayCase(uneCase);
        }
        JPanelGrille jPanel = new JPanelGrille(data);
        jFrame.setjPanelActive(jPanel);
        jFrame.setVisible(true);
        
    }
}
