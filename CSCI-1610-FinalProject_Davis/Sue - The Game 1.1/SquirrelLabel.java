import greenfoot.*;

import java.awt.Font;
/**
 * Squirrel Label - Pops up whenever Sue catches a squirrel. This label will be created only if the 
 *          squirrel stole bones. If so, the label displays how many points are added back to score.
 * 
 * @author Eric Davis
 * @version Version 1.0
 */
public class SquirrelLabel extends Actor
{
    private int delayCounter;
    
    
    public SquirrelLabel(String text)
    {
        GreenfootImage pointLabel = new GreenfootImage(50, 50);
        float fontSize = 24;
        Font font = pointLabel.getFont().deriveFont(fontSize);
        pointLabel.setFont(font);
        
        pointLabel.drawString(text, 0, 20);
        setImage(pointLabel);
        
        delayCounter = 0;
    }
    
    
    /**
     * Act - do whatever the SquirrelLabel wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (delayCounter == 100)
        {
            MyWorld world = (MyWorld)getWorld();
            world.removeObject(this);
            delayCounter = 0;
        }
        
        delayCounter = delayCounter + 1;
    }    
}
