import greenfoot.*;

/**
 * This is the world! The world holds all of the different objects that interact with each other. Most of the objects will be declared public so them and their class
 * methods can be accessed from other places in the program! When I started I used a separate method for each object to return a reference to it, but I have started
 * switching out these methods and simply changing the variables here to public. 
 * 
 * @author Eric Davis
 * @version Version 1.1
 */
public class MyWorld extends World
{
    public DogCatcher dogCatcher; 
    
    private Sue dog;
    
    public PocketLabel pocketLabel;  // Used as public
    public ScoreLabel scoreLabel;    // Used as public
    
    public Doghouse doghouse;
    
    private ThoughtBubble thoughtBubble;
    private Hydrant northeastHydrant;
    private Hydrant southeastHydrant;
    private Hydrant northwestHydrant;
    
    public boolean yellowBoneCreated;
    
    
    /**
     * Constructor for objects of class MyWorld. Paint order sets the layering order when objects are on top of one another.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with a cell size of 1x1 pixels.
        super(1250, 900, 1);
        
        yellowBoneCreated = false;
        
        setPaintOrder(Sue.class, DogCatcher.class, SpeechBubble.class, Hydrant.class, PocketLabel.class, ScoreLabel.class);
        
        prepareWorld();
                       
    }
    
    
    
    /**
     * Act - Does whatever the world should do on a frame by frame basis. 
     */
    public void act()
    {
        createBones();
    }
    
    
    /**
     * createBones      - Method called by the world's act procedure. This will generate a random number and then compare it to a range of values to create a bone
     *                    The hardcoded number 2000 and <= 10 and <=1 were trial and errored until I found a timing I liked.
     */
    private void createBones()
    {
        Bone bone = new Bone();
        Bone yellowBone = new YellowBone();
        
        int randomNumber = Greenfoot.getRandomNumber(2000);
        
        if (randomNumber <= 10  )
        {
            addObject(bone, Greenfoot.getRandomNumber(1150) + 50, Greenfoot.getRandomNumber(800) + 25);
        }
        
        if (randomNumber <= 1 && dog.returnAlreadyPeed() == false && yellowBoneCreated == false)
        {
            addObject(yellowBone, Greenfoot.getRandomNumber(1150) + 50, Greenfoot.getRandomNumber(800) + 25);
            yellowBoneCreated = true;
        }
        
        
        
    }
   
    
    
    /**
     * Method for World Preperation. Creates all the different objects in the world.
     */
    private void prepareWorld()
    {
        // Add DOG and create a variable to store it in
        dog = new Sue();
        addObject(dog, 150, 825);
        
        // Add the DOG CATCHER and create a variable to store it in
        dogCatcher = new DogCatcher();
        addObject(dogCatcher, 1000, 250);
        
        // Add the DOGHOUSE and create a variable to store it in
        doghouse = new Doghouse();
        addObject(doghouse, 150, 750);
        
        // Add the POCKET LABEL
        pocketLabel = new PocketLabel();
        addObject(pocketLabel, 150, 60);
        
        // Add the SCORE LABEL
        scoreLabel = new ScoreLabel();
        addObject(scoreLabel, 180, 90);
        
        // Add the THOUGHT BUBBLE (Do-nothing scoreboard for effect)
        thoughtBubble = new ThoughtBubble();
        addObject(thoughtBubble, 125, 120);
            
               
        
        // Adds hydrants and give them each a variable
             // NORTHEAST HYDRANT
        northeastHydrant = new Hydrant();
        addObject(northeastHydrant, 900, 200);
        
            // SOUTHEAST HYDRANT
        southeastHydrant = new Hydrant();
        addObject(southeastHydrant, 1000, 650);
        
            // NORTHWEST HYDRANT
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
    
    
    
    /**
     * Method used to return Sue (the dog) as a reference.
     */
    public Sue getDogReference()
    {
        return dog;
    }
         
    
    
    /** 
     * ***********REVIEW FOR DELETION********** (the object made public.)
     * 
     * getScoreLabelReference - Method used to return the Score Label as an object.
     */
    //public ScoreLabel getScoreLabelReference()
    //{
    //    return scoreLabel;
    //}
    
    
    
    public void setYellowBoneCreated(boolean x)
    {
        yellowBoneCreated = x;
    }
    
    
    //REVIEW FOR DELETION IF GET ONE AT OFFSET DOESNT WORK IN SUE CLASS
    //public DogCatcher getDogCatcherReference()
    //{
    //    return dogCatcher;
    // }
}
