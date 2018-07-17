import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FightWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
abstract public class FightWorld extends World
{
    protected GreenfootSound bgmusic;
    //constrcutor is the same as the World class one, but I had to write my own cause 
    //it couldnt be inherited thru grandparent class :(
    public FightWorld(int x, int y, int cellSize)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(x, y, cellSize); 
    }
    public void startMusic()
    {
        bgmusic.setVolume(60);
        bgmusic.playLoop();
    }
    public GreenfootSound getbgmusic()
    {
        return bgmusic;
    }   
    public void stopMusic()
    {
        bgmusic.stop();
    }
}
