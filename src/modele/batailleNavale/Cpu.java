/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele.batailleNavale;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author acassard
 */

public class Cpu extends Joueur {
    
    private Random rand;
    
    public Cpu(String nom) {
        super(nom);
        this.rand = new Random();
    }
    
    @Override
    public void tourDePlacement(){
        Bateau leBateau = inputToBateau();
        this.grille.placerBateau(leBateau);
    }
    
    @Override
    public Case inputToTir() {
        Case caseTiree = new Case(this.rand.nextInt(10), this.rand.nextInt(10));
        System.out.println("l'ordinateur tire sur " + caseTiree.getDisplayName());
        return caseTiree;
    }
    
    @Override
    public Bateau inputToBateau() {
        int maxIndex = this.grille.getBateauxRestants().size() ;
        TypeBateau typeChoisi = this.grille.getBateauxRestants().get(this.rand.nextInt(maxIndex));
        Case extremite1 = this.grille.getCaseByCoord(this.rand.nextInt(10), this.rand.nextInt(10));
        if (extremite1 == null) {
            return inputToBateau();
        }
        maxIndex = this.grille.getSecondeExtremite(typeChoisi, extremite1).size();
        Case extremite2 = this.grille.getSecondeExtremite(typeChoisi, extremite1).get(this.rand.nextInt(maxIndex));
        if (extremite2 == null) {
            return inputToBateau();
        }
        try {
                ArrayList<Case> casesBateau = this.grille.getCasesBateau(extremite1, extremite2);                
                Bateau leBateau = new Bateau(casesBateau, typeChoisi);
                return leBateau;
                
            } catch(CreationBateauException e) {
                System.out.print(e);
                return inputToBateau();
            }        
    }
}
