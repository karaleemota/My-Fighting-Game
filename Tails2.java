import greenfoot.*;

/**
 * Write a description of class Tails2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tails2 extends Fighter2
{
    private GreenfootImage rightFly1;
    private GreenfootImage rightFly2;
    private GreenfootImage rightFly3;
    private GreenfootImage rightFly4;
    private GreenfootImage leftFly1;
    private GreenfootImage leftFly2;
    private GreenfootImage leftFly3;
    private GreenfootImage leftFly4;
    private int flyCounter;
    private int flyCounter2;
    /**
     * Act - do whatever the Tails wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        animate();
        jump(4);
        lightAttack(5,2);
        moveRight(moveSpeed);
        moveLeft(moveSpeed);
        labelFollow();
        fallOffEdge();
        Player1Wins();
    }    
    public Tails2()
    {
        rightStand1 = new GreenfootImage("tailsRightStand1.gif");
        rightStand2 = new GreenfootImage("tailsRightStand2.gif");
        rightStand3 = new GreenfootImage("tailsRightStand3.gif");
        leftStand1 = new GreenfootImage("tailsLeftStand1.gif");
        leftStand2 = new GreenfootImage("tailsLeftStand2.gif");
        leftStand3 = new GreenfootImage("tailsLeftStand3.gif");
        moveRight1 = new GreenfootImage("tailsMoveRight1.gif");
        moveRight2 = new GreenfootImage("tailsMoveRight2.gif");
        moveRight3 = new GreenfootImage("tailsMoveRight3.gif");
        moveLeft1 = new GreenfootImage("tailsMoveLeft1.gif");
        moveLeft2 = new GreenfootImage("tailsMoveLeft2.gif");
        moveLeft3 = new GreenfootImage("tailsMoveLeft3.gif");
        rightPunch1 = new GreenfootImage("tailsRightPunch1.gif");
        rightPunch2 = new GreenfootImage("tailsRightPunch2.gif");
        rightPunch3 = new GreenfootImage("tailsRightPunch3.gif");
        rightPunch4 = new GreenfootImage("tailsRightPunch4.gif");
        leftPunch1 = new GreenfootImage("tailsLeftPunch1.gif");
        leftPunch2 = new GreenfootImage("tailsLeftPunch2.gif");
        leftPunch3 = new GreenfootImage("tailsLeftPunch3.gif");
        leftPunch4 = new GreenfootImage("tailsLeftPunch4.gif");
        rightFly1 = new GreenfootImage("tailsRightFly1.gif");
        rightFly2 = new GreenfootImage("tailsRightFly2.gif");
        rightFly3 = new GreenfootImage("tailsRightFly3.gif");
        rightFly4 = new GreenfootImage("tailsRightFly4.gif");
        leftFly1 = new GreenfootImage("tailsLeftFly1.gif");
        leftFly2 = new GreenfootImage("tailsLeftFly2.gif");
        leftFly3 = new GreenfootImage("tailsLeftFly3.gif");
        leftFly4 = new GreenfootImage("tailsLeftFly4.gif");
        punchSound = new GreenfootSound("tailsPunch.wav");
        setImage(rightStand1);
        moveSpeed = 5;
        p2 = new P2();
        healthBar = (new Bar("Tails","HP",100,100));
    }
    protected void addedToWorld(World world)
    {
        world.addObject(healthBar, 510, 30);
        world.addObject(p2,getX()+7,getY()-getImage().getHeight()/2-15);
    }
    public void jump(int height)
    {
          if(Greenfoot.isKeyDown("Up") && !Greenfoot.isKeyDown(".") && isFacedRight())
          {
              flyCounter2++;
              vSpeed = 0;
              if(flyCounter2 == 1)
              {
                   setLocation(getX(),getY()-height);
                   flyCounter2 = 0;
                }
              flyCounter++;
              if(flyCounter == 7)
                {
                   
                   if (getImage() == rightStand1 || getImage() == rightStand2 || getImage() == rightStand3
                         || getImage() == moveRight1 || getImage()==moveRight2 || getImage()==moveRight3
                         || getImage() == rightPunch1 || getImage() == rightPunch2 || getImage() == rightPunch3
                         || getImage() == rightPunch4)
                    {
                        setImage(rightFly1);
                    }
                    else if (getImage() == rightFly1 )
                    {
                        setImage(rightFly2);
                    }
                    else if(getImage() == rightFly2 )
                    {
                        setImage(rightFly3);
                    }
                    else if(getImage() == rightFly3 )
                    {
                        setImage(rightFly4);
                    }
                    else if ( getImage() != rightFly1 )
                    {
                        setImage(rightFly1);
                    }
                    flyCounter = 0;
                }
          }
          else if(Greenfoot.isKeyDown("Up") && !Greenfoot.isKeyDown(".") && !isFacedRight())
          {
              flyCounter2++;
              vSpeed = 0;
              if(flyCounter2 == 1)
              {
                   setLocation(getX(),getY()-height);
                   flyCounter2 = 0;
                }
              flyCounter++;
              if(flyCounter == 7)
                {
                   
                   if (getImage() == leftStand1 || getImage() == leftStand2 || getImage() == leftStand3
                         || getImage() == moveLeft1 || getImage()==moveLeft2 || getImage()==moveLeft3
                         || getImage() == leftPunch1 || getImage() == leftPunch2 || getImage() == leftPunch3
                         || getImage() == leftPunch4)
                    {
                        setImage(leftFly1);
                    }
                    else if (getImage() == leftFly1 )
                    {
                        setImage(leftFly2);
                    }
                    else if(getImage() == leftFly2 )
                    {
                        setImage(leftFly3);
                    }
                    else if(getImage() == leftFly3 )
                    {
                        setImage(leftFly4);
                    }
                    else if ( getImage() != leftFly1 )
                    {
                        setImage(leftFly1);
                    }
                    flyCounter = 0;
                }
                
        }
        else
                {
                    applyGravity();
                }
    }
    public void heavyAttack()
    {
    }
    public void labelFollow()
    {
        if(facedRight == true)
        {
            p2.setLocation(this.getX()+10,this.getY()-getImage().getHeight()/2-15);
        }
        else
        {
            p2.setLocation(this.getX()-10,this.getY()-getImage().getHeight()/2-15);
        }
    }
}
