/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gcsc.vrl.commons.math.ode;

import eu.mihosoft.vrl.annotation.ComponentInfo;
import eu.mihosoft.vrl.annotation.ParamInfo;
import eu.mihosoft.vrl.math.Trajectory;
import java.io.Serializable;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.nonstiff.DormandPrince853Integrator;
import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.ode.sampling.StepInterpolator;

/**
 *
 * @author Michael Hoffer &lt;info@michaelhoffer.de&gt;
 */
@ComponentInfo(name = "ODE Solver", category = "Commons/Math/ODE", description = "ODE solver based on Commons-Math FirstOrderIntegrator")
public class ODESolver implements Serializable {

    private static final long serialVersionUID = 1L;

    public ODESolver() {
    }

    public Trajectory solve(
            @ParamInfo(name = "Label", options = "value=\"Label 1\"") String label,
            @ParamInfo(name = "x0", options = "value=0.0D") double t0,
            @ParamInfo(name = "xn", options = "value=3.0D") double tn,
            @ParamInfo(name = "y0", options = "value=0.0") double y0,
            @ParamInfo(name = "Min Step", options = "value=1e-6D") double minStep,
            @ParamInfo(name = "Max Step", options = "value=1e-2D") double maxStep,
            @ParamInfo(name = "Abs.Tol.", options = "value=1e-10") double absTol,
            @ParamInfo(name = "Rel.Tol.", options = "value=1e-10") double relTol,
            @ParamInfo(name = "RHS") FirstOrderDifferentialEquations rhs) {

        FirstOrderIntegrator integrator = new DormandPrince853Integrator(minStep, maxStep, absTol, relTol);

        final Trajectory result = new Trajectory(label);

        StepHandler stepHandler = new StepHandler() {
            @Override
            public void init(double t0, double[] y0, double t) {
                result.add(t, y0[0]);
            }

            @Override
            public void handleStep(StepInterpolator interpolator, boolean isLast) {
                double t = interpolator.getCurrentTime();
                double[] y = interpolator.getInterpolatedState();
                result.add(t, y[0]);
            }
        };

        integrator.addStepHandler(stepHandler);

        double[] y = new double[]{y0}; // initial state
        integrator.integrate(rhs, t0, y, tn, y);

        return result;
    }
}
