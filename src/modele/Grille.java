/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author cantp
 */
public class Grille implements Observateur {

    private ArrayList<Case> lesCases;
    private ArrayList<Bateau> lesBateaux;
    private int taille = 9;
    private boolean etat = false; //etat des bateaux de la grille, false = il reste des bateaux, true = tous les bateaux détruits
    private final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public Grille() {

        this.lesCases = new ArrayList<>();
        this.lesBateaux = new ArrayList<>();
        for (int y = 0; y <= this.taille; y++) {
            for (int x = 0; x <= this.taille; x++) {
                Case uneCase = new Case(x, y);
                this.lesCases.add(uneCase);
            }
        }

    }

    public Grille(int taille) {

        this.taille = taille;
        this.lesCases = new ArrayList<>();
        for (int y = 0; y <= this.taille; y++) {
            for (int x = 0; x <= this.taille; x++) {
                Case uneCase = new Case(x, y);
                this.lesCases.add(uneCase);
            }
        }

    }

    //tire et retourne la case Tirée
    public Case tirer(int x, int y) {
        for (Case uneCase : this.lesCases) {
            if (uneCase.getX() == x && uneCase.getY() == y) {
                uneCase.setEtat(true);                
                return uneCase;
            }
        }
        return null;
    }

    //pareil que tirer mais avec une case en paramètre
    public Case tirer(Case uneCase) {
        return tirer(uneCase.getX(), uneCase.getY());
    }

    //Cette grille affiche les bateaux et les cases touchées
    public void afficherGrilleAlliee() {

        int x = 0;
        int y = 0;
        int nbCases = (this.taille + 1) * (this.taille + 1);
        System.out.println("  1|2|3|4|5|6|7|8|9|10");
        for (int i = 0; i < nbCases; i++) {
            if (x == 0) {
                System.out.print(this.alphabet.charAt(y) + "|");
            }
            for (Case uneCase : this.lesCases) {
                if (uneCase.getX() == x && uneCase.getY() == y) {
                    uneCase.printGridState();
                    break;
                }
            }
            //System.out.println("{x =" + String.valueOf(x) + "}/{y =" + String.valueOf(y) + "}");
            if (x == this.taille) {
                x = 0;
                y++;
                System.out.println("");
            } else {
                x++;
            }
        }
    }

    //Cette grille affiche seulement les cases Touchées
    public void afficherGrilleEnnemie() {

        int x = 0;
        int y = 0;
        int nbCases = (this.taille + 1) * (this.taille + 1);
        System.out.println("  1|2|3|4|5|6|7|8|9|10");
        for (int i = 0; i < nbCases; i++) {
            if (x == 0) {
                System.out.print(this.alphabet.charAt(y) + "|");
            }
            for (Case uneCase : this.lesCases) {
                if (uneCase.getX() == x && uneCase.getY() == y) {
                    if (uneCase.getBateauProprio() != null && !uneCase.isEtat()) {
                        System.out.print("O ");
                        break;
                    }
                    uneCase.printGridState();
                    break;
                }
            }
//            for (Bateau unBateau : this.lesBateaux){
//                Case caseBateau = unBateau.getCaseByCoord(x, y);
//                if(caseBateau != null){
//                    if(caseBateau.isEtat()){
//                        caseBateau.printBateauState();
//                        break;
//                    } 
//                    caseBateau.printGridState();                    
//                }
//            }
            //System.out.println("{x =" + String.valueOf(x) + "}/{y =" + String.valueOf(y) + "}");
            if (x == this.taille) {
                x = 0;
                y++;
                System.out.println("");
            } else {
                x++;
            }
        }
    }

    public void placerBateau(Bateau leBateau) {
//        ArrayList<Case> casesBateau = new ArrayList<>();
//        for (Case uneCase : leBateau.getCasesDuBateau()) {
//            Case caseGrille = this.getCaseByCoord(uneCase.getX(), uneCase.getY());
//            caseGrille.setBateauProprio(leBateau);
//            casesBateau.add(caseGrille);
//        }
//        leBateau.setCasesDuBateau(casesBateau);
        leBateau.ajouterObservateur(this);
        this.lesBateaux.add(leBateau);

    }
    
    public void retirerBateau(Bateau leBateau){
        if(lesBateaux.contains(leBateau)){
            for (Case uneCase : leBateau.getCasesDuBateau()){
                uneCase.setBateauProprio(null);
            }
            lesBateaux.remove(leBateau);
        }
    }

