package gcsc.vrl.hodgkin_huxley_plugin;

/**
 *
 * @author myra
 */
public class TimeDiscrepancyException extends Exception{
    
    /**
     *
     * @return
     */
    @Override
    public String toString(){
        return "Onset time cannot be bigger than offset time!"; 
    }
}
