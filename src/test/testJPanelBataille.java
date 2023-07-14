/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package test;

import vue.JPanelBataille;
import vue.JFrameMain;

/**
 *
 * @author acassard
 */
public class testJPanelBataille {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrameMain JFrame = new JFrameMain();
        JPanelBataille JPanel = new JPanelBataille();
        JFrame.setjPanelActive(JPanel);
        JFrame.setVisible(true);
        
    }
    
}
