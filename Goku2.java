import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Goku here.
 * 
 * @author Karalee Mota
 * @version 7/8/18
 */
public class Goku2 extends Fighter2
{
    private int moveSpeed;
    private char moveCounter1 = 0;
    private char moveCounter2 = 0;//time special attack animation
    //images
    GreenfootImage rightJump1 = Goku.rightJump1;
    GreenfootImage rightJump2 = Goku.rightJump2;
    GreenfootImage rightJump3 = Goku.rightJump3;
    GreenfootImage leftJump1 = Goku.leftJump1;
    GreenfootImage leftJump2 = Goku.leftJump2;
    GreenfootImage leftJump3 = Goku.leftJump3;
    GreenfootImage rightBlast1 = Goku.rightBlast1;
    GreenfootImage rightBlast2 = Goku.rightBlast2;
    GreenfootImage rightBlast3 = Goku.rightBlast3;
    GreenfootImage rightBlast4 = Goku.rightBlast4;
    GreenfootImage leftBlast1 = Goku.leftBlast1;
    GreenfootImage leftBlast2 = Goku.leftBlast2;
    GreenfootImage leftBlast3 = Goku.leftBlast3;
    GreenfootImage leftBlast4 = Goku.leftBlast4;
    //sounds
    private static GreenfootSound gokuBlastSound = new GreenfootSound("gokuBlast.wav");
    
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
        Player1Wins();
        checkForObjects();
    }    

    public Goku2()
    {
        rightStand1 = Goku.gokuRightStand1;
        rightStand2 = Goku.gokuRightStand2;
        rightStand3 = Goku.gokuRightStand3;
        leftStand1 = Goku.gokuLeftStand1;
        leftStand2 = Goku.gokuLeftStand2;
        leftStand3 = Goku.gokuLeftStand3;
        moveRight1 = Goku.gokuMoveRight1;
        moveRight2 = Goku.gokuMoveRight2;
        moveRight3 = Goku.gokuMoveRight3;
        moveLeft1 = Goku.gokuMoveLeft1;
        moveLeft2 = Goku.gokuMoveLeft2;
        moveLeft3 = Goku.gokuMoveLeft3;
        rightPunch1 = Goku.gokuRightPunch1;
        rightPunch2 = Goku.gokuRightPunch2;
        rightPunch3 = Goku.gokuRightPunch3;
        rightPunch4 = Goku.gokuRightPunch4;
        leftPunch1 = Goku.gokuLeftPunch1;
        leftPunch2 = Goku.gokuLeftPunch2;
        leftPunch3 = Goku.gokuLeftPunch3;
        leftPunch4 = Goku.gokuLeftPunch4;
        punchSound = Goku.gokuPunchSound;
        setImage(leftStand1);
        gokuBlastSound.setVolume(80);
        
        moveSpeed = 6;
        specialAttackTrue = false;//tells when s.a key has been pressed from fighter class
        lightAttackTrue = false;//tells when l.a. key has been pressed from fighter class
        p2 = new P2();
        healthBar = (new Bar("Goku","HP",100,100));
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

    protected void addedToWorld(World world)
    {
        world.addObject(healthBar, 550, 30);
        world.addObject(p2,getX(),getY()-getImage().getHeight()/2-15);
    }

    public Bar getHealthBar()
    {
        return healthBar;
    }

    public void specialAttack()
    {
        blastTimer++;
        if(Greenfoot.isKeyDown(",") && blastTimer >= 72)
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
                getWorld().addObject(new Blast2(6), this.getX()+40, this.getY()-2);
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
                getWorld().addObject(new Blast2(-6), this.getX()-40, this.getY()-2);
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
        p2.setLocation(this.getX(),this.getY()-getImage().getHeight()/2-15);
    }  
}
