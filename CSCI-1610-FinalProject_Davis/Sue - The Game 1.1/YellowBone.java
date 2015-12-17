import greenfoot.*;

/**
 * Yellow Bone - Sue's favorite treat! Except these yellow ones make her have to potty. Better find
 * a hydrant!
 * 
 * @author Eric Davis 
 * @version Version 1.0
 */
public class YellowBone extends Bone
{
    /**
     * Act - do whatever the YellowBone wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        MyWorld world = (MyWorld)getWorld();
        
        // Slowly turn the bone for effect.
        turn(-1);
        
        // Increment the life timer.
        lifeTimer = lifeTimer + 1;  //<---Variable from parent construtor
        
        // If lifetimer has reached max amount. Remove bone. removeBone() and variables from parent.
        if (lifeTimer == MAX_LIFETIME)
        {
            removeBone();    //<-- Method from parent class
           
            // Set the world boolean allowing the creation of more yellow bones.
            world.setYellowBoneCreated(false);
        }
    }    
}
