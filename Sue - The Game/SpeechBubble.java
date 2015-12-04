import greenfoot.*;

/**
 * Speech bubble class pops up on given events and the code below allows the speech bubble to follow
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
    
    
    
    /**
     * followDogCatcher()  - Cast the world to a variable and use it to call the dogCatcherLocation methods created in the
     * MyWorld class. The integers returned from these methods is assigned to a variable that is used when calling the 
     * moveTowards(X, Y) method.
     */
    public void followDogCatcher()
    {
        // Cast the world to a variable.
        MyWorld world = (MyWorld)getWorld();                
        
        // Create variables and assign them to the integers returned from the 2 dogCatcherLocation methods.
        int dogCatcherX = world.dogCatcherLocationX();      
        int dogCatcherY = world.dogCatcherLocationY();
        
        // Send coordinates to the moveTowards method.
        moveTowards(dogCatcherX, dogCatcherY);

    }
    
    
    
    /**
     * moveTowards  - Use 2 input parameters to move the speech bubble to follow the dog catcher. The If test checks the passed-in
     * values against its own position to determine which direction 
     */
    private void moveTowards(int X, int Y)
    {
              
        int bubbleSpeed = 2;
        
        if (X < getX())   // Dog Catcher is to the LEFT
        {              
            setLocation((getX() - bubbleSpeed), getY());
            
        }
        else if (X > getX())    // Dog Catcher is to the RIGHT
        {
            setLocation((getX() + bubbleSpeed), getY());
        }
        
      
    }
}
