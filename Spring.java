import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Spring here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spring extends Actor
{
    //Launches character into the air
    private static GreenfootImage spring1 = new GreenfootImage("spring1.png");
    private static GreenfootImage spring2 = new GreenfootImage("spring2.png");
    private static GreenfootImage spring3 = new GreenfootImage("spring3.png");
    private static GreenfootImage spring4 = new GreenfootImage("spring4.png");
    public static GreenfootSound springSound = new GreenfootSound("spring.wav");
    public static GreenfootSound springSoundCopy = new GreenfootSound("springCopy.wav");//so sound can play simultaneously
    private boolean turn = false;//to switch between playing spring.wav and its copy

    private char animateTimer;
    public boolean sprung;//changed in fighter class, tells spring when to spring up
    public void act()
    {
        springAction();
    }    

    public Spring() 
    {
        setImage(spring1);
        sprung = false;
        animateTimer = 0;
        springSound.setVolume(100);
    }

    public void springAction()//if spring has been jumped on, animate
    {
        animateTimer++;
        if(animateTimer % 5 == 0 && sprung)
        {
            if(getImage() == spring1)
            {
               springSound.play(); 
               setImage(spring2);
               setLocation(this.getX(),this.getY()+1);//so spring stays in place when image changes
            }
            else if(getImage() == spring2)
            {
                setImage(spring3);
                setLocation(this.getX(),this.getY()-12);//so spring stays in place when image changes
            }
            else if(getImage() == spring3)
            {
                setLocation(this.getX(),this.getY()+3);
                setImage(spring4);
            } 
            else
            {
                setLocation(this.getX(),this.getY()+8);
                setImage(spring1);
                sprung = false;
            }
            animateTimer = 0;
        }
    }
}
