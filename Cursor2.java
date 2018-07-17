import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cursor2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cursor2 extends Actor
{
    static GreenfootImage cursor2Image = new GreenfootImage("p2circle.PNG");
    public void act() 
    {
    }    
    public Cursor2()
    {
        setImage(cursor2Image); 
    }
    public CharacterPortrait getP2Cursor()
    {
       CharacterPortrait characterPortrait = (CharacterPortrait)getOneIntersectingObject(CharacterPortrait.class);
       return characterPortrait;
    }
}
