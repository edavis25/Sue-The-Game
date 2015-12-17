import greenfoot.*;

/**
 * Bone - Sue's favorite treat! She loves to collect them and horde them in her doghouse.
 * 
 * @author Eric Davis
 * @version Version 1.0
 */
public class Bone extends Actor
{
    // Life timer to remove bones after set time.
    public int lifeTimer;
    public final int MAX_LIFETIME;
    
    public Bone()
    {
        // Set a random starting rotation for variety.
        setRotation(Greenfoot.getRandomNumber(360));
        
        MAX_LIFETIME = 750;
        lifeTimer = 0;
    }
    
    
    /**
     * Act - do whatever the Bone wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Slowly turn the bone for effect.
        turn(1);
        
        // Increment the life timer.
        lifeTimer = lifeTimer + 1;
        
        // If the lifetimer reaches max amount, then remove the bone.
        if (lifeTimer == MAX_LIFETIME)
        {
            removeBone();
        }
    }    
    
    
    /**
     * removeBone  - Removes bone from the world. Shocking!
     */
    public void removeBone()
    {
        MyWorld world = (MyWorld)getWorld();
        world.removeObject(this);

    }
}
