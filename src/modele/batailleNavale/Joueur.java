/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele.batailleNavale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 *
 * @author cantp
 */
public class Joueur {

    protected String nom;
    protected Grille grille;
    protected ArrayList<Case> tirs;

    public Joueur(String nom) {
        this.nom = nom;
        this.grille = new Grille();
        this.tirs = new ArrayList<>();
    }

    //Affiche les bateaux restants, la grille puis place un bateau
    public void tourDePlacement() {
        printBateauxManquants(this.grille.getBateauxRestants());
        this.grille.afficherGrilleAlliee();
        Bateau leBateau = inputToBateau();        
        this.grille.placerBateau(leBateau);
    }

    //retourne la case que le joueur souhaite tirer
    //!! cette fonction n'agit pas sur la grille d'elle même
    public Case inputToTir() {
        String displayName = inputToCaseDisplayName("Entrez la case où vous souhaitez tirer :");
        Case caseTiree = new Case(displayName);
        if(this.tirs.contains(caseTiree)){
            System.out.println("Vous avez déjà tiré sur cette case, veuillez réessayer");
            return inputToTir();
        }
        this.tirs.add(caseTiree);
        return caseTiree;
    }

    public Bateau inputToBateau() {
        Scanner userInput = new Scanner(System.in);
        TypeBateau typeChoisi = (TypeBateau) demanderChoix("Quel genre de bateau allez vous placer ?", this.grille.getBateauxRestants());
        Case extremite1 = null;
        do {
            String case1 = inputToCaseDisplayName("Entrez la case contenant la première extrémité du bateau :");
            extremite1 = this.grille.getCaseByString(case1);
            if (extremite1 == null) {
                System.out.println("La case est déjà prise, prenez en une autre");
            }
        } while (extremite1 == null);
        Case extremite2 = (Case) demanderChoix("Cases possibles pour la deuxième extrémité", this.grille.getSecondeExtremite(typeChoisi, extremite1));
        try {
            ArrayList<Case> casesBateau = this.grille.getCasesBateau(extremite1, extremite2);
            Bateau leBateau = new Bateau(casesBateau, typeChoisi);
            return leBateau;
        } catch (CreationBateauException e) {
            System.out.println(e);
            return inputToBateau();
        }

    }

    //affiche la liste de bateaux manquants selon la liste de types envoyée
    //A utiliser avec Grille.getBateauxRestants
    public void printBateauxManquants(ArrayList<TypeBateau> typesManquants) {
        String debut = "Il vous manque: ";
        String milieu = "";
        int nbSousMarins = 0;
        for (TypeBateau leType : typesManquants) {
            if (leType == TypeBateau.SOUSMARIN) {
                nbSousMarins++;
            } else {
                milieu = milieu + ", 1 " + leType.toString();
            }
        }
        if (nbSousMarins > 0) {
            milieu = milieu + ", " + String.valueOf(nbSousMarins) + " " + TypeBateau.SOUSMARIN.toString();
        }
        milieu = milieu.replaceFirst(", ", "");

        System.out.println(debut + milieu);
    }

    //demande au joueur son choix parmi une liste d'objets, et retourne l'objet choisi
    //les objets passés doivent implémenter Affichable pour être affichés dans la console
    //la méthode getDisplayName est utilisée pour afficher le choix
    //Il faut caster l'objet retourné au type souhaité
    public Affichable demanderChoix(String question, ArrayList<? extends Affichable> lesChoix) {
        Scanner userInput = new Scanner(System.in);
        System.out.println(question);
        int i = 1;
        Map<Integer, Affichable> map = new HashMap<>();
        for (Affichable unChoix : lesChoix) {
            if (unChoix != null) {
                if (!map.containsValue(unChoix)) {
                    map.put(i, unChoix);
                    i++;
                }
            }
        }
        String listeChoix = "";
        for (Map.Entry<Integer, Affichable> entry : map.entrySet()) {
            listeChoix = listeChoix + " / " + entry.getValue().getDisplayName() + "(" + String.valueOf(entry.getKey()) + ")";
        }
        listeChoix = listeChoix.replaceFirst(" / ", "");
        System.out.println(listeChoix);
        System.out.println("Entrez le numéro correspondant :");
        try {
            Affichable choix = map.get(userInput.nextInt());
            System.out.println(choix.getDisplayName());
            return choix;
        } catch (InputMismatchException e) {
            System.out.println("Erreur dans la saisie du numéro, veuillez réessayer");
            return demanderChoix(question, lesChoix);
        }
    }

    //récupère une case dans la grille du joueur en lui demandant d'entrer le display name
    public String inputToCaseDisplayName(String question) {
        Scanner userInput = new Scanner(System.in);
        System.out.println(question);
        String displayName = userInput.nextLine();
        if (!Case.isDisplayNameValid(displayName)) {
            System.out.println("Nom de Case invalide, veuillez réessayer");
            return inputToCaseDisplayName(question);
        }
        return displayName;
    }

    public String getNom() {
        return nom;
    }

    public Grille getGrille() {
        return grille;
    }

}
