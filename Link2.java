import greenfoot.*;

/**
 * Write a description of class Link here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Link2 extends Fighter2
{
    int moveCounter2;
    private int moveSpeed;
    protected GreenfootSound linkSwingSword;
    int counter;
    /**
     * Act - do whatever the Link wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        applyGravity();
        animate();
        jump(10);
        lightAttack(5,3);
        moveRight(moveSpeed);
        moveLeft(moveSpeed);
        labelFollow();
        fallOffEdge();
        Player1Wins();
    }    
    public Link2()
    {
        rightStand1 = new GreenfootImage("linkRightStand1.png");
        rightStand2 = new GreenfootImage("linkRightStand2.png");
        rightStand3 = new GreenfootImage("linkRightStand3.png");
        leftStand1 = new GreenfootImage("linkLeftStand1.png");
        leftStand2 = new GreenfootImage("linkLeftStand2.png");
        leftStand3 = new GreenfootImage("linkLeftStand3.png");
        moveRight1 = new GreenfootImage("linkMoveRight1.png");
        moveRight2 = new GreenfootImage("linkMoveRight2.png");
        moveRight3 = new GreenfootImage("linkMoveRight3.png");
        moveLeft1 = new GreenfootImage("linkMoveLeft1.png");
        moveLeft2 = new GreenfootImage("linkMoveLeft2.png");
        moveLeft3 = new GreenfootImage("linkMoveLeft3.png");
        rightPunch1 = new GreenfootImage("linkRightPunch1.png");
        rightPunch2 = new GreenfootImage("linkRightPunch2.png");
        rightPunch3 = new GreenfootImage("linkRightPunch3.png");
        rightPunch4 = new GreenfootImage("linkRightPunch4.png");
        leftPunch1 = new GreenfootImage("linkLeftPunch1.png");
        leftPunch2 = new GreenfootImage("linkLeftPunch2.png");
        leftPunch3 = new GreenfootImage("linkLeftPunch3.png");
        leftPunch4 = new GreenfootImage("linkLeftPunch4.png");
        punchSound = new GreenfootSound("linkPunch.wav");
        linkSwingSword = new GreenfootSound("linkSwingSword.wav");
        setImage(rightStand1);
        moveSpeed = 5;
        moveCounter2 = 0;
        p2 = new P2();
        counter = 0;
        healthBar = (new Bar("Link","HP",100,100));
    }
    protected void addedToWorld(World world)
    {
        world.addObject(healthBar,510, 30);
        world.addObject(p2,getX(),getY()-getImage().getHeight()/2-15);
    }
    public Bar getHealthBar()
    {
        return healthBar;
    }
    public  void lightAttack(int speed, int damage)
    {
        if(Greenfoot.isKeyDown("1"))
        {
            opponent = (Fighter)getOneIntersectingObject(Fighter.class);
            if(isFacedRight())
            {
                moveCounter2++;
                if(moveCounter2 == speed)
                {
                    if (getImage() == rightStand1 || getImage() == rightStand2 || getImage() == rightStand3
                         || getImage() == moveRight1 || getImage()==moveRight2 || getImage()==moveRight3)
                    {
                        setLocation(this.getX()-20,this.getY()-35);
                        setImage(rightPunch1);
                    }
                    else if (getImage() == rightPunch1 )
                    {
                        setLocation(this.getX()+5,this.getY()-30);
                        linkSwingSword.play();
                        setImage(rightPunch2);
                    }
                    else if(getImage() == rightPunch2 )
                    {
                        setLocation(getX()+35,getY()-30);
                        setImage(rightPunch3);
                    }
                    else if(getImage() == rightPunch3)
                    {
                        setLocation(getX()-35,getY()+10);
                        setImage(rightPunch4);
                        if(opponent!=null)
                        {
                            opponent.healthBar.subtract(damage);
                            punchSound.play();
                        }
                    }
                    else if ( getImage() != rightPunch1 )
                    {
                        setLocation(this.getX()-20,this.getY()-35);
                        setImage(rightPunch1);
                    }
                    moveCounter2 = 0;
                }
            }
            else if(!isFacedRight())
            {
                moveCounter2++;
                if(moveCounter2 == speed)
                {
                    if (getImage() == leftStand1 || getImage() == leftStand2 || getImage() == leftStand3
                         || getImage() == moveLeft1 || getImage()==moveLeft2 || getImage()==moveLeft3)
                    {
                        setLocation(getX()+20,getY()-30);
                        
                        setImage(leftPunch1);
                    }
                    else if (getImage() == leftPunch1 )
                    {
                        setLocation(getX()-3,getY());
                        linkSwingSword.play();
                        setImage(leftPunch2);
                    }
                    else if(getImage() == leftPunch2 )
                    {
                        setLocation(getX()-35,getY());
                        setImage(leftPunch3);
                    }
                    else if(getImage() == leftPunch3)
                    {
                        setLocation(getX()+35,getY()+10);
                        setImage(leftPunch4);
                        if(opponent!=null)
                        {
                            opponent.healthBar.subtract(damage);
                            punchSound.play();
                        }
                    }
                    else if ( getImage() != leftPunch1 )
                    {
                        setImage(leftPunch1);
                    }
                    moveCounter2 = 0;
                }
           }
           counter=0;
        }
    }
    public void heavyAttack()
    {
    }
    public void labelFollow()
    {
        if(facedRight == true)
        {
            p2.setLocation(this.getX()-10,this.getY()-getImage().getHeight()/2-15);
        }
        else
        {
            p2.setLocation(this.getX()+10,this.getY()-getImage().getHeight()/2-15);
        }
    }
 
}

