import greenfoot.*;

/**
 * Speech bubble class pops up on given events and follows the code below allows the speech bubble to follow
 * the dog catcher rather than sit static in one spot.
 */
public class SpeechBubble extends Actor
{
    /**
     * Act - Call the followDogCatcher method so the speech bubble follows the path of the dog 
     * catcher. The speech bubble will only move left or right on the X axis.
     */
    public void act() 
    {
        followDogCatcher();
    }    
    
    public void followDogCatcher()
    {
        MyWorld world = (MyWorld)getWorld();                //<--- Cast the world so I can call
                                                            // the dogCatcherLocationX/Y methods        
        int dogCatcherX = world.dogCatcherLocationX();      // <--(here)
        int dogCatcherY = world.dogCatcherLocationY();
        
        moveTowards(dogCatcherX, dogCatcherY);

    }
    
    public void moveTowards(int X, int Y)
    {
              
        int speed = 2;
        
        if (X < getX())   // Dog Catcher is to the LEFT
        {              
            setLocation((getX() - speed), getY());
        }
        else if (X > getX())    // Dog Catcher is to the RIGHT
        {
            setLocation((getX() + speed), getY());
        }
        
    }
}
