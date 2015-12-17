import greenfoot.*;

/**
 * Doghouse - Place for Sue to live and horde her bones. Protect it from those evil squirrels!
 * 
 * @author Eric Davis
 * @version Version 1.0
 */
public class Doghouse extends Actor
{
    // Timer creates a delay when depositing the bones.
    private int timer;
    
    /**
     * Constructor
     */
    public Doghouse()
    {
        timer = 0;
    }
    
    
    /**
     * Act - If Sue has bones in her pocket, is touching the doghouse, and holding down "space", she will deposit the bones
     * from the pocket score to the total score.
     */
    public void act() 
    {
        
        MyWorld world = (MyWorld)getWorld();
        
                    
        if( isTouching(Sue.class) && Greenfoot.isKeyDown("space") && world.pocketLabel.pocketScore > 0 )
        {
            timer = timer + 1;
            
            if (timer == 50)
            {
                world.pocketLabel.adjustPocketScore(-1);
                world.scoreLabel.addToScore(1);
                timer = 0;
            }
            
        }
       
    }    
}