    //retourne une arrayList contenant les cases entre deux extrémités, en incluant ces extremités
    //throw CreationBateauException si l'une des cases entre deux est prise par un autre bateau
    public ArrayList<Case> getCasesBateau(Case extremite1, Case extremite2) throws CreationBateauException {
        int x1 = extremite1.getX();
        int x2 = extremite2.getX();
        int y1 = extremite1.getY();
        int y2 = extremite2.getY();
        String errMsg = "Il y a un bateau entre les deux extrémités";
        ArrayList<Case> lesCases = new ArrayList<>();
        lesCases.add(extremite1);
        lesCases.add(extremite2);        
        if (x1 == x2) {
            if (y1 > y2) {
                for (int i = y2 + 1; i < y1; i++) {
                    Case caseConcernee = getCaseByCoord(x1, i);
                    if(caseConcernee.getBateauProprio() != null) {
                        throw new CreationBateauException(errMsg);
                    }
                    lesCases.add(caseConcernee);                    
                }
            } else {
                for (int i = y1 + 1; i < y2; i++) {
                    Case caseConcernee = getCaseByCoord(x1, i);
                    if(caseConcernee.getBateauProprio() != null) {
                        throw new CreationBateauException(errMsg);
                    }
                    lesCases.add(caseConcernee);  
                }
            }
        } else {
            if (x1 > x2) {
                for (int i = x2 + 1; i < x1; i++) {
                    Case caseConcernee = getCaseByCoord(i, y1);
                    if(caseConcernee.getBateauProprio() != null) {
                        throw new CreationBateauException(errMsg);
                    }
                    lesCases.add(caseConcernee);  
                }
            } else {
                for (int i = x1 + 1; i < x2; i++) {
                    Case caseConcernee = getCaseByCoord(i, y1);
                    if(caseConcernee.getBateauProprio() != null) {
                        throw new CreationBateauException(errMsg);
                    }
                    lesCases.add(caseConcernee);
                }
            }
        }        
        return lesCases;
    }

    //retourne une case par son nom de case, null sinon
    //exemples de noms cases: A6, J2, E3...
    public Case getCaseByString(String nomCase) {
        char colonne = Character.toLowerCase(nomCase.charAt(0));
        String ligne = nomCase.substring(1);
        System.out.println("colonne = " + colonne + "/ ligne = " + ligne);
        int x = Integer.parseInt(ligne) - 1;
        int y = this.alphabet.indexOf(colonne);
        return getCaseByCoord(x, y);
    }

    //retourne la case si elle existe dans la grille, null sinon    
    public Case getCaseByCoord(int x, int y) {
        for (Case uneCase : this.lesCases) {
            if (uneCase.getX() == x && uneCase.getY() == y) {
                return uneCase;
            }
        }
        return null;
    }

    //Renvoie le nombre de TypeBateaux manquants sur la grille pour lancer la bataille
    //Pour rappel, une bataille a besoin de :
    //-1 PorteAvion
    //-1 Croiseur
    //-2 SousMarins
    //-1 Torpilleur
    public ArrayList<TypeBateau> getBateauxRestants() {
        ArrayList<TypeBateau> typesManquants = new ArrayList<>(List.of(TypeBateau.PORTEAVION, TypeBateau.CROISEUR, TypeBateau.TORPILLEUR, TypeBateau.SOUSMARIN, TypeBateau.SOUSMARIN));
        for (Bateau unBateau : this.lesBateaux) {
            typesManquants.remove(unBateau.getType());
        }
        return typesManquants;
    }
    
    public ArrayList<TypeBateau> getBateauxSurvivants(){
        ArrayList<TypeBateau> typesSurvivants = new ArrayList<>(List.of(TypeBateau.PORTEAVION, TypeBateau.CROISEUR, TypeBateau.TORPILLEUR, TypeBateau.SOUSMARIN, TypeBateau.SOUSMARIN));
        for (Bateau unBateau : this.lesBateaux) {
            if(unBateau.isEtat()){
                typesSurvivants.remove(unBateau.getType());
            }
        }
        return typesSurvivants;
    }

    //retourne une liste de cases pouvant correspondre à une seconde extremité de bateau selon le type et l'emplacement de la 1ère case
    public ArrayList<Case> getSecondeExtremite(TypeBateau unType, Case laCase) {        
        Case caseGauche = getCaseByCoord(laCase.getX() - unType.cases + 1, laCase.getY());        
        Case caseHaut = getCaseByCoord(laCase.getX(), laCase.getY() + unType.cases - 1);        
        Case caseBas = getCaseByCoord(laCase.getX(), laCase.getY() - unType.cases + 1);        
        Case caseDroite = getCaseByCoord(laCase.getX() + unType.cases - 1, laCase.getY());
        ArrayList<Case> casesPossibles = new ArrayList<>();        
        casesPossibles.add(caseGauche);
        casesPossibles.add(caseHaut);
        casesPossibles.add(caseBas);
        casesPossibles.add(caseDroite);
        Iterator<Case> it = casesPossibles.iterator();
        while(it.hasNext()){
            Case uneCase = it.next();
            if (uneCase == null){
                it.remove();
            } else {
                if(uneCase.getBateauProprio() != null){
                    it.remove();
                }
            }
        }
//        if (caseGauche.getBateauProprio() == null){casesPossibles.add(caseGauche);}
//        if (caseHaut.getBateauProprio() == null){casesPossibles.add(caseHaut);}
//        if (caseBas.getBateauProprio() == null){casesPossibles.add(caseBas);}
//        if (caseDroite.getBateauProprio() == null){casesPossibles.add(caseDroite);}        
        return casesPossibles;
    }

    @Override
    public void miseAJour(ObservableAbstrait unObservable) {
        boolean checkCoule = true;
        for (Bateau unBateau : this.lesBateaux) {
            if (!unBateau.isEtat()) {
                checkCoule = unBateau.isEtat();
                break;
            }
        }
        this.etat = checkCoule;
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

    public boolean isEtat() {
        return etat;
    }
    
    public ArrayList<Bateau> getLesBateaux(){
        return lesBateaux;
    }

}
