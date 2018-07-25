import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Blast here.
 * 
 * @author Karalee 
 * @version 7/8/18
 */
public class Blast2 extends Actor
{
    static protected GreenfootImage blastRight1 = new GreenfootImage("blastRight1.png");
    static protected GreenfootImage blastRight2 = new GreenfootImage("blastRight2.png");
    static protected GreenfootImage blastLeft1 = new GreenfootImage("blastLeft1.png");
    static protected GreenfootImage blastLeft2 = new GreenfootImage("blastLeft2.png");
    static protected GreenfootImage blastHit1 = new GreenfootImage("blastHit1.png");
    static protected GreenfootImage blastHit2 = new GreenfootImage("blastHit2.png");
    static protected GreenfootImage blastHit3 = new GreenfootImage("blastHit3.png");
    protected GreenfootImage blast1Image;//image will be determined based on which way
    protected GreenfootImage blast2Image;//blast is facing
    private boolean removed;
    private boolean hitObject;//tells if blast hit object
    int speed = 0;
    Fighter opponent;
    char x = 0; //used to slow down blast hitting animation
    char animateTimer = 0;//used to time animation
    /**
     * Act - do whatever the Blast wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move(speed);
        checkBorder();
        hit();
        animate();
    }    

    public Blast2(int inspeed)//similar to bullet class
    {
        speed = inspeed;
        removed = false;
        hitObject = false;
        if(speed > 0 )//faced right
        {
            blast1Image = blastRight1;
            blast2Image = blastRight2;
            setImage(blast1Image);
        }
        else if(speed < 0)//faced left
        {
            blast1Image = blastLeft1;
            blast2Image = blastLeft2;
            setImage(blast1Image);
        }
    }

    public void removeMe()//remove blast from world
    {
        getWorld().removeObject(this);
        removed = true;
    }

    public void checkBorder()//removes blast if it hits end of world
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
            opponent = (Fighter)getOneIntersectingObject(Fighter.class);
            if(x == 7){
                if(opponent!=null && !hitObject)
                {
                    hitObject = true;
                    if(getImage() == blastRight1 || getImage() == blastLeft1
                    || getImage() == blastRight2 || getImage() == blastLeft2)
                    {
                        setImage(blastHit1);
                        opponent.healthBar.subtract(2);
                        speed = 0; 
                    }
                }
                if( getImage() == blastHit1)
                {
                    setImage(blastHit2);
                }
                else if( getImage() == blastHit2)
                {
                    setImage(blastHit3);
                }
                else if(getImage() == blastHit3)
                {
                    removeMe();
                }
                x = 0;
            }
        }
    }

    private void animate()
    {
        animateTimer++;
        if(!hitObject && animateTimer >= 4)
        {
            if(getImage() == blast1Image)
            {
                setImage(blast2Image);
            }
            else
            {
                setImage(blast1Image);
            }
            animateTimer = 0;
        }
    }
}
