import greenfoot.*;

/**
 * Write a description of class Knuckles1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Knuckles extends Fighter
{
    private int moveCounter1 = 0;
    static GreenfootImage rightJump1;
    static GreenfootImage rightJump2;
    static GreenfootImage rightJump3;
    static GreenfootImage leftJump1;
    static GreenfootImage leftJump2;
    static GreenfootImage leftJump3;
    public void act() 
    {
        groundHeight = getImage().getHeight()/2;//update image to use for gravity in fighter class
        applyGravity();
        animate();
        jump(10);
        lightAttack(5,4);
        moveRight(moveSpeed);
        moveLeft(moveSpeed);
        labelFollow();
        fallOffEdge();
        Player2Wins();
        checkForObjects();
    }    

    public Knuckles()
    {
        rightStand1 = new GreenfootImage("knucklesRightStand1.gif");
        rightStand2 = new GreenfootImage("knucklesRightStand2.gif");
        rightStand3 = new GreenfootImage("knucklesRightStand3.gif");
        leftStand1 = new GreenfootImage("knucklesLeftStand1.gif");
        leftStand2 = new GreenfootImage("knucklesLeftStand2.gif");
        leftStand3 = new GreenfootImage("knucklesLeftStand3.gif");
        moveRight1 = new GreenfootImage("knucklesMoveRight1.gif");
        moveRight2 = new GreenfootImage("knucklesMoveRight2.gif");
        moveRight3 = new GreenfootImage("knucklesMoveRight3.gif");
        moveLeft1 = new GreenfootImage("knucklesMoveLeft1.gif");
        moveLeft2 = new GreenfootImage("knucklesMoveLeft2.gif");
        moveLeft3 = new GreenfootImage("knucklesMoveLeft3.gif");
        rightPunch1 = new GreenfootImage("knucklesRightPunch1.gif");
        rightPunch2 = new GreenfootImage("knucklesRightPunch2.gif");
        rightPunch3 = new GreenfootImage("knucklesRightPunch3.gif");
        rightPunch4 = new GreenfootImage("knucklesRightPunch4.gif");
        leftPunch1 = new GreenfootImage("knucklesLeftPunch1.gif");
        leftPunch2 = new GreenfootImage("knucklesLeftPunch2.gif");
        leftPunch3 = new GreenfootImage("knucklesLeftPunch3.gif");
        leftPunch4 = new GreenfootImage("knucklesLeftPunch4.gif");
        rightJump1 = new GreenfootImage("knucklesRightJump1.png");
        rightJump2 = new GreenfootImage("knucklesRightJump2.png");
        rightJump3 = new GreenfootImage("knucklesRightJump3.png");
        leftJump1 = new GreenfootImage("knucklesLeftJump1.png");
        leftJump2 = new GreenfootImage("knucklesLeftJump2.png");
        leftJump3 = new GreenfootImage("knucklesLeftJump3.png");
        punchSound = new GreenfootSound("knucklesPunch.wav");

        setImage(rightStand1);
        specialAttackTrue = false;//tells when s.a key has been pressed from fighter class
        moveSpeed = 4;
        p1 = new P1();
        healthBar = (new Bar("Knuckles","HP",100,100));
    }

    public void jump(int height)
    {
        
        if(Greenfoot.isKeyDown("w") )
        {
            if(isFacedRight())
            {
                moveCounter1++;
                if(moveCounter1 == 6 )
                {
                    if ((getImage()  != rightJump1 && getImage() !=rightJump2 && getImage() != rightJump3) || isOnGround())
                    {
                        setImage(rightJump1);
                    }
                    else if (getImage() == rightJump1 )
                    {
                        setImage(rightJump2);
                    }
                    else if(getImage() == rightJump2 )
                    {
                        setImage(rightJump3);
                    }
                    else if ( getImage() != rightJump3 )
                    {
                        setImage(rightJump1);
                    }
                    moveCounter1 = 0;
                }
            }
            else if(!isFacedRight())
            {
                moveCounter1++;
                if(moveCounter1 == 6 )
                {
                    if ((getImage()  != leftJump1 && getImage() !=leftJump2 && getImage() != leftJump3) || isOnGround())
                    {
                        setImage(leftJump1);
                    }
                    else if (getImage() == leftJump1 ) 
                    {
                        setImage(leftJump2);
                    }
                    else if(getImage() == leftJump2 )
                    {
                        setImage(leftJump3);
                    }
                    else if ( getImage() != leftJump3 )
                    {
                        setImage(leftJump1);
                    }
                    moveCounter1 = 0;
                }
            }
            setLocation(getX(),getY()-height);
        }
        else if(!isOnGround() && !specialAttackTrue && !lightAttackTrue && getImage()!=rightJump3 && getImage()!=leftJump3)
        {//animate falling off ledge
            if(isFacedRight())
            {
                setImage(rightJump2);
            }
            else
            {
                setImage(leftJump2);
            }
        }
    }

    protected void addedToWorld(World world)
    {
        world.addObject(healthBar, 125, 30);
        world.addObject(p1,getX(),getY()-getImage().getHeight()/2-15);
    }

    public Bar getHealthBar()
    {
        return healthBar;
    }

    public void specialAttack()
    {
    }

    public void labelFollow()
    {
        p1.setLocation(this.getX(),this.getY()-getImage().getHeight()/2-15);
    }
}
