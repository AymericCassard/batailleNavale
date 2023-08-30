/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vue.JPanelAccueil;

/**
 *
 * @author acassard
 */
public class CtrlAccueil implements ActionListener {
    
    private CtrlPrincipal ctrlP;
    private JPanelAccueil vue;
    
    public CtrlAccueil(CtrlPrincipal ctrlP){
        this.ctrlP = ctrlP;
        this.vue = new JPanelAccueil();
        this.vue.getjButton1Joueur().addActionListener(this);
        this.vue.getjButtonRegles().addActionListener(this);
        this.vue.getjButtonQuitter().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == this.vue.getjButton1Joueur()){
            this.ctrlP.afficherVueBataille();
        }
        if(e.getSource() == this.vue.getjButtonRegles()){
            this.ctrlP.afficherVueRegles();
        }
        if(e.getSource() == this.vue.getjButtonQuitter()){
            this.ctrlP.fermerApp();
        }
        
    }

    public JPanelAccueil getVue() {
        return vue;
    }        
    
}
