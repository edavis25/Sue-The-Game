import greenfoot.*;

/**
 * Sue is our hero. A fun loving dog who just wants to roam free and eat all the treats she can. 
 * Sue can do typical dog things, namely:
 * 1) Poop on the ground  (Press the "P" key)
 * 2) Pee on a hydrant    (Press "Space" key when touching hydrant)
 * 
 * @author Eric Davis 
 * @version 1.0 - December 2015
 */
public class Sue extends Actor
{
    
    // *** INSTANCE LEVEL DECLARATIONS ***
    public int animationCounter;        //<--Create counter for animation.
    public int actionCooldown;          //<--Create counter for the action cooldown timer.
    public final int MAX_COOLDOWN;      //<--Create constant for the maximum threshold of the cooldown timer.
    public boolean alreadyPeed;
    
   
    
    /**
     * Constructor.
     */
    public Sue()
    {
        actionCooldown = 0;
        MAX_COOLDOWN = 40;
        alreadyPeed = false;
    }
    
    
    
    /**
     * Act - do whatever the Sue wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        checkKeyPress();
        checkTouching();
        cooldown();
    }    
    
    
    
    /**
     * checkKeyPress - According to the key pressed, move the dog in the correct direction or trigger one of the actions.
     * Keys used (in order they appear): (1)"LEFT"  (2)"RIGHT"  (3)"UP"  (4)"DOWN" (5)"P"
     */
    public void checkKeyPress()
    {
        // ----------MOVEMENT---------->
        //"LEFT" DIRECTIONAL KEY
        if (Greenfoot.isKeyDown("left"))
        {
            setLocation(getX()-2, getY());
            walkAnimation();
        }
        //"RIGHT" DIRECTIONAL KEY
        if (Greenfoot.isKeyDown("right"))
        {
            setLocation(getX()+2, getY());
            walkAnimation();
        }
        //"UP" DIRECTIONAL KEY
        if (Greenfoot.isKeyDown("up"))
        {
            setLocation(getX(), getY()-2);
            walkAnimation();
        }
        //"DOWN" DIRECTIONAL KEY
        if (Greenfoot.isKeyDown("down"))
        {
            setLocation(getX(), getY()+2);
            walkAnimation();
        }
        
        
        // ---------ACTIONS---------->
        // "P" KEY  - If user presses "p" to poop. Tests the actionCooldown.
        if (Greenfoot.isKeyDown("p") && (actionCooldown == MAX_COOLDOWN))
        {
            MyWorld myworld = (MyWorld)getWorld();   // <-- Cast the world to a variable to create poop. Ha.
            
            // Creates poop at the dog's current coordinates. Ha.
            Poop poop = new Poop();                     //<-- Assign new Poop into variable
            myworld.addObject(poop, getX(), getY());    //<-- Calls method from World and creates puddle.
            
            actionCooldown = 0;     // Reset the action cooldown to prevent spamming.
        }
       
                
        
   }
    
    
    
    /**
     * walkAnimation  - Creates the innovative animation of the dog. Increments the animationCounter to have Sue rotate a little to the
     *      left and right depending on the value of the counter. There are 3 resting spots: 1) level, 2) left turn, 3) right turn.
     *      The dog simply shifts its angle depending on the value of the counter which will be incremented when a key is pressed.
     */
    private void walkAnimation()
    {
        animationCounter = animationCounter + 1;
        if (animationCounter == 10)
        {
            setRotation(10);
        }
        else if (animationCounter == 20)
        {
            setRotation(0);
        }
        else if (animationCounter == 30)
        {
            setRotation(-10);
        }
        else if (animationCounter == 40)
        {
            setRotation(0);
            animationCounter = 0;
        }
    }
    
    
    
    /**
     * checkTouching - Check if Sue is touching the different actors in the world.
     */
    private void checkTouching()
    {
        // Cast the world to a variable. The variable is used when creating the Pee and Poop. Ha.
        //MyWorld myworld = (MyWorld)getWorld();  ***** NOTE: CHECK IF THIS IS NECESSARY!*******
                                               
       
        // Removes different colored Bones
        if (isTouching(Bone.class))
        {
            removeTouching(Bone.class);
        }
        
        
                
        // If touching hydrant, press "space bar" to pee. Tests to see if the dog has already peed.
        if (isTouching(Hydrant.class) && alreadyPeed == false)
        {
            peeOnHydrant();
        }                      
              
    }
    
    
    
    /**
     *  peeOnHydrant  - Method for peeing on the hydrant. Called from the checkTouching method.
     */
    private void peeOnHydrant()
    {
        if (Greenfoot.isKeyDown("space"))
        {
            alreadyPeed = true;
            
            MyWorld myworld = (MyWorld)getWorld();
             
            // Creates pee at the dog's current coordinates. Ha.
            Puddle puddle = new Puddle();               //<-- Assign new Puddle into variable
            myworld.addObject(puddle, getX(), getY());  //<-- Calls method from World and creates puddle.
            
            //actionCooldown = 0;                         //<-- Reset actionCooldown to 0 to prevent spamming.
             
        }
    }
           
    
    
    /**
     * setAlreadyPeedFalse  - This method is called by the Dog Catcher's "cleanPee" method. The method is used when the dog catcher is finished
     * cleaning the pee and allows the alreadyPeed boolean here to be changed from the Dog Catcher class.
     */
    public void setAlreadyPeedFalse()
    {
         alreadyPeed = false;
    }
    
    
    
    /**
     *  actionCooldown  - Prevents action spamming. Action cooldown begins at its maximum and remains there until an action
     *      is used. When an action is used, the cooldown is set to 0 and will then begin to increase. Whenever an action is attempted,
     *      the cooldown will be tested and action will only be completed if cooldown is at its maximum value.
     */
        private void cooldown()
        {
            if (actionCooldown < MAX_COOLDOWN)
            {
                actionCooldown = actionCooldown + 1;
            }
        }
    
    
}
