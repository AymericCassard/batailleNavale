/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

/**
 *
 * @author acassard
 */
public enum TypeBateau implements Affichable {
    
        PORTEAVION(5),
        CROISEUR(4),
        SOUSMARIN(3),
        TORPILLEUR(2);
        
        public final int cases;
        
        private TypeBateau(int cases){
            this.cases = cases;
        }
        
        public String getDisplayName() {
            return this.toString() + "(" + String.valueOf(this.cases) + " cases)";
        }
    }
