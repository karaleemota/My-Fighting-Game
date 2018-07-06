import greenfoot.*;

/**
 * Write a description of class Knuckles2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Knuckles2 extends Fighter2
{
    public void act() 
    {
        applyGravity();
        animate();
        jump(10);
        lightAttack(5,4);
        moveRight(moveSpeed);
        moveLeft(moveSpeed);
        labelFollow();
        Player1Wins();
    }    
    public Knuckles2()
    {
        rightStand1 = new GreenfootImage("knucklesRightStand1.gif");
        rightStand2 = new GreenfootImage("knucklesRightStand2.gif");
        rightStand3 = new GreenfootImage("knucklesRightStand3.gif");
        leftStand1 = new GreenfootImage("knucklesLeftStand1.gif");
        leftStand2 = new GreenfootImage("knucklesLeftStand2.gif");
        leftStand3 = new GreenfootImage("knucklesLeftStand3.gif");
        moveRight1 = new GreenfootImage("knucklesMoveRight1.gif");
        moveRight2 = new GreenfootImage("knucklesMoveRight2.gif");
        moveRight3 = new GreenfootImage("knucklesMoveRight3.gif");
        moveLeft1 = new GreenfootImage("knucklesMoveLeft1.gif");
        moveLeft2 = new GreenfootImage("knucklesMoveLeft2.gif");
        moveLeft3 = new GreenfootImage("knucklesMoveLeft3.gif");
        rightPunch1 = new GreenfootImage("knucklesRightPunch1.gif");
        rightPunch2 = new GreenfootImage("knucklesRightPunch2.gif");
        rightPunch3 = new GreenfootImage("knucklesRightPunch3.gif");
        rightPunch4 = new GreenfootImage("knucklesRightPunch4.gif");
        leftPunch1 = new GreenfootImage("knucklesLeftPunch1.gif");
        leftPunch2 = new GreenfootImage("knucklesLeftPunch2.gif");
        leftPunch3 = new GreenfootImage("knucklesLeftPunch3.gif");
        leftPunch4 = new GreenfootImage("knucklesLeftPunch4.gif");
        punchSound = new GreenfootSound("knucklesPunch.wav");
        moveSpeed = 4;
        p2 = new P2();
        healthBar = (new Bar("Knuckles","HP",400,100));
    }
    protected void addedToWorld(World world)
    {
        world.addObject(healthBar, 500, 30);
        world.addObject(p2,getX(),getY()-getImage().getHeight()/2-15);
    }
    public Bar getHealthBar()
    {
        return healthBar;
    }
    public void heavyAttack()
    {
    }
    public void labelFollow()
    {
        p2.setLocation(this.getX(),this.getY()-getImage().getHeight()/2-15);
    }
}
