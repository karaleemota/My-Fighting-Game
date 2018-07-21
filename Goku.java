import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Goku here.
 * 
 * @author Karalee Mota
 * @version 7/8/18
 */
public class Goku extends Fighter
{
    private int moveSpeed;
    private char moveCounter1 = 0;
    private char moveCounter2 = 0;//time special attack animation
    //images
    GreenfootImage rightJump1;
    GreenfootImage rightJump2;
    GreenfootImage rightJump3;
    GreenfootImage leftJump1;
    GreenfootImage leftJump2;
    GreenfootImage leftJump3;
    private static GreenfootImage rightBlast1 = new GreenfootImage("gokuRightBlast1.png");
    private static GreenfootImage rightBlast2 = new GreenfootImage("gokuRightBlast2.png");
    private static GreenfootImage rightBlast3 = new GreenfootImage("gokuRightBlast3.png");
    private static GreenfootImage rightBlast4 = new GreenfootImage("gokuRightBlast4.png");
    private static GreenfootImage leftBlast1 = new GreenfootImage("gokuLeftBlast1.png");
    private static GreenfootImage leftBlast2 = new GreenfootImage("gokuLeftBlast2.png");
    private static GreenfootImage leftBlast3 = new GreenfootImage("gokuLeftBlast3.png");
    private static GreenfootImage leftBlast4 = new GreenfootImage("gokuLeftBlast4.png");
    //sounds
    private static GreenfootSound gokuBlastSound = new GreenfootSound("gokuBlast.wav");
    
    private char blastTimer;//limits amount of special attacks goku can do at a time
    private int speed = 5;//speed defines how fast goku will animate
    
    public void act() 
    {
        groundHeight = getImage().getHeight()/2;//update image to use for gravity in fighter class
        applyGravity();
        animate();
        jump(14);
        lightAttack(5,3);
        specialAttack();
        moveRight(moveSpeed);
        moveLeft(moveSpeed);
        labelFollow();
        fallOffEdge();
        Player2Wins();
        checkForObjects();
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
        gokuBlastSound.setVolume(80);
        
        moveSpeed = 6;
        specialAttackTrue = false;//tells when s.a key has been pressed from fighter class
        p1 = new P1();
        healthBar = (new Bar("Goku","HP",100,100));
    }

    public void jump(int height) 
    {  
        
        if(Greenfoot.isKeyDown("w") && !lightAttackTrue)
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
        else if(!isOnGround() && !specialAttackTrue && !lightAttackTrue)//animate falling off ledge
        {
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
        blastTimer++;
        if(Greenfoot.isKeyDown("o") && blastTimer >= 75)
        {         
            specialAttackTrue = true;
        }
        if(isFacedRight() && specialAttackTrue)
        {
            moveCounter2++;                
            if (moveCounter2 == speed)
            {
                setImage(rightBlast1);
            }
            else if (moveCounter2 == (speed*2) )
            {
                setImage(rightBlast2);
            }
            else if(moveCounter2 == (speed*3) )
            {
                setImage(rightBlast3);//do blast here
                gokuBlastSound.play();
                getWorld().addObject(new Blast(6), this.getX()+40, this.getY()-2);
            }
            else if(moveCounter2 == (speed*4))
            {
                setImage(rightBlast4);
                moveCounter2 = 0;//punch complete
                specialAttackTrue =false;
                blastTimer = 0;//reset blast timer so goku cant use it for time amount
            }         
        }
        else if(!isFacedRight() && specialAttackTrue)
        {
            moveCounter2++;               
            if (moveCounter2 == speed)
            {
                setImage(leftBlast1);
            }
            else if (moveCounter2 == (speed*2) )
            {
                setImage(leftBlast2);
            }
            else if(moveCounter2 == (speed*3) ) 
            {
                setImage(leftBlast3);//do blast here
                gokuBlastSound.play();
                getWorld().addObject(new Blast(-6), this.getX()-40, this.getY()-2);
            }
            else if(moveCounter2 == (speed*4))
            {
                setImage(leftBlast4);   
                moveCounter2 = 0;//punch complete
                specialAttackTrue =false;
                blastTimer = 0;
            }         
        }
    }
    public void labelFollow()
    {
        p1.setLocation(this.getX(),this.getY()-getImage().getHeight()/2-15);
    }  
}
