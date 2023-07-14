/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package test;
import modele.Bateau;
import modele.Case;
import modele.Grille;
import modele.TypeBateau;
import modele.CreationBateauException;
import java.util.ArrayList;

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
        //initialisation des variables
        Grille laGrille = new Grille();        
        Case c1 = new Case(0,1);
        Case c2 = new Case(0,2);
        Case c3 = new Case(0,3);        
        ArrayList<Case> lesCases = new ArrayList<>();
        lesCases.add(c1);
        lesCases.add(c2);
        lesCases.add(c3);
        try {
            Bateau sousMarin1 = new Bateau(lesCases, TypeBateau.SOUSMARIN);
            laGrille.placerBateau(sousMarin1);
        } catch(CreationBateauException e) {
            System.out.println(e);
        }
        laGrille.tirer(0,2);
        laGrille.afficherGrilleAlliee();
        System.out.println("");
        laGrille.afficherGrilleEnnemie();
        for(TypeBateau unType : laGrille.getBateauxRestants()) {
            System.out.println(unType);
        }
        
        //Test de getCasesBateau
        System.out.println("Test de getCasesBateau");
        Case extremite1 = laGrille.getCaseByCoord(3, 3);
        Case extremite2 = laGrille.getCaseByCoord(3, 6);
        try{
            lesCases = laGrille.getCasesBateau(extremite1, extremite2);
        } catch(CreationBateauException e) {
            System.out.println(e);
        }
        
        for (Case uneCase : lesCases) {
            System.out.println(uneCase.toString());
        }
        
        //Test de l'observateur bateau
        System.out.println("Test de l'observateur bateau");
        //laGrille.tirer(0, 1);
        laGrille.tirer(0, 3);
        System.out.println("La grille est-elle detruite? " + laGrille.isEtat());
        Case c4 = new Case(0,4);
        Case c5 = new Case(0,5);
        Case c6 = new Case(0,6);
        lesCases = new ArrayList<>();
        lesCases.add(c4);
        lesCases.add(c5);
        lesCases.add(c6);
        try {
            Bateau sousMarin2 = new Bateau(lesCases, TypeBateau.SOUSMARIN);
            laGrille.placerBateau(sousMarin2);
        } catch (CreationBateauException e) {
            System.out.print(e);
        }
        laGrille.tirer(0, 4);
        laGrille.afficherGrilleAlliee();
        System.out.println("La grille est-elle detruite? " + laGrille.isEtat());
        
    }
    
}
