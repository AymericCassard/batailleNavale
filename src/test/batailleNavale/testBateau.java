/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test.batailleNavale;
import modele.batailleNavale.Bateau;
import modele.batailleNavale.Case;
import java.util.ArrayList;
import modele.batailleNavale.CreationBateauException;
import modele.batailleNavale.TypeBateau;

/**
 *
 * @author acassard
 */
public class testBateau {
    
    public static void main(String[] args){
        
        ArrayList<Case> lesCases = new ArrayList<>();
        ArrayList<Case> lesCasesCasEtrange = new ArrayList<>();
        Case c1 = new Case(0,1);
        Case c2 = new Case(0,2);
        Case c3 = new Case(0,3);
        Case c4 = new Case(0, 4);
        lesCases.add(c1);
        lesCases.add(c2);
        lesCases.add(c3);
        lesCasesCasEtrange.add(c2);        
        lesCasesCasEtrange.add(c4);
        try{
        Bateau bateauTest = new Bateau(lesCases,TypeBateau.SOUSMARIN);
        Bateau echec = new Bateau(lesCasesCasEtrange, TypeBateau.SOUSMARIN);        
        bateauTest.getCaseByCoord(0, 1).setEtat(true);
        bateauTest.getCaseByCoord(0, 2).setEtat(true);
        bateauTest.getCaseByCoord(0, 3).setEtat(true);
        System.out.println("Nombre de cases touchées: " +  String.valueOf(bateauTest.getNbTouche()));
        System.out.println("Etat du Bateau : " + String.valueOf(bateauTest.isEtat()));
        System.out.print(echec.toString());
        } catch(CreationBateauException e) {
            System.out.println(e);
        }
    }
}
