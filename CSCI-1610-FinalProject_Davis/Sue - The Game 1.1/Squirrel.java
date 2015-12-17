import greenfoot.*;

/**
 * Aaaah! Squirrel! Eve hates squirrels!! They will randomly run out of a bush and try and steal the bones
 * from her doghouse. They must be stopped!
 * 
 * @author Eric Davis
 * @version Version 1.0
 */
public class Squirrel extends Actor
{
    // Initialize the 5 images for the squirrel.
    private GreenfootImage left;
    private GreenfootImage left1;
    private GreenfootImage right;
    private GreenfootImage right1;
    private GreenfootImage sitting;
    
    // Counter for animation
    private int animationCounter;
    
    // Variables to introduce a pause before squirrel starts its action
    private boolean paused;
    private int pauseCounter;
    
    // Variables for the stealBones method
    public int bonesStolen;
    private int MAX_BONES_STOLEN;
    private int stealCounter;
    
    // Signal for squirrel to return to starting coordinates.
    private boolean returnHome;
    
    // Home coordinates for squirrel (origin of creation)
    private int homeX;
    private int homeY;
    
    private int speed;
    
    /**
     * Constructor.
     */
    public Squirrel(int myhomeX, int myhomeY)
    {
        homeX = myhomeX;
        homeY = myhomeY;
        
        animationCounter = 0;
        
        // Initialize the different images for animation
        left = new GreenfootImage("squirrelLeft.png");
        left1 = new GreenfootImage("squirrelLeft1.png");
        right = new GreenfootImage("squirrelRight.png");
        right1 = new GreenfootImage("squirrelRight1.png");
        sitting = new GreenfootImage("squirrelSitting.png");
        
        
        bonesStolen = 0;
        MAX_BONES_STOLEN = Greenfoot.getRandomNumber(4) + 3;
        
        // Flag determining if the dog should return home. Not to start with, obviously.
        returnHome = false;
        
        // Introduce a pause to give player warning of the squirrel before it starts running
        paused = true;     //<---Start as TRUE to pause the squirrel before action.
        pauseCounter = 0;
        
        speed = 2;
        
        setImage(sitting);
    }
   
    
    
    /**
     * Act -  Handles the squirrels actions. "paused" starts as true to introduce a delay to show
     * the squirrel sitting in the bush to warn the player of its presence. After going through the
     * paused period, head towards the doghouse until the returnHome flag is set to true, in which
     * case head to beginning coordinates.
     */
    public void act() 
    {
        
        if (paused == true)
        {
            pause(200);    
        }
        else
        {  
            if (returnHome == true)
            {
                returnHome();
            }
            else
            {
                moveTowardsDoghouse();
            }
        }
        
        // Check for touching the various objects.
        checkTouching();
        
    }    
    
    
    
    /**
     * returnHome   - Method called when the squirrel should be heading back to origin of creation.
     *             After he steals his max amount of bones.
     */
    private void returnHome()
    {
        MyWorld world = (MyWorld)getWorld();
        
        //setImage(right);
        animate(homeX);
        world.dogCatcher.moveTowards(homeX, homeY, this, speed);
        
    }
    
    
    
    /**
     * checkTouching    - Check to see if squirrel is touching various items.
     */
    private void checkTouching()
    {
               
        if (isTouching(Sue.class))
        {
            MyWorld world = (MyWorld)getWorld();    
            if (bonesStolen > 0)
            {
                // Create label showing how many bones are returned
                world.addObject(new SquirrelLabel("+" + bonesStolen), getX(), getY());
                // Add bones back to total score
                world.scoreLabel.addToScore(bonesStolen);
            }
            
            // Create a sound for dog to bark.
            GreenfootSound woof = new GreenfootSound("woof.wav");
            woof.setVolume(100);
            woof.play();
            
            world.removeObject(this);
            
        }
        
        // If dog is touching doghouse and should not return home, steal the bones.
        else if (isTouching(Doghouse.class) && returnHome == false)
        {
            //pause(300);
            stealBones();
            
        }
        
        // If the squirrel is touching the bush and he is returning home, remove object. He escaped.
        else if (isTouching(Bush.class) && returnHome == true)
        {
            MyWorld world = (MyWorld)getWorld();
            world.removeObject(this);
        }
        
        
        
    }
    
    
    /**
     * pause()  - Pause the squirrel for a moment before executing its attack on the doghouse. The
     *          parameter acts as a timer determing how long the squirrel should remain paused.
     */
    private void pause(int x)
    {
         pauseCounter = pauseCounter + 1;
         
         setImage(sitting);
         
         if (pauseCounter == x)
         {
             paused = false;
             pauseCounter = 0;
             animationCounter = 0;
         }
    }
    
    
    /**
     * stealBones   - Method used to steal bones. Called if the squirrel is touching the doghouse
     *          and returnHome flag is false. The counter increments make sure there is a delay when
     *          the bones are being stolen. Once the preset amount is reached (MAX_BONES_STOLEN) or
     *          Total Score = 0, then set the return home flag to true to send squirrel home.
     */
    private void stealBones()
    {
        MyWorld world = (MyWorld)getWorld();
        
        setImage(sitting);
        
        stealCounter = stealCounter + 1;
        
        animationCounter = 0;
        if ((stealCounter == 50) && returnHome == false)
        {
            world.scoreLabel.addToScore(-1);
            bonesStolen = bonesStolen + 1;
            stealCounter = 0;
            
        }
        if (bonesStolen == MAX_BONES_STOLEN || world.scoreLabel.totalScore == 0)
        {
            returnHome = true;
            
        }
                
    }
    
    
    /**
     * moveTowardsDoghouse()    - Method that moves the squirrel towards the doghouse.
     */
    private void moveTowardsDoghouse()
    {
        MyWorld world = (MyWorld)getWorld();
        
        animate(world.doghouse.getX());
        
        // Call the Dog Catcher's moveTowards method to move the squirrel.
        world.dogCatcher.moveTowards(world.doghouse.getX(), world.doghouse.getY(), this, speed);
                
    }
    
    
   
   
   /**
     * animate    - Animate the squirrel. The parameter "destinationX" is input to determine if the squirrel
     *          should be facing left or right.
     */
   private void animate(int destinationX)
    {
         MyWorld world = (MyWorld)getWorld();
         
         animationCounter = animationCounter + 1;
         
         
         if (destinationX > getX() )
         {
             
             if (animationCounter == 1)
             {
                 setImage(right);
             }
             else if (animationCounter == 20)
             {
                 setImage(right1);
             }
             else if (animationCounter == 40)
             {
                 animationCounter = 0;
             }
         }
         
         else if (destinationX < getX() )
         {
             if (animationCounter == 1)
             {
                 setImage(left);
             }
             else if (animationCounter == 20)
             {
                  setImage(left1);
             }
             else if (animationCounter == 40)
             {
                  animationCounter = 0;
             }
         }
        
        }
}
