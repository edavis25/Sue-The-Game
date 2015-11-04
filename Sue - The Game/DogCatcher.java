import greenfoot.*;

/**
 * The Dog Catcher is trying to catch Sue...run! Has 2 images for left and right.
 */
public class DogCatcher extends Actor
{
    private GreenfootImage left;
    private GreenfootImage right;
    private boolean busy;
    private int cleanPuddleCounter;
           
                    
    /**
     * CONSTRUCTOR 
     */
    public DogCatcher()
    {
        left = new GreenfootImage("dogcatcherleft.png");
        right = new GreenfootImage("dogcatcherright.png");
        setImage(left);
        busy = false;
                
    }
    
    
    /**
     * Act method for the Dog Catcher
     */
    public void act() 
    {
        if (busy != true)
        {
            findDog();
        }
        checkHydrants();
       
    }
    
    
    /**
     * Method to check if the hydrants have been peed on or not.
     */
    public void checkHydrants()
    {
       MyWorld world = (MyWorld)getWorld();     //<-- Casts world to look for the Hydrants
              
       Hydrant northwestHydrant = world.getNorthwestHydrant();      //<-- Puts the Northeast Hydrant
       Hydrant northeastHydrant = world.getNortheastHydrant();                                                                                                         
       Hydrant southeastHydrant = world.getSoutheastHydrant(); 
            
       
       if ((northeastHydrant.checkPeedOn() == true) && (busy != true))
       {
           moveTowards(northeastHydrant.getX(), northeastHydrant.getY());
           cleanPee();                     
       }             
       else if (northwestHydrant.checkPeedOn() == true)
       {
           moveTowards(northwestHydrant.getX(), northeastHydrant.getY());
           busy = true;
        }
        else if (southeastHydrant.checkPeedOn() == true)
        {
            moveTowards(southeastHydrant.getX(), southeastHydrant.getY());
            busy = true;
        }
        
       
       //***EXAMPLE*  
        //Space spaceWorld = (Space) getWorld();  // get a reference to the world
        //Counter counter = spaceWorld.getCounter();  // get a reference to the counter
        //counter.bumpCount(5);  
        //*END EXAMPLE***

        
    }
         
    public void findDog()
    {
        MyWorld world = (MyWorld)getWorld();   //<-- Casts world to call the dogLocation methods from
                                               // the world class.
        
        int dogX = world.dogLocationX();       //<-- Assign the dog's X coordinate to a variable
        int dogY = world.dogLocationY();       //<-- Assign the dog's Y coordinate to a variable
        
        moveTowards(dogX, dogY);    //<-- Call moveTowards method with dog's coordinates as parameters
    }
    
    /**
     * This method tells the Dog Catcher where to move given a set of 2 coordinates. It can be used 
     * to chase the dog or it can be used with 2 static coordinates. The parameters of "X" and "Y" 
     * should be the x and y coordinates of the object the dog catcher needs to move towards. These
     * parameters are compared with the Dog Catcher's current location. The 1 added to the coordinates on
     * some of the If tests makes sure the Dog Catcher can move to every pixel and not glitch out because
     * he can't hit a specific pixel because his speed is 2 pixels.
     */
    public void moveTowards(int X, int Y)
    {
              
        int speed = 1;
        
        if (X < getX())   // Dog Catcher Facing LEFT
        {
            setImage(left);                  
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
        else if (X > getX())   // Facing RIGHT
        {
            setImage(right);            
            if (Y < getY())        // Y-Less than (northeast)
            {
                setRotation(-25);
                setLocation((getX() + speed), (getY() - speed));
            }
            else if (Y > getY())    // Y-Greater than (southeast)
            {
                setRotation(25);
                setLocation((getX() + speed), (getY() + speed));     
            }
            else if (Y == getY())   // Equal Y coordinates - move right
            {
                setRotation(0);
                setLocation((getX() + speed), getY());
            }
        }
        else if (X == getX())
        {
            
            if (Y < getY())
            {
                setRotation(0);
                setLocation((getX()), getY() - speed);
            }
            else if (Y > getY())
            {
                setRotation(0);
                setLocation((getX()), getY() + speed);
            }
        }    
    }
    
    
    
    public void cleanPee()
    {
        // Reference to the world
        MyWorld myworld = (MyWorld)getWorld();
        
        
        if (isTouching(Puddle.class))
        {
            
            
            // Create a Speech Bubble
            if (isTouching(SpeechBubble.class) == false)
            {
                SpeechBubble speechBubble = new SpeechBubble();
                myworld.addObject(speechBubble, getX(), getY() - 75);
            }
            
            
            if (cleanPuddleCounter == 200)
            {
                removeTouching(Puddle.class);
                removeTouching(SpeechBubble.class);
                cleanPuddleCounter = 0;
            }
            cleanPuddleCounter += 1;            
        }
    }
    
}

