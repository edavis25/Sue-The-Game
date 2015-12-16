import greenfoot.*;

/**
 * The Dog Catcher is our villain. He is trying to catch Sue because he's a fascist jerk! 
 * 1) Has 2 images to face either left or right.
 * 2) Becomes "Busy" when there is pee on a hydrant he must clean.
 */
public class DogCatcher extends Actor
{
    private GreenfootImage left;
    private GreenfootImage right;
    //private GreenfootImage rightWithBubble;   -- REVIEW THESE 2 FOR DELETION
    //private GreenfootImage leftWithBubble;    
    private boolean busy;
    private int cleanPuddleCounter;
    private int speed;
           
                    
    /**
     * CONSTRUCTOR 
     */
    public DogCatcher()
    {
        left = new GreenfootImage("dogcatcherleft.png");
        right = new GreenfootImage("dogcatcherright.png");
        //rightWithBubble = new GreenfootImage("dogcatcherrightBubble.png");  --REVIEW THESE 2 FOR DELETION
        //leftWithBubble = new GreenfootImage("dogcatcherleftBubble.png");
        setImage(left);
        busy = false;
        speed = 1;
                
    }
    
    
    /**
     * Act method for the Dog Catcher
     */
    public void act() 
    {
        if (busy == false)  //(busy != true) NOT BUSY = CHASE DOG
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
       MyWorld world = (MyWorld)getWorld();     //<-- Casts world to look for the Hydrants.
              
       Hydrant northwestHydrant = world.getNorthwestHydrant();      //<-- Put the hydrants into variables.
       Hydrant northeastHydrant = world.getNortheastHydrant();                                                                                                         
       Hydrant southeastHydrant = world.getSoutheastHydrant(); 
            
       
       if ((northeastHydrant.checkPeedOn() == true) ) //&& (busy != true) - removed
       {
           busy = true;
           moveTowards(northeastHydrant.getX(), northeastHydrant.getY());
           cleanPee();                     
       } 
       
       else if (northwestHydrant.checkPeedOn() == true)
       {
           busy = true;
           moveTowards(northwestHydrant.getX(), northeastHydrant.getY());
           cleanPee();
        }
        
       else if (southeastHydrant.checkPeedOn() == true)
        {
            busy = true;
            moveTowards(southeastHydrant.getX(), southeastHydrant.getY());
            cleanPee();
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
              
        //int speed = 1;
        
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
    
    
    
    /**
     * cleanPee  - Method used by the Dog Catcher when touching a Puddle. References the World to create the Speech Bubble and references the Sue class to change
     * the alreadyPeed instance variable in the Sue class by calling the setAlreadyPeedFalse method. Begin incrementing the cleanPuddleCounter, create a speech bubble,
     * and when the counter reaches a set amount (time) it will remove the puddle and the bubble and the Dog Catcher will resume chasing the dog.
     * 
     */
    private void cleanPee()
    {
           
        // Reference to the world
        MyWorld myworld = (MyWorld)getWorld();
        
        // Reference to the dog (used to change the 
        Sue dog = myworld.getDogReference();
        
        if (isTouching(Puddle.class))
        {
                        
            // Create a Speech Bubble  If statements stops creation of duplicate bubbles.
            if (isTouching(SpeechBubble.class) == false && cleanPuddleCounter == 100)
            {
                SpeechBubble speechBubble = new SpeechBubble();
                myworld.addObject(speechBubble, getX(), getY() - 75);
                
                //createBubble();  -- REVIEW FOR DELETION
                
            }
            
            // Finishes cleaning the pee when the counter hits correct value.
            if (cleanPuddleCounter == 200)
            {
                removeTouching(Puddle.class);
                removeTouching(SpeechBubble.class);
                cleanPuddleCounter = 0;
                busy = false;
                
                // Calls the setAlreadyPeedFalse method in the Sue class to change a boolean in the Sue class. The varible keeps
                // track of whether or not the dog has peed already (only once allowed)
                dog.setAlreadyPeedFalse();
            }
            
            cleanPuddleCounter += 1;
        }

    }
    
    
    
    /**
     *  createBubble  - Create the Speech bubble by setting a new image. Test to see if catcher
     *  is facing left or right and then set the image to leftWithBubble or rightWithBubble.
     *  
     *  *** REVIEW FOR DELETION *** (bubble creation by switching images. delete instance variables that it uses 
     */
    public void createBubble()
    {
        if (getImage() == left)
        {
            //setImage(leftWithBubble);
        }
        else
        {
            //setImage(rightWithBubble);
        }
    }
    
}

