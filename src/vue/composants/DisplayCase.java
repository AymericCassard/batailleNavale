/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue.composants;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import modele.Observateur;
import modele.Case;
import modele.ObservableAbstrait;

/**
 *
 * @author acassard
 */
public class DisplayCase implements Observateur {
    
    private ImageIcon img;
    public enum Etat{
        EMPTY,
        MATE,
        MISSED,
        HIT,
        DESTROYED
    }
    public enum backgroundColors{
        //light yellow
        SELECTED(new Color(255, 255, 102, 125)),
        //light green
        POTENTIALSHIP(new Color(152,251,152, 125));
        
        public final Color color;
        
        backgroundColors(Color color){
            this.color = color;
        }
    }
    private Etat etat;
    private Color backgroundColor;
    private Border border;
    private boolean edgeCase;
    
    public DisplayCase(Case uneCase){
        this.backgroundColor = null;
        this.border = BorderFactory.createLineBorder(Color.black);
        this.edgeCase = false;
        determineEtatFromCase(uneCase);
        attributeImgFromEtat();
        uneCase.ajouterObservateur(this);        
    }
    
    public DisplayCase(Etat etat){
        this.etat = etat;
        this.backgroundColor = null;
        this.edgeCase = false;
        attributeImgFromEtat();
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
                this.img = new ImageIcon(this.getClass().getResource("/resources/img/mate.png"));
                break;
            case MISSED:
                this.img = new ImageIcon(this.getClass().getResource("/resources/img/missed.png"));
                break;
            case DESTROYED:
                this.img = new ImageIcon(this.getClass().getResource("/resources/img/destruction.png"));
                break;
            case HIT:
                this.img = new ImageIcon(this.getClass().getResource("/resources/img/explosion.png"));
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
        attributeImgFromEtat();
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }
    
    public Border getBorder() {
        return border;
    }

    public void setBackgroundColor(Color BackgroundColor) {
        this.backgroundColor = BackgroundColor;
    }
    
    public void setInnerBorder(int top, int left, int bottom, int right, Color color){
        MatteBorder matteBorder = BorderFactory.createMatteBorder(top, left, bottom, right, color);
        this.border = BorderFactory.createCompoundBorder(border, matteBorder);        
        if (left > 0){
            this.edgeCase = true;
        }        
    }
    
    public boolean getEdgeCase(){
        return edgeCase;
    }
    
}
