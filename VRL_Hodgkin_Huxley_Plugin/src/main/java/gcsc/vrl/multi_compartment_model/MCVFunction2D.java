/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gcsc.vrl.multi_compartment_model;

import eu.mihosoft.vrl.math.*;

/**
 *
 * @author myra
 */
public class MCVFunction2D implements Function2D{
   
    private int numComp;
    double[] g = new double[numComp];
    
    private double ie; 
    
    private double im; 
    
    
    private int idNeighbor; 

/*------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    /**
     * Constructor
     */
    public MCVFunction2D() {
    }
    
    /**
     * running the function
     * @param x
     * @param y
     * @return 
     */
    @Override
    public Double run(Double x, Double y){

//      UNVOLLSTAENDIG!!!
        Double mcvfct; 
        double tmp2 = 0;
       
         
        mcvfct = -im + ie + tmp2;
        return mcvfct; 
    }
    
    
}
