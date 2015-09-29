import greenfoot.*;

/**
 * Write a description of class Hydrant here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
        if (isTouching(Puddle.class))
        {
            peedOn = true;
        }
    }    
}
