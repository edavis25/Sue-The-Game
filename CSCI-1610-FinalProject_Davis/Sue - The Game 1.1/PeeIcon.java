import greenfoot.*;
import java.awt.Color;

/**
 * Pee Icon   - Draws an oval that serves as a marker for whether or not the dog is able to pee
 * on a hydrant. After eating a Yellow Bone the icon is added to the world within the ThoughtBubble
 * and after peeing on a hydrant, the icon is removed.
 * 
 * @author Eric Davis
 * @version 1.0
 */
public class PeeIcon extends Actor
{
    public PeeIcon()
    {
        createIcon();
    }
    
    /**
     * Act - do whatever the PeeIcon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
      
    public void act() 
    {
        
    }
    
    private void createIcon()
    {
        // Create a new Greenfoot Image. (width, height) of image in pixels.
        GreenfootImage icon = new GreenfootImage(50, 50);
        // Create a new color matching the YellowBone color. The parameters are the RGB values.
        Color color = new Color (229, 203, 62);
        // Set the current drawing color
        icon.setColor(color);
        // Draw and fill an oval. (x co-ord, y co-ord, width, height);
        icon.fillOval(25, 25, 25, 25);
        // Set the actor's image.
        setImage(icon);
    }
}
