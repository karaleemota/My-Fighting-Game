import greenfoot.*;
 
/**
 * Write a description of class Fighter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Fighter2 extends Actor
{
    public void act() 
    {
        
    }  
    int vSpeed = 1;
    protected P2 p2; //p2 label over players head
    protected GreenfootImage rightStand1;
    protected GreenfootImage rightStand2;
    protected GreenfootImage rightStand3;
    protected GreenfootImage leftStand1;
    protected GreenfootImage leftStand2;
    protected GreenfootImage leftStand3;
    protected GreenfootImage moveRight1;
    protected GreenfootImage moveRight2;
    protected GreenfootImage moveRight3;
    protected GreenfootImage moveLeft1;
    protected GreenfootImage moveLeft2;
    protected GreenfootImage moveLeft3;
    protected GreenfootImage rightPunch1;
    protected GreenfootImage rightPunch2;
    protected GreenfootImage rightPunch3;
    protected GreenfootImage rightPunch4;
    protected GreenfootImage leftPunch1;
    protected GreenfootImage leftPunch2;
    protected GreenfootImage leftPunch3;
    protected GreenfootImage leftPunch4;
    protected GreenfootSound punchSound;
    private int x;
    private int moveCounter1;
    private int moveCounter2;
    private int moveCounter3;
    protected int moveSpeed; //speed each character walks
    boolean facedRight = true;
    boolean gameOver = false;
    protected boolean addedObject = false;
    public Bar healthBar;
    Fighter opponent;
    World backCharacterWorld = new SelectCharacter();
    int groundHeight = getImage().getHeight()/2;//How far it is from the middle of the actor to the bottom
    public Fighter2()
    {
        
    }
    public Fighter2(int health)
    {
        
    }
    public void jump(int height)
    {
          if(Greenfoot.isKeyDown("up"))
          {
              setLocation(getX(),getY()-height);
          }
    }
    public void moveRight(int speed)
    {
        if(Greenfoot.isKeyDown("right") && !Greenfoot.isKeyDown("."))
        {
           setLocation(getX()+speed,getY());
           if(isOnGround() && isFacedRight())
            {
                moveCounter1++;
                if(moveCounter1 == speed)
                {
                    if (getImage() == rightStand1 || getImage() == rightStand2 || getImage() == rightStand3)
                    {
                        setImage(moveRight1);
                    }
                    else if (getImage() == moveRight1 )
                    {
                        setImage(moveRight2);
                    }
                    else if(getImage() == moveRight2 )
                    {
                        setImage(moveRight3);
                    }
                    else if ( getImage() != moveRight1 )
                    {
                        setImage(moveRight1);
                    }
                    moveCounter1 = 0;
                }
            }
        }
    }
    public void moveLeft(int speed)
    {
        if(Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown("."))
        {
            setLocation(getX()-speed,getY());
            if(isOnGround() && isFacedRight()==false)
            {
                moveCounter1++;
                if(moveCounter1 == speed)
                {
                    if (getImage() == leftStand1 || getImage() == leftStand2 || getImage() == leftStand3)
                    {
                        setImage(moveLeft1);
                    }
                    else if (getImage() == moveLeft1 )
                    {
                        setImage(moveLeft2);
                    }
                    else if(getImage() == moveLeft2 )
                    {
                        setImage(moveLeft3);
                    }
                    else if ( getImage() != moveLeft1 )
                    {
                        setImage(moveLeft1);
                    }
                    moveCounter1 = 0;
                }
            }
        }
    }
    public  void lightAttack(int speed, int damage)
    {
        if(Greenfoot.isKeyDown("."))
        {
            opponent = (Fighter)getOneIntersectingObject(Fighter.class);
            if(isFacedRight())
            {
                moveCounter2++;
                if(moveCounter2 == speed)
                {
                    if (getImage() == rightStand1 || getImage() == rightStand2 || getImage() == rightStand3
                         || getImage() == moveRight1 || getImage()==moveRight2 || getImage()==moveRight3)
                    {
                        setImage(rightPunch1);
                    }
                    else if (getImage() == rightPunch1 )
                    {
                        setImage(rightPunch2);
                    }
                    else if(getImage() == rightPunch2 )
                    {
                        setImage(rightPunch3);
                    }
                    else if(getImage() == rightPunch3)
                    {
                        setImage(rightPunch4);
                        if(opponent!=null)
                        {
                            opponent.healthBar.subtract(damage);
                            punchSound.play();
                        }
                    }
                    else if ( getImage() != rightPunch1 )
                    {
                        setImage(rightPunch1);
                    }
                    moveCounter2 = 0;
                }
            }
            else if(!isFacedRight())
            {
                moveCounter2++;
                if(moveCounter2 == speed)
                {
                    if (getImage() == leftStand1 || getImage() == leftStand2 || getImage() == leftStand3
                         || getImage() == moveLeft1 || getImage()==moveLeft2 || getImage()==moveLeft3)
                    {
                        setImage(leftPunch1);
                    }
                    else if (getImage() == leftPunch1 )
                    {
                        setImage(leftPunch2);
                    }
                    else if(getImage() == leftPunch2 )
                    {
                        setImage(leftPunch3);
                    }
                    else if(getImage() == leftPunch3)
                    {
                        setImage(leftPunch4);
                        if(opponent!=null)
                        {
                            opponent.healthBar.subtract(damage);
                            punchSound.play();
                        }
                    }
                    else if ( getImage() != leftPunch1 )
                    {
                        setImage(leftPunch1);
                    }
                    moveCounter2 = 0;
                }
            }
        }
    }
    public abstract void heavyAttack();

    public boolean isOnGround()//checks if character is touching the ground
    {
        if(getOneObjectAtOffset(0,(getImage().getHeight()/2)-2,Platform.class)!=null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void applyGravity()
    {   
        if(!isOnGround())
        { 
            setLocation (getX(), getY() + vSpeed);
            vSpeed += 1;
            if(vSpeed % 6 == 0)
            {
                vSpeed = vSpeed--;
            }
        }
        else
        {
           Actor platformBelow = getOneObjectAtOffset(0,(getImage().getHeight()/2)-2,Platform.class);
            GreenfootImage platformImage = platformBelow.getImage();                //Get the platform's image
            int topOfPlatform = platformBelow.getY()-platformImage.getHeight()/2+5;
            int newY = topOfPlatform - groundHeight; 
            setLocation (getX(), newY);
            vSpeed = 0;
        }
    }
    
    public void block()
    {
        
    }
    
    public boolean isFacedRight()
    {
        if(Greenfoot.isKeyDown("right"))
        {
            facedRight = true;
        }
        else if(Greenfoot.isKeyDown("left"))
        {
            facedRight = false;
        }
        return facedRight;
    }
    
    public void animate()
    {
        x = x+1;
        if (x == 8)
        {
            if(isOnGround() && isFacedRight() && !Greenfoot.isKeyDown("right") && !Greenfoot.isKeyDown(".")
               && !Greenfoot.isKeyDown("left"))
            {
                if (getImage() == rightStand1 )
                {
                    setImage(rightStand2);
                }
                else if (getImage() == rightStand2 )
                {
                    setImage(rightStand3);
                }
                else if ( getImage() != rightStand1 )
                {
                    setImage(rightStand1);
                }
            }
            
            else if(isOnGround() && !isFacedRight() && !Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown(".")
                    && !Greenfoot.isKeyDown("right"))
            {
                if (getImage() == leftStand1 )
                {
                    setImage(leftStand2);
                }
                else if (getImage() == leftStand2 )
                {
                    setImage(leftStand3);
                }
                else if ( getImage() != leftStand1 )
                {
                    setImage(leftStand1);
                }
            }
            x = 0;
        }
    }
    public void fallOffEdge()
    {
        if(getY() > 447 )
        {
            healthBar.setValue(0);
        }
    }
    public void Player1Wins()
    {
        World1 w = (World1)getWorld();
        if(healthBar.getValue() == 0)
        {
            if(addedObject == false)
            {
                w.addObject(new Player2Wins(),300,250);
                addedObject = true;
            }
            if(Greenfoot.isKeyDown("enter"))
            {
                Greenfoot.playSound("enter.wav");
                w.stopMusic();
                Greenfoot.setWorld(backCharacterWorld);
            }
        }
        }
    
    public abstract void labelFollow();
}
