/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gcsc.vrl.hodgkin_huxley_plugin;

import eu.mihosoft.vrl.annotation.*;
import eu.mihosoft.vrl.math.Function2D;

/**
 *
 * activation function of potassium channel
 *
 * @author myra
 */
public class NFunction2D implements Function2D{
   
    
    private double v; 

    public double getV() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
    }

    /**
     * Constructor
     */
    public NFunction2D() {
        
    }
    
    
     /**
     * implementation of the steady state (in)activation function in the HH model
     * @return Steady state inactivation function n_infinity
     */
    @MethodInfo(name="n infinity", noGUI=true)
    public double ninf(){
        double alpha_n;
        double beta_n; 
        
        if(v==-55){
            alpha_n = 1/10;   //alpha_n = ?     
       }else{
            alpha_n = 0.01*(v + 55)/(1-(Math.exp(-0.1*(v+55)))); 

        }
        beta_n = 0.125 * Math.exp(-0.0125*(v+65));
        

        
        return alpha_n/(alpha_n + beta_n);
    }
    
    /**
     * Implementation of the voltage-dependent time constant in the HH model 
     * @return voltage dependent time constant tau_n
     */
    @MethodInfo(name="time constant Tau n", noGUI=true)
    public double taun(){
        double alpha_n;
        double beta_n; 
        
//        if(v==-55){
//            alpha_n = 1/10;  //alpha_n = ? 
//       }else{
            alpha_n = 0.01*(v + 55)/(1-(Math.exp(-0.1*(v+55))));

//        }
        beta_n = 0.125 * Math.exp(-0.0125*(v+65));

//        if(v==10){
//            alpha_n = 1/10;   //alpha_n = ?     
//        }else{         
//             alpha_n = (10-v)/(100 * (Math.exp((10-v)/10)-1));
//        }
//        beta_n = 1/8 * Math.exp(-v/80);
//        
        return 1/(alpha_n + beta_n);
    }
    
    /**
     * Running the function
     * @param x
     * @param y
     * @return 
     */
   @Override 
   public Double run(Double x, Double y){
     
      return (ninf()-y)/taun();
       
   }  
   
   
}
