import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet2 extends Actor
{
    protected GreenfootImage bulletRight;
    protected GreenfootImage bulletLeft;
    protected GreenfootImage bulletHit1;
    protected GreenfootImage bulletHit2;
    protected GreenfootImage bulletHit3;
    protected boolean removed;
    int speed = 0;
    Fighter opponent;
    int x = 0; //used to slow down bullet hitting animation
    /**
     * Act - do whatever the Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move(speed);
        checkBorder();
        hit();
    }    
    public Bullet2(int inspeed)
    {
        bulletRight = new GreenfootImage("bulletRight.png");
        bulletLeft = new GreenfootImage("bulletLeft.png");
        bulletHit1 = new GreenfootImage("bulletHit1.png");
        bulletHit2 = new GreenfootImage("bulletHit2.png");
        bulletHit3 = new GreenfootImage("bulletHit3.png");
        speed = inspeed;
        removed = false;
        if(speed >= 0 )
        {
            setImage(bulletRight);
        }
        else if(speed < 0)
        {
            setImage(bulletLeft);
        }
    }
    public void removeMe()//remove bullet from world
    {
        getWorld().removeObject(this);
        removed = true;
    }
    public void checkBorder()//removes bullet if it hits end of world
    {
       if(removed == false)
       {
           if (getX() == 599)
           {
               removeMe();
               removed = true;
            }
            else if (getX() == 0)
            {
                removeMe();
                removed = true;
            }
        }
    }
    public void hit()
    {
        x = x + 1;
        if(removed == false)
        {
            opponent = (Fighter)getOneIntersectingObject(Fighter.class);
            if(x == 6){
                if(opponent!=null)
                {
                    if(getImage() == bulletRight || getImage() == bulletLeft)
                    {
                        setImage(bulletHit1);
                        opponent.healthBar.subtract(2);
                        speed = 0;
                    }
                }
                if( getImage() == bulletHit1)
                    {
                        setImage(bulletHit2);
                    }
                    else if( getImage() == bulletHit2)
                    {
                        setImage(bulletHit3);
                        removeMe();
                    }
                x = 0;
            }
        }
    }
}
