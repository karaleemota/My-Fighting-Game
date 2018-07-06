import greenfoot.*;
 //y 27 units apart
/**
 * Write a description of class GameWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class World1 extends World
{
    private GreenfootSound bgmusic;
    public World1()
    {    
       super(600, 450, 1); 
       //addObject(new PlatformTop(),304,306);
       addObject(new PlatformBottom(),304,333);
       bgmusic = new GreenfootSound("brawlMenu1.mp3");
       startMusic();
    }
    public void startMusic()
    {
        bgmusic.setVolume(65);
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
