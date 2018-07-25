import greenfoot.*;

/**
 * Write a description of class Tails here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tails2 extends Fighter2
{
    //images
    GreenfootImage rightFly1 = Tails.rightFly1;
    GreenfootImage rightFly2 = Tails.rightFly2;
    GreenfootImage rightFly3 = Tails.rightFly3;
    GreenfootImage rightFly4 = Tails.rightFly4;
    GreenfootImage leftFly1 = Tails.leftFly1;
    GreenfootImage leftFly2 = Tails.leftFly2;
    GreenfootImage leftFly3 = Tails.leftFly3;
    GreenfootImage leftFly4 = Tails.leftFly4;
    GreenfootImage tailsRightJump1 = Tails.tailsRightJump1;
    GreenfootImage tailsLeftJump1 = Tails.tailsLeftJump1;
    GreenfootImage tailsRightJump2 = Tails.tailsRightJump2;
    GreenfootImage tailsLeftJump2 = Tails.tailsLeftJump2; 
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
        Player1Wins();//tells when player 1 has no hp left
        checkForObjects();
    }    

    public Tails2()
    {
        rightStand1 = Tails.tailsRightStand1;
        rightStand2 = Tails.tailsRightStand2;
        rightStand3 = Tails.tailsRightStand3;
        leftStand1 = Tails.tailsLeftStand1;
        leftStand2 = Tails.tailsLeftStand2;
        leftStand3 = Tails.tailsLeftStand3;
        moveRight1 = Tails.tailsMoveRight1;
        moveRight2 = Tails.tailsMoveRight2;
        moveRight3 = Tails.tailsMoveRight3;
        moveLeft1 = Tails.tailsMoveLeft1;
        moveLeft2 = Tails.tailsMoveLeft2;
        moveLeft3 = Tails.tailsMoveLeft3;
        rightPunch1 = Tails.tailsRightPunch1;
        rightPunch2 = Tails.tailsRightPunch2;
        rightPunch3 = Tails.tailsRightPunch3;
        rightPunch4 = Tails.tailsRightPunch4;
        leftPunch1 = Tails.tailsLeftPunch1;
        leftPunch2 = Tails.tailsLeftPunch2;
        leftPunch3 = Tails.tailsLeftPunch3;
        leftPunch4 = Tails.tailsLeftPunch4;
        punchSound = Tails.tailsPunchSound;
        setImage(leftStand1);

        specialAttackTrue = false;//tells when s.a key has been pressed from fighter class
        lightAttackTrue = false;//tells when l.a. key has been pressed from fighter class
        moveSpeed = 5;
        p2 = new P2();
        healthBar = (new Bar("Tails","HP",100,100));
    }

    protected void addedToWorld(World world)
    {
        world.addObject(healthBar, 550, 30);
        world.addObject(p2,getX()+7,getY()-getImage().getHeight()/2-15);
    }

    public void jump(int height)
    {

        if(Greenfoot.isKeyDown("up") && !Greenfoot.isKeyDown(".") && isFacedRight())
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
        else if(Greenfoot.isKeyDown("up") && !Greenfoot.isKeyDown(".") && !isFacedRight())
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
            if(!isOnGround() && !specialAttackTrue && !lightAttackTrue && !Greenfoot.isKeyDown("up"))//animate falling off ledge
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
            p2.setLocation(this.getX()+10,this.getY()-getImage().getHeight()/2-15);
        }
        else
        {
            p2.setLocation(this.getX()-10,this.getY()-getImage().getHeight()/2-15);
        }
    }
}
