import greenfoot.*;

/**
 * Write a description of class Sonic here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sonic2 extends Fighter2
{
    //images
    GreenfootImage rightJump1 = Sonic.rightJump1;
    GreenfootImage rightJump2 = Sonic.rightJump2;
    GreenfootImage rightJump3 = Sonic.rightJump3;
    GreenfootImage leftJump1 = Sonic.leftJump1;
    GreenfootImage leftJump2 = Sonic.leftJump2;
    GreenfootImage leftJump3 = Sonic.leftJump3;
    
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
        Player1Wins();
        checkForObjects();
    }    

    public Sonic2()
    {
        rightStand1 = Sonic.sonicRightStand1;
        rightStand2 = Sonic.sonicRightStand2;
        rightStand3 = Sonic.sonicRightStand3;
        leftStand1 = Sonic.sonicLeftStand1;
        leftStand2 = Sonic.sonicLeftStand2;
        leftStand3 = Sonic.sonicLeftStand3;
        moveRight1 = Sonic.sonicMoveRight1;
        moveRight2 = Sonic.sonicMoveRight2;
        moveRight3 = Sonic.sonicMoveRight3;
        moveLeft1 = Sonic.sonicMoveLeft1;
        moveLeft2 = Sonic.sonicMoveLeft2;
        moveLeft3 = Sonic.sonicMoveLeft3;
        rightPunch1 = Sonic.sonicRightPunch1;
        rightPunch2 = Sonic.sonicRightPunch2;
        rightPunch3 = Sonic.sonicRightPunch3;
        rightPunch4 = Sonic.sonicRightPunch4;
        leftPunch1 = Sonic.sonicLeftPunch1;
        leftPunch2 = Sonic.sonicLeftPunch2;
        leftPunch3 = Sonic.sonicLeftPunch3;
        leftPunch4 = Sonic.sonicLeftPunch4;
        punchSound = Sonic.sonicPunchSound;
        setImage(leftStand1);

        specialAttackTrue = false;//tells when s.a key has been pressed from fighter class
        lightAttackTrue = false;//tells when l.a. key has been pressed from fighter class
        moveSpeed = 7;
        p2 = new P2();
        healthBar = (new Bar("Sonic","HP",100,100));
    }

    public void jump(int height)
    {
        if(isOnGround() && jumped) 
        {
            jumped = false;//must have touched ground from previous jump 
        }
        if(Greenfoot.isKeyDown("up") && vSpeed > -1 && !hitSpring)
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
        world.addObject(healthBar, 550, 30);
        world.addObject(p2,getX(),getY()-getImage().getHeight()/2-15);
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
        p2.setLocation(this.getX(),this.getY()-getImage().getHeight()/2-15);
    }

}