import greenfoot.*;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    private Sue dog;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1250, 900, 1);
        setPaintOrder(Sue.class, Hydrant.class);
        
        dog = new Sue();
        addObject(dog, 170, 650);
        
    }
    
    
    public int dogLocationX()
    {
        return dog.getX();
    }
    
    public int dogLocationY()
    {
        return dog.getY();
    }
}
