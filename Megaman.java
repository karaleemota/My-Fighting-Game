import greenfoot.*;
/**
 * Write a description of class megaman here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Megaman extends Fighter
{
    static GreenfootImage megamanRightStand1 = new GreenfootImage("megamanRightStand1.png");
    static GreenfootImage megamanRightStand2 = new GreenfootImage("megamanRightStand2.png");
    static GreenfootImage megamanRightStand3 = new GreenfootImage("megamanRightStand3.png");
    static GreenfootImage megamanLeftStand1 = new GreenfootImage("megamanLeftStand1.png");
    static GreenfootImage megamanLeftStand2 = new GreenfootImage("megamanLeftStand2.png");
    static GreenfootImage megamanLeftStand3 = new GreenfootImage("megamanLeftStand3.png");
    static GreenfootImage megamanMoveRight1 = new GreenfootImage("megamanMoveRight1.png");
    static GreenfootImage megamanMoveRight2 = new GreenfootImage("megamanMoveRight2.png");
    static GreenfootImage megamanMoveRight3 = new GreenfootImage("megamanMoveRight3.png");
    static GreenfootImage megamanMoveLeft1 = new GreenfootImage("megamanMoveLeft1.png");
    static GreenfootImage megamanMoveLeft2 = new GreenfootImage("megamanMoveLeft2.png");
    static GreenfootImage megamanMoveLeft3 = new GreenfootImage("megamanMoveLeft3.png");
    static GreenfootImage rightShoot1 = new GreenfootImage("megamanRightShoot1.png");
    static GreenfootImage rightShoot2 = new GreenfootImage("megamanRightShoot2.png");
    static GreenfootImage rightShoot3 = new GreenfootImage("megamanRightShoot3.png");
    static GreenfootImage rightShoot4 = new GreenfootImage("megamanRightShoot4.png");
    static GreenfootImage leftShoot1 = new GreenfootImage("megamanLeftShoot1.png");
    static GreenfootImage leftShoot2 = new GreenfootImage("megamanLeftShoot2.png");
    static GreenfootImage leftShoot3 = new GreenfootImage("megamanLeftShoot3.png");
    static GreenfootImage leftShoot4 = new GreenfootImage("megamanLeftShoot4.png");
    static GreenfootImage rightJump1 = new GreenfootImage("megamanRightJump1.png");
    static GreenfootImage rightJump2 = new GreenfootImage("megamanRightJump2.png");
    static GreenfootImage rightJump3 = new GreenfootImage("megamanRightJump3.png");
    static GreenfootImage leftJump1 = new GreenfootImage("megamanLeftJump1.png");
    static GreenfootImage leftJump2 = new GreenfootImage("megamanLeftJump2.png");
    static GreenfootImage leftJump3 = new GreenfootImage("megamanLeftJump3.png");
    static GreenfootSound megamanBuster = new GreenfootSound("megamanBuster.wav");
    private int moveCounter1;
    private int moveCounter2;
    private char moveCounter4; //slows down rate megaman can shoot
    public void act() 
    {
        groundHeight = getImage().getHeight()/2;//update image to use for gravity in fighter class
        applyGravity();
        animate();
        jump(12);
        shoot(5,3);
        moveRight(moveSpeed);
        moveLeft(moveSpeed);
        labelFollow();
        fallOffEdge();
        Player2Wins();
        checkForObjects();
    }    

    public Megaman()
    {
        rightStand1 = megamanRightStand1;
        rightStand2 = megamanRightStand2;
        rightStand3 = megamanRightStand3;
        leftStand1 = megamanLeftStand1;
        leftStand2 = megamanLeftStand2;
        leftStand3 = megamanLeftStand3;
        moveRight1 = megamanMoveRight1;
        moveRight2 = megamanMoveRight2;
        moveRight3 = megamanMoveRight3;
        moveLeft1 = megamanMoveLeft1;
        moveLeft2 = megamanMoveLeft2;
        moveLeft3 = megamanMoveLeft3;
        setImage(rightStand1);

        moveSpeed = 5;
        moveCounter1 = 0;
        moveCounter2 = 0;
        moveCounter4 = 0;
        specialAttackTrue = false;//tells when s.a key has been pressed from fighter class
        lightAttackTrue = false;//tells when l.a. key has been pressed from fighter class
        p1 = new P1();
        healthBar = (new Bar("Megaman","HP",100,100));
    }

    protected void addedToWorld(World world)
    {
        world.addObject(healthBar, 125, 30);
        world.addObject(p1,getX(),getY()-getImage().getHeight()/2-15);
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
                setImage(rightJump3);
            }
            else
            {
                setImage(leftJump3);
            }
        }
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

    public  void shoot(int speed, int damage)
    {
        moveCounter4++;
        if(Greenfoot.isKeyDown("p"))
        {         
            lightAttackTrue = true;
        }
        if(isFacedRight() && lightAttackTrue)
        {
            moveCounter2++;                
            if (moveCounter2 == speed)
            {
                setImage(rightShoot1);
            }
            else if (moveCounter2 == (speed*2) )
            {
                setImage(rightShoot2);
                //megaman shoots here
                megamanBuster.play();
                getWorld().addObject(new Bullet(5), this.getX()+48, this.getY()-7);
            }
            else if(moveCounter2 == (speed*3) )
            {
                setImage(rightShoot3);
            }
            else if(moveCounter2 == (speed*4))
            {
                setImage(rightShoot4);
                moveCounter2 = 0;//Shoot complete
                lightAttackTrue =false;
            }         
        }
        else if(!isFacedRight() && lightAttackTrue)
        {
            moveCounter2++;               
            if (moveCounter2 == speed)
            {
                setImage(leftShoot1);
            }
            else if (moveCounter2 == (speed*2) )
            {
                setImage(leftShoot2);
                //megaman shoots here
                megamanBuster.play();
                getWorld().addObject(new Bullet(-5), this.getX()-48, this.getY()-7);
            }
            else if(moveCounter2 == (speed*3) )
            {
                setImage(leftShoot3);
            }
            else if(moveCounter2 == (speed*4))
            {
                setImage(leftShoot4); 
                moveCounter2 = 0;//Shoot complete
                lightAttackTrue =false;
            }         
        }

    }
}

