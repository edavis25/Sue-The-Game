import greenfoot.*;

import java.awt.Font;


/**
 * Write a description of class PocketLabel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PocketLabel extends Actor
{
    
    public int pocketScore;
    
    public final int MAX_AMOUNT = 10;
    
    
    public PocketLabel()
    {
              
         // Create and set new GreenfootImage for the displayed string. (width, height) 
         setImage(new GreenfootImage(150, 20));
         
         pocketScore = 0;  // Initialize pocket score to 0
         
         updatePocketScore();  // Call method to update and set image.
                          
    }
    
    
    
    /**
     * Act - do whatever the PocketLabel wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //showLabel();
        updatePocketScore();
        
    }    
    
    
    
    
    /**
     * adjustPocketScore(int x)  - Adjusts the pocket score with whatever parameter is input when called.
     */
    public void adjustPocketScore(int x)
    {
        pocketScore = pocketScore + x;
    }
         
    
    
    /**
     * getPocketScore()  - Public method used to return the Pocket Label's current score.
     */
    public int getPocketScore()
    {
        return pocketScore;
    }
    
    
    
    /**
     * getMaxAmount()  - Public method returning the constant that determines max amount allowed in pocket.
     */
    public int getMaxAmount()
    {
        return MAX_AMOUNT;
    }
    
    
    
    /**
     * updatePocketScore()  - Formats and draws the string that is shown in the main pocket Label.
     *  
     */
    private void updatePocketScore()
    {
        // Gets the created GreenfootImage and sets it into a variable.
        GreenfootImage pocketScoreImage = getImage();
                
        // Clear the old image. Prevents images stacking on one another.
        pocketScoreImage.clear();
       
        // Create variable to hold the font size.
        float fontSize = 22;
        
        // Create and store in variable a new font type using fontSize varible just created
        Font font = pocketScoreImage.getFont().deriveFont(fontSize);
        
        // Set the image's font using the variable just created
        pocketScoreImage.setFont(font);
        
        // Draw string to the image.
        pocketScoreImage.drawString("Pocket: " + pocketScore + "/" + MAX_AMOUNT, 1, 20);
        
    }
    
    
    // CURRENTLY NOT USED
    private void showLabel()
    {
        GreenfootImage scoreImage = new GreenfootImage(50, 15);
        
        scoreImage.drawString("Pocket", 10, 10);
        setImage(scoreImage);
    }
}
