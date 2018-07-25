import greenfoot.*;

/**
 * Write a description of class Tails here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tails extends Fighter
{
    static GreenfootImage tailsRightStand1 = new GreenfootImage("tailsRightStand1.gif");
    static GreenfootImage tailsRightStand2 = new GreenfootImage("tailsRightStand2.gif");
    static GreenfootImage tailsRightStand3 = new GreenfootImage("tailsRightStand3.gif");
    static GreenfootImage tailsLeftStand1 = new GreenfootImage("tailsLeftStand1.gif");
    static GreenfootImage tailsLeftStand2 = new GreenfootImage("tailsLeftStand2.gif");
    static GreenfootImage tailsLeftStand3 = new GreenfootImage("tailsLeftStand3.gif");
    static GreenfootImage tailsMoveRight1 = new GreenfootImage("tailsMoveRight1.gif");
    static GreenfootImage tailsMoveRight2 = new GreenfootImage("tailsMoveRight2.gif");
    static GreenfootImage tailsMoveRight3 = new GreenfootImage("tailsMoveRight3.gif");
    static GreenfootImage tailsMoveLeft1 = new GreenfootImage("tailsMoveLeft1.gif");
    static GreenfootImage tailsMoveLeft2 = new GreenfootImage("tailsMoveLeft2.gif");
    static GreenfootImage tailsMoveLeft3 = new GreenfootImage("tailsMoveLeft3.gif");
    static GreenfootImage tailsRightPunch1 = new GreenfootImage("tailsRightPunch1.gif");
    static GreenfootImage tailsRightPunch2 = new GreenfootImage("tailsRightPunch2.gif");
    static GreenfootImage tailsRightPunch3 = new GreenfootImage("tailsRightPunch3.gif");
    static GreenfootImage tailsRightPunch4 = new GreenfootImage("tailsRightPunch4.gif");
    static GreenfootImage tailsLeftPunch1 = new GreenfootImage("tailsLeftPunch1.gif");
    static GreenfootImage tailsLeftPunch2 = new GreenfootImage("tailsLeftPunch2.gif");
    static GreenfootImage tailsLeftPunch3 = new GreenfootImage("tailsLeftPunch3.gif");
    static GreenfootImage tailsLeftPunch4 = new GreenfootImage("tailsLeftPunch4.gif");
    static GreenfootImage rightFly1 = new GreenfootImage("tailsRightFly1.gif");
    static GreenfootImage rightFly2 = new GreenfootImage("tailsRightFly2.gif");
    static GreenfootImage rightFly3 = new GreenfootImage("tailsRightFly3.gif");
    static GreenfootImage rightFly4 = new GreenfootImage("tailsRightFly4.gif");
    static GreenfootImage leftFly1 = new GreenfootImage("tailsLeftFly1.gif");
    static GreenfootImage leftFly2 = new GreenfootImage("tailsLeftFly2.gif");
    static GreenfootImage leftFly3 = new GreenfootImage("tailsLeftFly3.gif");
    static GreenfootImage leftFly4 = new GreenfootImage("tailsLeftFly4.gif");
    static GreenfootImage tailsRightJump1 = new GreenfootImage("tailsRightJump1.png"); 
    static GreenfootImage tailsLeftJump1 = new GreenfootImage("tailsLeftJump1.png");
    static GreenfootImage tailsRightJump2 = new GreenfootImage("tailsRightJump2.png");
    static GreenfootImage tailsLeftJump2 = new GreenfootImage("tailsLeftJump2.png");
    //sounds
    static GreenfootSound tailsPunchSound = new GreenfootSound("tailsPunch.wav");
    private int flyCounter;
    private int flyCounter2;
    /**
     * Act - do whatever the Tails wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        groundHeight = getImage().getHeight()/2;//update image to use for gravity in fighter class
        animate();
        jump(4);//tails can fly!
        lightAttack(5,2);
        moveRight(moveSpeed);
        moveLeft(moveSpeed);
        labelFollow();//p1 label follows character
        fallOffEdge();
        Player2Wins();//tells when player 1 has no hp left
        checkForObjects();
    }    

    public Tails()
    {
        rightStand1 = tailsRightStand1;
        rightStand2 = tailsRightStand2;
        rightStand3 = tailsRightStand3;
        leftStand1 = tailsLeftStand1;
        leftStand2 = tailsLeftStand2;
        leftStand3 = tailsLeftStand3;
        moveRight1 = tailsMoveRight1;
        moveRight2 = tailsMoveRight2;
        moveRight3 = tailsMoveRight3;
        moveLeft1 = tailsMoveLeft1;
        moveLeft2 = tailsMoveLeft2;
        moveLeft3 = tailsMoveLeft3;
        rightPunch1 = tailsRightPunch1;
        rightPunch2 = tailsRightPunch2;
        rightPunch3 = tailsRightPunch3;
        rightPunch4 = tailsRightPunch4;
        leftPunch1 = tailsLeftPunch1;
        leftPunch2 = tailsLeftPunch2;
        leftPunch3 = tailsLeftPunch3;
        leftPunch4 = tailsLeftPunch4;
        punchSound = tailsPunchSound;
        setImage(rightStand1);

        specialAttackTrue = false;//tells when s.a key has been pressed from fighter class
        lightAttackTrue = false;//tells when l.a. key has been pressed from fighter class
        moveSpeed = 5;
        p1 = new P1();
        healthBar = (new Bar("Tails","HP",100,100));
    }

    protected void addedToWorld(World world)
    {
        world.addObject(healthBar, 125, 30);
        world.addObject(p1,getX()+7,getY()-getImage().getHeight()/2-15);
    }

    public void jump(int height)
    {

        if(Greenfoot.isKeyDown("w") && !Greenfoot.isKeyDown("p") && isFacedRight())
        {
            hitSpring = false;//no longer animate as if he is bouncing from spring
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
        else if(Greenfoot.isKeyDown("w") && !Greenfoot.isKeyDown("p") && !isFacedRight())
        {
            hitSpring = false;//no longer animate as if he is bouncing from spring
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
        else if(hitSpring)//launched from spring
        {
            applyGravity();

            if(vSpeed < 0)//going up
            {
                if(isFacedRight())
                {
                    setImage(tailsRightJump1);
                }
                else
                {
                    setImage(tailsLeftJump1);
                }
            }

            else //going down
            {
                if(isFacedRight())
                {
                    setImage(tailsRightJump2);
                }
                else
                {
                    setImage(tailsLeftJump2);
                }
            }
        }
        else
        {
            applyGravity();
            if(!isOnGround() && !specialAttackTrue && !lightAttackTrue && !Greenfoot.isKeyDown("w"))//animate falling off ledge
            {
                if(isFacedRight())
                {
                    setImage(rightFly2);
                }
                else
                {
                    setImage(leftFly2);
                }
            }
        }
    }

    public void specialAttack()
    {//will be implemented in the future..
    }

    public void labelFollow()
    {
        if(facedRight == true)
        {
            p1.setLocation(this.getX()+10,this.getY()-getImage().getHeight()/2-15);
        }
        else
        {
            p1.setLocation(this.getX()-10,this.getY()-getImage().getHeight()/2-15);
        }
    }
}
