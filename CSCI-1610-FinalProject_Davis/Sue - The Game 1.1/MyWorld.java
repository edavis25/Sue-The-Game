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
    
    // Declare the 2 labels in the Thought Bubbles.    
    public PocketLabel pocketLabel; 
    public ScoreLabel scoreLabel; 
    
    public Doghouse doghouse;
    
    private ThoughtBubble thoughtBubble;
    
    // The 3 Hydrants in the world.
    private Hydrant northeastHydrant;
    private Hydrant southeastHydrant;
    private Hydrant northwestHydrant;
    
    // Used to make sure only 1 yellow bone can be created at any given time.
    public boolean yellowBoneCreated;
    
    // Used for squirrel spawn. SpawnTime = how long until it spawns. Counter keeps track.
    private int squirrelSpawnTime = Greenfoot.getRandomNumber(500) + 2000;  // Adds a little randomness.
    private int squirrelSpawnCounter = 0;
   
    // The 4 Trees in the world.
    private Tree northwestTree;
    private Tree northeastTree;
    private Tree southeastTree;
    private Tree southwestTree;
    
    // The 4 inhabitable bushes
    private Bush northBush;
    private Bush northeastBush;
    private Bush eastBush;
    private Bush southeastBush;
    
    public Squirrel squirrel;
    
    /**
     * Constructor for objects of class MyWorld. Paint order sets the layering order when objects are on top of one another.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with a cell size of 1x1 pixels.
        super(1250, 700, 1);
        
        yellowBoneCreated = false;
        
        setPaintOrder(Sue.class, DogCatcher.class, SpeechBubble.class, Hydrant.class, Doghouse.class, PocketLabel.class, ScoreLabel.class, Bone.class, Bush.class, Tree.class);
        
        prepareWorld();
                       
    }
    
    
    
    /**
     * Act - Does whatever the world should do on a frame by frame basis. Create bones and squirrels.
     */
    public void act()
    {
        createBones();
        createSquirrel();
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
            addObject(bone, Greenfoot.getRandomNumber(1000) + 50, Greenfoot.getRandomNumber(600) + 25);
        }
        
        if (randomNumber <= 1 && dog.returnAlreadyPeed() == false && yellowBoneCreated == false)
        {
            addObject(yellowBone, Greenfoot.getRandomNumber(1000) + 50, Greenfoot.getRandomNumber(600) + 25);
            yellowBoneCreated = true;
        }
        
        
        
    }
   
    
    /**
     * createSquirrel       - Called to create a squirrel. Get a random value in between 0 - 500 and then add it to
     *                      a large number to keep numbers down.
     */
    private void createSquirrel()
    {
        // Increment the counter.
        squirrelSpawnCounter = squirrelSpawnCounter + 1;
        
        // If the set squirrelSpawnTime has been reached, check total score.
        if (squirrelSpawnCounter == squirrelSpawnTime)
        {
            
            // If total score is greater than 5, create a squirrel.
            if (scoreLabel.totalScore >= 5)
            {
                Bush homeBush = getHomeBush();
                Squirrel squirrel = new Squirrel(homeBush.getX(), homeBush.getY());
                addObject(squirrel, homeBush.getX(), homeBush.getY());
            }
            
            // Reset spawnTime and spawnCounter
            squirrelSpawnCounter = 0;
            squirrelSpawnTime = Greenfoot.getRandomNumber(500) + 2000;
        }
        
        
    }
    
    
    /**
     * getHomeBush      - Pick and return one of the home bushes at random. This is used when creating a
     *                  squirrel. Whatever bush is chosen here will be the created squirrel's home bush.
     *                  (Place of creation)
     */
    private Bush getHomeBush()
    {
        int random = Greenfoot.getRandomNumber(4) + 1;
        Bush homeBush = new Bush();
        
        if (random == 1)
        {
            homeBush = northBush;
        }
        else if (random == 2)
        {
            homeBush = northeastBush;
        }
        else if (random == 3)
        {
            homeBush = eastBush;
        }
        else
        {
            homeBush = southeastBush;
        }
        
        return homeBush;
     }
    
    
     
    /**
     * Method for World Preperation. Creates all the different objects in the world.
     */
    private void prepareWorld()
    {
        // Add DOG and create a variable to store it in
        dog = new Sue();
        addObject(dog, 130, 620);
        
        // Add the DOG CATCHER and create a variable to store it in
        dogCatcher = new DogCatcher();
        addObject(dogCatcher, 1000, 300);
        
        // Add the DOGHOUSE and create a variable to store it in
        doghouse = new Doghouse();
        addObject(doghouse, 124,550);
        
        // Add the POCKET LABEL
        pocketLabel = new PocketLabel();
        addObject(pocketLabel, 150, 60);
        
        // Add the SCORE LABEL
        scoreLabel = new ScoreLabel();
        addObject(scoreLabel, 180, 90);
        
        // Add the THOUGHT BUBBLE (Do-nothing scoreboard for effect)
        thoughtBubble = new ThoughtBubble();
        addObject(thoughtBubble, 125, 120);
            
               
        // Create Bushes
            // Squirrel inhabitable bushes
                //NORTHEAST BUSH
        northeastBush = new Bush();
        addObject(northeastBush, 1220, 144);
                //EAST BUSH
        eastBush = new Bush();
        addObject(eastBush, 1210, 420);
                //SOUTHEAST BUSH
        southeastBush = new Bush();
        addObject(southeastBush, 800, 672); 
                // NORTH BUSH
        northBush = new Bush();
        addObject(northBush, 770, 42);
               
        
        // Adds hydrants and give them each a variable
             // NORTHEAST HYDRANT
        northeastHydrant = new Hydrant();
        addObject(northeastHydrant, 1050, 150);
        
            // SOUTHEAST HYDRANT
        southeastHydrant = new Hydrant();
        addObject(southeastHydrant, 1100, 600);
        
            // NORTHWEST HYDRANT
        northwestHydrant = new Hydrant();
        addObject(northwestHydrant, 500, 150);
        
       
        // Create Trees
            //NORTHWEST TREE
        northwestTree = new Tree();
        addObject(northwestTree, 300, 90);
            
            //NORTHEAST TREE
        northeastTree = new Tree();
        addObject(northeastTree, 1180, 80);
        
            //SOUTHEAST TREE
        southeastTree = new Tree();
        addObject(southeastTree, 750, 630);
        
            //SOUTHWEST TREE
        southwestTree = new Tree();
        addObject(southwestTree, 55, 460);
        
        
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
         
    
    
    public void setYellowBoneCreated(boolean x)
    {
        yellowBoneCreated = x;
    }
        
   
}
