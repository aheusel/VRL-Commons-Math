/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gcsc.vrl.hodgkin_huxley_plugin;

/**
 *
 * @author myra
 */
public class FinalCurrentException extends Exception{
    
    @Override
    public String toString(){
        return "Final time of current injection may not exceed the final time of the measurement! ";
    }

}
