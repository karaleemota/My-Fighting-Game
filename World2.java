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
    public static GreenfootSound world2Music = new GreenfootSound("dragonBallBGMusic.wav");
    public static GreenfootImage world2BG = new GreenfootImage("tournamentArena.png");
    public World2()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(684, 513, 1);
        bgmusic = world2Music;
        addObject(new TourneyArenaPlatformTop(), 354, 469);
        startMusic();
    }
}
