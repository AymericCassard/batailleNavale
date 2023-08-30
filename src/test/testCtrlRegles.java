/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import controlleur.CtrlPrincipal;
import controlleur.CtrlRegles;
import vue.JFrameMain;

/**
 *
 * @author acassard
 */
public class testCtrlRegles {
    
    public static void main(String[] args){
        CtrlPrincipal ctrlP = new CtrlPrincipal();
        JFrameMain JFrame = new JFrameMain();
        CtrlRegles ctrlRegles = new CtrlRegles(ctrlP);        
        ctrlP.setJFrameMain(JFrame);
        ctrlP.setCtrlRegles(ctrlRegles);
        ctrlP.afficherVueRegles();
        ctrlP.getJFrame().setVisible(true);
    }    
}
