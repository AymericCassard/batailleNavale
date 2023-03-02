package modele.batailleNavale;

import java.util.ArrayList;

/**
 *
 * @author cantp
 */
public class Bateau {
           
    private ArrayList<Case> casesDuBateau;
    private boolean etat; //false = bateau intact / true = coulé
    
    
    public Bateau(ArrayList<Case> lesCases) {
        this.casesDuBateau = lesCases;
    }

    public ArrayList<Case> getCasesDuBateau() {
        return casesDuBateau;
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
    
    
    
}
