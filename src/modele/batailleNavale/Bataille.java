/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele.batailleNavale;

/**
 *
 * @author cantp
 */
public class Bataille {

    private Joueur joueur1;
    private Joueur joueur2;

    public Bataille(Joueur j1, Joueur j2) {

        this.joueur1 = j1;
        this.joueur2 = j2;

    }

//    public String checkTir() {
//        //Check Tirs J1
//        if (this.joueur1.getTirs().size() > this.joueur2.getTirs().size()) {
//            Tirs dernierTir = this.joueur1.getTirs().get(this.joueur1.getTirs().size() - 1);
//            if (this.joueur2.getGrille().getLesCases()[dernierTir.getX()][dernierTir.getY()].isEtat()) {
//                this.joueur1.getTirs().set(this.joueur1.getTirs().size() - 1, new Tirs(dernierTir.getX(), dernierTir.getY(), "Touché"));
//                return "Touché";
//            } else {
//                this.joueur1.getTirs().set(this.joueur1.getTirs().size() - 1, new Tirs(dernierTir.getX(), dernierTir.getY(), "Manqué"));
//                return "Manqué";
//
//            }
//            //Check Tirs J2
//        } else {
//            Tirs dernierTir = this.joueur2.getTirs().get(this.joueur2.getTirs().size() - 1);
//            if (this.joueur1.getGrille().getLesCases()[dernierTir.getX()][dernierTir.getY()].isEtat()) {
//                this.joueur2.getTirs().set(this.joueur2.getTirs().size() - 1, new Tirs(dernierTir.getX(), dernierTir.getY(), "Touché"));
//                return "Touché";
//            } else {
//                this.joueur2.getTirs().set(this.joueur2.getTirs().size() - 1, new Tirs(dernierTir.getX(), dernierTir.getY(), "Manqué"));
//                return "Manqué";
//
//            }
//        }
//    }
    
    //public 

//    public String checkWin() {
//        for () {
//            
//        }
//    }
    
}
