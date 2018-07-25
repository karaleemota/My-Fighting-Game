import greenfoot.*;

/**
 * Write a description of class Knuckles1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MrIncredible extends Fighter
{
    static protected GreenfootImage rightJump1;
    static protected GreenfootImage rightJump2;
    static protected GreenfootImage rightJump3;
    static protected GreenfootImage leftJump1;
    static protected GreenfootImage leftJump2;
    static protected GreenfootImage leftJump3;
    private static GreenfootImage rightSA1 = new GreenfootImage("mrIncredibleRightSA1.png");//incredible's special attack images
    private static GreenfootImage rightSA2 = new GreenfootImage("mrIncredibleRightSA2.png");//incredible's special attack images
    private static GreenfootImage rightSA3 = new GreenfootImage("mrIncredibleRightSA3.png");//incredible's special attack images
    private static GreenfootImage rightSA4 = new GreenfootImage("mrIncredibleRightSA4.png");//incredible's special attack images
    private static GreenfootImage leftSA1 = new GreenfootImage("mrIncredibleLeftSA1.png");
    private static GreenfootImage leftSA2 = new GreenfootImage("mrIncredibleLeftSA2.png");
    private static GreenfootImage leftSA3 = new GreenfootImage("mrIncredibleLeftSA3.png");
    private static GreenfootImage leftSA4 = new GreenfootImage("mrIncredibleLeftSA4.png");
    
    private int moveCounter1;//to animate jump
    private char slideTimer = 0;//limits time he can use slide special attack
    private char moveCounter2 = 0;//time special attack animation
    private int speed = 3;//speed defines how fast mrIncred will animate when attacking
    public void act() 
    {
        groundHeight = getImage().getHeight()/2;//update image to use for gravity in fighter class
        applyGravity();
        animate();
        jump(10);
        lightAttack(5,4);
        specialAttack();
        moveRight(moveSpeed);
        moveLeft(moveSpeed);
        labelFollow();
        fallOffEdge();
        Player2Wins();
        checkForObjects();
    }    

    public MrIncredible()
    {
        rightStand1 = new GreenfootImage("mrIncredibleRightStand1.png");
        rightStand2 = new GreenfootImage("mrIncredibleRightStand2.png");
        rightStand3 = new GreenfootImage("mrIncredibleRightStand3.png");
        leftStand1 = new GreenfootImage("mrIncredibleLeftStand1.png");
        leftStand2 = new GreenfootImage("mrIncredibleLeftStand2.png");
        leftStand3 = new GreenfootImage("mrIncredibleLeftStand3.png");
        moveRight1 = new GreenfootImage("mrIncredibleMoveRight1.png");
        moveRight2 = new GreenfootImage("mrIncredibleMoveRight2.png");
        moveRight3 = new GreenfootImage("mrIncredibleMoveRight3.png");
        moveLeft1 = new GreenfootImage("mrIncredibleMoveLeft1.png");
        moveLeft2 = new GreenfootImage("mrIncredibleMoveLeft2.png");
        moveLeft3 = new GreenfootImage("mrIncredibleMoveLeft3.png");
        rightPunch1 = new GreenfootImage("mrIncredibleRightPunch1.png");
        rightPunch2 = new GreenfootImage("mrIncredibleRightPunch2.png");
        rightPunch3 = new GreenfootImage("mrIncredibleRightPunch3.png");
        rightPunch4 = new GreenfootImage("mrIncredibleRightPunch4.png");
        leftPunch1 = new GreenfootImage("mrIncredibleLeftPunch1.png");
        leftPunch2 = new GreenfootImage("mrIncredibleLeftPunch2.png");
        leftPunch3 = new GreenfootImage("mrIncredibleLeftPunch3.png");
        leftPunch4 = new GreenfootImage("mrIncredibleLeftPunch4.png");
        rightJump1 = new GreenfootImage("mrIncredibleRightJump1.png");
        rightJump2 = new GreenfootImage("mrIncredibleRightJump2.png");
        rightJump3 = new GreenfootImage("mrIncredibleRightJump3.png");
        leftJump1 = new GreenfootImage("mrIncredibleLeftJump1.png");
        leftJump2 = new GreenfootImage("mrIncredibleLeftJump2.png");
        leftJump3 = new GreenfootImage("mrIncredibleLeftJump3.png");
        punchSound = new GreenfootSound("knucklesPunch.wav");

        setImage(rightStand1);
        specialAttackTrue = false;//tells when s.a key has been pressed from fighter class
        lightAttackTrue = false;//tells when l.a. key has been pressed from fighter class
        moveSpeed = 4;
        p1 = new P1();
        healthBar = (new Bar("Mr. Incredible","HP",100,100));
    }

    protected void addedToWorld(World world)
    {
        world.addObject(healthBar, 152, 30);
        world.addObject(p1,getX(),getY()-getImage().getHeight()/2-15);
    }

    public Bar getHealthBar()
    {
        return healthBar;
    }

    public void specialAttack()
    {
        slideTimer++;
        if(Greenfoot.isKeyDown("o") && slideTimer >= 45)
        {          
            specialAttackTrue = true;
        }
        if(isFacedRight() && specialAttackTrue)
        {
            moveCounter2++;  
            setLocation(this.getX()+4,this.getY());//make mr incredible slide while doing attack
            if (moveCounter2 == speed)
            {
                setImage(rightSA1);
            }
            else if (moveCounter2 == (speed*2) )
            {
                setImage(rightSA2);
            }
            else if(moveCounter2 == (speed*3) )
            {
                setImage(rightSA3);//do SA here
                opponent = (Fighter2)getOneIntersectingObject(Fighter2.class);
                if(opponent!=null)
                {
                    opponent.healthBar.subtract(4);
                    punchSound.play();
                    //faced right punch, so push opponent to the right
                    opponent.setLocation(opponent.getX()+19,opponent.getY());
                }  
            }
            else if(moveCounter2 == (speed*4))
            {
                setImage(rightSA4);
                moveCounter2 = 0;//slide complete
                specialAttackTrue = false;
                slideTimer = 0;//reset SA timer so goku cant use it for time amount
            }         
        }
        else if(!isFacedRight() && specialAttackTrue)
        {
            moveCounter2++;   
            setLocation(this.getX()-4,this.getY());//make mr incredible slide while doing attack
            if (moveCounter2 == speed)
            {
                setImage(leftSA1);
            }
            else if (moveCounter2 == (speed*2) )
            {
                setImage(leftSA2);
            }
            else if(moveCounter2 == (speed*3) ) 
            {
                setImage(leftSA3);//do SA here
                opponent = (Fighter2)getOneIntersectingObject(Fighter2.class);
                if(opponent!=null)
                {           
                    opponent.healthBar.subtract(4);
                    punchSound.play();
                    //faced left punch, so push opponent to the left
                    opponent.setLocation(opponent.getX()-19,opponent.getY());
                } 
            }
            else if(moveCounter2 == (speed*4))
            {
                setImage(leftSA4);   
                moveCounter2 = 0;//punch complete
                specialAttackTrue =false;
                slideTimer = 0;
            }         
        }
    }

    public void jump(int height)
    {   //mostly just animates jump
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
                setImage(rightJump2);
            }
            else
            {
                setImage(leftJump2);
            }
        }
    }

    public void labelFollow()
    {
        p1.setLocation(this.getX(),this.getY()-getImage().getHeight()/2-15);
    }
}
