/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele.batailleNavale;

/**
 *
 * @author cantp
 */
public class Case {
 
    private boolean etat; //false = intact / true = touché
    private int x;
    private int y;
    
    public Case(int x, int y) {
        this.x = x;
        this.y = y;
        this.etat = false;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
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
    
    public void printGridState(){
        if (this.etat){
            System.out.print("X");
            return;
        }
        System.out.print("O");
    }

    @Override
    public String toString() {
        return "Case{" + "etat=" + "}";
    }
    
    
    
    
    
}
