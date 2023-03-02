/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package test.batailleNavale;
import modele.batailleNavale.*;

/**
 *
 * @author cantp
 */
public class testBataille {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Joueur j1 = new Joueur("testman");
        Joueur j2 = new Joueur("testman2");
        Bataille batailleTest = new Bataille(j1,j2);
        j1.tirer(1, 2);
        j1.tirer(4, 6);
        j1.tirer(7, 3);
        System.out.print(batailleTest.checkTir());
    }
    
}
