import greenfoot.*;
 //y 27 units apart
/**
 * Write a description of class GameWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class World1 extends FightWorld
{
    public static GreenfootSound world1Music = new GreenfootSound("brawlMenu1.mp3");
    public World1()
    {    
       super(600, 450, 1); 
       //addObject(new PlatformTop(),304,306);
       addObject(new PlatformBottom(),304,333);
       bgmusic = world1Music;
       startMusic();
    }
}
