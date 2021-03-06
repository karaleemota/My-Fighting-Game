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
    static WorldPortrait world3Portrait = new World3Portrait(); //sonic sunset hill
    static SelectWorldCursor cursor = new SelectWorldCursor();
    FightWorld fightWorld;//holds world to be fought in
    
    private int enterCounter;//to stop enter key from being pressed too early
    private MouseInfo mouse;//detect mouse
    private boolean clicked; //detect when mouse is clicked
    private boolean isGrabbed = false;
    
    String worldSelectedName;//used to hold name of world selected
    public SelectWorld()
    {    
        super(684, 513, 1);
        setBackground(SelectCharacter.selectCharBackground);
        enterCounter = 100;
        clicked = false;  
        
        addObject(world1Portrait,135,85);
        addObject(world2Portrait,338,85);
        addObject(world3Portrait,540,85);
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
           SelectCharacter.bgmusic.stop();
           Greenfoot.setWorld(fightWorld); //go to world to fight!
        }
    }
    public void enterCountDown()
    {
        if(enterCounter > 0)
        {
            enterCounter--;
        }
    }
    public int getEnterCounter()
    {
        return enterCounter;
    }
    public void setEnterCounter(int num)
    {
        enterCounter = num;
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
        else if(cursor.getWorldCursor() == world3Portrait)
        {
            worldSelectedName = "world3";
        }
        else//null
        {
            worldSelectedName = "world1";
        }
    }
    public void toFightWorld()
    {//takes player to world to fight
        worldSelected();
        setWorld();
        addPlayer1();
        addPlayer2();
    }
    public void setWorld()//sets world based on one chosen in menu
    {
        if(worldSelectedName == "world1")
        {
            fightWorld = new World1();
        }
        else if(worldSelectedName == "world2")
        {
            fightWorld = new World2();
        }
        else if(worldSelectedName == "world3")
        {
            fightWorld = new World3();
        }
        else//in case any errors
        {
            fightWorld = new World1();
        }
    }
    public void addPlayer1()//adds p1 to world based on character selected
    {
        if(SelectCharacter.p1CharacterName == "Tails")//player 1 added to world
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
    public void addPlayer2()//adds p2 to world based on character selected
    {
        if(SelectCharacter.p2CharacterName == "Tails")//player 1 added to world
        {
            fightWorld.addObject(new Tails2(),500, 200);
        }
        else if(SelectCharacter.p2CharacterName == "Knuckles")
        {
            fightWorld.addObject(new Knuckles2(),500, 200);
        }
        else if(SelectCharacter.p2CharacterName == "Link")
        {
            fightWorld.addObject(new Link2(),500, 200);
        }
        else if(SelectCharacter.p2CharacterName == "Pikachu")
        {
            fightWorld.addObject(new Pikachu2(),500, 200);
        }
        else if(SelectCharacter.p2CharacterName == "Megaman")
        {
            fightWorld.addObject(new Megaman2(),500, 200);
        }
        else if(SelectCharacter.p2CharacterName == "Mario")
        {
            fightWorld.addObject(new Mario2(),500, 200);
        }
        else if(SelectCharacter.p2CharacterName == "Goku")
        {
            fightWorld.addObject(new Goku2(),500, 200);
        }
        else if(SelectCharacter.p2CharacterName == "MrIncredible")
        {
            fightWorld.addObject(new MrIncredible2(),500, 200);
        }
        else//SONIC
        {//in case some error happens, just play as sonic
            fightWorld.addObject(new Sonic2(),500, 200);
        }
    }
}
