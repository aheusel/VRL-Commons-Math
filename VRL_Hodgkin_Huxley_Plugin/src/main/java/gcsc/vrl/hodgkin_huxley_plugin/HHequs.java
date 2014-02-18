/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gcsc.vrl.hodgkin_huxley_plugin;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;

/**
 *
 * @author myra
 */
public class HHequs implements FirstOrderDifferentialEquations{
    
    
    private VFunction2D vf;
    private final NFunction2D nf = new NFunction2D();
    private final MFunction2D mf = new MFunction2D();
    private final HFunction2D hf = new HFunction2D();

    public HHequs() {
        
    }

    
    public void setVF(VFunction2D vf) {
        this.vf =  vf;
    }
    
    @Override
    public int getDimension() {
        return 4;
    }

    @Override
    public void computeDerivatives(double t, double[] y, double[] yDot) throws MaxCountExceededException, DimensionMismatchException {
//        vf.setN(yDot[1]);
//        vf.setM(yDot[2]);
//        vf.setH(yDot[3]);
        
        double v = y[0];
        double n = y[1];
        double m = y[2];
        double h = y[3];
        
        vf.setN(n);
        vf.setM(m);
        vf.setH(h);
        
        nf.setV(v);
        mf.setV(v);
        hf.setV(v);
        
        yDot[0] = vf.run(t, y[0]);
        yDot[1] = nf.run(t, y[1]);
        yDot[2] = mf.run(t, y[2]);
        yDot[3] = hf.run(t, y[3]);
       
    }
    
}
