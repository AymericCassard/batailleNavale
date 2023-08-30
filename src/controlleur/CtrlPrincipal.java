/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlleur;
import vue.*;
import modele.*;

/**
 *
 * @author acassard
 */
public class CtrlPrincipal {
    
    private JFrameMain JFrame;
    private CtrlAccueil ctrlAccueil;
    private CtrlBataille ctrlBataille;
    private CtrlRegles ctrlRegles;
    private CtrlFin ctrlFin;

    public JFrameMain getJFrame() {
        return JFrame;
    }

    public void setJFrameMain(JFrameMain JFrame) {
        this.JFrame = JFrame;
    }

    public void setCtrlAccueil(CtrlAccueil ctrlAccueil) {
        this.ctrlAccueil = ctrlAccueil;
    }

    public void setCtrlBataille(CtrlBataille ctrlBataille) {
        this.ctrlBataille = ctrlBataille;
    }
    
    public void setCtrlRegles(CtrlRegles ctrlRegles) {
        this.ctrlRegles = ctrlRegles;
    }
    
    public void setCtrlFin(CtrlFin ctrlFin){
        this.ctrlFin = ctrlFin;
    }
    
    public void afficherVueAccueil(){
        this.JFrame.setjPanelActive(this.ctrlAccueil.getVue());
        this.JFrame.setLocationRelativeTo(null);
    }
    
    public void afficherVueBataille(){
        this.ctrlBataille = new CtrlBataille(this);
        this.JFrame.setjPanelActive(this.ctrlBataille.getVue());
        this.JFrame.setLocationRelativeTo(null);
    }
    
    public void afficherVueRegles(){
        this.JFrame.setjPanelActive(this.ctrlRegles.getVue());
        this.JFrame.setLocationRelativeTo(null);        
    }
    
    public void afficherVueFin(JPanelFin.Resultat resultat){
        this.ctrlFin.getVue().generateJpanelFromResult(resultat);
        this.JFrame.setjPanelActive(this.ctrlFin.getVue());
        this.JFrame.setLocationRelativeTo(null);
    }
    
    public void fermerApp(){
        this.JFrame.dispose();
    }
    
    public void demarrer(){
        afficherVueAccueil();
        this.JFrame.setVisible(true);
    }
}
