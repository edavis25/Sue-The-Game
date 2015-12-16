import greenfoot.*;

/**
 * Write a description of class Squirrel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Squirrel extends Actor
{
   
    private GreenfootImage left;
    private GreenfootImage left1;
    private GreenfootImage right;
    private GreenfootImage right1;
    private GreenfootImage sitting;
    
    private static int animationCounter;
    
    
    
    /**
     * Constructor.
     */
    public Squirrel()
    {
        animationCounter = 0;
        
        // Initialize the different images for animation
        left = new GreenfootImage("squirrelLeft.png");
        left1 = new GreenfootImage("squirrelLeft1.png");
        right = new GreenfootImage("squirrelRight.png");
        right1 = new GreenfootImage("squirrelRight1.png");
        sitting = new GreenfootImage("squirrelSitting.png");
        
        setImage(sitting);
    }
    
    /**
     * Act - do whatever the Squirrel wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        MyWorld world = (MyWorld)getWorld();
        //animate();
        moveTowardsDoghouse(world.doghouse.getX(), world.doghouse.getY());
        
    }    
    
    
    private void moveTowardsDoghouse(int X, int Y)
    {
        MyWorld world = (MyWorld)getWorld();
        
        //animate();
        //world.dogCatcher.moveTowards(world.doghouse.getX(), world.doghouse.getY());
        int speed = 1;
        
        animate();
        
        
        // Instead of rewriting this code, try to pull the 2 set image statements found
        // method in the Dog Catcher class and put them into their own method to switch
        // between right/left. By moving them I should be able to call the procedure
        // and not have to worry about switching the image. 
        if (X < getX())   // Dog Catcher Facing LEFT
        {
            //setImage(left);                  
            if (Y < getY())  // Y-Less than (northwest)
            {
                setRotation(25);
                setLocation((getX() - speed), (getY() - speed));
            }
            else if (Y > getY())     //Y-Greater than (southwest)
            {
                setRotation(-25);
                setLocation((getX() - speed), (getY() + speed));
            }
            else if (Y == getY())               // Equal Y coordinates - move left
            {
                setRotation(0);
                setLocation((getX() - speed), getY());
            }
        }
    }
    
    
    //maybe pull in a string left or right as parameter to determine the direction and then concat
    // the parameter to change the image like when creating the piano notes. So if the parameter was 
    // animate(string direction) the user could pass in "left" or "right" to concat with the image
    // file names.
    ]
    private void animate()
    {
         
         animationCounter = animationCounter + 1;
         
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
