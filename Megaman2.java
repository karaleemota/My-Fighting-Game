import greenfoot.*;
/**
 * Write a description of class megaman here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Megaman2 extends Fighter2
{
    GreenfootImage rightShoot1 = Megaman.rightShoot1;
    GreenfootImage rightShoot2 = Megaman.rightShoot2;
    GreenfootImage rightShoot3 = Megaman.rightShoot3;
    GreenfootImage rightShoot4 = Megaman.rightShoot4;
    GreenfootImage leftShoot1 = Megaman.leftShoot1;
    GreenfootImage leftShoot2 = Megaman.leftShoot2;
    GreenfootImage leftShoot3 = Megaman.leftShoot3;
    GreenfootImage leftShoot4 = Megaman.leftShoot4;
    GreenfootImage rightJump1 = Megaman.rightJump1;
    GreenfootImage rightJump2 = Megaman.rightJump2;
    GreenfootImage rightJump3 = Megaman.rightJump3;
    GreenfootImage leftJump1 = Megaman.leftJump1;
    GreenfootImage leftJump2 = Megaman.leftJump2;
    GreenfootImage leftJump3 = Megaman.leftJump3; 
    GreenfootSound megamanBuster = Megaman.megamanBuster;
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
        Player1Wins();
        checkForObjects();
    }    

    public Megaman2()
    {
        rightStand1 = Megaman.megamanRightStand1;
        rightStand2 = Megaman.megamanRightStand2;
        rightStand3 = Megaman.megamanRightStand3;
        leftStand1 = Megaman.megamanLeftStand1;
        leftStand2 = Megaman.megamanLeftStand2;
        leftStand3 = Megaman.megamanLeftStand3;
        moveRight1 = Megaman.megamanMoveRight1;
        moveRight2 = Megaman.megamanMoveRight2;
        moveRight3 = Megaman.megamanMoveRight3;
        moveLeft1 = Megaman.megamanMoveLeft1;
        moveLeft2 = Megaman.megamanMoveLeft2;
        moveLeft3 = Megaman.megamanMoveLeft3;
        setImage(leftStand1);

        moveSpeed = 5;
        moveCounter1 = 0;
        moveCounter2 = 0;
        moveCounter4 = 0;
        specialAttackTrue = false;//tells when s.a key has been pressed from fighter class
        lightAttackTrue = false;//tells when l.a. key has been pressed from fighter class
        p2 = new P2();
        healthBar = (new Bar("Megaman","HP",100,100));
    }

    protected void addedToWorld(World world)
    {
        world.addObject(healthBar, 550, 30);
        world.addObject(p2,getX(),getY()-getImage().getHeight()/2-15);
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
        p2.setLocation(this.getX(),this.getY()-getImage().getHeight()/2-15); 
    }

    public  void shoot(int speed, int damage)
    {
        moveCounter4++;
        if(Greenfoot.isKeyDown("."))
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
                getWorld().addObject(new Bullet2(5), this.getX()+48, this.getY()-7);
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
                getWorld().addObject(new Bullet2(-5), this.getX()-48, this.getY()-7);
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

