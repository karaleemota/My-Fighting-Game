import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ThunderBolt here.
 * 
 * @author Karalee Mota
 * @version 7/9/18
 */
public class ThunderBolt extends Actor
{
    private boolean facedRight;//determines which way bolt will travel
    private boolean removed;//tells when to remove this object from world
    private boolean hitOpponent = false;//tells if oppenent has been hit
    private int speed;//speed bolt travels accross stage
    private int gSpeed = 4;//used for gravity
    private char animateTimer = 0;//controls speed of animations
    private char hitTimer = 0;//for hit animation
    Fighter2 opponent;//player bolt can hit
    int groundHeight = getImage().getHeight()/2;//How far it is from the middle of the actor to the bottom 
    static private GreenfootImage thunderBolt0 = new GreenfootImage("thunderBolt0.png");
    static private GreenfootImage thunderBolt1 = new GreenfootImage("thunderBolt1.png");
    static private GreenfootImage thunderBolt2 = new GreenfootImage("thunderBolt2.png");
    static private GreenfootImage thunderBolt3 = new GreenfootImage("thunderBolt3.png");
    static private GreenfootImage thunderBolt4 = new GreenfootImage("thunderBolt4.png");
    static private GreenfootImage thunderBolt5 = new GreenfootImage("thunderBolt5.png");
    static private GreenfootSound electric1Sound = new GreenfootSound("electric1.wav");

    public void act() 
    {
        applyGravity();
        move(speed);
        animate();
        checkBorder();
        hit();
    }    

    public ThunderBolt()
    {
        setImage(thunderBolt1);
        speed = 0;
        electric1Sound.setVolume(90);
        removed = false;
    }

    public ThunderBolt(int speed_)//speed bolt moves across platform
    {
        //negative speed means its faced left
        setImage(thunderBolt1);
        speed = speed_;
        removed = false;
        if(speed < 0)
        {
            facedRight = false;//faced left
        }
        else//faced right
        {
            facedRight = true;
        }
        electric1Sound.setVolume(90);//vol of sound effect
    }

    protected boolean isOnGround()//checks if character is touching the ground
    {
        if(getOneObjectAtOffset(0,(getImage().getHeight()/2)+3,Platform.class)!=null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    protected void applyGravity()
    {   
        if(removed == false)
        {
            groundHeight = getImage().getHeight()/2;//How far it is from the middle of the actor to the bottom 
            if(!isOnGround())
            { 
                setLocation (getX(), getY() + gSpeed);
            }
            else
            {
                Actor platformBelow = getOneObjectAtOffset(0,((getImage().getHeight()/2)+3),Platform.class);
                GreenfootImage platformImage = platformBelow.getImage();    //Get the platform's image
                int topOfPlatform = platformBelow.getY()-platformImage.getHeight()/2+5;
                int newY = topOfPlatform - groundHeight; 
                setLocation (getX(), newY);
                gSpeed = 0;
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
            else if(getY() == 449)
            {
                removeMe();
                removed = true;
            }
        }
    }

    public void hit()
    {
        if(removed == false)
        {
            hitTimer++;
            opponent = (Fighter2)getOneIntersectingObject(Fighter2.class);
            if(hitTimer % 6 == 0)
            {
                if(opponent!=null && !hitOpponent)
                {
                    hitOpponent = true;
                    setImage(thunderBolt0);
                    opponent.healthBar.subtract(2);
                    speed = 0;
                    hitTimer = 0;
                }
                if(hitOpponent && hitTimer >= 6)
                {
                    removeMe();
                }
            }
        }
    }

    private void animate()
    {
        animateTimer++;
        if(removed == false)
        {
            if(animateTimer == 5 && !isOnGround() && !hitOpponent)//not on ground
            {
                if(getImage() == thunderBolt1)
                {
                    setImage(thunderBolt5); 
                }
                else
                {
                    setImage(thunderBolt1); 
                }
                animateTimer = 0;
            }
            else if(animateTimer == 5 && isOnGround() && !hitOpponent)//is on ground
            {
                if(facedRight)
                {
                    if(getImage() == thunderBolt1)
                    {
                        setImage(thunderBolt2);
                    }
                    else if(getImage() == thunderBolt2)
                    {
                        electric1Sound.play();
                        setImage(thunderBolt3);
                    }
                    else if(getImage() == thunderBolt3)
                    {
                        setImage(thunderBolt4);
                    }
                    else
                    {
                        setImage(thunderBolt1);
                    }
                }
                else//faced left
                {
                    if(getImage() == thunderBolt1)
                    {
                        setImage(thunderBolt4);
                    }
                    else if(getImage() == thunderBolt4)
                    {
                        electric1Sound.play();
                        setImage(thunderBolt3);
                    }
                    else if(getImage() == thunderBolt3)
                    {
                        setImage(thunderBolt2);
                    }
                    else
                    {
                        setImage(thunderBolt1);
                    }
                }
                animateTimer = 0;
            }
        }
    }
}
