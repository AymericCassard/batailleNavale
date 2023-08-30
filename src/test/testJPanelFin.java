/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import vue.JFrameMain;
import vue.JPanelFin;

/**
 *
 * @author acassard
 */
public class testJPanelFin {
    public static void main(String[] args){
        JFrameMain JFrame = new JFrameMain();
        JPanelFin JPanel = new JPanelFin();
        JPanel.generateJpanelFromResult(JPanelFin.Resultat.VICTOIRE);
        JFrame.setjPanelActive(JPanel);
        
        JFrameMain JFrame1 = new JFrameMain();
        JPanelFin JPanel1 = new JPanelFin();
        JPanel1.generateJpanelFromResult(JPanelFin.Resultat.DEFAITE);
        JFrame1.setjPanelActive(JPanel1);
        
        JFrame.setVisible(true);
        JFrame1.setVisible(true);
    }
}
