/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gcsc.vrl.hodgkin_huxley_plugin;

import eu.mihosoft.vrl.annotation.ComponentInfo;
import eu.mihosoft.vrl.annotation.ParamInfo;
import eu.mihosoft.vrl.math.Trajectory;
import java.io.Serializable;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator;
//import org.apache.commons.math3.ode.nonstiff.DormandPrince853Integrator;
//import org.apache.commons.math3.ode.nonstiff.EulerIntegrator;
import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.ode.sampling.StepInterpolator;

/**
 *
 * @author myra
 */
@ComponentInfo(name = "ODE Solver", category = "Commons/Math/ODE", description = "ODE solver based on Commons-Math FirstOrderIntegrator")
public class ODESolver implements Serializable{
    
    private static final long serialVersionUID = 1L;

    public ODESolver() {
    }
    
    //initial state muss noch definiert werden
    public Trajectory solve(
            @ParamInfo(name = "Label") String label,
            @ParamInfo(name = "t0", options = "value=0.0D") double t0,
            @ParamInfo(name = "tn", options = "value=2.0D") double tn,
            @ParamInfo(name = "v0", options = "value=-70.0") double y0,
            @ParamInfo(name = "Min Step", options = "value=1e-6D") double minStep,
            @ParamInfo(name = "Max Step", options = "value=1e-2D") double maxStep,
            @ParamInfo(name = "Abs.Tol.", options = "value=1e-10") double absTol,
            @ParamInfo(name = "Rel.Tol.", options = "value=1e-10") double relTol,
            @ParamInfo(name = "RHS") FirstOrderDifferentialEquations rhs) {

        FirstOrderIntegrator integrator = new DormandPrince54Integrator(minStep, maxStep, absTol, relTol); 
        
//       FirstOrderIntegrator integrator = new EulerIntegrator(0.01);
        
        // Trajectory[] result = new Trajectory[4];
        
        //for(int i = 0; i < result.length; i++) {
        //    result[i] = new Trajectory(label + "V,M,N,H");
        //}

        final Trajectory result = new Trajectory(label);

        StepHandler stepHandler = new StepHandler() {
            @Override
            public void init(double t0, double[] y0, double t) {
 //               result.add(t, y0[0]); ist dieser wert korrekt?
//                  System.out.println("step1: " + t);
            }

            @Override
            public void handleStep(StepInterpolator interpolator, boolean isLast) {
                double t = interpolator.getCurrentTime();
                double[] y = interpolator.getInterpolatedState();
                result.add(t, y[0]); 
//               System.out.println("step2: " + t);
            }
        };

        integrator.addStepHandler(stepHandler);
        
        VFunction2D v0 = new VFunction2D();
        NFunction2D n0 = new NFunction2D();
        MFunction2D m0 = new MFunction2D();
        HFunction2D h0 = new HFunction2D(); 
        
        double[] y = new double[]{y0, n0.ninf(y0), m0.minf(y0), h0.hinf(y0) }; // initial state
        
        //Fehlercheck
//        System.out.println("check v1: "+y[0]);
//        System.out.println("check n1: "+y[1]);
//        System.out.println("check m1: "+y[2]);
//        System.out.println("check h1: "+y[3]);
        
        n0.setV(y[0]);
        m0.setV(y[0]);
        h0.setV(y[0]);
        v0.setN(y[1]);
        v0.setM(y[2]);
        v0.setH(y[3]);//wo genau kommt das hin?
        
        System.out.println("check v2: "+y[0]);
        System.out.println("check n2: "+y[1]);
        System.out.println("check m2: "+y[2]);
        System.out.println("check h2: "+y[3]);
        
        integrator.integrate(rhs, t0, y, tn, y);
        
        System.out.println("check v3: "+y[0]);
        System.out.println("check n3: "+y[1]);
        System.out.println("check m3: "+y[2]);
        System.out.println("check h3: "+y[3]);
 
//        for(int i=0; i<y.length; i++){
//                Math.pow(n,4)*gBarK*(y - eK) + Math.pow(m,3)*h*(y - eNa)
//                System.out.println(""+y[i]);
//                
//            }
        
        return result;
    }
    
    public Trajectory solveN(
            @ParamInfo(name = "Label") String label,
            @ParamInfo(name = "t0", options = "value=0.0D") double t0,
            @ParamInfo(name = "tn", options = "value=200.0D") double tn,
            @ParamInfo(name = "v0", options = "value=-70.0") double y0,
            @ParamInfo(name = "Min Step", options = "value=1e-6D") double minStep,
            @ParamInfo(name = "Max Step", options = "value=1e-2D") double maxStep,
            @ParamInfo(name = "Abs.Tol.", options = "value=1e-10") double absTol,
            @ParamInfo(name = "Rel.Tol.", options = "value=1e-10") double relTol,
            @ParamInfo(name = "RHS") FirstOrderDifferentialEquations rhs) {

        FirstOrderIntegrator integrator = new DormandPrince54Integrator(minStep, maxStep, absTol, relTol); 
        
//       FirstOrderIntegrator integrator = new EulerIntegrator(0.01);

        final Trajectory result = new Trajectory(label);

        StepHandler stepHandler = new StepHandler() {
            @Override
            public void init(double t0, double[] y0, double t) {
                result.add(t, y0[1]);
                  System.out.println("step1: " + t);
            }

            @Override
            public void handleStep(StepInterpolator interpolator, boolean isLast) {
                double t = interpolator.getCurrentTime();
                double[] y = interpolator.getInterpolatedState();
                result.add(t, y[1]); 
               System.out.println("step2: " + t);
            }
        };

        integrator.addStepHandler(stepHandler);
        
        VFunction2D v0 = new VFunction2D();
        NFunction2D n0 = new NFunction2D();
        MFunction2D m0 = new MFunction2D();
        HFunction2D h0 = new HFunction2D(); 
        
        double[] y = new double[]{y0, n0.ninf(y0), m0.minf(y0), h0.hinf(y0) }; // initial state
        
        n0.setV(y[0]);
        m0.setV(y[0]);
        h0.setV(y[0]);
        v0.setN(y[1]);
        v0.setM(y[2]);
        v0.setH(y[3]);//wo genau kommt das hin?
        
        integrator.integrate(rhs, t0, y, tn, y);

        return result;
    }
    
