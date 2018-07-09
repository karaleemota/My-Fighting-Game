import greenfoot.*;

/**
 * Write a description of class Knuckles1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MrIncredible extends Fighter
{
    static protected GreenfootImage rightJump1;
    static protected GreenfootImage rightJump2;
    static protected GreenfootImage rightJump3;
    static protected GreenfootImage leftJump1;
    static protected GreenfootImage leftJump2;
    static protected GreenfootImage leftJump3;
    private int moveCounter1;//to animate jump
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
    }    
    public MrIncredible()
    {
        rightStand1 = new GreenfootImage("mrIncredibleRightStand1.png");
        rightStand2 = new GreenfootImage("mrIncredibleRightStand2.png");
        rightStand3 = new GreenfootImage("mrIncredibleRightStand3.png");
        leftStand1 = new GreenfootImage("mrIncredibleLeftStand1.png");
        leftStand2 = new GreenfootImage("mrIncredibleLeftStand2.png");
        leftStand3 = new GreenfootImage("mrIncredibleLeftStand3.png");
        moveRight1 = new GreenfootImage("mrIncredibleMoveRight1.png");
        moveRight2 = new GreenfootImage("mrIncredibleMoveRight2.png");
        moveRight3 = new GreenfootImage("mrIncredibleMoveRight3.png");
        moveLeft1 = new GreenfootImage("mrIncredibleMoveLeft1.png");
        moveLeft2 = new GreenfootImage("mrIncredibleMoveLeft2.png");
        moveLeft3 = new GreenfootImage("mrIncredibleMoveLeft3.png");
        rightPunch1 = new GreenfootImage("mrIncredibleRightPunch1.png");
        rightPunch2 = new GreenfootImage("mrIncredibleRightPunch2.png");
        rightPunch3 = new GreenfootImage("mrIncredibleRightPunch3.png");
        rightPunch4 = new GreenfootImage("mrIncredibleRightPunch4.png");
        leftPunch1 = new GreenfootImage("mrIncredibleLeftPunch1.png");
        leftPunch2 = new GreenfootImage("mrIncredibleLeftPunch2.png");
        leftPunch3 = new GreenfootImage("mrIncredibleLeftPunch3.png");
        leftPunch4 = new GreenfootImage("mrIncredibleLeftPunch4.png");
        rightJump1 = new GreenfootImage("mrIncredibleRightJump1.png");
        rightJump2 = new GreenfootImage("mrIncredibleRightJump2.png");
        rightJump3 = new GreenfootImage("mrIncredibleRightJump3.png");
        leftJump1 = new GreenfootImage("mrIncredibleLeftJump1.png");
        leftJump2 = new GreenfootImage("mrIncredibleLeftJump2.png");
        leftJump3 = new GreenfootImage("mrIncredibleLeftJump3.png");
        punchSound = new GreenfootSound("knucklesPunch.wav");
        
        specialAttackTrue = false;//tells when s.a key has been pressed from fighter class
        moveSpeed = 4;
        p1 = new P1();
        healthBar = (new Bar("Mr. Incredible","HP",100,100));
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
    public void jump(int height)
    {   //mostly just animates jump
          
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
    public void labelFollow()
    {
        p1.setLocation(this.getX(),this.getY()-getImage().getHeight()/2-15);
    }
}
