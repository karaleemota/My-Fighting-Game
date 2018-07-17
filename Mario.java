import greenfoot.*;

/**
 * Write a description of class  here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mario extends Fighter
{
    int moveCounter1 = 0;
    GreenfootImage rightJump1;
    GreenfootImage rightJump2;
    GreenfootImage rightJump3;
    GreenfootImage leftJump1;
    GreenfootImage leftJump2;
    GreenfootImage leftJump3;
    public void act() 
    {
        groundHeight = getImage().getHeight()/2;//update image to use for gravity in fighter class
        applyGravity();
        animate();
        jump(13);
        lightAttack(5,4);
        moveRight(moveSpeed);
        moveLeft(moveSpeed);
        labelFollow();
        fallOffEdge();
        Player2Wins();
    }    
    public Mario()
    {
        rightStand1 = new GreenfootImage("marioRightStand1.png");
        rightStand2 = new GreenfootImage("marioRightStand2.png");
        rightStand3 = new GreenfootImage("marioRightStand3.png");
        leftStand1 = new GreenfootImage("marioLeftStand1.png");
        leftStand2 = new GreenfootImage("marioLeftStand2.png");
        leftStand3 = new GreenfootImage("marioLeftStand3.png");
        moveRight1 = new GreenfootImage("marioMoveRight1.png");
        moveRight2 = new GreenfootImage("marioMoveRight2.png");
        moveRight3 = new GreenfootImage("marioMoveRight3.png");
        moveLeft1 = new GreenfootImage("marioMoveLeft1.png");
        moveLeft2 = new GreenfootImage("marioMoveLeft2.png");
        moveLeft3 = new GreenfootImage("marioMoveLeft3.png");
        rightPunch1 = new GreenfootImage("marioRightPunch1.png");
        rightPunch2 = new GreenfootImage("marioRightPunch2.png");
        rightPunch3 = new GreenfootImage("marioRightPunch3.png");
        rightPunch4 = new GreenfootImage("marioRightPunch4.png");
        leftPunch1 = new GreenfootImage("marioLeftPunch1.png");
        leftPunch2 = new GreenfootImage("marioLeftPunch2.png");
        leftPunch3 = new GreenfootImage("marioLeftPunch3.png");
        leftPunch4 = new GreenfootImage("marioLeftPunch4.png");
        rightJump1 = new GreenfootImage("marioRightJump1.png");
        rightJump2 = new GreenfootImage("marioRightJump2.png");
        rightJump3 = new GreenfootImage("marioRightJump3.png");
        leftJump1 = new GreenfootImage("marioLeftJump1.png");
        leftJump2 = new GreenfootImage("marioLeftJump2.png");
        leftJump3 = new GreenfootImage("marioLeftJump3.png");        
        punchSound = new GreenfootSound("marioPunch.wav");
        
        setImage(rightStand1);
        specialAttackTrue = false;//tells when s.a key has been pressed from fighter class
        moveSpeed = 5; 
        p1 = new P1();
        healthBar = (new Bar("Mario","HP",100,100));
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
    }
    public void specialAttack()
    {
    }
    public void labelFollow()
    {
        p1.setLocation(this.getX(),this.getY()-getImage().getHeight()/2-15);
    }
}
