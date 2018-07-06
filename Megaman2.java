import greenfoot.*;

/**
 * Write a description of class megaman here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Megaman2 extends Fighter2
{
    private int moveSpeed;
    /**
     * Act - do whatever the megaman wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    protected GreenfootImage rightShoot1;
    protected GreenfootImage rightShoot2;
    protected GreenfootImage rightShoot3;
    protected GreenfootImage rightShoot4;
    protected GreenfootImage leftShoot1;
    protected GreenfootImage leftShoot2;
    protected GreenfootImage leftShoot3;
    protected GreenfootImage leftShoot4;
    protected GreenfootImage rightJump1;
    protected GreenfootImage rightJump2;
    protected GreenfootImage rightJump3;
    protected GreenfootImage leftJump1;
    protected GreenfootImage leftJump2;
    protected GreenfootImage leftJump3;
    protected GreenfootSound megamanBuster;
    private int moveCounter1;
    private int moveCounter2;
    private int moveCounter4; //slows down rate megaman can shoot
    public void act() 
    {
        applyGravity();
        animate();
        jump(12);
        moveCounter4++;
        shoot(5,3);
        moveRight(moveSpeed);
        moveLeft(moveSpeed);
        labelFollow();
        fallOffEdge();
        Player1Wins();
    }    
    public Megaman2()
    {
        rightStand1 = new GreenfootImage("megamanRightStand1.png");
        rightStand2 = new GreenfootImage("megamanRightStand2.png");
        rightStand3 = new GreenfootImage("megamanRightStand3.png");
        leftStand1 = new GreenfootImage("megamanLeftStand1.png");
        leftStand2 = new GreenfootImage("megamanLeftStand2.png");
        leftStand3 = new GreenfootImage("megamanLeftStand3.png");
        moveRight1 = new GreenfootImage("megamanMoveRight1.png");
        moveRight2 = new GreenfootImage("megamanMoveRight2.png");
        moveRight3 = new GreenfootImage("megamanMoveRight3.png");
        moveLeft1 = new GreenfootImage("megamanMoveLeft1.png");
        moveLeft2 = new GreenfootImage("megamanMoveLeft2.png");
        moveLeft3 = new GreenfootImage("megamanMoveLeft3.png");
        rightShoot1 = new GreenfootImage("megamanRightShoot1.png");
        rightShoot2 = new GreenfootImage("megamanRightShoot2.png");
        rightShoot3 = new GreenfootImage("megamanRightShoot3.png");
        rightShoot4 = new GreenfootImage("megamanRightShoot4.png");
        leftShoot1 = new GreenfootImage("megamanLeftShoot1.png");
        leftShoot2 = new GreenfootImage("megamanLeftShoot2.png");
        leftShoot3 = new GreenfootImage("megamanLeftShoot3.png");
        leftShoot4 = new GreenfootImage("megamanLeftShoot4.png");
        rightJump1 = new GreenfootImage("megamanRightJump1.png");
        rightJump2 = new GreenfootImage("megamanRightJump2.png");
        rightJump3 = new GreenfootImage("megamanRightJump3.png");
        leftJump1 = new GreenfootImage("megamanLeftJump1.png");
        leftJump2 = new GreenfootImage("megamanLeftJump2.png");
        leftJump3 = new GreenfootImage("megamanLeftJump3.png");
        megamanBuster = new GreenfootSound("megamanBuster.wav");
        moveCounter2 = 0;
        setImage(rightStand1);
        moveSpeed = 5;
        moveCounter1 = 0;
        p2 = new P2();
        healthBar = (new Bar("Megaman","HP",100,100));
    }
    protected void addedToWorld(World world)
    {
        world.addObject(healthBar, 500, 30);
        world.addObject(p2,getX(),getY()-getImage().getHeight()/2-15);
    }
        public void jump(int height)
    {
          
          if(Greenfoot.isKeyDown("up") )
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
    public Bar getHealthBar()
    {
        return healthBar;
    }
    public void heavyAttack()
    {
    }
    public void labelFollow()
    {
        p2.setLocation(this.getX(),this.getY()-getImage().getHeight()/2-15);
    }
    public  void shoot(int speed, int damage)
    {
        if(Greenfoot.isKeyDown("."))
        {
            if(isFacedRight())
            {
                moveCounter2++;
                if(moveCounter2 == speed)
                {
                    if (getImage() == rightStand1 || getImage() == rightStand2 || getImage() == rightStand3
                         || getImage() == moveRight1 || getImage()==moveRight2 || getImage()==moveRight3)
                    {
                        setImage(rightShoot1);
                    }
                    else if (getImage() == rightShoot1 )
                    {
                        setImage(rightShoot2);
                        //megaman shoots here
                        megamanBuster.play();
                        getWorld().addObject(new Bullet2(5), this.getX()+48, this.getY()-7);
                    }
                    else if(getImage() == rightShoot2 )
                    {
                        setImage(rightShoot3);
                    }
                    else if(getImage() == rightShoot3)
                    {
                        setImage(rightShoot4);
                    }
                    else if ( getImage() != rightShoot1 )
                    {
                        setImage(rightShoot1);
                    }
                    moveCounter2 = 0;
                }
            }
            else if(!isFacedRight())
            {
                moveCounter2++;
                if(moveCounter2 == speed)
                {
                    if (getImage() == leftStand1 || getImage() == leftStand2 || getImage() == leftStand3
                         || getImage() == moveLeft1 || getImage()==moveLeft2 || getImage()==moveLeft3)
                    {
                        setImage(leftShoot1);
                    }
                    else if (getImage() == leftShoot1 )
                    {
                        setImage(leftShoot2);
                        //megaman shoots here
                        megamanBuster.play();
                        getWorld().addObject(new Bullet2(-5), this.getX()-48, this.getY()-7);
                    }
                    else if(getImage() == leftShoot2 )
                    {
                        setImage(leftShoot3);
                    }
                    else if(getImage() == leftShoot3)
                    {
                        setImage(leftShoot4);
                    }
                    else if ( getImage() != leftShoot1 )
                    {
                        setImage(leftShoot1);
                    }
                    moveCounter2 = 0;
                }
            }
        }
    }
}

