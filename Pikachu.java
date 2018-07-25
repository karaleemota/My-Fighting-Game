import greenfoot.*;

/**
 * Write a description of class Pikachu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pikachu extends Fighter
{
    //images
    static GreenfootImage pikachuRightStand1 = new GreenfootImage("pikachuRightStand1.png");
    static GreenfootImage pikachuRightStand2 = new GreenfootImage("pikachuRightStand2.png");
    static GreenfootImage pikachuRightStand3 = new GreenfootImage("pikachuRightStand3.png");
    static GreenfootImage pikachuLeftStand1 = new GreenfootImage("pikachuLeftStand1.png");
    static GreenfootImage pikachuLeftStand2 = new GreenfootImage("pikachuLeftStand2.png");
    static GreenfootImage pikachuLeftStand3 = new GreenfootImage("pikachuLeftStand3.png");
    static GreenfootImage pikachuMoveRight1 = new GreenfootImage("pikachuMoveRight1.png");
    static GreenfootImage pikachuMoveRight2 = new GreenfootImage("pikachuMoveRight2.png");
    static GreenfootImage pikachuMoveRight3 = new GreenfootImage("pikachuMoveRight3.png");
    static GreenfootImage pikachuMoveLeft1 = new GreenfootImage("pikachuMoveLeft1.png");
    static GreenfootImage pikachuMoveLeft2 = new GreenfootImage("pikachuMoveLeft2.png");
    static GreenfootImage pikachuMoveLeft3 = new GreenfootImage("pikachuMoveLeft3.png");
    static GreenfootImage pikachuRightPunch1 = new GreenfootImage("pikachuRightPunch1.png");
    static GreenfootImage pikachuRightPunch2 = new GreenfootImage("pikachuRightPunch2.png");
    static GreenfootImage pikachuRightPunch3 = new GreenfootImage("pikachuRightPunch3.png");
    static GreenfootImage pikachuRightPunch4 = new GreenfootImage("pikachuRightPunch4.png");
    static GreenfootImage pikachuLeftPunch1 = new GreenfootImage("pikachuLeftPunch1.png");
    static GreenfootImage pikachuLeftPunch2 = new GreenfootImage("pikachuLeftPunch2.png");
    static GreenfootImage pikachuLeftPunch3 = new GreenfootImage("pikachuLeftPunch3.png");
    static GreenfootImage pikachuLeftPunch4 = new GreenfootImage("pikachuLeftPunch4.png");
    static GreenfootImage rightJump1 = new GreenfootImage("pikachuRightJump1.png");
    static GreenfootImage rightThunderBolt1 = new GreenfootImage("pikachuRightThunderBolt1.png");
    static GreenfootImage rightThunderBolt2 = new GreenfootImage("pikachuRightThunderBolt2.png");
    static GreenfootImage rightThunderBolt3 = new GreenfootImage("pikachuRightThunderBolt3.png");
    static GreenfootImage rightThunderBolt4 = new GreenfootImage("pikachuRightThunderBolt4.png");
    static GreenfootImage leftThunderBolt1 = new GreenfootImage("pikachuLeftThunderBolt1.png");
    static GreenfootImage leftThunderBolt2 = new GreenfootImage("pikachuLeftThunderBolt2.png");
    static GreenfootImage leftThunderBolt3 = new GreenfootImage("pikachuLeftThunderBolt3.png");
    static GreenfootImage leftThunderBolt4 = new GreenfootImage("pikachuLeftThunderBolt4.png");
    static GreenfootImage leftJump1 = new GreenfootImage("pikachuLeftJump1.png");
    //sounds
    static GreenfootSound pikachuPunchSound = new GreenfootSound("pikachuPunch.wav");
    
    private int moveanimateSpeed;
    private int moveCounter2;
    private char boltTimer;//limits amt of bolts at a time
    private int animateSpeed = 5;//animateSpeed defines how fast goku will animate
    
    public void act() 
    {
        groundHeight = getImage().getHeight()/2;//update image to use for gravity in fighter class
        applyGravity();
        animate();
        jump(11);
        lightAttack(4,2);
        specialAttack();
        moveRight(moveanimateSpeed);
        moveLeft(moveanimateSpeed);
        labelFollow();
        fallOffEdge();
        Player2Wins();
        checkForObjects();
    }    

    public Pikachu()
    {
        rightStand1 = pikachuRightStand1;
        rightStand2 = pikachuRightStand2;
        rightStand3 = pikachuRightStand3;
        leftStand1 = pikachuLeftStand1;
        leftStand2 = pikachuLeftStand2;
        leftStand3 = pikachuLeftStand3;
        moveRight1 = pikachuMoveRight1;
        moveRight2 = pikachuMoveRight2;
        moveRight3 = pikachuMoveRight3;
        moveLeft1 = pikachuMoveLeft1;
        moveLeft2 = pikachuMoveLeft2;
        moveLeft3 = pikachuMoveLeft3;
        rightPunch1 = pikachuRightPunch1;
        rightPunch2 = pikachuRightPunch2;
        rightPunch3 = pikachuRightPunch3;
        rightPunch4 = pikachuRightPunch4;
        leftPunch1 = pikachuLeftPunch1;
        leftPunch2 = pikachuLeftPunch2;
        leftPunch3 = pikachuLeftPunch3;
        leftPunch4 = pikachuLeftPunch4;
        punchSound = pikachuPunchSound;
        setImage(rightStand1);

        specialAttackTrue = false;//tells when s.a key has been pressed from fighter class
        lightAttackTrue = false;//tells when l.a. key has been pressed from fighter class
        moveanimateSpeed = 6;
        moveCounter2 = 0;
        boltTimer = 0;
        p1 = new P1();
        healthBar = (new Bar("Pikachu","HP",100,100));
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

    public void jump(int height)
    {
        
        if(Greenfoot.isKeyDown("w") && vSpeed > -1 && !hitSpring )
        {
            if(isFacedRight())
            {
                if(getImage()  != rightJump1 || isOnGround())
                {
                    setImage(rightJump1);
                }
            }
            else if(!isFacedRight())
            {
                if (getImage()  != leftJump1 || isOnGround())
                {
                    setImage(leftJump1);
                }
            }
            setLocation(getX(),getY()-height);
        }
        if(!isOnGround() && !specialAttackTrue && !lightAttackTrue && !Greenfoot.isKeyDown("w"))//animate falling off ledge
        {
            if(isFacedRight())
            {
                setImage(rightJump1);
            }
            else
            {
                setImage(leftJump1);
            }
        }
    }

    public void specialAttack()
    {
        boltTimer++;
        if(Greenfoot.isKeyDown("o") && boltTimer >= 66)
        {
            specialAttackTrue = true;
        }
        if(isFacedRight() && specialAttackTrue)
        {
            moveCounter2++;                
            if (moveCounter2 == animateSpeed)
            {
                setImage(rightThunderBolt1);
            }
            else if (moveCounter2 == (animateSpeed*2) )
            {
                setImage(rightThunderBolt2);
            }
            else if(moveCounter2 == (animateSpeed*3) )
            {
                setImage(rightThunderBolt3);//do ThunderBolt here
                
                getWorld().addObject(new ThunderBolt(4), this.getX()+40, this.getY());
            }
            else if(moveCounter2 == (animateSpeed*4))
            {
                setImage(rightThunderBolt4);
                moveCounter2 = 0;//punch complete
                specialAttackTrue =false;
                boltTimer = 0;//reset ThunderBolt timer so goku cant use it for time amount
            }         
        }
        else if(!isFacedRight() && specialAttackTrue)
        {
            moveCounter2++;               
            if (moveCounter2 == animateSpeed)
            {
                setImage(leftThunderBolt1);
            }
            else if (moveCounter2 == (animateSpeed*2) )
            {
                setImage(leftThunderBolt2);
            }
            else if(moveCounter2 == (animateSpeed*3) ) 
            {
                setImage(leftThunderBolt3);//do ThunderBolt here
                
                getWorld().addObject(new ThunderBolt(-4), this.getX()-40, this.getY());
            }
            else if(moveCounter2 == (animateSpeed*4))
            {
                setImage(leftThunderBolt4);   
                moveCounter2 = 0;//punch complete
                specialAttackTrue =false;
                boltTimer = 0;
            }         
        }
    }

    public void labelFollow()
    {
        p1.setLocation(this.getX(),this.getY()-getImage().getHeight()/2-15);
    }

}

