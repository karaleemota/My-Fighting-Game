import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class World2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class World2 extends FightWorld
{
    //dragon ball fighting arena
    public static GreenfootSound world2Music = new GreenfootSound("dragonBallBGMusic.mp3");
    public World2()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 450, 1);
        bgmusic = world2Music;
        addObject(new TourneyArenaPlatformTop(), 311, 415);
        startMusic();
    }
}
