import greenfoot.*;

/**
 * Write a description of class megajoel2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Megajoel2 extends Fighter2
{
    private int moveSpeed;
    /**
     * Act - do whatever the megajoel wants to do. This method is called whenever
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
    protected GreenfootSound megamanBuster; 
    private int moveCounter1;
    private int moveCounter2;
    private int moveCounter4; //slows down rate megajoel can shoot
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
    public Megajoel2()
    {
        rightStand1 = new GreenfootImage("megajoelRightStand1.png");
        rightStand2 = new GreenfootImage("megajoelRightStand2.png");
        rightStand3 = new GreenfootImage("megajoelRightStand3.png");
        leftStand1 = new GreenfootImage("megajoelLeftStand1.png");
        leftStand2 = new GreenfootImage("megajoelLeftStand2.png");
        leftStand3 = new GreenfootImage("megajoelLeftStand3.png");
        moveRight1 = new GreenfootImage("megajoelMoveRight1.png");
        moveRight2 = new GreenfootImage("megajoelMoveRight2.png");
        moveRight3 = new GreenfootImage("megajoelMoveRight3.png");
        moveLeft1 = new GreenfootImage("megajoelMoveLeft1.png");
        moveLeft2 = new GreenfootImage("megajoelMoveLeft2.png");
        moveLeft3 = new GreenfootImage("megajoelMoveLeft3.png");
        rightShoot1 = new GreenfootImage("megajoelRightShoot1.png");
        rightShoot2 = new GreenfootImage("megajoelRightShoot2.png");
        rightShoot3 = new GreenfootImage("megajoelRightShoot3.png");
        rightShoot4 = new GreenfootImage("megajoelRightShoot4.png");
        leftShoot1 = new GreenfootImage("megajoelLeftShoot1.png");
        leftShoot2 = new GreenfootImage("megajoelLeftShoot2.png");
        leftShoot3 = new GreenfootImage("megajoelLeftShoot3.png");
        leftShoot4 = new GreenfootImage("megajoelLeftShoot4.png");
        megamanBuster = new GreenfootSound("megamanBuster.wav");
        moveCounter2 = 0;
        setImage(rightStand1);
        moveSpeed = 5;
        moveCounter1 = 0;
        p2 = new P2();
        healthBar = (new Bar("Joel","HP",100,100));
    }
    protected void addedToWorld(World world)
    {
        world.addObject(healthBar, 500, 30);
        world.addObject(p2,getX(),getY()-getImage().getHeight()/2-15);
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
        if(Greenfoot.isKeyDown("1"))
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
                        //megajoel shoots here
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
                        //megajoel shoots here
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

