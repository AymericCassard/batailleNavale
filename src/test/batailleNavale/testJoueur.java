/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package test.batailleNavale;
import modele.batailleNavale.Joueur;

/**
 *
 * @author cantp
 */
public class testJoueur {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Joueur joueurTest = new Joueur("TestMan");
//        joueurTest.placerBateauHorizontal(1, 0);
//        joueurTest.placerBateauVertical(8, 1);
        
        System.out.println(joueurTest.getGrille().afficherGrille());
        
        joueurTest.tirer(6, 7);
        
    }
    
}
