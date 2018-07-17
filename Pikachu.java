import greenfoot.*;

/**
 * Write a description of class Pikachu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pikachu extends Fighter
{
    private int moveanimateSpeed;
    private int moveCounter2;
    private char boltTimer;//limits amt of bolts at a time
    private int animateSpeed = 5;//animateSpeed defines how fast goku will animate
    
    GreenfootImage rightJump1;
    GreenfootImage leftJump1;
    private static GreenfootImage rightThunderBolt1 = new GreenfootImage("pikachuRightThunderBolt1.png");
    private static GreenfootImage rightThunderBolt2 = new GreenfootImage("pikachuRightThunderBolt2.png");
    private static GreenfootImage rightThunderBolt3 = new GreenfootImage("pikachuRightThunderBolt3.png");
    private static GreenfootImage rightThunderBolt4 = new GreenfootImage("pikachuRightThunderBolt4.png");
    private static GreenfootImage leftThunderBolt1 = new GreenfootImage("pikachuLeftThunderBolt1.png");
    private static GreenfootImage leftThunderBolt2 = new GreenfootImage("pikachuLeftThunderBolt2.png");
    private static GreenfootImage leftThunderBolt3 = new GreenfootImage("pikachuLeftThunderBolt3.png");
    private static GreenfootImage leftThunderBolt4 = new GreenfootImage("pikachuLeftThunderBolt4.png");
    /**
     * Act - do whatever the pikachu wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
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
    }    

    public Pikachu()
    {
        rightStand1 = new GreenfootImage("pikachuRightStand1.png");
        rightStand2 = new GreenfootImage("pikachuRightStand2.png");
        rightStand3 = new GreenfootImage("pikachuRightStand3.png");
        leftStand1 = new GreenfootImage("pikachuLeftStand1.png");
        leftStand2 = new GreenfootImage("pikachuLeftStand2.png");
        leftStand3 = new GreenfootImage("pikachuLeftStand3.png");
        moveRight1 = new GreenfootImage("pikachuMoveRight1.png");
        moveRight2 = new GreenfootImage("pikachuMoveRight2.png");
        moveRight3 = new GreenfootImage("pikachuMoveRight3.png");
        moveLeft1 = new GreenfootImage("pikachuMoveLeft1.png");
        moveLeft2 = new GreenfootImage("pikachuMoveLeft2.png");
        moveLeft3 = new GreenfootImage("pikachuMoveLeft3.png");
        rightPunch1 = new GreenfootImage("pikachuRightPunch1.png");
        rightPunch2 = new GreenfootImage("pikachuRightPunch2.png");
        rightPunch3 = new GreenfootImage("pikachuRightPunch3.png");
        rightPunch4 = new GreenfootImage("pikachuRightPunch4.png");
        leftPunch1 = new GreenfootImage("pikachuLeftPunch1.png");
        leftPunch2 = new GreenfootImage("pikachuLeftPunch2.png");
        leftPunch3 = new GreenfootImage("pikachuLeftPunch3.png");
        leftPunch4 = new GreenfootImage("pikachuLeftPunch4.png");
        rightJump1 = new GreenfootImage("pikachuRightJump1.png");
        leftJump1 = new GreenfootImage("pikachuLeftJump1.png");
        punchSound = new GreenfootSound("pikachuPunch.wav");
        setImage(rightStand1);

        setImage(rightStand1);
        specialAttackTrue = false;//tells when s.a key has been pressed from fighter class
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
        
        if(Greenfoot.isKeyDown("w") )
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

