import greenfoot.*;

/**
 * Write a description of class Sonic here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sonic extends Fighter
{
    //images
    static GreenfootImage sonicRightStand1 = new GreenfootImage("sonicRightStand1.gif");
    static GreenfootImage sonicRightStand2 = new GreenfootImage("sonicRightStand2.gif");
    static GreenfootImage sonicRightStand3 = new GreenfootImage("sonicRightStand3.gif");
    static GreenfootImage sonicLeftStand1 = new GreenfootImage("sonicLeftStand1.gif");
    static GreenfootImage sonicLeftStand2 = new GreenfootImage("sonicLeftStand2.gif");
    static GreenfootImage sonicLeftStand3 = new GreenfootImage("sonicLeftStand3.gif");
    static GreenfootImage sonicMoveRight1 = new GreenfootImage("sonicMoveRight1.gif");
    static GreenfootImage sonicMoveRight2 = new GreenfootImage("sonicMoveRight2.gif");
    static GreenfootImage sonicMoveRight3 = new GreenfootImage("sonicMoveRight3.gif");
    static GreenfootImage sonicMoveLeft1 = new GreenfootImage("sonicMoveLeft1.gif");
    static GreenfootImage sonicMoveLeft2 = new GreenfootImage("sonicMoveLeft2.gif");
    static GreenfootImage sonicMoveLeft3 = new GreenfootImage("sonicMoveLeft3.gif");
    static GreenfootImage sonicRightPunch1 = new GreenfootImage("sonicRightPunch1.gif");
    static GreenfootImage sonicRightPunch2 = new GreenfootImage("sonicRightPunch2.gif");
    static GreenfootImage sonicRightPunch3 = new GreenfootImage("sonicRightPunch3.gif");
    static GreenfootImage sonicRightPunch4 = new GreenfootImage("sonicRightPunch4.gif");
    static GreenfootImage sonicLeftPunch1 = new GreenfootImage("sonicLeftPunch1.gif");
    static GreenfootImage sonicLeftPunch2 = new GreenfootImage("sonicLeftPunch2.gif");
    static GreenfootImage sonicLeftPunch3 = new GreenfootImage("sonicLeftPunch3.gif");
    static GreenfootImage sonicLeftPunch4 = new GreenfootImage("sonicLeftPunch4.gif");
    static GreenfootImage rightJump1 = new GreenfootImage("sonicRightJump1.gif");
    static GreenfootImage rightJump2 = new GreenfootImage("sonicRightJump2.gif");
    static GreenfootImage rightJump3 = new GreenfootImage("sonicRightJump3.gif");
    static GreenfootImage leftJump1 = new GreenfootImage("sonicLeftJump1.gif");
    static GreenfootImage leftJump2 = new GreenfootImage("sonicLeftJump2.gif");
    static GreenfootImage leftJump3 = new GreenfootImage("sonicLeftJump3.gif");
    //sounds
    static GreenfootSound sonicPunchSound = new GreenfootSound("sonicPunch.wav");
    private int moveSpeed;
    private int moveCounter1 = 0;
    /**
     * Act - do whatever the Sonic wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        groundHeight = getImage().getHeight()/2;//update image to use for gravity in fighter class
        applyGravity();
        animate();
        jump(13);
        lightAttack(5,2);
        moveRight(moveSpeed);
        moveLeft(moveSpeed);
        labelFollow();
        fallOffEdge();
        Player2Wins();
        checkForObjects();
    }    

    public Sonic()
    {
        rightStand1 = sonicRightStand1;
        rightStand2 = sonicRightStand2;
        rightStand3 = sonicRightStand3;
        leftStand1 = sonicLeftStand1;
        leftStand2 = sonicLeftStand2;
        leftStand3 = sonicLeftStand3;
        moveRight1 = sonicMoveRight1;
        moveRight2 = sonicMoveRight2;
        moveRight3 = sonicMoveRight3;
        moveLeft1 = sonicMoveLeft1;
        moveLeft2 = sonicMoveLeft2;
        moveLeft3 = sonicMoveLeft3;
        rightPunch1 = sonicRightPunch1;
        rightPunch2 = sonicRightPunch2;
        rightPunch3 = sonicRightPunch3;
        rightPunch4 = sonicRightPunch4;
        leftPunch1 = sonicLeftPunch1;
        leftPunch2 = sonicLeftPunch2;
        leftPunch3 = sonicLeftPunch3;
        leftPunch4 = sonicLeftPunch4;
        punchSound = sonicPunchSound;
        setImage(rightStand1);

        specialAttackTrue = false;//tells when s.a key has been pressed from fighter class
        lightAttackTrue = false;//tells when l.a. key has been pressed from fighter class
        moveSpeed = 7;
        p1 = new P1();
        healthBar = (new Bar("Sonic","HP",100,100));
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