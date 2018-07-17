import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class IntroScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IntroScreen extends World
{
    static GreenfootImage introScreenImage = new GreenfootImage("2.jpg");
    public IntroScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(625, 450, 1); 
        setBackground(introScreenImage);
    }
    
    public void act()
    {
      
        //start the game when user presses space bar
        if (Greenfoot.isKeyDown("space"))
        {
            Greenfoot.playSound("enter.wav");
            Greenfoot.setWorld(new SelectCharacter());
        }
    }
}
