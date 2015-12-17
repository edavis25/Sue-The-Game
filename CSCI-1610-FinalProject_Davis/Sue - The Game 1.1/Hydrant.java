import greenfoot.*;

/**
 * Hydrant  - Seems like a good place to pee...
 * 
 * @author Eric Davis
 * @version Version 1.0
 */
public class Hydrant extends Actor
{
    private boolean peedOn;
        
    /**
     * Constructor
     */
    public Hydrant()
    {
        peedOn = false;
    }
       
    
    
    /**
     * Act - do whatever the Hydrant wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
    }
    
    
    
    /**
     * Method to return PeedOn boolean to other classes. Called by the dog catcher.
     */
    public boolean checkPeedOn()
    {
        if (isTouching(Puddle.class))
        {
            peedOn = true;
            return peedOn;
        }
        else
        {
            peedOn = false;
            return peedOn;
        }
    }
    
}
