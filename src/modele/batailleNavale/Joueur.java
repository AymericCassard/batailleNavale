/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele.batailleNavale;

import java.util.ArrayList;

/**
 *
 * @author cantp
 */
public class Joueur {

    private String nom;
    private Grille grille;
    private ArrayList<Bateau> lesBateaux;

    public Joueur(String nom) {
        this.nom = nom;
        this.grille = new Grille();
        this.lesBateaux = new ArrayList<>();
    }       

    public String getNom() {
        return nom;
    }

    public Grille getGrille() {
        return grille;
    }


}
