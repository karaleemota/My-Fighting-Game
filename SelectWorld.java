import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SelectWorld here.
 * same code as selectCharacter, may use abstract parent class in future
 * @author Karalee Mota 
 * @version 7/16/18
 */
public class SelectWorld extends World
{
    
    
    static WorldPortrait world1Portrait = new World1Portrait();//world1
    static WorldPortrait world2Portrait = new World2Portrait();//dragon ball fighting arena  
    static SelectWorldCursor cursor = new SelectWorldCursor();
    FightWorld fightWorld;//holds world to be fought in
    
    private int enterCounter;//to stop enter key from being pressed too early
    private MouseInfo mouse;//detect mouse
    private boolean clicked; //detect when mouse is clicked
    private boolean isGrabbed = false;
    
    String worldSelectedName;//used to hold name of world selected
    public SelectWorld()
    {    
        super(625, 450, 1); 
        setBackground(SelectCharacter.selectCharBackground);
        enterCounter = 100;
        clicked = false;  
        
        addObject(world1Portrait,100,75);
        addObject(world2Portrait,300,75);
        addObject(cursor,90,75);
    }
    public void act()
    {
        setPaintOrder(Cursor.class);
        dragIcon(cursor);
        enterCountDown();
        if (enterCounter == 0 && Greenfoot.isKeyDown("enter"))
        {
           Greenfoot.playSound("enter.wav");
           toFightWorld();//code to set characters and world;
           Greenfoot.setWorld(fightWorld); //go to world to fight!
           SelectCharacter.bgmusic.stop();
        }
    }
    public void enterCountDown()
    {
        if(enterCounter > 0 )
        {
            enterCounter--;
        }
    }
    public boolean isClicked()
    {
        if(mouse.getButton() == 1 && clicked == false)
        {
            clicked = true;
        }
        else if(mouse.getButton() == 1 && clicked == true)
        {
            clicked = false;
        }
        return clicked;
    }
    public boolean mouseOnScreen()
    {
        if(mouse != null)
        {
            if(mouse.getX() == 1 || mouse.getX() == 624 || mouse.getY() == 1 || mouse.getY() == 449)
            {
                return false;
            }   
            else
            {
                return true;
            }
        }
        else 
        {
            return false;
        }
    }
    public void dragIcon(Actor icon)
    {
        if (Greenfoot.mousePressed(icon) && !isGrabbed)
        {
            // grab the object
            isGrabbed = true;
            SelectCharacter.cursorSound.setVolume(85);
            SelectCharacter.cursorSound.play();
            // the rest of this block will avoid this object being dragged UNDER other objects
            MouseInfo mi = Greenfoot.getMouseInfo();
            removeObject(icon);
            addObject(icon, mi.getX(), mi.getY());
            return;
        }
        // check for actual dragging of the object
        if ((Greenfoot.mouseDragged(icon)) && isGrabbed)
        {
            // follow the mouse
            MouseInfo mi = Greenfoot.getMouseInfo();
            icon.setLocation(mi.getX(), mi.getY());
            return;
        }
        // check for mouse button release
        if (Greenfoot.mouseDragEnded(icon) && isGrabbed)
        {
            // release the object
            isGrabbed = false;
            return;
        }
    }
    private void worldSelected()//this code will set the fight world
    {//returns world portrait cursor is touching
        if(cursor.getWorldCursor() == world1Portrait)
        {
            worldSelectedName = "world1";
        }
        else if(cursor.getWorldCursor() == world2Portrait)
        {
            worldSelectedName = "world2";
        }
        else//null
        {
            worldSelectedName = "world1";
        }
    }
    public void toFightWorld()
    {//takes player to world to fight
        worldSelected();
        if(worldSelectedName == "world1")
        {
            fightWorld = new World1();
        }
        else if(worldSelectedName == "world2")
        {
            fightWorld = new World2();
        }
        else//in case any errors
        {
            fightWorld = new World1();
        }
        if(SelectCharacter.p1CharacterName == "Tails")
        {
            fightWorld.addObject(new Tails(),100, 200);
        }
        else if(SelectCharacter.p1CharacterName == "Knuckles")
        {
            fightWorld.addObject(new Knuckles(),100, 200);
        }
        else if(SelectCharacter.p1CharacterName == "Link")
        {
            fightWorld.addObject(new Link(),100, 200);
        }
        else if(SelectCharacter.p1CharacterName == "Pikachu")
        {
            fightWorld.addObject(new Pikachu(),100, 200);
        }
        else if(SelectCharacter.p1CharacterName == "Megaman")
        {
            fightWorld.addObject(new Megaman(),100, 200);
        }
        else if(SelectCharacter.p1CharacterName == "Mario")
        {
            fightWorld.addObject(new Mario(),100, 200);
        }
        else if(SelectCharacter.p1CharacterName == "Goku")
        {
            fightWorld.addObject(new Goku(),100, 200);
        }
        else if(SelectCharacter.p1CharacterName == "MrIncredible")
        {
            fightWorld.addObject(new MrIncredible(),100, 200);
        }
        else//SONIC
        {//in case some error happens, just play as sonic
            fightWorld.addObject(new Sonic(),100, 200);
        }
        
    }
}
