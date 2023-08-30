/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lanceur;

import controlleur.CtrlAccueil;
import controlleur.CtrlBataille;
import controlleur.CtrlFin;
import controlleur.CtrlPrincipal;
import controlleur.CtrlRegles;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import vue.JFrameMain;
import vue.JPanelAccueil;
import vue.JPanelBataille;

/**
 *
 * @author acassard
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());            
        } catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e){
            System.out.println(e);
        }                
        CtrlPrincipal ctrlP = new CtrlPrincipal();        
        JFrameMain JFrame = new JFrameMain();                
        CtrlAccueil ctrlAccueil = new CtrlAccueil(ctrlP);
        CtrlBataille ctrlBataille = new CtrlBataille(ctrlP);
        CtrlRegles ctrlRegles = new CtrlRegles(ctrlP);
        CtrlFin ctrlFin = new CtrlFin(ctrlP);
        ctrlP.setJFrameMain(JFrame);
        ctrlP.setCtrlAccueil(ctrlAccueil);
        ctrlP.setCtrlBataille(ctrlBataille);
        ctrlP.setCtrlRegles(ctrlRegles);
        ctrlP.setCtrlFin(ctrlFin);
        ctrlP.demarrer();
    }
    
}
