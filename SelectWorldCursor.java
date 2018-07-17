import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SelectWorldCursor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SelectWorldCursor extends Actor
{
    static GreenfootImage selectWorldCursor = new GreenfootImage("targetCursor.PNG");
    public void act() 
    {
        // Add your action code here.
    }    
    public SelectWorldCursor()
    {
        setImage(selectWorldCursor);
    }
    public WorldPortrait getWorldCursor()
    {//returns world portrait cursor is touching
       WorldPortrait worldPortrait = (WorldPortrait)getOneIntersectingObject(WorldPortrait.class);
       return worldPortrait;
    }
}
