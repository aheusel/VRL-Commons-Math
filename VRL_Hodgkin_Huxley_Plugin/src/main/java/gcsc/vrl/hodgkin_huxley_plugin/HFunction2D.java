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
 * @author myra
 */
public class HFunction2D implements Function2D{
    
    private double v;

    public double getV() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
    }

    public HFunction2D() {
        
    }
    
    
    /**
     * implementation of the steady state (in)activation function in the HH model
     * @return Steady state inactivation function h_infinity
     */
    @MethodInfo(name="h infinity", noGUI=true)
    public double hinf(){
        double alpha_h;
        double beta_h;
        
        alpha_h = 0.07 * Math.exp(-0.05*(v+65));
        beta_h = 1/(1+ Math.exp(-0.1*(v+35)));
        
        return alpha_h/(alpha_h + beta_h);
    }
    
     /**
     * Implementation of the voltage-dependent time constant in the HH model 
     * @return voltage dependent time constant tau_h
     */
    @MethodInfo(name="time constant Tau h", noGUI=true)
    public double tauh(){
        double alpha_h;
        double beta_h;
        
        alpha_h = 0.07 * Math.exp(-0.05*(v+65));
     
        beta_h = 1/(1+ Math.exp(-0.1*(v+35)));
        
        return 1/(alpha_h + beta_h);
    }
    
    @Override 
   public Double run(Double x, Double y){
       
       return (hinf()-y)/tauh();
     
   }    
   
   
}
