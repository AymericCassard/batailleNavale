/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlleur;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vue.JPanelRegles;

/**
 *
 * @author acassard
 */
public class CtrlRegles implements ActionListener {
    
    private CtrlPrincipal ctrlP;
    private JPanelRegles vue;
    
    public CtrlRegles(CtrlPrincipal ctrlP){
        this.ctrlP = ctrlP;
        this.vue = new JPanelRegles();
        this.vue.getJButtonSuivant().addActionListener(this);
        this.vue.getJButtonPrecedent().addActionListener(this);
        this.vue.getJButtonRetour().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == this.vue.getJButtonSuivant()){
            CardLayout cardContainer = (CardLayout)this.vue.getCardContainer().getLayout();
            cardContainer.next(this.vue.getCardContainer());
        }
        if(e.getSource() == this.vue.getJButtonPrecedent()){
            CardLayout cardContainer = (CardLayout)this.vue.getCardContainer().getLayout();
            cardContainer.previous(this.vue.getCardContainer());
        }
        if(e.getSource() == this.vue.getJButtonRetour()){
            ctrlP.afficherVueAccueil();
        }
    }
    
    public JPanelRegles getVue(){
        return vue;
    }
}
