/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

/**
 *
 * @author acassard
 */
import java.util.*;
public abstract class ObservableAbstrait {
    // Liste des observateurs
    private List<Observateur> lesObservateurs = new LinkedList<>();
    /** Ajouter un observateur de la liste
     * @param pObservateur */
    public void ajouterObservateur(Observateur pObservateur) {
        lesObservateurs.add(pObservateur);
    }
    /** Supprimer un observateur de la liste
     * @param pObservateur */
    public void supprimerObservateur(Observateur pObservateur) {
        lesObservateurs.remove(pObservateur);
    }
    /** Notifier à tous les observateurs de la liste que l'objet à été mis à jour. */
    protected void notifier() {
        for(Observateur unObservateur : lesObservateurs) {
            unObservateur.miseAJour(this);
        }
    }
}