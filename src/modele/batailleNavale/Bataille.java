/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele.batailleNavale;

import java.util.Scanner;
/**
 *
 * @author cantp
 */
public class Bataille {

    private Joueur joueur1;
    private Joueur joueur2;
        

    public Bataille(Joueur j1, Joueur j2) {

        this.joueur1 = j1;
        this.joueur2 = j2;
    }
        
    public void phaseDePlacement(){
        System.out.println("Phase de placement");
        //on répète le tour de placement jusqu'a ce que le joueur aie placé tous ses bateaux
        while (!this.joueur1.getGrille().getBateauxRestants().isEmpty()) {
            this.joueur1.tourDePlacement();
        }
        while (!this.joueur2.getGrille().getBateauxRestants().isEmpty()) {
            this.joueur2.tourDePlacement();
        }
        System.out.println("Phase de placement finie");
    }
    
    //les joueurs se tirent dessus jusqu'a ce qu'un gagnant soit retourné
    public Joueur phaseDeBataille(){
        System.out.println("Début de la bataille");
        while (!this.joueur1.getGrille().isEtat() && !this.joueur2.getGrille().isEtat()){
            System.out.println("Tour j1");
            printEtatGrilles(joueur1);
            j1TireSurJ2(joueur1.inputToTir());
            if(this.joueur2.getGrille().isEtat()){
                return this.joueur1;
            }
            System.out.println("Tour j2");
            j2TireSurJ1(joueur2.inputToTir());
            if(this.joueur1.getGrille().isEtat()){
                return this.joueur2;
            }
        }
        return null;
    }
    
    public void j1TireSurJ2(Case caseTiree) {
        Bateau bateauTouche = this.joueur2.getGrille().tirer(caseTiree);
        String printString;
        if (bateauTouche == null){
            printString = "Manqué";            
        } else {
            printString = "Touché";
            if (bateauTouche.isEtat()){
                printString += " Coulé";
            }
        }
        
        printString += "!";
        System.out.println(printString);
    }
    
    public void j2TireSurJ1(Case caseTiree) {
        Bateau bateauTouche = this.joueur1.getGrille().tirer(caseTiree);
        String printString;
        if (bateauTouche == null){
            printString = "Manqué";            
        } else {
            printString = "Touché";
            if (bateauTouche.isEtat()){
                printString += " Coulé";
            }
        }
        
        printString += "!";        
        System.out.println(printString);
    }
    
    public void printEtatGrilles(Joueur jConcerne){
        if(jConcerne == this.joueur1){
            System.out.println("Votre grille");
            this.joueur1.getGrille().afficherGrilleAlliee();
            System.out.println("Grille ennemie");
            this.joueur2.getGrille().afficherGrilleEnnemie();
            return;
        }
        if(jConcerne == this.joueur2){
            System.out.println("Votre grille");
            this.joueur2.getGrille().afficherGrilleAlliee();
            System.out.println("Grille ennemie");
            this.joueur1.getGrille().afficherGrilleEnnemie();
        }
    }
    
    public static void speechVictoire(Joueur gagnant){
        System.out.println("Bravo " + gagnant.getNom() + "! Tu as détruit l'opposition!");
    }
    
    //retourne un joueur avec son nom input utilisateur
    public static Joueur demanderNom() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Quel est votre nom?");
        String nomJoueur = userInput.nextLine();
        return new Joueur(nomJoueur);
    }
    
    public void demarrer() {
        phaseDePlacement();
        Joueur gagnant = phaseDeBataille();
        speechVictoire(gagnant);
    }

    }
        
   
