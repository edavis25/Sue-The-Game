import greenfoot.*;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    private DogCatcher dogCatcher; 
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
        setPaintOrder(Sue.class, SpeechBubble.class, Hydrant.class);
        
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
        
        dogCatcher = new DogCatcher();
        addObject(dogCatcher, 1000, 250);
        
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
        
        //int i = 5;
        //showText(""+i, 100, 100);
        
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
     * 2 methods for finding dog catcher's location.
     */
    public int dogCatcherLocationX()  
    {
        return dogCatcher.getX();
    }
    public int dogCatcherLocationY()
    {
        return dogCatcher.getY();
    }
    
    
    
    /**
     * 3 Methods used to return the different Hydrants as references. This is used so I can call the peedOn() method (located in the Hydrant class)
     * from other Actors. This will return a specific instance of a Hydrant which can then be used to call the peedOn() method which will ultimately
     * return a true/false result. The Dog Catcher needs to check if each hydrant has been peed on by taking this reference and then calling
     * the method inside of the Hydrant for the boolean answer. Three seperate methods grouped together.
     */
    public Hydrant getNorthwestHydrant()
    {
        return northwestHydrant;
    }
    public Hydrant getNortheastHydrant()
    {
        return northeastHydrant;
    }
    public Hydrant getSoutheastHydrant()
    {
        return southeastHydrant;
    }
    
    
    
}
