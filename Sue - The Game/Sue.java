import greenfoot.*;

/**
 * Write a description of class Sue here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sue extends Actor
{
    public int animationCounter;
    public int actionCooldown;
    public int MAX_COOLDOWN;
    
    
    /**
     * Constructor
     */
    public Sue()
    {
        actionCooldown = 0;
        MAX_COOLDOWN = 40;
       
            
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
    
    
    public void checkKeyPress()
    {
        //LEFT
        if (Greenfoot.isKeyDown("left"))
        {
            setLocation(getX()-2, getY());
            walkAnimation();
        }
        if (Greenfoot.isKeyDown("right"))
        {
            setLocation(getX()+2, getY());
            walkAnimation();
        }
        if (Greenfoot.isKeyDown("up"))
        {
            setLocation(getX(), getY()-2);
            walkAnimation();
        }
        if (Greenfoot.isKeyDown("down"))
        {
            setLocation(getX(), getY()+2);
            walkAnimation();
        }
    }
    
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
     * Check if Sue is touching the different Actors in the World.
     */
    private void checkTouching()
    {
        MyWorld myworld = (MyWorld)getWorld(); //<-- Casting? Needed to call the "addObject" method which is 
                                               // used to create Pee and Poop. Ha.
       
        // Removes different colored Bones
        if (isTouching(Bone.class))
        {
            removeTouching(Bone.class);
        }
        else if (isTouching(PurpleBone.class))
        {
            removeTouching(PurpleBone.class);
        }
        else if (isTouching(GreenBone.class))
        {
            removeTouching(GreenBone.class);
        }
        else if (isTouching(RedBone.class))
        {
            removeTouching(RedBone.class);
        }
        
        // If touching hydrant, press "space bar" to pee.
        if ((isTouching(Hydrant.class)) && (Greenfoot.isKeyDown("space")) && (actionCooldown == MAX_COOLDOWN))
        {
            Puddle puddle = new Puddle();               //<-- Assign new puddle into variable
                
            myworld.addObject(puddle, getX(), getY());  //<-- Calls method from World and creates puddle.
            actionCooldown = 0;
        }
        
        if (Greenfoot.isKeyDown("p") && (actionCooldown == MAX_COOLDOWN))
        {
            Poop poop = new Poop();
            myworld.addObject(poop, getX(), getY());
            actionCooldown = 0;
        }
              
    }
    
    /**
         * Action cooldown to prevent action spamming
         */
        private void cooldown()
        {
            if (actionCooldown < MAX_COOLDOWN)
            {
                actionCooldown = actionCooldown + 1;
            }
        }
    
    
}
