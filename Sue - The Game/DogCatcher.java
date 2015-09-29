import greenfoot.*;

/**
 * The Dog Catcher is trying to catch Sue...run! Has 2 images for left and right.
 */
public class DogCatcher extends Actor
{
    private GreenfootImage left;
    private GreenfootImage right;
                
    /**
     * CONSTRUCTOR 
     */
    public DogCatcher()
    {
        left = new GreenfootImage("dogcatcherleft.png");
        right = new GreenfootImage("dogcatcherright.png");
        setImage(left);
                
    }
    
    
    /**
     * Act method for the Dog Catcher
     */
    public void act() 
    {
        findDog();
    }
    
    
    /**
     * The following method acts as the AI for the dog catcher. The first IF statement 
     * checks the X coordinate of the dog. If the dogs X coordinate is smaller the catcher
     * faces left and if X greater then catcher faces right. After setting the corrrect 
     * direction of the dog catcher (by changing its image) it will then find the Y coordinate
     * of the dog and set rotation and move towards the correct direction.
     */
    public void findDog()
    {
        MyWorld world = (MyWorld)getWorld();        //<-- Reference to find the Dog in the World
        
        int dogX = world.dogLocationX();            //<-- Calls methods from MyWorld to return the dog's
        int dogY = world.dogLocationY();            // X and Y coordiantes. Assign them to variables.
                      
        if (dogX < getX())    // Dog Catcher Facing LEFT
        {
            setImage(left);
            
            if (dogY < getY())     // Y-Less than (northwest)
            {
                setRotation(25);
                setLocation((getX()-2), (getY()-1));
            }
            else if (dogY > getY())     //Y-Greater than (southwest)
            {
                setRotation(-25);
                setLocation((getX()-2), (getY()+1));
            }
            else if (dogY == getY())     // Equal Y coordinates - move left
            {
                setRotation(0);
                setLocation((getX() -2), getY());
            }
        }
        else if (dogX > getX())   // Facing RIGHT
        {
            setImage(right);
            
            if (dogY < getY())        // Y-Less than (northeast)
            {
                setRotation(-25);
                setLocation((getX()+2), (getY()-1));
            }
            else if (dogY > getY())    // Y-Greater than (southeast)
            {
                setRotation(25);
                setLocation((getX()+2), (getY()+1));
                              
            }
            else if (dogY == getY())   // Equal Y coordinates - move right
            {
                setRotation(0);
                setLocation((getX() +2), getY());
            }
        }
        else if (dogX == getX())
        {
            
            if (dogY < getY())
            {
                setRotation(0);
                setLocation((getX()), getY()-2);
            }
            else if (dogY > getY())
            {
                setRotation(0);
                setLocation((getX()), getY()+2);
            }
        }                                                
    }
    
    /**
     * Method to check if the hydrants have been peed on or not.
     */
    public void checkHydrants()
    {
        MyWorld world = (MyWorld)getWorld();
        
    }
}

