/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue.composants;

import javax.swing.ImageIcon;
import modele.Observateur;
import modele.Case;
import modele.ObservableAbstrait;

/**
 *
 * @author acassard
 */
public class DisplayCase implements Observateur {
    
    private ImageIcon img;
    private enum Etat{
        EMPTY,
        MATE,
        MISSED,
        HIT,
        DESTROYED
    }
    private Etat etat; 
    
    public DisplayCase(Case uneCase){
        determineEtatFromCase(uneCase);
        attributeImgFromEtat();
        uneCase.ajouterObservateur(this);
    }
    
    private void determineEtatFromCase(Case uneCase){
        //pas de bateau, pas touché EMPTY
        if(!uneCase.isEtat() && uneCase.getBateauProprio() == null){
            this.etat = Etat.EMPTY;
            return;
        }
        //bateau, pas touché MATE
        if(!uneCase.isEtat() && uneCase.getBateauProprio() != null){
            this.etat = Etat.MATE;
            return;
        }
        //pas de bateau, touché MISSED
        if(uneCase.isEtat() && uneCase.getBateauProprio() == null){
            this.etat = Etat.MISSED;
            return;
        }
        //Bateau detruit DESTROYED
        if(uneCase.getBateauProprio().isEtat()){
            this.etat = Etat.DESTROYED;
            return;
        }
        //bateau, touché HIT
        if(uneCase.isEtat() && uneCase.getBateauProprio() != null){
            this.etat = Etat.HIT;            
        }
    }
    
    private void attributeImgFromEtat(){
        switch(this.etat){
            case EMPTY:
                this.img = new ImageIcon();
                break;
            case MATE:
                this.img = new ImageIcon(this.getClass().getResource("./mate.png"));
                break;
            case MISSED:
                this.img = new ImageIcon(this.getClass().getResource("./missed.png"));
                break;
            case DESTROYED:
                this.img = new ImageIcon(this.getClass().getResource("./destruction.png"));
                break;
            case HIT:
                this.img = new ImageIcon(this.getClass().getResource("./explosion.png"));
                break;
        }
    }
    
    @Override
    public void miseAJour(ObservableAbstrait unObservable){
        Case caseObservee = (Case)unObservable;
        determineEtatFromCase(caseObservee);
        attributeImgFromEtat();
    }

    public ImageIcon getImg() {
        return img;
    }

    public void setImg(ImageIcon img) {
        this.img = img;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }
    
    
}
