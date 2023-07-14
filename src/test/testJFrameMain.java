/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;
import vue.*;

/**
 *
 * @author acassard
 */
public class testJFrameMain {
    
    public static void main(String[] args) {
        JFrameMain jFrame = new JFrameMain();
        JPanelAccueil jPanel = new JPanelAccueil(); 
        jFrame.setjPanelActive(jPanel);
        jFrame.setVisible(true);        
    }
}
