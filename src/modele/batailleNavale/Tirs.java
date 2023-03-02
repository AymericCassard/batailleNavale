/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele.batailleNavale;

/**
 *
 * @author cantp
 */
public class Tirs {
    
    private int x;
    private int y;
    //Touché ou manqué
    private String etat;
    
    public Tirs(int x, int y) {
        this.x = x;
        this.y = y;
        this.etat = null;
    }
    public Tirs(int x, int y, String etat) {
        this.x = x;
        this.y = y;
        this.etat = etat;
    }
    
    public Tirs() {
        this.x = 1;
        this.y = 1;
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

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Tirs{" + "x=" + x + ", y=" + y + ", etat=" + etat + '}';
    }
    
    
    
}
