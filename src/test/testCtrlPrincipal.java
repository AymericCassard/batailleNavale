/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package test;

import controlleur.CtrlAccueil;
import controlleur.CtrlBataille;
import controlleur.CtrlFin;
import controlleur.CtrlPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import vue.JFrameMain;
import vue.JPanelFin;

/**
 *
 * @author acassard
 */
public class testCtrlPrincipal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CtrlPrincipal ctrlP = new CtrlPrincipal();
        JFrameMain JFrame = new JFrameMain();                
        CtrlAccueil ctrlAccueil = new CtrlAccueil(ctrlP);
        CtrlBataille ctrlBataille = new CtrlBataille(ctrlP);
        CtrlFin ctrlFin = new CtrlFin(ctrlP);
        ctrlP.setJFrameMain(JFrame);
        ctrlP.setCtrlAccueil(ctrlAccueil);
        ctrlP.setCtrlBataille(ctrlBataille);
        ctrlP.setCtrlFin(ctrlFin);
        JButton debug = new JButton("debug");
        debug.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println(ctrlBataille.getBataille().getJoueur1().getLastBateauTouche());
            }
        } );
        ctrlBataille.getVue().getJPanelBotBar().add(debug);
        
        ctrlP.demarrer();        
    }
    
}
