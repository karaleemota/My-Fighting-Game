import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet here.
 * 
 * @author Karalee Mota
 * @version (a version number or a date)
 */
public class Bullet extends Actor
{
    static protected GreenfootImage bulletRight = new GreenfootImage("bulletRight.png");
    static protected GreenfootImage bulletLeft = new GreenfootImage("bulletLeft.png");
    static protected GreenfootImage bulletHit1 = new GreenfootImage("bulletHit1.png");
    static protected GreenfootImage bulletHit2 = new GreenfootImage("bulletHit2.png");
    static protected GreenfootImage bulletHit3 = new GreenfootImage("bulletHit3.png");
    private boolean removed;
    int speed = 0;
    Fighter2 opponent;
    char x = 0; //used to slow down bullet hitting animation
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

    public Bullet(int inspeed)
    {
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
            if (getX() == 683)
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
        x++;
        if(removed == false)
        {
            opponent = (Fighter2)getOneIntersectingObject(Fighter2.class);
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
