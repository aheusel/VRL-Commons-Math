/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gcsc.vrl.hodgkin_huxley_plugin;

/**
 *
 * @author myra
 */
public class InitialExceedsFinalException extends Exception{
    
    @Override
    public String toString(){
        return "Intial time of current injection cannot exceed final time of current injection";
    }
}
