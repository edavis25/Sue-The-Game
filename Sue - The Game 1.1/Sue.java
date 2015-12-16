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
    public boolean alreadyPeed;
    private boolean ableToPee;
    
    private int imageCounter = 0;
   
     //Delete if using max amount for allowing poop.
    public final int MAX_COOLDOWN;      //<--Create constant for the maximum threshold of the cooldown timer.
    public int actionCooldown;          //<--Create counter for the action cooldown timer.
    
    /**
     * Constructor.
     */
    public Sue()
    {
        actionCooldown = 0;     //<--Delete if using max amount for allowing poop.
        MAX_COOLDOWN = 40;      //<--Delete if using max amount for allowing poop.
        
        alreadyPeed = false;
        ableToPee = false;
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
        checkPocket();
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
        // "P" KEY  - If user presses "p" to poop.
        // Check for key press. If key is pressed, compare the current pocket score with max amount.
        // If the pocket is full then poop and remove 5 from the pocket. Reset action counter.                        
        if (Greenfoot.isKeyDown("p") )
        {
            MyWorld myworld = (MyWorld)getWorld();   // <-- Cast the world to a variable to create poop. Ha.
            
            if (myworld.pocketLabel.getPocketScore() == myworld.pocketLabel.getMaxAmount())
            {
                // Creates poop at the dog's current coordinates. Ha
                Poop poop = new Poop();                     //<-- Assign new Poop into variable
                myworld.addObject(poop, getX(), getY());    //<-- Calls method from World and creates poo.
            
                // Remove 5 from pocket score after pooping.
                myworld.pocketLabel.adjustPocketScore(-5);
            
                actionCooldown = 0;     // Reset the action cooldown to prevent spamming.
            }
                                   
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
                                                     
       
        // Touching removes different colored Bones
        if (isTouching(Bone.class))
        {
            // Cast world into a variable.
            MyWorld world = (MyWorld)getWorld();
            
            if (isTouching(YellowBone.class))
                {
                    PeeIcon peeIcon = new PeeIcon();
                    
                    ableToPee = true;
                    removeTouching(Bone.class);
                    
                    // Create icon
                    world.addObject(peeIcon, 120, 110);
                 }
            else if (world.pocketLabel.getPocketScore() < world.pocketLabel.getMaxAmount())
                {
                    world.pocketLabel.adjustPocketScore(1);
                    removeTouching(Bone.class);
                }
                
        }
                             
        
        // Touching Dog Catcher to End Game.
        if (isTouching(DogCatcher.class))
        {
            catchDog();
        }
        
               
        // If touching hydrant, press "space bar" to pee. Tests to see if the dog has already peed.
        if (isTouching(Hydrant.class) && alreadyPeed == false && ableToPee == true )
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
            
            ableToPee = false;
            
            //Remove the Pee Icon
            myworld.removeObjects(myworld.getObjects(PeeIcon.class));
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
     * returnAlreadyPeed()  - Public method to return if the dog has alreadyPeed
     */
    public boolean returnAlreadyPeed()
    {
        return alreadyPeed;
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
    
    
    
    /**
     * checkPocket  - Checks the amount in the pocket and flashes brown if full.
     */
    private void checkPocket()
    {
        MyWorld world = (MyWorld)getWorld();
               
        GreenfootImage white = new GreenfootImage("sue xtrasmall.png");
        
        if (world.pocketLabel.pocketScore == world.pocketLabel.MAX_AMOUNT)
        {
            imageCounter = imageCounter + 1;
            
            if (imageCounter == 200)
            {
                GreenfootImage brown = new GreenfootImage("sue xtrasmallBrown.png");
                setImage(brown);
            }
            else if (imageCounter == 250)
            {
                setImage(white);
                imageCounter = 0;
            }
                        
        }
        else
        {
            setImage(white);
        }
    }
    
    
    
    /**
     * catchDog   - The following pot of spaghetti code is my humble attempt at creating a workaround for allowing the dog catcher to
     *      catch the dog only if they are really touching/overlapping. Since the imported images are rectangles, they have transparent
     *      borders that would trigger the isTouching() method leading to the dog being caught when the images' borders touched, which often
     *      left a gap where the actors didn't appear to be actually touching in runtime (their outtermost, unseen, rectangular borders were touching).
     *      
     *      My solution was to use the getOneObjectAtOffset method to try and get the actors to overlap each other before trigger the dog being
     *      caught. The getOneObjectAtOffset method uses a set of coordinates to determine where the objects will actually be considered touching relative
     *      to where the actor currently is. By offsetting the coordinates a little bit, I could alter the positioning to only be triggered depending
     *      on which direction the dog catcher was moving relative to the dog. 
     *      
     *      The outer IF tests to see if the Dog Catcher is above, below, or level with the Dog.
     *      After determining the Y position as smaller(DogCatcher=Above), larger(Below), or equal(Level), the nested IF would then check the
     *      relative X values the same way to determine if the dog is left, right, or level (on X axis). The final IF then uses the getOneObjectAtOffset
     *      to return an actor if it is interecting at the offset. The path to the final IF test will determine which direction the Dog Catcher is relative
     *      to the Dog, so the offset values are changed to offset depending on which direction the dog catcher is coming from. Example: If Dog Catcher is directly left,
     *      the offset X value would need to be positive to add a few pixels so that he is only considered touching the Dog when he intersects with the new coordinates 
     *      which are slightly to the right of the outermost left border of the dog (allowing them to overlap a little). The hardcoded numbers change the offset coordinates
     *      in pixels and have been trial and errored to find the best relative positioning to actually trigger the dog getting caught.
     *      
     *      This is a mouthful and writing the description confused even me. I'm almost positive there is a more elegant solution, but being brand new to
     *      coding, this is the most workable solution I could come up with...please feel free to help clean this up with some expertise!
     */
    private void catchDog()
    {
        // Cast the world into a variable ---->
        MyWorld world = (MyWorld)getWorld();
        
        
        // Catcher is Above the Dog = Catcher(Y) < Dog(Y)
        if (getY() > world.dogCatcher.getY() )
        {
            // Catcher is Left of the Dog = Catcher(X) < Dog(X)
            if (getX() > world.dogCatcher.getX() )
            {
                // GetOneObjectAtOffset returns and object so needs to compare this returned object's class type with the actual dogCatcher.
                if (getOneObjectAtOffset(+25, +25, DogCatcher.class) == world.dogCatcher )
                {
                    Greenfoot.stop();
                }
            }
            // Catcher is Right of the Dog = Catcher(X) > Dog(X) 
            else if (getX() < world.dogCatcher.getX() )
            {
                if (getOneObjectAtOffset(-25, +25, DogCatcher.class) == world.dogCatcher )
                {
                    Greenfoot.stop();
                }
                }
            // Catcher is directly above the Dog. = Catcher(X) = Dog(X) ... Catcher(Y) < Dog(Y)
            else
                if (getOneObjectAtOffset(0, +25, DogCatcher.class) == world.dogCatcher )
                {
                    Greenfoot.stop();
                }
        }
            
        
        // Catcher is Below the Dog = Catcher(Y) > Dog(Y)
        else if (getY() < world.dogCatcher.getY() )
        {
            //Catcher is Left of the Dog = Catcher(X) < Dog(X)
            if ( getX() > world.dogCatcher.getX() )
            {
                if (getOneObjectAtOffset(+25, -25, DogCatcher.class) == world.dogCatcher )
                {
                    Greenfoot.stop();
                }
            }
            //Catcher is Right of the Dog = Catcher(X) > Dog(X)
            else if (getX() < world.dogCatcher.getX() )
            {
                if (getOneObjectAtOffset(-25, -25, DogCatcher.class) == world.dogCatcher )
                {
                    Greenfoot.stop();
                }
            }
            //Catcher is directly below the Dog. = Catcher(X) = Dog(X) ... Catcher(Y) < Dog(Y)
            else
                if (getOneObjectAtOffset(0, -30, DogCatcher.class) == world.dogCatcher )
                {
                    Greenfoot.stop();
                }
        }
        
        
         //Catcher is Level With the Dog = Catcher(Y) = Dog(Y)
        else
        {
           // Catcher is Left of Dog = Catcher(X) < Dog(X)
           if (getX() > world.dogCatcher.getX() )
           {
               if (getOneObjectAtOffset(+25, 0, DogCatcher.class) == world.dogCatcher )
               {
                   Greenfoot.stop();
               }
                    
           }
           //Catcher is Right of Dog = Catcher(X) > Dog(X)
           else if (getX() < world.dogCatcher.getX() )
           {
               if (getOneObjectAtOffset(-25, 0, DogCatcher.class) == world.dogCatcher )
               {
                   Greenfoot.stop();
                }
           }
        }
            
    }
}
