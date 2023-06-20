/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele.batailleNavale;
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
                Case uneCase = new Case (x,y);
                this.lesCases.add(uneCase);
            }
        }        
        
    }
    
    public Grille(int taille) {
        
        this.taille = taille;
        this.lesCases = new ArrayList<>() ;
        for (int y = 0; y <= this.taille; y++) {
            for (int x = 0; x <= this.taille; x++) {
                Case uneCase = new Case (x,y);
                this.lesCases.add(uneCase);
            }
        }
        
    }
    
    //retourne un bateau si il est touché, null sinon
    public Bateau tirer(int x, int y){
                
        for(Bateau unBateau : this.lesBateaux){            
            for(Case uneCase : unBateau.getCasesDuBateau()){                
                if (uneCase.getX() == x && uneCase.getY() == y){
                uneCase.setEtat(true);                
                return unBateau;
                }                
            }                        
        }
        for(Case uneCase : this.lesCases){
            if (uneCase.getX() == x && uneCase.getY() == y){
                uneCase.setEtat(true);                
                break;
            }
        }
        return null;        
    }
    
    //pareil que tirer mais avec une case en paramètre
    public Bateau tirer(Case uneCase) {
        return tirer(uneCase.getX(), uneCase.getY());
    }
        
    
    //Cette grille affiche les bateaux et les cases touchées
    public void afficherGrilleAlliee(){
         
        int x = 0;
        int y = 0;
        int nbCases = (this.taille + 1) * (this.taille + 1); 
        System.out.println("  1|2|3|4|5|6|7|8|9|10");        
        for(int i = 0; i < nbCases; i++){
            if (x == 0){
                System.out.print(this.alphabet.charAt(y) + "|");
            }
            for (Case uneCase : this.lesCases){
                if(uneCase.getX() == x  && uneCase.getY() == y){
                    uneCase.printGridState();
                    break;
                }
            }
            for (Bateau unBateau : this.lesBateaux){
                Case caseBateau = unBateau.getCaseByCoord(x, y);
                if(caseBateau != null){
                    caseBateau.printBateauState();
                    break;
                }
            }
            //System.out.println("{x =" + String.valueOf(x) + "}/{y =" + String.valueOf(y) + "}");
            if(x == this.taille){
                x = 0;
                y++;
                System.out.println("");
            } else {
                x++;
            }            
        }        
    }
    
    //Cette grille affiche seulement les cases Touchées
    public void afficherGrilleEnnemie(){
         
        int x = 0;
        int y = 0;
        int nbCases = (this.taille + 1) * (this.taille + 1); 
        System.out.println("  1|2|3|4|5|6|7|8|9|10");        
        for(int i = 0; i < nbCases; i++){
            if (x == 0){
                System.out.print(this.alphabet.charAt(y) + "|");
            }
            for (Case uneCase : this.lesCases){
                if(uneCase.getX() == x  && uneCase.getY() == y){
                    uneCase.printGridState();
                    break;
                }
            }
            for (Bateau unBateau : this.lesBateaux){
                Case caseBateau = unBateau.getCaseByCoord(x, y);
                if(caseBateau != null){
                    if(caseBateau.isEtat()){
                        caseBateau.printBateauState();
                        break;
                    } 
                    caseBateau.printGridState();                    
                }
            }
            //System.out.println("{x =" + String.valueOf(x) + "}/{y =" + String.valueOf(y) + "}");
            if(x == this.taille){
                x = 0;
                y++;
                System.out.println("");
            } else {
                x++;
            }            
        }        
    }
    
    public void placerBateau(Bateau leBateau){
        Iterator<Case> it = this.lesCases.iterator();
        while(it.hasNext()){
            Case currentCase = it.next();
            for(Case uneCase : leBateau.getCasesDuBateau()){                
                if(uneCase.getX() == currentCase.getX() && uneCase.getY() == currentCase.getY()){
                    it.remove();
                }
            }
        }
        leBateau.ajouterObservateur(this);
        this.lesBateaux.add(leBateau);
        
    }
    
    //retourne une arrayList contenant les cases entre deux extrémités, en incluant ces extremités
    //throw CreationBateauException si l'une des cases entre deux est nulle, donc prise par un autre bateau
    public ArrayList<Case> getCasesBateau(Case extremite1, Case extremite2) throws CreationBateauException{
        int x1 = extremite1.getX();
        int x2 = extremite2.getX();
        int y1 = extremite1.getY();
        int y2 = extremite2.getY();
        ArrayList<Case> lesCases = new ArrayList<>();
        lesCases.add(extremite1);
        lesCases.add(extremite2);
        if (x1 == x2) {            
            if (y1 > y2) {
                for (int i = y2 + 1; i < y1; i++){
                    lesCases.add(getCaseByCoord(x1, i));
                }
            } else {
                for (int i = y1 + 1; i < y2; i++){
                    lesCases.add(getCaseByCoord(x1, i));
                }
            }
        } else {            
           if(x1 > x2){
               for (int i = x2 + 1; i < x1; i++){
                    lesCases.add(getCaseByCoord(i, y1));
                }
           } else {
               for (int i = x1 + 1; i < x2; i++){
                    lesCases.add(getCaseByCoord(i, y1));
                }
           } 
        }
        if (lesCases.contains(null)){
            throw new CreationBateauException("Il y a un bateau entre les deux extrémités");
        }
        return lesCases;
    }
    
    //retourne une case par son nom de case, null sinon
    //exemples de noms cases: A6, J2, E3...
    public Case getCaseByString(String nomCase){        
            char colonne = Character.toLowerCase(nomCase.charAt(0));
            String ligne = nomCase.substring(1);
            System.out.println("colonne = " + colonne + "/ ligne = " + ligne);
            int x = Integer.parseInt(ligne) - 1;
            int y = this.alphabet.indexOf(colonne);                        
            return getCaseByCoord(x, y);                
    }
    
    //retourne la case si elle existe dans la grille, null sinon
    //ne retourne pas la case si elle fait partie d'un bateau
    public Case getCaseByCoord(int x,int y){
        for(Case uneCase : this.lesCases){
            if(uneCase.getX() == x  && uneCase.getY() == y){
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
            if(unBateau.getType() == TypeBateau.PORTEAVION){
                typesManquants.remove(TypeBateau.PORTEAVION);
            }
            if(unBateau.getType() == TypeBateau.CROISEUR){
                typesManquants.remove(TypeBateau.CROISEUR);
            }
            if(unBateau.getType() == TypeBateau.SOUSMARIN){
                typesManquants.remove(TypeBateau.SOUSMARIN);
            }
            if(unBateau.getType() == TypeBateau.TORPILLEUR){
                typesManquants.remove(TypeBateau.TORPILLEUR);
            }
        }
        return typesManquants;        
    }
    
    //retourne une liste de cases pouvant correspondre à une seconde extremité de bateau selon le type et l'emplacement de la 1ère case
    public ArrayList<Case> getSecondeExtremite(TypeBateau unType, Case laCase){
        //case a gauche
        Case case1 = getCaseByCoord(laCase.getX() - unType.cases + 1, laCase.getY());
        //case en haut
        Case case2 = getCaseByCoord(laCase.getX(), laCase.getY() + unType.cases - 1);
        //case en bas
        Case case3 = getCaseByCoord(laCase.getX(), laCase.getY() - unType.cases + 1);
        //case a droite
        Case case4 = getCaseByCoord(laCase.getX() + unType.cases - 1, laCase.getY());
        ArrayList<Case> casesPossibles = new ArrayList<>();
        casesPossibles.add(case1);
        casesPossibles.add(case2);
        casesPossibles.add(case3);
        casesPossibles.add(case4);
        return casesPossibles;        
    }
    
    @Override
    public void miseAJour(ObservableAbstrait unObservable){
        boolean checkCoule = true;
        for (Bateau unBateau : this.lesBateaux) {
            if(!unBateau.isEtat()) {
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
    
}
