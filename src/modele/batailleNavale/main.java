/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package modele.batailleNavale;

/**
 *
 * @author acassard
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Joueur j1 = Bataille.demanderNom();
        Joueur j2 = new Cpu("Ordinateur");
        Bataille laBataille = new Bataille(j1, j2);
        laBataille.demarrer();
    }
    
}
