import greenfoot.*;

/**
 * Tree  - Doesn't do much. Just breaks up the white and looks pretty. 
 * 
 * @author Eric Davis
 * @version 1.0
 */
public class Tree extends Actor
{
    
    /**
     * Constructor.  - There are 2 different tree images: (1)"tree1.png" and (2)"tree2.png" . The 
     * constructor will get a random number (1 or 2) and then concatenate it to determine which one of the 2
     * images the tree will use for variety's sake.
     */
    
    public Tree()
    {
        
        
        int random = Greenfoot.getRandomNumber(2) + 1;
                
        setImage(new GreenfootImage("tree" + random + ".png"));
                
    }
    
    
    /**
     * Act - do whatever the Tree wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */         
    public void act() 
    {
        // Add your action code here.
    }    
}
