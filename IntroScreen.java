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
    static SelectCharacter selectCharacterScreen = new SelectCharacter();//used in future to select character
    public IntroScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(684, 513, 1);
        setBackground(introScreenImage); 
    }
    
    public void act()
    {
      
        //start the game when user presses enter
        if (Greenfoot.isKeyDown("enter"))
        {
            Greenfoot.playSound("enter.wav");
            selectCharacterScreen.setEnterCounter(110);
            Greenfoot.setWorld(selectCharacterScreen); 
        }
    }
}
