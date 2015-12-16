import greenfoot.*;

/**
 * Doghouse
 * 
 * @author Eric Davis
 * @version Version 1.0
 */
public class Doghouse extends Actor
{
    private int timer;
    
    /**
     * Constructor
     */
    public Doghouse()
    {
        timer = 0;
    }
    
    
    /**
     * Act - do whatever the Doghouse wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Gets various references and sets them into variables to be able to call their procedures.
        MyWorld world = (MyWorld)getWorld();
        
        // REVIEW FOR DELETION. Made variables public in the world and can use them that way.
        
        //PocketLabel pocketLabel = world.getPocketLabelReference();
        //ScoreLabel scoreLabel = world.getScoreLabelReference();
        
        
        if( isTouching(Sue.class) && Greenfoot.isKeyDown("space") && world.pocketLabel.getPocketScore() > 0 )
        {
            timer = timer + 1;
            
            if (timer == 50)
            {
                world.pocketLabel.adjustPocketScore(-1);
                world.scoreLabel.addToScore();
                timer = 0;
            }
            //pocketLabel.removeFromPocket();
            //scoreLabel.addToScore();
        }
       
    }    
}
