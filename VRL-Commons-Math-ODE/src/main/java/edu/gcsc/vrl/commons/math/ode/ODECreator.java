/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gcsc.vrl.commons.math.ode;

import eu.mihosoft.vrl.annotation.ComponentInfo;
import eu.mihosoft.vrl.annotation.OutputInfo;
import eu.mihosoft.vrl.annotation.ParamInfo;
import eu.mihosoft.vrl.math.GroovyFunction1D;
import java.io.Serializable;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;

/**
 *
 * @author Michael Hoffer &lt;info@michaelhoffer.de&gt;
 */
@ComponentInfo(name = "ODE Creator", category = "Commons/Math/ODE", description = "Creates RHS for ODESolver")
public class ODECreator implements Serializable {

    private static final long serialVersionUID = 1L;

    @OutputInfo(name="RHS")
    public FirstOrderDifferentialEquations createXSquareODE() {
        FirstOrderDifferentialEquations result = new XSquareODE();

        return result;
    }

    @OutputInfo(name="RHS")
    public FirstOrderDifferentialEquations createExpressionODE(
            @ParamInfo(name = "f(x,y)") GroovyFunction1D f) {
        FirstOrderDifferentialEquations result = new GroovyFunctionODE(f);

        return result;
    }
}
