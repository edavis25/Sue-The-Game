import greenfoot.*;

/**
 * Write a description of class BrownBone here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BrownBone extends Bone
{
    /**
     * Act - do whatever the BrownBone wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        turn(1);
        
        lifeTimer = lifeTimer + 1;  //<---Variable from parent construtor
        
        if (lifeTimer == 500)
        {
            removeBone();  //<-- Method from parent class
        }
        
    }    
}
