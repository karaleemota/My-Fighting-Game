import greenfoot.*;

public class Cursor extends Actor
{
    private int counter;
    static GreenfootImage cursorImage = new GreenfootImage("p1circle.PNG");
    public Cursor()
    {
        setImage(cursorImage);
        counter = 0;
    }
    public void act() 
    {
       
    }    
    protected void addedToWorld(World world)
    {
    }
    public void moveCursor()
    {
        if(Greenfoot.isKeyDown("left"))
        {
            move(-4);
        }
        if(Greenfoot.isKeyDown("right"))
        {
            move(4);
        }
        if(Greenfoot.isKeyDown("up"))
        {
            setLocation(getX(),getY()-4);
        }
        if(Greenfoot.isKeyDown("down"))
        {
            setLocation(getX(),getY()+4);
        }
    }
    public boolean spaceDown()
    {
        if(Greenfoot.isKeyDown("space"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public CharacterPortrait getP1Cursor()
    {
       CharacterPortrait characterPortrait = (CharacterPortrait)getOneIntersectingObject(CharacterPortrait.class);
       return characterPortrait;
    }
   
}
