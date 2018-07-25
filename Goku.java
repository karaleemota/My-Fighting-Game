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
    static GreenfootImage rightJump1 = new GreenfootImage("gokuRightJump1.png");
    static GreenfootImage rightJump2 = new GreenfootImage("gokuRightJump2.png");
    static GreenfootImage rightJump3 = new GreenfootImage("gokuRightJump3.png");
    static GreenfootImage leftJump1 = new GreenfootImage("gokuLeftJump1.png");
    static GreenfootImage leftJump2 = new GreenfootImage("gokuLeftJump2.png");
    static GreenfootImage leftJump3 = new GreenfootImage("gokuLeftJump3.png");
    static GreenfootImage gokuRightStand1 = new GreenfootImage("gokuRightStand1.png");
    static GreenfootImage gokuRightStand2 = new GreenfootImage("gokuRightStand2.png");
    static GreenfootImage gokuRightStand3 = new GreenfootImage("gokuRightStand3.png");
    static GreenfootImage gokuLeftStand1 = new GreenfootImage("gokuLeftStand1.png");
    static GreenfootImage gokuLeftStand2 = new GreenfootImage("gokuLeftStand2.png");
    static GreenfootImage gokuLeftStand3 = new GreenfootImage("gokuLeftStand3.png");
    static GreenfootImage gokuMoveRight1 = new GreenfootImage("gokuMoveRight1.png");
    static GreenfootImage gokuMoveRight2 = new GreenfootImage("gokuMoveRight2.png");
    static GreenfootImage gokuMoveRight3 = new GreenfootImage("gokuMoveRight3.png");
    static GreenfootImage gokuMoveLeft1 = new GreenfootImage("gokuMoveLeft1.png");
    static GreenfootImage gokuMoveLeft2 = new GreenfootImage("gokuMoveLeft2.png");
    static GreenfootImage gokuMoveLeft3 = new GreenfootImage("gokuMoveLeft3.png");
    static GreenfootImage gokuRightPunch1 = new GreenfootImage("gokuRightPunch1.png");
    static GreenfootImage gokuRightPunch2 = new GreenfootImage("gokuRightPunch2.png");
    static GreenfootImage gokuRightPunch3 = new GreenfootImage("gokuRightPunch3.png");
    static GreenfootImage gokuRightPunch4 = new GreenfootImage("gokuRightPunch4.png");
    static GreenfootImage gokuLeftPunch1 = new GreenfootImage("gokuLeftPunch1.png");
    static GreenfootImage gokuLeftPunch2 = new GreenfootImage("gokuLeftPunch2.png");
    static GreenfootImage gokuLeftPunch3 = new GreenfootImage("gokuLeftPunch3.png");
    static GreenfootImage gokuLeftPunch4 = new GreenfootImage("gokuLeftPunch4.png");
    static GreenfootImage gokuRightJump1 = new GreenfootImage("gokuRightJump1.png");
    static GreenfootImage gokuRightJump2 = new GreenfootImage("gokuRightJump2.png");
    static GreenfootImage gokuRightJump3 = new GreenfootImage("gokuRightJump3.png");
    static GreenfootImage gokuLeftJump1 = new GreenfootImage("gokuLeftJump1.png");
    static GreenfootImage gokuLeftJump2 = new GreenfootImage("gokuLeftJump2.png");
    static GreenfootImage gokuLeftJump3 = new GreenfootImage("gokuLeftJump3.png");
    static GreenfootImage rightBlast1 = new GreenfootImage("gokuRightBlast1.png");
    static GreenfootImage rightBlast2 = new GreenfootImage("gokuRightBlast2.png");
    static GreenfootImage rightBlast3 = new GreenfootImage("gokuRightBlast3.png");
    static GreenfootImage rightBlast4 = new GreenfootImage("gokuRightBlast4.png");
    static GreenfootImage leftBlast1 = new GreenfootImage("gokuLeftBlast1.png");
    static GreenfootImage leftBlast2 = new GreenfootImage("gokuLeftBlast2.png");
    static GreenfootImage leftBlast3 = new GreenfootImage("gokuLeftBlast3.png");
    static GreenfootImage leftBlast4 = new GreenfootImage("gokuLeftBlast4.png");
    //sounds
    static GreenfootSound gokuPunchSound = new GreenfootSound("gokuPunch.wav");
    static GreenfootSound gokuBlastSound = new GreenfootSound("gokuBlast.wav");

    private char blastTimer;//limits amount of special attacks goku can do at a time
    private int speed = 5;//speed defines how fast goku will animate when attacking

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
        rightStand1 = gokuRightStand1;
        rightStand2 = gokuRightStand2;
        rightStand3 = gokuRightStand3;
        leftStand1 = gokuLeftStand1;
        leftStand2 = gokuLeftStand2;
        leftStand3 = gokuLeftStand3;
        moveRight1 = gokuMoveRight1;
        moveRight2 = gokuMoveRight2;
        moveRight3 = gokuMoveRight3;
        moveLeft1 = gokuMoveLeft1;
        moveLeft2 = gokuMoveLeft2;
        moveLeft3 = gokuMoveLeft3;
        rightPunch1 = gokuRightPunch1;
        rightPunch2 = gokuRightPunch2;
        rightPunch3 = gokuRightPunch3;
        rightPunch4 = gokuRightPunch4;
        leftPunch1 = gokuLeftPunch1;
        leftPunch2 = gokuLeftPunch2;
        leftPunch3 = gokuLeftPunch3;
        leftPunch4 = gokuLeftPunch4;
        punchSound = gokuPunchSound;
        setImage(rightStand1);
        gokuBlastSound.setVolume(80);

        moveSpeed = 6;
        specialAttackTrue = false;//tells when s.a key has been pressed from fighter class
        lightAttackTrue = false;//tells when l.a. key has been pressed from fighter class
        p1 = new P1();
        healthBar = (new Bar("Goku","HP",100,100));
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
        if(Greenfoot.isKeyDown("o") && blastTimer >= 72)
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
