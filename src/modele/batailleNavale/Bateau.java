package modele.batailleNavale;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author cantp
 */
public class Bateau extends ObservableAbstrait implements Observateur {
           
    private ArrayList<Case> casesDuBateau;
    private int nbTouche = 0;
    private boolean etat = false; //false = bateau intact / true = coulé
    private TypeBateau type; //contient le nom, ainsi que le nombre de cases censées être occupées
    
    //exception a construire en cas de non continuité du bateau
    public Bateau(ArrayList<Case> lesCases,TypeBateau type) throws CreationBateauException {
        //le bateau est seulement construit si l'on envoie le même nombre de cases que celui prévu par son type
        if (lesCases.size() == type.cases) {            
            if(verifierContinuite(lesCases, type)){ 
                this.casesDuBateau = lesCases;
                this.type = type;
                for (Case uneCase : this.casesDuBateau) {
                    uneCase.ajouterObservateur(this);
                }
            } else {
                throw new CreationBateauException("Une case du bateau n'est pas continue");
            }
        } else {
            throw new CreationBateauException("Il manque une case au bateau par rapport au type annoncé");
        }      
        
    }

    public ArrayList<Case> getCasesDuBateau() {
        return casesDuBateau;
    }
    
    //retourne la case si elle existe dans le bateau, null sinon
    public Case getCaseByCoord(int x,int y){
        for(Case uneCase : this.casesDuBateau){
            if(uneCase.getX() == x  && uneCase.getY() == y){
                return uneCase;
            }
        }
        return null;
    }
    
    //verifie que la liste de cases en paramètre est bien continue
    private boolean verifierContinuite(ArrayList<Case> lesCases,TypeBateau type){
        Iterator<Case> it1 = lesCases.iterator();
        Case firstCase = it1.next();
        it1 = lesCases.iterator();
//        Iterator<Case> it2 = this.casesDuBateau.iterator();        
//        it2.next();        
        boolean xChange = false;
        boolean yChange = false;
        int[] minMaxX = {firstCase.getX(),firstCase.getX()};
        int[] minMaxY = {firstCase.getY(),firstCase.getY()};
        while(it1.hasNext()){
            Case currentCase = it1.next();            
            if (firstCase.getX() != currentCase.getX()) {
                xChange = true;
                if (currentCase.getX() < minMaxX[0]) {
                    minMaxX[0] = currentCase.getX();
                }   
                if (currentCase.getX() > minMaxX[1]) {
                    minMaxX[1] = currentCase.getX();
                }
            }
            if (firstCase.getY() != currentCase.getY()) {
                yChange = true;
                if (currentCase.getY() < minMaxY[0]) {
                    minMaxY[0] = currentCase.getY();
                }
                if (currentCase.getY() > minMaxY[1]) {
                    minMaxY[1] = currentCase.getY();
                }
            }
            if(xChange && yChange) {
                return false;
            }            
//            while(it2.hasNext()){
//                Case nextCase = it2.next();
//                if (currentCase.getX() != nextCase.getX() && currentCase.getY() != nextCase.getY()) {
//                    continuite = false;
//                }
//            }
//            it2 = this.casesDuBateau.iterator();
//            it2.next();
        }
        if (xChange && minMaxX[1] - minMaxX[0] >= type.cases){
            return false;
        }
        if (yChange && minMaxY[1] - minMaxY[0] >= type.cases){
            return false;
        }
        System.out.println("minMaxX=" + minMaxX[0] + ", " + minMaxX[1]);
        System.out.println("minMaxY=" + minMaxY[0] + ", " + minMaxY[1]);
        return true;
    }

    public void setCasesDuBateau(ArrayList<Case> casesDuBateau) {
        this.casesDuBateau = casesDuBateau;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public TypeBateau getType() {
        return type;
    }        

    public int getNbTouche() {
        return nbTouche;
    }

    public void setNbTouche(int nbTouche) {
        this.nbTouche = nbTouche;
    }

    @Override
    public String toString() {
        String returnedString = "Bateau{" + "casesDuBateau=";
        for (Case uneCase : this.casesDuBateau){
            returnedString += uneCase.toString();
        }
        returnedString += ", nbTouche=" + nbTouche + ", etat=" + etat + ", type=" + type.toString() + '}';
        return returnedString;
    }
    
    
    @Override
    public void miseAJour(ObservableAbstrait unObservable){
        this.nbTouche++;
        if (this.nbTouche == this.type.cases){
            this.etat = true;
            this.notifier();
        }
    }        
    
}
