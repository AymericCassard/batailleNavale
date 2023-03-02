/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele.batailleNavale;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author cantp
 */
public class Grille {
    
    private ArrayList<Case> lesCases;
    private ArrayList<Bateau> lesBateaux;
    private int taille = 9;
    
    public Grille() {
        
        this.lesCases = new ArrayList<>();
        this.lesBateaux = new ArrayList<>();
        for (int y = 0; y < this.taille; y++) {
            for (int x = 0; x < this.taille; x++) {
                Case uneCase = new Case (x,y);
                this.lesCases.add(uneCase);
            }
        }        
        
    }
    
    public Grille(int taille) {
        
        this.taille = taille;
        this.lesCases = new ArrayList<>() ;
        for (int y = 0; y < this.taille; y++) {
            for (int x = 0; x < this.taille; x++) {
                Case uneCase = new Case (x,y);
                this.lesCases.add(uneCase);
            }
        }
        
    }
    
    public void tirer(int x, int y){
        
        for(Bateau unBateau : this.lesBateaux){
            for(Case uneCase : unBateau.getCasesDuBateau()){
                if (uneCase.getX() == x && uneCase.getY() == y){
                uneCase.setEtat(true);
                System.out.println("Touché");
                return;
                }
            }
            
        }
        for(Case uneCase : this.lesCases){
            if (uneCase.getX() == x && uneCase.getY() == y){
                uneCase.setEtat(true);
                System.out.println("Manqué");
                return;
            }
        }
        System.out.println("Grille.tirer ERREUR");
    }
    
//    public void afficherTirs(){
//         
//        int x = 0;
//        int y = 0;         
//        for(){
//            
//        }        
//    }
    
    public void placerBateau(ArrayList<Case> lesCases){
        Iterator<Case> it = this.lesCases.iterator();
        while(it.hasNext()){
            for(Case uneCase : lesCases){
                if(uneCase.getX() == it.next().getX() && uneCase.getY() == it.next().getY()){
                    it.remove();
                }
            }
        }
        Bateau leBateau = new Bateau(lesCases);
        this.lesBateaux.add(leBateau);
        
    }
    
    public int getTaille() {
        
        return this.taille;
        
    }

    public ArrayList<Case> getLesCases() {
        return lesCases;
    }

    public void setLesCases(ArrayList<Case> lesCases) {
        this.lesCases = lesCases;
    }
    
    
}
