import greenfoot.*;

import java.awt.Font;

/**
 * Write a description of class ScoreLabel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScoreLabel extends Actor
{
    
    public int totalScore;
    
    public ScoreLabel()
    {
        setImage(new GreenfootImage (200, 25));
        
        totalScore = 0;
        
        updateTotalScore();
    }
    
    
    /**
     * Act - do whatever the ScoreLabel wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        updateTotalScore();
        
    }    
         
    
    /**
     * updateTotalScore()  - Formats and draws the string in the main Score Label
     */
    private void updateTotalScore()
    {
        // Gets the created GreenfootImage and sets it into a variable.
        GreenfootImage totalScoreImage = getImage();
        
        // Clear the old image. Prevents images stacking on one another.
        totalScoreImage.clear();
        
        // Create variable to hold the font size.
        float fontSize = 30;
        
        // Create and store in variable a new font type using fontSize varible just created
        Font font = totalScoreImage.getFont().deriveFont(fontSize);
        
        // Set the image's font using the variable just created
        totalScoreImage.setFont(font);
        
        // Draw string to the image.
        totalScoreImage.drawString("Score: " + totalScore, 1, 25);
    }
    
    
     /**
     * addToScore()  - A public method used to add 1 to the total score. 
     */
    public void addToScore()
    {
        totalScore = totalScore + 1;
    }
}