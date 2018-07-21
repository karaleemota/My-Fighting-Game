import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class World3 here. 
 * 
 * @author Karalee Mota
 * @version 7/18/18
 */
public class World3 extends FightWorld
{
    //Sonic Advance 3 Sunset Hill
    public static GreenfootSound sunsetHillMusic = new GreenfootSound("sunsetHill.wav");
    public static GreenfootImage sunsetHillBG = new GreenfootImage("sunsetHillBackground.png");
    
    static Platform sunsetHillTopPlatform = new SunsetHillPlatform2();
    static Platform sunsetHillBottomPlatform1 = new SunsetHillPlatform0();
    static Platform sunsetHillBottomPlatform2 = new SunsetHillPlatform0();
    static Actor sunsetHillBarrier = new SunsetHillBarrier();
    static Spring spring = new Spring();
    static PalmTree palmTree = new PalmTree();
    
    Ring ring1 = new Ring();
    Ring ring2 = new Ring();
    Ring ring3 = new Ring();
    public World3()
    {
       super(684, 513, 1);
       addObject(sunsetHillBarrier,626,145); 
       addObject(palmTree,118,325);
       addObject(new SunsetHillCheckers(),658,290);       
       addObject(new SunsetHillCheckers(),658,395);
       addObject(new SunsetHillCheckers(),658,500);
       addObject(new SunsetHillCheckers(),517,290);
       addObject(new SunsetHillCheckers(),517,395);
       addObject(new SunsetHillCheckers(),517,500); 
       addObject(sunsetHillTopPlatform,623,212);
       addObject(sunsetHillBottomPlatform1,200,499);
       addObject(sunsetHillBottomPlatform2,468,499);
       addObject(spring,356,429); 
       addObject(ring1,500,154);
       addObject(ring2,580,154);
       addObject(ring3,660,154); 
       bgmusic = sunsetHillMusic; 
       startMusic();
    } 
}