    public Trajectory solveM(
            @ParamInfo(name = "Label") String label,
            @ParamInfo(name = "t0", options = "value=0.0D") double t0,
            @ParamInfo(name = "tn", options = "value=200.0D") double tn,
            @ParamInfo(name = "v0", options = "value=-70.0") double y0,
            @ParamInfo(name = "Min Step", options = "value=1e-6D") double minStep,
            @ParamInfo(name = "Max Step", options = "value=1e-2D") double maxStep,
            @ParamInfo(name = "Abs.Tol.", options = "value=1e-10") double absTol,
            @ParamInfo(name = "Rel.Tol.", options = "value=1e-10") double relTol,
            @ParamInfo(name = "RHS") FirstOrderDifferentialEquations rhs) {

        FirstOrderIntegrator integrator = new DormandPrince54Integrator(minStep, maxStep, absTol, relTol); 
        
//       FirstOrderIntegrator integrator = new EulerIntegrator(0.01);

        final Trajectory result = new Trajectory(label);

        StepHandler stepHandler = new StepHandler() {
            @Override
            public void init(double t0, double[] y0, double t) {
                result.add(t, y0[2]);
                  System.out.println("step1: " + t);
            }

            @Override
            public void handleStep(StepInterpolator interpolator, boolean isLast) {
                double t = interpolator.getCurrentTime();
                double[] y = interpolator.getInterpolatedState();
                result.add(t, y[2]); 
               System.out.println("step2: " + t);
            }
        };

        integrator.addStepHandler(stepHandler);
        
        VFunction2D v0 = new VFunction2D();
        NFunction2D n0 = new NFunction2D();
        MFunction2D m0 = new MFunction2D();
        HFunction2D h0 = new HFunction2D(); 
        
        double[] y = new double[]{y0, n0.ninf(y0), m0.minf(y0), h0.hinf(y0) }; // initial state
        
         n0.setV(y[0]);
        m0.setV(y[0]);
        h0.setV(y[0]);
        v0.setN(y[1]);
        v0.setM(y[2]);
        v0.setH(y[3]);//wo genau kommt das hin?
        
        integrator.integrate(rhs, t0, y, tn, y);

        return result;
    }
    
    public Trajectory solveH(
            @ParamInfo(name = "Label") String label,
            @ParamInfo(name = "t0", options = "value=0.0D") double t0,
            @ParamInfo(name = "tn", options = "value=200.0D") double tn,
            @ParamInfo(name = "v0", options = "value=-70.0") double y0,
            @ParamInfo(name = "Min Step", options = "value=1e-6D") double minStep,
            @ParamInfo(name = "Max Step", options = "value=1e-2D") double maxStep,
            @ParamInfo(name = "Abs.Tol.", options = "value=1e-10") double absTol,
            @ParamInfo(name = "Rel.Tol.", options = "value=1e-10") double relTol,
            @ParamInfo(name = "RHS") FirstOrderDifferentialEquations rhs) {

        FirstOrderIntegrator integrator = new DormandPrince54Integrator(minStep, maxStep, absTol, relTol); 
        
//       FirstOrderIntegrator integrator = new EulerIntegrator(0.01);

        final Trajectory result = new Trajectory(label);

        StepHandler stepHandler = new StepHandler() {
            @Override
            public void init(double t0, double[] y0, double t) {
                result.add(t, y0[3]);
                  System.out.println("step1: " + t);
            }

            @Override
            public void handleStep(StepInterpolator interpolator, boolean isLast) {
                double t = interpolator.getCurrentTime();
                double[] y = interpolator.getInterpolatedState();
                result.add(t, y[3]); 
               System.out.println("step2: " + t);
            }
        };

        integrator.addStepHandler(stepHandler);
        
        VFunction2D v0 = new VFunction2D();
        NFunction2D n0 = new NFunction2D();
        MFunction2D m0 = new MFunction2D();
        HFunction2D h0 = new HFunction2D(); 
        
        double[] y = new double[]{y0, n0.ninf(y0), m0.minf(y0), h0.hinf(y0) }; // initial state
        
         n0.setV(y[0]);
        m0.setV(y[0]);
        h0.setV(y[0]);
        v0.setN(y[1]);
        v0.setM(y[2]);
        v0.setH(y[3]);//wo genau kommt das hin?
        
        integrator.integrate(rhs, t0, y, tn, y);

        return result;
    }
}
