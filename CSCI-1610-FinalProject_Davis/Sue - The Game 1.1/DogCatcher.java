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
    
    private boolean busy;
    private int cleanPuddleCounter;
    private int speed;
    
    private boolean chasingDog;
                    
    /**
     * Constructor.
     */
    public DogCatcher()
    {
        left = new GreenfootImage("dogcatcherleft.png");
        right = new GreenfootImage("dogcatcherright.png");
        
        setImage(left);
        busy = false;
        speed = 1;
        
        chasingDog = true;
    }
    
    
    /**
     * Act method for the Dog Catcher
     */
    public void act() 
    {
        if (chasingDog == true)  //(busy != true) NOT BUSY = CHASE DOG
        {
            findDog();
        }
                
        checkHydrants();
        cleanPee();  //<---Also handles Poop. Thats why it is in Act, so the catcher always checks isTouching poop. Ha.
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
            
       
       if ((northeastHydrant.checkPeedOn() == true)  && (busy == false))
       {
           chasingDog = false;
           //busy = true;
           moveTowards(northeastHydrant.getX(), northeastHydrant.getY(), this, speed);
           //cleanPee();               
           setDirectionFacing(northeastHydrant.getX(), northeastHydrant.getY());
       } 
       
       else if (northwestHydrant.checkPeedOn() == true && (busy == false))
       {
           chasingDog = false;
           //busy = true;
           moveTowards(northwestHydrant.getX(), northeastHydrant.getY(), this, speed);
           //cleanPee();
           setDirectionFacing(northwestHydrant.getX(), northeastHydrant.getY());
        }
        
       else if (southeastHydrant.checkPeedOn() == true && (busy == false))
        {
            chasingDog = false;
            //busy = true;
            moveTowards(southeastHydrant.getX(), southeastHydrant.getY(), this, speed);
            //cleanPee();
            setDirectionFacing(southeastHydrant.getX(), southeastHydrant.getY());
        }
        
                      
    }
         
    
    
    public void findDog()
    {
        MyWorld world = (MyWorld)getWorld();   //<-- Casts world to call the dogLocation methods from
                                               // the world class.
        
        int dogX = world.dogLocationX();       //<-- Assign the dog's X coordinate to a variable
        int dogY = world.dogLocationY();       //<-- Assign the dog's Y coordinate to a variable
        
               
        setDirectionFacing(dogX, dogY);
        moveTowards(dogX, dogY, this, speed);    //<-- Call moveTowards method with dog's coordinates as parameters
    }
    
    
    
    public void setDirectionFacing(int X, int Y)
    {
        if (X > getX())   // Dog Catcher Facing LEFT
        {
            setImage(right);
        }
        else
        {
            setImage(left);
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
        
        if (isTouching(Puddle.class) || isTouching(Poop.class))
        {
            chasingDog = false;
            busy = true;
            cleanPuddleCounter = cleanPuddleCounter + 1;
            
            // Create a Speech Bubble  If  statements stops creation of duplicate bubbles.
            if (isTouching(SpeechBubble.class) == false && cleanPuddleCounter == 100)
            {
                SpeechBubble speechBubble = new SpeechBubble();
                myworld.addObject(speechBubble, getX(), getY() - 75);
                
                //createBubble();  -- REVIEW FOR DELETION
                
            }
            
            // Finishes cleaning the pee when the counter hits correct value.
            if (cleanPuddleCounter == 350)
            {
                removeTouching(Puddle.class);
                removeTouching(Poop.class);
                removeTouching(SpeechBubble.class);
                cleanPuddleCounter = 0;
                busy = false;
                
                chasingDog = true;
                // Calls the setAlreadyPeedFalse method in the Sue class to change a boolean in the Sue class. The varible keeps
                // track of whether or not the dog has peed already (only once allowed)
                dog.setAlreadyPeedFalse();
                
                myworld.setYellowBoneCreated(false); //This boolean in MyWorld allows creation of yellow bones.
            }
            
            
        }

    }
    
    
    
    
    
    
    /**
     * This method tells the Dog Catcher where to move given a set of 2 coordinates. It can be used 
     * to chase the dog or it can be used with 2 static coordinates. The parameters of "X" and "Y" 
     * should be the x and y coordinates of the object the dog catcher needs to move towards. These
     * parameters are compared with the Dog Catcher's current location. The 1 added to the coordinates on
     * some of the If tests makes sure the Dog Catcher can move to every pixel and not glitch out because
     * he can't hit a specific pixel because his speed is 2 pixels.
     * 
     */
    public void moveTowards(int X, int Y, Actor object, int speed)
    {
              
        
        
        if (X < object.getX())   // Dog Catcher Facing LEFT
        {
            //setImage(left);                  
            if (Y < object.getY())  // Y-Less than (northwest)
            {
                object.setRotation(25);
                object.setLocation((object.getX() - speed), (object.getY() - speed));
            }
            else if (Y > object.getY())     //Y-Greater than (southwest)
            {
                object.setRotation(-25);
                object.setLocation((object.getX() - speed), (object.getY() + speed));
            }
            else if (Y == object.getY())               // Equal Y coordinates - move left
            {
                object.setRotation(0);
                object.setLocation((object.getX() - speed), object.getY());
            }
        }
        else if (X > object.getX())   // Facing RIGHT
        {
            //setImage(right);            
            if (Y < object.getY())        // Y-Less than (northeast)
            {
                object.setRotation(-25);
                object.setLocation((object.getX() + speed), (object.getY() - speed));
            }
            else if (Y > object.getY())    // Y-Greater than (southeast)
            {
                object.setRotation(25);
                object.setLocation((object.getX() + speed), (object.getY() + speed));     
            }
            else if (Y == object.getY())   // Equal Y coordinates - move right
            {
                object.setRotation(0);
                object.setLocation((object.getX() + speed), object.getY());
            }
        }
        else if (X == object.getX())
        {
            
            if (Y < object.getY())
            {
                object.setRotation(0);
                object.setLocation((object.getX()), object.getY() - speed);
            }
            else if (Y > object.getY())
            {
                object.setRotation(0);
                object.setLocation((object.getX()), object.getY() + speed);
            }
        }    
    }
       
}

