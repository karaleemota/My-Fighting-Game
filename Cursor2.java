import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cursor2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cursor2 extends Actor
{
    /**
     * Act - do whatever the Cursor2 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
    }    
    public CharacterPortrait getP2Cursor()
    {
       CharacterPortrait characterPortrait = (CharacterPortrait)getOneIntersectingObject(CharacterPortrait.class);
       return characterPortrait;
    }
}
