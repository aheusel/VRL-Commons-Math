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
public class MFunction2D implements Function2D{
    
    private double v;

    public double getV() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
    }

    public MFunction2D() {
    }
    
    
    /**
     * implementation of the steady state (in)activation function in the HH model
     *  
     * @return Steady state inactivation function m_infinity
     */
    @MethodInfo(name="m infinity", noGUI=true)
    public double minf(){
        double alpha_m;
        double beta_m;
        if(v==-40){
            alpha_m = 1; //alpha_m = ?
        }else{
            alpha_m = 0.1*(v+40)/(1-(Math.exp(-0.1*(v+40))));
        }
        
        beta_m = 4*Math.exp(-0.0556*(v+65));
        
        return alpha_m/(alpha_m + beta_m);
    }
    
    
    /**
     * Implementation of the voltage-dependent time constant in the HH model 
     * 
     * @return voltage dependent time constant tau_m
     */
    
    @MethodInfo(name="time constant Tau m", noGUI=true)
    public double taum(){
        double alpha_m;
        double beta_m;
        if(v==-40){
            alpha_m = 1; //alpha_m = ?//fallunterscheidung ist schon noetig;morgen nochmal michael fragen, welche werte man nehmen sollte 
        }else{
            alpha_m = 0.1*(v+40)/(1-(Math.exp(-0.1*(v+40))));
        }
        
        beta_m = 4*Math.exp(-0.0556*(v+65));
        
        return 1/(alpha_m + beta_m);
    }
    
    @Override 
   public Double run(Double x, Double y){
       
     return (minf()-y)/taum();
    
       
   }   
   
 
    
}
