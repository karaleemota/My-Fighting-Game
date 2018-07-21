import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math;//math needed to calculate angle bomb is thrown at
 
/**
 * Write a description of class Bomb here.
 * 
 * @author Karalee Mota
 * @version 7/3/18
 */
public class Bomb extends Actor
{
    /**
     * Act - do whatever the Bomb wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    static protected GreenfootImage bombRight1 = new GreenfootImage("bombRight1.png");
    static protected GreenfootImage bombRight2 = new GreenfootImage("bombRight2.png");
    static protected GreenfootImage bombLeft1 = new GreenfootImage("bombLeft1.png");
    static protected GreenfootImage bombLeft2 = new GreenfootImage("bombLeft2.png");
    static protected GreenfootImage bombExplosion = new GreenfootImage("bombExplosion.png");
    static protected GreenfootSound explosionSound = new GreenfootSound("bombExplosion.wav");
    
    private int animateTimer;//used to slow or speed up animation
    private int animateExplosionTimer;//used to slow down animation of bomb exploding so its visible
    private boolean facedRight;//determines which way bomb is facing to animate it
    private int ySpeed = 1;//used for acceleration of gravity in y direction
    private int xSpeed = 0;//used for speed in x direction
    int groundHeight = getImage().getHeight()/2;//How far it is from the middle of the actor to the bottom 
    private boolean removed;//tells when to remove this object from world
    Fighter2 opponent;//oppenent is player bomb can inflict damage on
    private boolean exploded;//tells if bomb has exploded
    private boolean explosionSoundPlayed = false;

    public void act() 
    {
        hitOpponent();
        applyGravity();
        animate();
        checkBorder();
        explode();//allows bomb to explode when exploded = true
    }    
    public Bomb()
    {
        facedRight = true;
        animateTimer = 0;
        setImage(bombRight1);
        removed = false;
        exploded = false;
    }
    public Bomb(boolean facedRight_)//takes bool to determine which way bomb is facing
    {
        facedRight = facedRight_;
        animateTimer = 0;
        removed = false;
        exploded = false;
        animateExplosionTimer = 0;
        if(facedRight_ == true)
        {
            setImage(bombRight1);
        }
        else
        {
            setImage(bombLeft1);
        }
    }
    //this constructor takes a bool that determines which way the bomb is facing, the angle
    // its thrown at, and the speed it is intially going at. Used in link's class
    //angle should be between 0 and 90 degrees, velocity should be positive
    public Bomb(boolean facedRight_, int angle_, int velocity_)
    {
        facedRight = facedRight_;
        animateTimer = 0;
        removed = false;
        exploded = false;
        animateExplosionTimer = 0;
        int angle = angle_;
        int velocity = velocity_;
        if(angle < 0 || angle > 90)//angle given was out of bounds
        {
            angle = 0;
        }
        if(velocity < 0)//velocity given was negative
        {
            velocity *= -1;
        }
        ySpeed = (int)(Math.sin(Math.toRadians(angle))*velocity);//speed bomb will rise up
        xSpeed = (int)(Math.cos(Math.toRadians(angle))*velocity);//x direction speed bomb will travel
        if(facedRight_ == true)
        {
            setImage(bombRight1);
        }
        else
        {
            xSpeed *= -1;//make xspeed go in left direction
            setImage(bombLeft1);
        }
    }
    public boolean isFacedRight()
    {
        return facedRight;
    }
    public boolean isOnGround()//checks if bobm is touching the ground
    {
        if(getOneObjectAtOffset(0,(getImage().getHeight()/2)+3,Platform.class)!=null)
        {
            exploded = true;//explode bomb when hitting ground
            return true;
        }
        else
        {
            return false;
        }
    }
    protected void applyGravity()//includes x direction velocity bomb is moving
    {   
        if(removed == false)
        {
            if(!isOnGround())
            { 
                setLocation (getX() + xSpeed, getY() + ySpeed);
                ySpeed += 1;//accelerate
                if(ySpeed % 6 == 0)
                {//buffer acceleration
                    ySpeed = ySpeed--;
                }
            }
        }
    }
    public void removeMe()//remove bomb from world
    {
        removed = true;
        getWorld().removeObject(this);
    }
    public void checkBorder()//removes bomb if it hits end of world
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
            else if(getY() == 511)
            {
                removeMe();
                removed = true;
            }
        }
    }
    protected void hitOpponent()
    {
        if(removed == false && exploded == false)
        {
            opponent = (Fighter2)getOneIntersectingObject(Fighter2.class);
            if(opponent!=null)
            {
                 opponent.healthBar.subtract(2);
                 exploded = true;
            }
        }
    }
    protected void explode()
    {
        if(exploded == true)
        {
            if(explosionSoundPlayed == false)
            {
                setImage(bombExplosion);
                explosionSound.play();
                explosionSoundPlayed = true;
            }
            animateExplosionTimer++;
        }
        if(animateExplosionTimer == 4)
        {
            removed = true;
            removeMe();
            animateExplosionTimer = 0;
        }
    }
    private void animate()//animates the bomb
    {
        if(removed == false)
        {
            animateTimer += 1;
            if(animateTimer == 5)//only animate every 8 counts
            {
                if(isFacedRight())//animate bomb facing right
                {
                    if(getImage() == bombRight1)
                    {
                        setImage(bombRight2);
                    }
                    else if(getImage() == bombRight2)
                    {
                        setImage(bombRight1);
                    }
                }
                else//bomb is faced left
                {
                    if(getImage() == bombLeft1)
                    {
                        setImage(bombLeft2);
                    }
                    else if(getImage() == bombLeft2)
                    {
                        setImage(bombLeft1);
                    }
                }
                animateTimer = 0;
            }
        }
    }
}
