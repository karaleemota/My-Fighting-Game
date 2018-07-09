import greenfoot.*;

/**
 * Write a description of class Link here.
 * 
 * @author Karalee Mota
 * @version 7/1/18
 */
public class Link extends Fighter
{
    private int moveSpeed;
    protected GreenfootSound linkSwingSword;
    int counter;
    int moveCounter2;
    private int moveCounter3 = 0;//time animation of throwing
    private char x = 0;//used for standing animation
    static private GreenfootImage rightJump1;
    static private GreenfootImage leftJump1;
    //images below will be used when link is carrying bomb
    static private GreenfootImage linkRightStandBomb1;
    static private GreenfootImage linkRightStandBomb2;
    static private GreenfootImage linkRightStandBomb3;
    static private GreenfootImage linkLeftStandBomb1;
    static private GreenfootImage linkLeftStandBomb2;
    static private GreenfootImage linkLeftStandBomb3;
    static private GreenfootImage linkMoveRightBomb1 = new GreenfootImage("linkMoveRightBomb1.png");
    static private GreenfootImage linkMoveRightBomb2 = new GreenfootImage("linkMoveRightBomb2.png");
    static private GreenfootImage linkMoveRightBomb3 = new GreenfootImage("linkMoveRightBomb3.png"); 
    static private GreenfootImage linkMoveLeftBomb1 = new GreenfootImage("linkMoveLeftBomb1.png");
    static private GreenfootImage linkMoveLeftBomb2 = new GreenfootImage("linkMoveLeftBomb2.png");
    static private GreenfootImage linkMoveLeftBomb3 = new GreenfootImage("linkMoveLeftBomb3.png");
    //images below will be used when link isnt carrying a bomb
    static private GreenfootImage linkMoveRight1 = new GreenfootImage("linkMoveRight1.png");
    static private GreenfootImage linkMoveRight2 = new GreenfootImage("linkMoveRight2.png");
    static private GreenfootImage linkMoveRight3 = new GreenfootImage("linkMoveRight3.png");
    static private GreenfootImage linkMoveLeft1 = new GreenfootImage("linkMoveLeft1.png");
    static private GreenfootImage linkMoveLeft2 = new GreenfootImage("linkMoveLeft2.png");
    static private GreenfootImage linkMoveLeft3 = new GreenfootImage("linkMoveLeft3.png");
    static private GreenfootImage linkRightStand1 = new GreenfootImage("linkRightStand1.png");
    static private GreenfootImage linkRightStand2 = new GreenfootImage("linkRightStand2.png");
    static private GreenfootImage linkRightStand3 = new GreenfootImage("linkRightStand3.png");
    static private GreenfootImage linkLeftStand1 = new GreenfootImage("linkLeftStand1.png");
    static private GreenfootImage linkLeftStand2  = new GreenfootImage("linkLeftStand2.png");
    static private GreenfootImage linkLeftStand3 = new GreenfootImage("linkLeftStand3.png");
    static private GreenfootImage linkRightThrow1 = new GreenfootImage("linkRightThrowBomb1.png");
    static private GreenfootImage linkRightThrow2 = new GreenfootImage("linkRightThrowBomb2.png");
    static private GreenfootImage linkRightThrow3 = new GreenfootImage("linkRightThrowBomb3.png");
    static private GreenfootImage linkLeftThrow1 = new GreenfootImage("linkLeftThrowBomb1.png");
    static private GreenfootImage linkLeftThrow2 = new GreenfootImage("linkLeftThrowBomb2.png");
    static private GreenfootImage linkLeftThrow3 = new GreenfootImage("linkLeftThrowBomb3.png");
    //sounds below
    static private GreenfootSound linkBombDeploySound = new GreenfootSound("linkBombDeploy.wav");
    private boolean carryingBomb;//tells if link is carrying bomb
    private boolean throwBomb = false;
    private char bombTimer;//limits the amount of bombs that can be thrown at a time
    /**
     * Act - do whatever the Link wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        groundHeight = getImage().getHeight()/2;//update image to use for gravity in fighter class
        applyGravity();
        animate();
        jump(12);
        lightAttack(6,3);
        throwBomb();
        specialAttack();
        moveRight(moveSpeed);
        moveLeft(moveSpeed);
        labelFollow();
        fallOffEdge();
        Player2Wins();
    }    
    public Link()
    {
        rightStand1 = linkRightStand1;
        rightStand2 = linkRightStand2;
        rightStand3 = linkRightStand3;
        leftStand1 = linkLeftStand1;
        leftStand2 = linkLeftStand2;
        leftStand3 = linkLeftStand3;
        moveRight1 = linkMoveRight1;
        moveRight2 = linkMoveRight2;
        moveRight3 = linkMoveRight3;
        moveLeft1 = linkMoveLeft1;
        moveLeft2 = linkMoveLeft2;
        moveLeft3 = linkMoveLeft3;
        rightPunch1 = new GreenfootImage("linkRightPunch1.png");
        rightPunch2 = new GreenfootImage("linkRightPunch2.png");
        rightPunch3 = new GreenfootImage("linkRightPunch3.png");
        rightPunch4 = new GreenfootImage("linkRightPunch4.png");
        leftPunch1 = new GreenfootImage("linkLeftPunch1.png");
        leftPunch2 = new GreenfootImage("linkLeftPunch2.png");
        leftPunch3 = new GreenfootImage("linkLeftPunch3.png");
        leftPunch4 = new GreenfootImage("linkLeftPunch4.png");
        rightJump1 = new GreenfootImage("linkRightJump1.png");
        leftJump1 = new GreenfootImage("linkLeftJump1.png");
        linkRightStandBomb1 = new GreenfootImage("linkRightStandBomb1.png");
        linkRightStandBomb2 = new GreenfootImage("linkRightStandBomb2.png");
        linkRightStandBomb3 = new GreenfootImage("linkRightStandBomb3.png");
        linkLeftStandBomb1 = new GreenfootImage("linkLeftStandBomb1.png");
        linkLeftStandBomb2 = new GreenfootImage("linkLeftStandBomb2.png");
        linkLeftStandBomb3 = new GreenfootImage("linkLeftStandBomb3.png");
        punchSound = new GreenfootSound("linkPunch.wav");
        linkSwingSword = new GreenfootSound("linkSwingSword.wav");
        setImage(rightStand1);
        
        moveSpeed = 5;
        moveCounter2 = 0;
        carryingBomb = false;//not carrying bom when starting
        specialAttackTrue = false;//tells when s.a key has been pressed from fighter class
        p1 = new P1();
        counter = 0;
        healthBar = (new Bar("Link","HP",100,100));
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
    public  void lightAttack(int speed, int damage)//can only swing sword if not carrying bomb
    {
        if(Greenfoot.isKeyDown("p") && !carryingBomb)
        {         
            lightAttackTrue = true;
        }
        if(isFacedRight() && lightAttackTrue)
        {
            moveCounter2++;                
            if (moveCounter2 == speed)
            {
                setImage(rightPunch1);
            }
            else if (moveCounter2 == (speed*2) )
            {
                setImage(rightPunch2);
                linkSwingSword.play();
            }
            else if(moveCounter2 == (speed*3) )
            {
                setImage(rightPunch3);
            }
            else if(moveCounter2 == (speed*4))
            {
                setImage(rightPunch4);
                opponent = (Fighter2)getOneIntersectingObject(Fighter2.class);
                if(opponent!=null)
                {
                    opponent.healthBar.subtract(damage);
                    punchSound.play();
                }   
                moveCounter2 = 0;//punch complete 
                lightAttackTrue =false;
            }         
        }
        else if(!isFacedRight() && lightAttackTrue)
        {
            moveCounter2++;               
            if (moveCounter2 == speed)
            {
                setImage(leftPunch1);
            }
            else if (moveCounter2 == (speed*2) )
            {
                setImage(leftPunch2);
                linkSwingSword.play();
            }
            else if(moveCounter2 == (speed*3) )
            {
                setImage(leftPunch3);
            }
            else if(moveCounter2 == (speed*4))
            {
                setImage(leftPunch4);
                opponent = (Fighter2)getOneIntersectingObject(Fighter2.class);
                if(opponent!=null)
                {           
                    opponent.healthBar.subtract(damage);
                    punchSound.play();
                }   
                moveCounter2 = 0;//punch complete
                lightAttackTrue =false;
            }         
        }
        }
    public void jump(int height)
    {
          if(Greenfoot.isKeyDown("w") && !carryingBomb)//cant jump while carrying bomb
          {
              if(isFacedRight())
                {
                      setImage(rightJump1);
                }
                else if(!isFacedRight())
                {
                     setImage(leftJump1);
                }
              setLocation(getX(),getY()-height);
          }
    }
    public void specialAttack()
    {
        bombTimer++;
        if(Greenfoot.isKeyDown("o"))
        {
            if(!carryingBomb && (bombTimer > 80))//not carrying bomb, so now carry it
            {
                carryingBomb = true;//now carryin bomb
                linkBombDeploySound.play();//sound effect to take out bomb
                
                //change images so he's carrying bomb
                rightStand1 = linkRightStandBomb1;
                rightStand2 = linkRightStandBomb2;
                rightStand3 = linkRightStandBomb3;
                leftStand1 = linkLeftStandBomb1;
                leftStand2 = linkLeftStandBomb2;
                leftStand3 = linkLeftStandBomb3;
                moveRight1 = linkMoveRightBomb1;
                moveRight2 = linkMoveRightBomb2;
                moveRight3 = linkMoveRightBomb3;
                moveLeft1 = linkMoveLeftBomb1;
                moveLeft2 = linkMoveLeftBomb2;
                moveLeft3 = linkMoveLeftBomb3;
                
                bombTimer = 0;
            }
            else if(carryingBomb && bombTimer > 30)//is carrying bomb, so throw it
            {
                if(isFacedRight())
                {
                    setImage(linkRightThrow1);
                }
                else//faced left so make bomb facing left
                {
                    setImage(linkLeftThrow1);
                }
                throwBomb = true;//now throwBomb will execute and animate bomb throwing
                //change images so he's not carrying bomb}
                rightStand1 = linkRightStand1;
                rightStand2 = linkRightStand2;
                rightStand3 = linkRightStand3;
                leftStand1 = linkLeftStand1;
                leftStand2 = linkLeftStand2;
                leftStand3 = linkLeftStand3;
                moveRight1 = linkMoveRight1;
                moveRight2 = linkMoveRight2;
                moveRight3 = linkMoveRight3;
                moveLeft1 = linkMoveLeft1;
                moveLeft2 = linkMoveLeft2;
                moveLeft3 = linkMoveLeft3;
                
                bombTimer = 0;
            }
        }
    }
    private void throwBomb()//helps animate link throwing bomb
    {
        if(throwBomb == true)
        {
            moveCounter3++;
            if(isFacedRight())
            {   
                if(moveCounter3 == 5)
                {
                   getWorld().addObject(new Bomb(true,10,16), this.getX()+43, this.getY()-41);
                   setImage(linkRightThrow2);
                }
                else if(moveCounter3 == 10)
                {
                   setImage(linkRightThrow3);
                   moveCounter3 = 0;
                   throwBomb = false;
                   carryingBomb = false;//bomb has been thrown
                }
            }
            else//faced left so make bomb facing left
            {
                if(moveCounter3 == 5)
                {
                   getWorld().addObject(new Bomb(false,10,16), this.getX()-43, this.getY()-41);
                   setImage(linkLeftThrow2);
                }
                else if(moveCounter3 == 10)
                {
                   setImage(linkLeftThrow3);
                   moveCounter3 = 0;
                   throwBomb = false;
                   carryingBomb = false;//bomb has been thrown
                }
            }
        }
    }
    protected void animate()
    {
        x++;
        if (x == 8)
        {
            if(isOnGround() && isFacedRight() && !Greenfoot.isKeyDown("d") && !Greenfoot.isKeyDown("p")
               && !Greenfoot.isKeyDown("a") && !lightAttackTrue && !throwBomb)
            {
                if (getImage() == rightStand1 )
                {
                    setImage(rightStand2);
                }
                else if (getImage() == rightStand2 )
                {
                    setImage(rightStand3);
                }
                else if ( getImage() != rightStand1 )
                {
                    setImage(rightStand1);
                }
            }
            
            else if(isOnGround() && !isFacedRight() && !Greenfoot.isKeyDown("a") && !Greenfoot.isKeyDown("p")
                    && !Greenfoot.isKeyDown("d") && !lightAttackTrue && !throwBomb)
            {
                if (getImage() == leftStand1 )
                {
                    setImage(leftStand2);
                }
                else if (getImage() == leftStand2 )
                {
                    setImage(leftStand3);
                }
                else if ( getImage() != leftStand1 )
                {
                    setImage(leftStand1);
                }
            }
            x = 0;
        }
    }
    public void labelFollow()
    {
        if(facedRight == true)
        {
            p1.setLocation(this.getX()-10,this.getY()-getImage().getHeight()/2-15);
        }
        else
        {
            p1.setLocation(this.getX()+10,this.getY()-getImage().getHeight()/2-15);
        }
    }
}