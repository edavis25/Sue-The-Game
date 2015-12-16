import greenfoot.*;

/**
 * Write a description of class Bone here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bone extends Actor
{
    
    public int lifeTimer;
    
    public Bone()
    {
        setRotation(Greenfoot.getRandomNumber(360));
        lifeTimer = 0;
    }
    
    
    /**
     * Act - do whatever the Bone wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        turn(1);
        
        lifeTimer = lifeTimer + 1;
        
        if (lifeTimer == 500)
        {
            removeBone();
        }
    }    
    
    public void removeBone()
    {
        MyWorld world = (MyWorld)getWorld();
        world.removeObject(this);

    }
}
