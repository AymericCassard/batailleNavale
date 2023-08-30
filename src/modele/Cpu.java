/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

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
        if(this.tirs.contains(caseTiree)){
            return inputToTir();
        }
        this.tirs.add(caseTiree);        
        return caseTiree;
    }
    
    @Override
    public Bateau inputToBateau() {
        int maxIndex = this.grille.getBateauxRestants().size() ;
        TypeBateau typeChoisi = this.grille.getBateauxRestants().get(this.rand.nextInt(maxIndex));
        Case extremite1 = this.grille.getCaseByCoord(this.rand.nextInt(10), this.rand.nextInt(10));
        if (extremite1.getBateauProprio() != null) {
            return inputToBateau();
        }
        maxIndex = this.grille.getSecondeExtremite(typeChoisi, extremite1).size();
        if (maxIndex <= 0){
            return inputToBateau();
        }
        Case extremite2 = this.grille.getSecondeExtremite(typeChoisi, extremite1).get(this.rand.nextInt(maxIndex));        
        try {
                ArrayList<Case> casesBateau = this.grille.getCasesBateau(extremite1, extremite2);                
                Bateau leBateau = new Bateau(casesBateau, typeChoisi);
                return leBateau;
                
            } catch(CreationBateauException e) {                
                return inputToBateau();
            }        
    }
}
