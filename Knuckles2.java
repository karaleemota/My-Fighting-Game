import greenfoot.*;

/**
 * Write a description of class Knuckles1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Knuckles2 extends Fighter2
{
    //images
    GreenfootImage rightJump1 = Knuckles.rightJump1;
    GreenfootImage rightJump2 = Knuckles.rightJump2;
    GreenfootImage rightJump3 = Knuckles.rightJump3;
    GreenfootImage leftJump1 = Knuckles.leftJump1;
    GreenfootImage leftJump2 = Knuckles.leftJump2;
    GreenfootImage leftJump3 = Knuckles.leftJump3;
    
    private int moveCounter1 = 0;
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
        Player1Wins();
        checkForObjects();
    }    

    public Knuckles2()
    {
        rightStand1 = Knuckles.knucklesRightStand1;//preserve memory by using Knuckles class images 
        rightStand2 = Knuckles.knucklesRightStand2;
        rightStand3 = Knuckles.knucklesRightStand3;
        leftStand1 = Knuckles.knucklesLeftStand1;
        leftStand2 = Knuckles.knucklesLeftStand2;
        leftStand3 = Knuckles.knucklesLeftStand3;
        moveRight1 = Knuckles.knucklesMoveRight1;
        moveRight2 = Knuckles.knucklesMoveRight2;
        moveRight3 = Knuckles.knucklesMoveRight3;
        moveLeft1 = Knuckles.knucklesMoveLeft1;
        moveLeft2 = Knuckles.knucklesMoveLeft2;
        moveLeft3 = Knuckles.knucklesMoveLeft3;
        rightPunch1 = Knuckles.knucklesRightPunch1;
        rightPunch2 = Knuckles.knucklesRightPunch2;
        rightPunch3 = Knuckles.knucklesRightPunch3;
        rightPunch4 = Knuckles.knucklesRightPunch4;
        leftPunch1 = Knuckles.knucklesLeftPunch1;
        leftPunch2 = Knuckles.knucklesLeftPunch2;
        leftPunch3 = Knuckles.knucklesLeftPunch3;
        leftPunch4 = Knuckles.knucklesLeftPunch4;
        punchSound = new GreenfootSound("knucklesPunch.wav");

        setImage(leftStand1);
        specialAttackTrue = false;//tells when s.a key has been pressed from fighter class
        lightAttackTrue = false;//tells when l.a. key has been pressed from fighter class
        moveSpeed = 4;
        p2 = new P2();
        healthBar = (new Bar("Knuckles","HP",100,100));
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
