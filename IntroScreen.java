import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class IntroScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IntroScreen extends World
{
    static GreenfootImage introScreenImage = new GreenfootImage("introScreen.png");
    public IntroScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(684, 513, 1);
        setBackground(introScreenImage);
    }
    
    public void act()
    {
      
        //start the game when user presses space bar
        if (Greenfoot.isKeyDown("enter"))
        {
            Greenfoot.playSound("enter.wav");
            Greenfoot.setWorld(new SelectCharacter()); 
        }
    }
}
