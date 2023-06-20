/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele.batailleNavale;

/**
 *
 * @author cantp
 */
public class Case extends ObservableAbstrait implements Affichable {

    private boolean etat; //false = intact / true = touché
    private int x;
    private int y;

    public Case(int x, int y) {
        this.x = x;
        this.y = y;
        this.etat = false;
    }

    public Case(String displayName) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        this.x = Integer.parseInt(displayName.substring(1)) - 1;
        this.y = alphabet.indexOf(displayName.charAt(0));        
        this.etat = false;
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

    public void printGridState() {
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
    public String toString() {
        return "Case{etat=" + this.etat + ", x=" + String.valueOf(this.x) + ", y=" + String.valueOf(this.y) + "}";
    }

}
