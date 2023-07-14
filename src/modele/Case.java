/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

/**
 *
 * @author cantp
 */
public class Case extends ObservableAbstrait implements Affichable {

    private boolean etat; //false = intact / true = touché
    private int x;
    private int y;
    private Bateau bateauProprio;

    public Case(int x, int y) {
        this.x = x;
        this.y = y;
        this.etat = false;
        this.bateauProprio = null;
    }

    public Case(String displayName) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        this.x = Integer.parseInt(displayName.substring(1)) - 1;
        this.y = alphabet.indexOf(displayName.charAt(0));        
        this.etat = false;
        this.bateauProprio = null;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
        this.notifier();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public Bateau getBateauProprio(){
        return this.bateauProprio;
    }
    
    public void setBateauProprio(Bateau unBateau){
        this.bateauProprio = unBateau;
    }

    public void printGridState() {
        if(this.bateauProprio != null) {
            if (this.etat){
                System.out.print("# ");
                return; 
            }
            System.out.print("@ ");
            return;
        }
        if (this.etat) {
            System.out.print("X ");
            return;
        }
        System.out.print("O ");
    }

    public void printBateauState() {
        if (this.etat) {
            System.out.print("# ");
            return;
        }
        System.out.print("@ ");
    }

    public String getDisplayName() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String DisplayName = alphabet.charAt(y) + String.valueOf(x + 1);
        return DisplayName;
    }

    //vérifie qu"un string est un formatage possible pour un displayName
    public static boolean isDisplayNameValid(String displayName) {
        if (displayName.length() < 2 || displayName.length() > 3) {
            return false;
        }
        String xAxis = "abcdefghij";
        String x = displayName.charAt(0) + "";
        if (!xAxis.contains(x.toLowerCase())) {
            return false;
        }
        try {
            int y = Integer.parseInt(displayName.substring(1));
            if (y <= 0 || y > 10) {
                return false;
            }            
        } catch (NumberFormatException e) {
            return false; //la fin du display name n'est pas un int
        }
        return true;
    }
    
    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if ((obj == null) || (obj.getClass() != this.getClass())){
            return false;
        }
        Case laCase = (Case)obj;
        return ((this.etat == laCase.etat) && (this.x == laCase.x) && (this.y == laCase.y));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.etat ? 1 : 0);
        hash = 97 * hash + this.x;
        hash = 97 * hash + this.y;
        return hash;
    }        

    @Override
    public String toString() {
        return "Case{etat=" + this.etat + ", x=" + String.valueOf(this.x) + ", y=" + String.valueOf(this.y) + "}";
    }

}
