//Author: Karalee Mota
import greenfoot.*; 
/**
 * Write a description of class Fighter here.
 * 
 * @author Karalee Mota 
 * @version 3.0.0 5/5/17
 */
public abstract class Fighter extends Actor
{
    public void act() 
    {

    }  
    protected int vSpeed = 1;
    static protected P1 p1; //p1 label over players head
    static protected GreenfootImage rightStand1;//all images to animate
    static protected GreenfootImage rightStand2;
    static protected GreenfootImage rightStand3;
    static protected GreenfootImage leftStand1;
    static protected GreenfootImage leftStand2;
    static protected GreenfootImage leftStand3;
    static protected GreenfootImage moveRight1;
    static protected GreenfootImage moveRight2;
    static protected GreenfootImage moveRight3;
    static protected GreenfootImage moveLeft1;
    static protected GreenfootImage moveLeft2;
    static protected GreenfootImage moveLeft3;
    static protected GreenfootImage rightPunch1; 
    static protected GreenfootImage rightPunch2;
    static protected GreenfootImage rightPunch3;
    static protected GreenfootImage rightPunch4;
    static protected GreenfootImage leftPunch1;
    static protected GreenfootImage leftPunch2;
    static protected GreenfootImage leftPunch3;
    static protected GreenfootImage leftPunch4;
    protected GreenfootSound punchSound;

    protected boolean hitSpring = false;//tells when player is air borne from spring
    private int x;
    protected int lightAttackCnt = 0;//used to keep track of light attack
    protected boolean lightAttackTrue;//bool to determine if player is doing a light attack
    protected boolean specialAttackTrue;//bool to determine if player is doing s.a. so animations dont conflict
    private int moveCounter1;
    private int moveCounter2;
    private int moveCounter3;
    protected int moveSpeed; //speed each character walks
    boolean facedRight = true;
    protected boolean addedObject = false;
    public Bar healthBar;
    Fighter2 opponent;
    SelectCharacter backCharacterWorld = IntroScreen.selectCharacterScreen;//char sel screen is reused
    protected boolean jumped = false;//tells when player has jumped and not yet touched ground
    int groundHeight = getImage().getHeight()/2;//How far it is from the middle of the actor to the bottom  
    public Fighter()
    {

    }

    public Fighter(int health)
    {

    }

    protected void jump(int height)
    {
        if(Greenfoot.isKeyDown("w")) 
        {
            setLocation(10,10);
            vSpeed = -1*height;//negative gravity speed will make fighter go up
        }
    }

    protected void moveRight(int speed)
    {
        if(Greenfoot.isKeyDown("d") && !Greenfoot.isKeyDown("p") && !lightAttackTrue && !specialAttackTrue)
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

    protected void moveLeft(int speed)
    {
        if(Greenfoot.isKeyDown("a") && !Greenfoot.isKeyDown("p") && !lightAttackTrue && !specialAttackTrue)
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

    protected  void lightAttack(int speed, int damage)
    {
        if(Greenfoot.isKeyDown("p"))
        {         
            lightAttackTrue = true;
        }
        if(isFacedRight() && lightAttackTrue)
        {
            moveCounter2++;                
            if (moveCounter2 == speed)
            {
                setImage(rightPunch1);
            }
            else if (moveCounter2 == (speed*2) )
            {
                setImage(rightPunch2);
            }
            else if(moveCounter2 == (speed*3) )
            {
                setImage(rightPunch3);
            }
            else if(moveCounter2 == (speed*4))
            {
                setImage(rightPunch4);
                opponent = (Fighter2)getOneIntersectingObject(Fighter2.class);
                if(opponent!=null)
                {
                    opponent.healthBar.subtract(damage);
                    punchSound.play();
                    //faced right punch, so push opponent to the right
                    opponent.setLocation(opponent.getX()+4,opponent.getY());
                }   
                moveCounter2 = 0;//punch complete
                lightAttackTrue =false;
            }         
        }
        else if(!isFacedRight() && lightAttackTrue)
        {
            moveCounter2++;               
            if (moveCounter2 == speed)
            {
                setImage(leftPunch1);
            }
            else if (moveCounter2 == (speed*2) )
            {
                setImage(leftPunch2);
            }
            else if(moveCounter2 == (speed*3) )
            {
                setImage(leftPunch3);
            }
            else if(moveCounter2 == (speed*4))
            {
                setImage(leftPunch4);
                opponent = (Fighter2)getOneIntersectingObject(Fighter2.class);
                if(opponent!=null)
                {           
                    opponent.healthBar.subtract(damage);
                    punchSound.play();
                    //faced left punch, so push opponent to the left
                    opponent.setLocation(opponent.getX()-4,opponent.getY());
                }   
                moveCounter2 = 0;//punch complete
                lightAttackTrue =false;
            }         
        }
    }

    public abstract void specialAttack();

    public boolean isOnGround()//checks if character is touching the ground
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
            Actor platformBelow = getOneObjectAtOffset(0,((getImage().getHeight()/2)+3),Platform.class);
            GreenfootImage platformImage = platformBelow.getImage();    //Get the platform's image
            int topOfPlatform = platformBelow.getY()-platformImage.getHeight()/2+5;
            int newY = topOfPlatform - groundHeight; 
            setLocation (getX(), newY);
            vSpeed = 0;
        }
    }

    protected void block()
    {

    }

    public boolean isFacedRight()
    {
        if(Greenfoot.isKeyDown("d"))
        {
            facedRight = true;
        }
        else if(Greenfoot.isKeyDown("a"))
        {
            facedRight = false;
        }
        return facedRight;
    }

    protected void animate()
    {
        x = x+1;
        if (x == 8)
        {
            if(isOnGround() && isFacedRight() && !Greenfoot.isKeyDown("d") && !Greenfoot.isKeyDown("p")
            && !Greenfoot.isKeyDown("a") && !lightAttackTrue && !specialAttackTrue)
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

            else if(isOnGround() && !isFacedRight() && !Greenfoot.isKeyDown("a") && !Greenfoot.isKeyDown("p")
            && !Greenfoot.isKeyDown("d") && !lightAttackTrue && !specialAttackTrue)
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

    protected void fallOffEdge()
    {
        if(getY() > 510 )
        {
            healthBar.setValue(0);
        }
    }

    public void Player2Wins()
    {
        FightWorld w = (FightWorld)getWorld();
        if(healthBar.getValue() == 0)
        {
            if(addedObject == false)
            {
                w.addObject(new Player2Wins(),342,256);
                addedObject = true; 
            }
            if(Greenfoot.isKeyDown("enter"))
            {
                Greenfoot.playSound("enter.wav");
                w.stopMusic();
                Greenfoot.setWorld(backCharacterWorld);
                backCharacterWorld.setEnterCounter(110);
            }

        }
    }

    public void checkForSpring()
    {
        Spring spring = getOneObjectAtOffset(0,((getImage().getHeight()/2)+3),Spring.class);
        if(spring != null)//there is spring below fighter's feet
        {
            hitSpring = true;
            spring.sprung = true;//make spring do spring action
            vSpeed = -23;//reverse gravity to push player up
            jumped = false;
        }
        if(isOnGround())
        {
            hitSpring = false;//touched ground after being sprung
        }
    }

    public void checkForObjects()//checks for objects like springs,rings,ect..
    {
        checkForSpring();
    }

    public int getVSpeed()//returns 'gravity' speed of fighter
    {
        return vSpeed;
    }

    protected abstract void labelFollow();
}
