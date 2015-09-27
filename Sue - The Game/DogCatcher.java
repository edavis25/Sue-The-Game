import greenfoot.*;

/**
 * Write a description of class DogCatcher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DogCatcher extends Actor
{
    private GreenfootImage left;
    private GreenfootImage right;
    private int currentRotate;
    
    
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
     * Act - do whatever the DogCatcher wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        findDog();
    }
    
    
    public void findDog()
    {
        MyWorld world = (MyWorld)getWorld();
        int dogX = world.dogLocationX();
        int dogY = world.dogLocationY();
        int maxRotate = 50;
        int minRotate = -50;
        
        
        
        if (dogX < getX())    // Facing LEFT
        {
            setImage(left);
            
            if (dogY < getY() && (currentRotate < maxRotate))          // Y-Less than (northwest)
            {
                setRotation(getRotation() -2);
                currentRotate = currentRotate + 2;
                setLocation((getX()-2), (getY()-1));
            }
            else if (dogY > getY() && (currentRotate > minRotate))     //Y-Greater than (southwest)
            {
                setRotation(getRotation() +2);
                currentRotate = currentRotate -2;
                
                setLocation((getX()-2), (getY()+1));
                //move(-2);
            }
            else if (dogY == getY())
            {
                setRotation(0);
                setLocation((getX() -2), getY());
            }
        }
        else if (dogX > getX())   // Facing RIGHT
        {
            setImage(right);
            move(2);
            if (dogY < getY() && (currentRotate < maxRotate))        // Y-Less than (northeast)
            {
                setRotation(getRotation() +2);
                currentRotate = currentRotate -2;
            }
            else if (dogY > getY() && (currentRotate > minRotate))    // Y-Greater than (southeast)
            {
                setRotation(getRotation() -2);
                currentRotate = currentRotate +2;
                
            }
        }
          
        
        
        
        
       
    }
}

