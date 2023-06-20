/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test.batailleNavale;
import modele.batailleNavale.Case;

/**
 *
 * @author acassard
 */
public class testCase {
    
    public static void main(String[] args){
        String validDisplayName = "A6";
        String invalidDisplayName = "1000";
        String invalidDisplayName1 = "Y1";
        String invalidDisplayName2 = "AAA";
        System.out.println("validDisplay est il valide ? " + Case.isDisplayNameValid(validDisplayName));
        System.out.println("invalidDisplayName est il valide ? " + Case.isDisplayNameValid(invalidDisplayName));
        System.out.println("invalidDisplayName1 est il valide ? " + Case.isDisplayNameValid(invalidDisplayName1));
        System.out.println("invalidDisplayName2 est il valide ? " + Case.isDisplayNameValid(invalidDisplayName2));
    }
}
