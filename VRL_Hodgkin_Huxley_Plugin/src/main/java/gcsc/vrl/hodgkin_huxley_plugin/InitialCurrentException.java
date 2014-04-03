/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gcsc.vrl.hodgkin_huxley_plugin;

/**
 *
 * @author myra
 */
public class InitialCurrentException extends Exception{
    
    @Override
    public String toString(){
        return "The initial time of current injection cannot be smaller than the initial time of the measurement!";
    }
}
