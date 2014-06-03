/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gcsc.vrl.multi_compartment_model;

import java.util.ArrayList;
import gcsc.vrl.hodgkin_huxley_plugin.*;

/**
 *
 * @author myra
 */
public class Compartment {
    
    /**
     * total number of compartments created 
     */
    private static int totalNumber= 0; 
   
    /**
     * length of the compartment  
     */
    private double length; 
    
    /**
     * radius of the compartment 
     */
    private double radius; 
    
    /**
     * 
     */
    private double r_L; 
    
    /**
     * current injected into the compartment 
     */
    private double ie; 
    
    /**
     * membrane current of the compartment, which is calculated using the Hodgkin Huxley plugin (or in another way!)
     */
    private double im; 
    
    /**
     * membrane voltage of this compartment  
     */
    // each compartment only knows its own voltage, but it can access the voltage from its neighbor
    private double v; 
    
    /**
     * intercompartmental conductances
     */
    private double[] g; 

    /**
     * number to identify the compartment
     */
    private int id; 
    
    /**
     * all compartments that are coupled to instanciated compartment are listed here
     */
    private ArrayList<Compartment> dependencies; 

    /*--------------------------------------------------------------------------------------------------------------------------------------*/
    /** 
     * Constructor 
     */
    public Compartment() {
        totalNumber++;
        id = totalNumber;
        
    }    
    
    public void setID(int id){
        this.id = id; 
    }
    
    public int getID(){
        return id;
    }
    
    /**
     * Compartments coupled to the instanciated compartment are added to a list of compartments in order to establish relationship between coupled compartments.
     * @param c 
     */
    public void link(Compartment c){
        
    }
    
//    /**
//     * 
//     */
//    public void calculateim(ImFunction im){
//        //um den membran strom zu berechnen, brauchen wir die daten aus Hodgkin und Huxley
//   
//    }
    

    
    
}
