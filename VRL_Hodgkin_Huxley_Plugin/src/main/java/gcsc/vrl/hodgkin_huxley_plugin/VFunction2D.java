/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gcsc.vrl.hodgkin_huxley_plugin;


import eu.mihosoft.vrl.annotation.*;
import eu.mihosoft.vrl.math.Function2D;
import java.io.Serializable;

/**
 *
 * @author myra
 */
@ComponentInfo(name = "VFunction2D", category = "Commons/Math/ODE", description = "")
public class VFunction2D implements Function2D, Serializable{
    
    private static final long serialVersionUID = 1L;
    
    /**
     * conductance of potassium in mS/mm^2
     */
    public double gBarK;
    /**
     * equilibrium potential of potassium in mV
     */
    public double eK;
     /**
     * conductance of sodium in mS/mm^2
     */
    public double gBarNa;
    /**
     * equilibrium potential of sodium in mV
     */
    public double eNa;
     /**
     * leakage conductance in mS/mm^2
     */
    public double gBarL;
    
    /**
     * leakage equilibrium potential of potassium in mV
     */
    public double eL;
    
    /**
     * induced current in uA/mm^2
     */
    public double i;
    
    /**
     * membrane capacitance in uF/mm^2 
     */
    public double cm;
    
    
    /**
     * current time step, which is set in the ODESolver 
     */ 
    private double t;
    
    /**
     * potassium channel activation
     */
    private double n;
    
    /**
     * sodium channel activation
     */
    private double m;
    
    /**
     * sodium channel inactivation
     */
    private double h;

    /**
     * constructor - creates 2D-voltage function
     */
    public VFunction2D() {
    }
    
    /**
     * user interface to edit parameters of the Hodgkin-Huxley-Model
     * @param gBarK maximal conductance of potassium
     * @param eK equilibrium potential of potassium
     * @param gBarNa maximal conductance of sodium
     * @param eNa equilibrium potential of sodium
     * @param gBarL maximal leakage-conductance
     * @param eL equilibrium potential of leakage current
     * @param cm membrane capacitance 
     */
    public void init(
            @ParamInfo(name="gBar_K in mS/mm^2", options="value=0.36D") double gBarK, 
            @ParamInfo(name="E_K in mV", options="value=-77.00D")double eK, 
            @ParamInfo(name="gBar_Na in mS/mm^2", options="value=1.2D") double gBarNa, 
            @ParamInfo(name="E_Na in mV", options="value=50.00D") double eNa, 
            @ParamInfo(name="gBar_L in mS/mm^2", options="value=0.003D") double gBarL, 
            @ParamInfo(name="E_L in mV", options="value=-54.387D") double eL,  
            @ParamInfo(name="Membrane capacity in uF/mm^2", options="value=0.01D") double cm) {
        this.gBarK = gBarK;
        this.eK = eK;
        this.gBarNa = gBarNa;
        this.eNa = eNa;
        this.gBarL = gBarL;
        this.eL = eL;
        this.cm = cm;  
        
    }
    
    /**
     * Set the current time step - this is applied in ODESolver
     * @param t current time step
     */
    @MethodInfo(name="setH", noGUI=true)
    public void setCurrentT(double t){
        this.t = t;
    }
    
    /**
     * User interface to set the onset- and offset-time of the external current
     * @param ti0 Onset time
     * @param tin Offset time
     * @param i
     */
    public void setI(
            @ParamInfo(name="Onset of external current (>0)")double ti0,
            @ParamInfo(name="Offset of external current")double tin,
            @ParamInfo(name="external current")double i){
        
        if(ti0>tin){
            System.err.println("Onset time cannot be bigger than offset time!");//need exception here TODO
        }
        
        if(ti0<=t && t<=tin ){
            this.i=i;
        }else{ 
            this.i = 0;
        }
    }
            
    @MethodInfo(name="setN", noGUI=true)
    public void setN(double n) {
        this.n = n;
    }

    @MethodInfo(name="setM", noGUI=true)
    public void setM(double m) {
        this.m = m;
    }

    @MethodInfo(name="setH", noGUI=true)
    public void setH(double h) {
        this.h = h;
    }
    
    @MethodInfo(name="getN", noGUI=true)
    public double getN() {
        return n;
    }

    @MethodInfo(name="getM", noGUI=true)
    public double getM() {
        return m;
    }

    @MethodInfo(name="getH", noGUI=true)
    public double getH() {
        return h;
    }
    
    
    /**
     * running the function
     * @param x
     * @param y
     * @return 
     */
    @MethodInfo(name="run", noGUI=true)
    @Override
    public Double run(Double x, Double y) {
       
        return (i-(Math.pow(n,4)*gBarK*(y - eK) + Math.pow(m,3)*h*(y - eNa) + gBarL*(y - eL)))/cm;
        
    }
    
      
}
