/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package test;
import modele.Joueur;
import modele.Cpu;
import modele.Bataille;

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
        Joueur j2 = new Cpu("testman2");
        Bataille batailleTest = new Bataille(j1,j2);
        batailleTest.phaseDePlacement();        
    }
    
}
