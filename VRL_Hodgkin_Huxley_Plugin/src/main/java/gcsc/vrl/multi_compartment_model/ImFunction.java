/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gcsc.vrl.multi_compartment_model;
//import gcsc.vrl.hodgkin_huxley_plugin.*;
/**
 *
 * @author myra
 */
public class ImFunction {
//    private NFunction2D nf = new NFunction2D(); 
//    private MFunction2D mf = new MFunction2D(); 
//    private HFunction2D hf = new HFunction2D(); 
    double n; 
    double m;
    double h; 
    private double v; 


    public ImFunction() {
    }

    public double getN() {
        return n;
    }

    public double getM() {
        return m;
    }

    public double getH() {
        return h;
    }
    
    public double getV() {
        return v;
    }

    public void setN(double n) {
        this.n = n;
    }

    public void setM(double m) {
        this.m = m;
    }

    public void setH(double h) {
        this.h = h;
    }

    
    public void setV(double v) {
        this.v = v;
    }
    
    public double calculateIm(double gBarNa, double eNa, double gBarK, double eK, double gBarL, double eL){

        double im = Math.pow(n,4) * gBarK * (v - eK) + Math.pow(m,3) *h* gBarNa* (v - eNa) + gBarL* (v - eL);
        return im;
    }
    
}
