/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vue.JPanelFin;

/**
 *
 * @author acassard
 */
public class CtrlFin implements ActionListener {
    
    private CtrlPrincipal ctrlP;
    private JPanelFin vue;
    
    public CtrlFin(CtrlPrincipal ctrlP){
        this.ctrlP = ctrlP;
        this.vue = new JPanelFin();
        this.vue.getJButtonRetourAccueil().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == this.vue.getJButtonRetourAccueil()){
            this.ctrlP.afficherVueAccueil();
        }
    }
    
    public JPanelFin getVue(){
        return vue;
    }
    
}
