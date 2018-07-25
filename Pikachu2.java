import greenfoot.*;

/**
 * Write a description of class Pikachu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pikachu2 extends Fighter2
{
    //images
    GreenfootImage rightJump1 = Pikachu.rightJump1;
    GreenfootImage leftJump1 = Pikachu.leftJump1;
    GreenfootImage rightThunderBolt1 = Pikachu.rightThunderBolt1;
    GreenfootImage rightThunderBolt2 = Pikachu.rightThunderBolt2;
    GreenfootImage rightThunderBolt3 = Pikachu.rightThunderBolt3;
    GreenfootImage rightThunderBolt4 = Pikachu.rightThunderBolt4;
    GreenfootImage leftThunderBolt1 = Pikachu.leftThunderBolt1;
    GreenfootImage leftThunderBolt2 = Pikachu.leftThunderBolt2;
    GreenfootImage leftThunderBolt3 = Pikachu.leftThunderBolt3;
    GreenfootImage leftThunderBolt4 = Pikachu.leftThunderBolt4;
    
    private int moveanimateSpeed;
    private int moveCounter2;
    private char boltTimer;//limits amt of bolts at a time
    private int animateSpeed = 5;//animateSpeed defines how fast goku will animate
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
        Player1Wins();
        checkForObjects();
    }    

    public Pikachu2()
    {
        rightStand1 = Pikachu.pikachuRightStand1;
        rightStand2 = Pikachu.pikachuRightStand2;
        rightStand3 = Pikachu.pikachuRightStand3;
        leftStand1 = Pikachu.pikachuLeftStand1;
        leftStand2 = Pikachu.pikachuLeftStand2;
        leftStand3 = Pikachu.pikachuLeftStand3;
        moveRight1 = Pikachu.pikachuMoveRight1;
        moveRight2 = Pikachu.pikachuMoveRight2;
        moveRight3 = Pikachu.pikachuMoveRight3;
        moveLeft1 = Pikachu.pikachuMoveLeft1;
        moveLeft2 = Pikachu.pikachuMoveLeft2;
        moveLeft3 = Pikachu.pikachuMoveLeft3;
        rightPunch1 = Pikachu.pikachuRightPunch1;
        rightPunch2 = Pikachu.pikachuRightPunch2;
        rightPunch3 = Pikachu.pikachuRightPunch3;
        rightPunch4 = Pikachu.pikachuRightPunch4;
        leftPunch1 = Pikachu.pikachuLeftPunch1;
        leftPunch2 = Pikachu.pikachuLeftPunch2;
        leftPunch3 = Pikachu.pikachuLeftPunch3;
        leftPunch4 = Pikachu.pikachuLeftPunch4;
        punchSound = Pikachu.pikachuPunchSound;
        setImage(leftStand1);

        specialAttackTrue = false;//tells when s.a key has been pressed from fighter class
        lightAttackTrue = false;//tells when l.a. key has been pressed from fighter class
        moveanimateSpeed = 6;
        moveCounter2 = 0;
        boltTimer = 0;
        p2 = new P2();
        healthBar = (new Bar("Pikachu","HP",100,100));
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

    public void jump(int height)
    {
        if(Greenfoot.isKeyDown("up") && vSpeed > -1 && !hitSpring )
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
        if(!isOnGround() && !specialAttackTrue && !lightAttackTrue && !Greenfoot.isKeyDown("up"))//animate falling off ledge
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
        if(Greenfoot.isKeyDown(",") && boltTimer >= 66)
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
                
                getWorld().addObject(new ThunderBolt2(4), this.getX()+40, this.getY());
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
                
                getWorld().addObject(new ThunderBolt2(-4), this.getX()-40, this.getY());
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
        p2.setLocation(this.getX(),this.getY()-getImage().getHeight()/2-15);
    }

}

