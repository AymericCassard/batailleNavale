/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package test.batailleNavale;
import modele.batailleNavale.Grille;
//import modele.batailleNavale.Bateau;
import java.util.ArrayList;
import modele.batailleNavale.Case;

/**
 *
 * @author cantp
 */
public class testGrille {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Test du contenu de la grille
        Grille laGrille = new Grille();
        laGrille.afficherTirs();
        Case c1 = new Case(0,1);
        Case c2 = new Case(0,2);
        Case c3 = new Case(0,3);
        ArrayList<Case> lesCases = new ArrayList<>();
        lesCases.add(c1);
        lesCases.add(c2);
        lesCases.add(c3);
        laGrille.placerBateau(lesCases);
        laGrille.tirer(0,2);
        laGrille.afficherTirs();
        
    }
    
}
