import greenfoot.*;

/**
 * Write a description of class Pikachu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pikachu2 extends Fighter2
{
    private int moveSpeed;
    private int moveCounter2;
    GreenfootImage rightJump1;
    GreenfootImage leftJump1;
    /**
     * Act - do whatever the pikachu wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        applyGravity();
        animate();
        jump(11);
        lightAttack(4,2);
        moveRight(moveSpeed);
        moveLeft(moveSpeed);
        labelFollow();
        fallOffEdge();
        Player1Wins();
    }    
    public Pikachu2()
    {
        rightStand1 = new GreenfootImage("pikachuRightStand1.png");
        rightStand2 = new GreenfootImage("pikachuRightStand2.png");
        rightStand3 = new GreenfootImage("pikachuRightStand3.png");
        leftStand1 = new GreenfootImage("pikachuLeftStand1.png");
        leftStand2 = new GreenfootImage("pikachuLeftStand2.png");
        leftStand3 = new GreenfootImage("pikachuLeftStand3.png");
        moveRight1 = new GreenfootImage("pikachuMoveRight1.png");
        moveRight2 = new GreenfootImage("pikachuMoveRight2.png");
        moveRight3 = new GreenfootImage("pikachuMoveRight3.png");
        moveLeft1 = new GreenfootImage("pikachuMoveLeft1.png");
        moveLeft2 = new GreenfootImage("pikachuMoveLeft2.png");
        moveLeft3 = new GreenfootImage("pikachuMoveLeft3.png");
        rightPunch1 = new GreenfootImage("pikachuRightPunch1.png");
        rightPunch2 = new GreenfootImage("pikachuRightPunch2.png");
        rightPunch3 = new GreenfootImage("pikachuRightPunch3.png");
        rightPunch4 = new GreenfootImage("pikachuRightPunch4.png");
        leftPunch1 = new GreenfootImage("pikachuLeftPunch1.png");
        leftPunch2 = new GreenfootImage("pikachuLeftPunch2.png");
        leftPunch3 = new GreenfootImage("pikachuLeftPunch3.png");
        leftPunch4 = new GreenfootImage("pikachuLeftPunch4.png");
        rightJump1 = new GreenfootImage("pikachuRightJump1.png");
        leftJump1 = new GreenfootImage("pikachuLeftJump1.png");
        punchSound = new GreenfootSound("pikachuPunch.wav");
        setImage(rightStand1);
        moveSpeed = 6;
        moveCounter2 = 0;
        p2 = new P2();
        healthBar = (new Bar("Pikachu","HP",100,100));
    }
    protected void addedToWorld(World world)
    {
        world.addObject(healthBar,502, 30);
        world.addObject(p2,getX(),getY()-getImage().getHeight()/2-15);
    }
    public Bar getHealthBar()
    {
        return healthBar;
    }
    public void jump(int height)
    {
          
          if(Greenfoot.isKeyDown("up") )
          {
             if(isFacedRight())
              {
                   if(getImage()  != rightJump1 || isOnGround())
                    {
                        setImage(rightJump1);
                    }
              }
              else if(!isFacedRight())
              {
                   if (getImage()  != leftJump1 || isOnGround())
                     {
                        setImage(leftJump1);
                     }
              }
              setLocation(getX(),getY()-height);
          }
    }
    public void heavyAttack()
    {
    }
    public void labelFollow()
    {
        p2.setLocation(this.getX(),this.getY()-getImage().getHeight()/2-15);
    }
 
}

