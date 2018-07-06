import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Goku here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Goku extends Fighter
{
    private int moveSpeed;
    int moveCounter1 = 0;
    GreenfootImage rightJump1;
    GreenfootImage rightJump2;
    GreenfootImage rightJump3;
    GreenfootImage leftJump1;
    GreenfootImage leftJump2;
    GreenfootImage leftJump3;
    /**
     * Act - do whatever the Sonic wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        groundHeight = getImage().getHeight()/2;//update image to use for gravity in fighter class
        applyGravity();
        animate();
        jump(14);
        lightAttack(5,3);
        moveRight(moveSpeed);
        moveLeft(moveSpeed);
        labelFollow();
        fallOffEdge();
        Player2Wins();
    }    
    public Goku()
    {
        rightStand1 = new GreenfootImage("gokuRightStand1.png");
        rightStand2 = new GreenfootImage("gokuRightStand2.png");
        rightStand3 = new GreenfootImage("gokuRightStand3.png");
        leftStand1 = new GreenfootImage("gokuLeftStand1.png");
        leftStand2 = new GreenfootImage("gokuLeftStand2.png");
        leftStand3 = new GreenfootImage("gokuLeftStand3.png");
        moveRight1 = new GreenfootImage("gokuMoveRight1.png");
        moveRight2 = new GreenfootImage("gokuMoveRight2.png");
        moveRight3 = new GreenfootImage("gokuMoveRight3.png");
        moveLeft1 = new GreenfootImage("gokuMoveLeft1.png");
        moveLeft2 = new GreenfootImage("gokuMoveLeft2.png");
        moveLeft3 = new GreenfootImage("gokuMoveLeft3.png");
        rightPunch1 = new GreenfootImage("gokuRightPunch1.png");
        rightPunch2 = new GreenfootImage("gokuRightPunch2.png");
        rightPunch3 = new GreenfootImage("gokuRightPunch3.png");
        rightPunch4 = new GreenfootImage("gokuRightPunch4.png");
        leftPunch1 = new GreenfootImage("gokuLeftPunch1.png");
        leftPunch2 = new GreenfootImage("gokuLeftPunch2.png");
        leftPunch3 = new GreenfootImage("gokuLeftPunch3.png");
        leftPunch4 = new GreenfootImage("gokuLeftPunch4.png");
        rightJump1 = new GreenfootImage("gokuRightJump1.png");
        rightJump2 = new GreenfootImage("gokuRightJump2.png");
        rightJump3 = new GreenfootImage("gokuRightJump3.png");
        leftJump1 = new GreenfootImage("gokuLeftJump1.png");
        leftJump2 = new GreenfootImage("gokuLeftJump2.png");
        leftJump3 = new GreenfootImage("gokuLeftJump3.png");
        punchSound = new GreenfootSound("gokuPunch.wav");
        setImage(rightStand1);
        moveSpeed = 6;
        p1 = new P1();
        healthBar = (new Bar("Goku","HP",100,100));
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
