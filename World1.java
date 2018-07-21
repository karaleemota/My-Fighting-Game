import greenfoot.*;
 //y 27 units apart
/**
 * Write a description of class GameWorld here.
 * 
 * @author Karalee Mota 
 * @version 6/10/18
 */
public class World1 extends FightWorld
{
    //Classic Smash Bros. Stage
    public static GreenfootSound world1Music = new GreenfootSound("brawlMenu1.wav");
    static GreenfootImage world1Background = new GreenfootImage("world1Background.jpg");
    public World1()
    {    
       super(684, 513, 1);
       //addObject(new PlatformTop(),304,306);
       addObject(new PlatformBottom(),343,406);
       setBackground(world1Background);
       bgmusic = world1Music;
       startMusic();
    }
}
