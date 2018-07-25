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
    static GreenfootImage ring5 = new GreenfootImage("ring5.png");//make ring invisible to 'despawn' it
    static GreenfootSound ringSound = new GreenfootSound("ring.wav");
    
    private boolean inWorld;//tells if ring is invisible and has been 'removed'
    private boolean removed;//tells if ring has been removed and memory of it has been cleared
    Fighter player;//fihter that ring can possibly intersect
    Fighter2 player2;
    private char animateTimer;//control speed of animation
    private int respawnTime;//determines how long until the ring will respawn into the world. 
    private int respawnCnt;//increments every time 'act' is called to time the respawn of the ring
    //negative value means it never respawns. each digit is equal to one call of the act method
    public void act() 
    { 
        ringAction();
        animate();
        respawn();//respawn if respanTime > -1
    }    
    public Ring()
    {
        setImage(ring1);
        animateTimer = 0;
        ringSound.setVolume(100);
        respawnTime = -1;
        respawnCnt = 0;
        inWorld = true;
        removed = false;
    }
    public Ring(int respawnTime_)
    {
        setImage(ring1);
        animateTimer = 0;
        ringSound.setVolume(100);
        respawnTime = respawnTime_;
        respawnCnt = 0;
        inWorld = true;
        removed = false;
    }
    public void removeMe()//remove bullet from world
    {
        inWorld = false;
        removed = true;
        getWorld().removeObject(this);
    }
    public void ringAction()//player 'eats' ring, health goes up,ring diappears
    {
        if(!removed && inWorld)
        {
            player = (Fighter)getOneIntersectingObject(Fighter.class);
            player2 = (Fighter2)getOneIntersectingObject(Fighter2.class);
            if(player != null && player2 != null)//touching two players at once
            {
                player.healthBar.add(1);
                player2.healthBar.add(1);
                ringSound.play();
                if(respawnTime <= 0)//respawn time is negative, so no respawn
                {
                    removeMe();
                }
                else//respawn time is not negative, so respawn later
                {
                    inWorld = false;//'remove' from world temporarly
                }
            }
            else if(player != null && player2 == null)//only player1 intersecting
            {
                player.healthBar.add(2);
                ringSound.play();
                if(respawnTime <= 0)//respawn time is negative, so no respawn
                {
                    removeMe();
                }
                else//respawn time is not negative, so respawn later
                {
                    inWorld = false;//'remove' from world temporarly
                }
            }
            else if(player == null && player2 != null)//only player2 intersecting
            {
                player2.healthBar.add(2);
                ringSound.play();
                if(respawnTime <= 0)//respawn time is negative, so no respawn
                {
                    removeMe();
                }
                else//respawn time is not negative, so respawn later
                {
                    inWorld = false;//'remove' from world temporarly 
                }
            }
        }
    }
    private void animate()
    {
        animateTimer++;
        if(!inWorld && !removed)//'removed' from world, but will respawn later
        {
            setImage(ring5);
        }
        if(animateTimer >= 6 && !removed  && inWorld)
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
    private void respawn()//respawns ring
    {
        if(!removed && respawnTime > 0 && !inWorld)//positive respawnTime, so will respawn
        {
            respawnCnt++;
            if(respawnCnt >= respawnTime)//time reached to respawn!
            {
                inWorld = true;//add back to world by making ring visible
                respawnCnt = 0;
            }
        }
        //do nothing if respawn time is negative
    }
    public int getRespawnTime()//returns reswpan time
    {
        return respawnTime;
    }
}
