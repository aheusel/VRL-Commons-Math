/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gcsc.vrl.commons.math.ode;

import eu.mihosoft.vrl.math.GroovyFunction1D;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;

/**
 *
 * @author Michael Hoffer &lt;info@michaelhoffer.de&gt;
 */
public class GroovyFunctionODE implements FirstOrderDifferentialEquations {
    
    private final GroovyFunction1D f;

    GroovyFunctionODE(GroovyFunction1D f) {
        this.f = f;
    }

    @Override
    public int getDimension() {
        return 1;
    }

    @Override
    public void computeDerivatives(double t, double[] y, double[] yDot) throws MaxCountExceededException, DimensionMismatchException {
        yDot[0] = f.run(t);
    }
    
}
