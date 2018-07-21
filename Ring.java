import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ring here.
 * 
 * @author Karalee
 * @version 7/20/18
 */
public class Ring extends Actor
{
    static GreenfootImage ring1 = new GreenfootImage("ring1.png");
    static GreenfootImage ring2 = new GreenfootImage("ring2.png");
    static GreenfootImage ring3 = new GreenfootImage("ring3.png");
    static GreenfootImage ring4 = new GreenfootImage("ring4.png");
    static GreenfootSound ringSound = new GreenfootSound("ring.wav");
    
    private boolean removed;//tells if ring has been removed
    Fighter player;//fihter that ring can possibly intersect
    Fighter2 player2;
    private char animateTimer;//control speed of animation
    public void act() 
    { 
        ringAction();
        animate();
    }    
    public Ring()
    {
        setImage(ring1);
        animateTimer = 0;
        ringSound.setVolume(100);
    }
    public void removeMe()//remove bullet from world
    {
        getWorld().removeObject(this);
        removed = true;
    }
    public void ringAction()//player 'eats' ring, health goes up,rign diappears
    {
        if(removed == false)
        {
            player = (Fighter)getOneIntersectingObject(Fighter.class);
            player2 = (Fighter2)getOneIntersectingObject(Fighter2.class);
            if(player != null && player2 != null)//touching two players at once
            {
                player.healthBar.add(2);
                player2.healthBar.add(2);
                ringSound.play();
                removeMe();
            }
            else if(player != null && player2 == null)//only player1 intersecting
            {
                player.healthBar.add(2);
                ringSound.play();
                removeMe();
            }
            else if(player == null && player2 != null)//only player2 intersecting
            {
                player2.healthBar.add(2);
                ringSound.play();
                removeMe();
            }
        }
    }
    private void animate()
    {
        animateTimer++;
        if(animateTimer >= 6 && removed == false)
        {
            if(getImage() == ring1)
            {
                setImage(ring2);
            }
            else if(getImage() == ring2)
            {
                setImage(ring3);
            }
            else if(getImage() == ring3)
            {
                setImage(ring4);
            }
            else
            {
                setImage(ring1);
            }
            animateTimer = 0;
        }
    }
}
