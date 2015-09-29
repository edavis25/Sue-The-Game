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
    private Hydrant northeastHydrant;
    private Hydrant southeastHydrant;
    private Hydrant northwestHydrant;
    
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1250, 900, 1);
        setPaintOrder(Sue.class, Hydrant.class);
        
        prepareWorld();
               
    }
    
    /**
     * Method for World Preperation
     */
    private void prepareWorld()
    {
        // Add dog and create a variable to store it in
        dog = new Sue();
        addObject(dog, 170, 650);
        
        // Adds hydrants and give them each a variable
            // NORTHEAST
        northeastHydrant = new Hydrant();
        addObject(northeastHydrant, 900, 200);
            // SOUTHEAST
        southeastHydrant = new Hydrant();
        addObject(southeastHydrant, 1000, 650);
            // NORTHWEST
        northwestHydrant = new Hydrant();
        addObject(northwestHydrant, 300, 150);
    }
    
    /**
     * 2 methods for finding dog's location.
     */
    public int dogLocationX()  
    {
        return dog.getX();
    }
    public int dogLocationY()
    {
        return dog.getY();
    }
    
    /**
     * Returning Hydrants method
     */
    public Hydrant getNortheastHydrant()
    {
        return northeastHydrant;
    }
    
    
}
