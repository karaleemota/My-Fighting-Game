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
    static GreenfootImage rightJump1 = new GreenfootImage("knucklesRightJump1.png");
    static GreenfootImage rightJump2 = new GreenfootImage("knucklesRightJump2.png");
    static GreenfootImage rightJump3 = new GreenfootImage("knucklesRightJump3.png");
    static GreenfootImage leftJump1 = new GreenfootImage("knucklesLeftJump1.png");
    static GreenfootImage leftJump2 = new GreenfootImage("knucklesLeftJump2.png");
    static GreenfootImage leftJump3 = new GreenfootImage("knucklesLeftJump3.png"); 
    static GreenfootImage knucklesRightStand1 = new GreenfootImage("knucklesRightStand1.gif");
    static GreenfootImage knucklesRightStand2 = new GreenfootImage("knucklesRightStand2.gif");
    static GreenfootImage knucklesRightStand3 = new GreenfootImage("knucklesRightStand3.gif");
    static GreenfootImage knucklesLeftStand1 = new GreenfootImage("knucklesLeftStand1.gif");
    static GreenfootImage knucklesLeftStand2 = new GreenfootImage("knucklesLeftStand2.gif");
    static GreenfootImage knucklesLeftStand3 = new GreenfootImage("knucklesLeftStand3.gif");
    static GreenfootImage knucklesMoveRight1 = new GreenfootImage("knucklesMoveRight1.gif");
    static GreenfootImage knucklesMoveRight2 = new GreenfootImage("knucklesMoveRight2.gif");
    static GreenfootImage knucklesMoveRight3 = new GreenfootImage("knucklesMoveRight3.gif");
    static GreenfootImage knucklesMoveLeft1 = new GreenfootImage("knucklesMoveLeft1.gif");
    static GreenfootImage knucklesMoveLeft2 = new GreenfootImage("knucklesMoveLeft2.gif");
    static GreenfootImage knucklesMoveLeft3 = new GreenfootImage("knucklesMoveLeft3.gif");
    static GreenfootImage knucklesRightPunch1 = new GreenfootImage("knucklesRightPunch1.gif");
    static GreenfootImage knucklesRightPunch2 = new GreenfootImage("knucklesRightPunch2.gif");
    static GreenfootImage knucklesRightPunch3 = new GreenfootImage("knucklesRightPunch3.gif");
    static GreenfootImage knucklesRightPunch4 = new GreenfootImage("knucklesRightPunch4.gif");
    static GreenfootImage knucklesLeftPunch1 = new GreenfootImage("knucklesLeftPunch1.gif");
    static GreenfootImage knucklesLeftPunch2 = new GreenfootImage("knucklesLeftPunch2.gif");
    static GreenfootImage knucklesLeftPunch3 = new GreenfootImage("knucklesLeftPunch3.gif");
    static GreenfootImage knucklesLeftPunch4 = new GreenfootImage("knucklesLeftPunch4.gif");
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
        rightStand1 = knucklesRightStand1;
        rightStand2 = knucklesRightStand2;
        rightStand3 = knucklesRightStand3;
        leftStand1 = knucklesLeftStand1;
        leftStand2 = knucklesLeftStand2;
        leftStand3 = knucklesLeftStand3;
        moveRight1 = knucklesMoveRight1;
        moveRight2 = knucklesMoveRight2;
        moveRight3 = knucklesMoveRight3;
        moveLeft1 = knucklesMoveLeft1;
        moveLeft2 = knucklesMoveLeft2;
        moveLeft3 = knucklesMoveLeft3;
        rightPunch1 = knucklesRightPunch1;
        rightPunch2 = knucklesRightPunch2;
        rightPunch3 = knucklesRightPunch3;
        rightPunch4 = knucklesRightPunch4;
        leftPunch1 = knucklesLeftPunch1;
        leftPunch2 = knucklesLeftPunch2;
        leftPunch3 = knucklesLeftPunch3;
        leftPunch4 = knucklesLeftPunch4;
        punchSound = new GreenfootSound("knucklesPunch.wav");

        setImage(rightStand1);
        specialAttackTrue = false;//tells when s.a key has been pressed from fighter class
        lightAttackTrue = false;//tells when l.a. key has been pressed from fighter class
        moveSpeed = 4;
        p1 = new P1();
        healthBar = (new Bar("Knuckles","HP",100,100));
    }

    public void jump(int height)
    {
        if(isOnGround() && jumped) 
        {
            jumped = false;//must have touched ground from previous jump 
        }
        if(Greenfoot.isKeyDown("w") && vSpeed > -1 && !hitSpring)
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
            jumped = true;//is in air from jumping
        }
        else if(vSpeed < 0)
        {//probably hit spring, so animate as if jumping up
            if(isFacedRight())
            {
                setImage(rightJump1);
            }
            else
            {
                setImage(leftJump1);
            }
        }
        else if(!isOnGround() && !specialAttackTrue && !lightAttackTrue && jumped == false)
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
