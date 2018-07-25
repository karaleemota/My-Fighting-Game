import greenfoot.*;

public class SelectCharacter extends World
{
    private int enterCounter;
    private boolean isGrabbed = false;
    static public GreenfootSound cursorSound = new GreenfootSound("cursor.wav");//sound when cursor is grabbed
    static public GreenfootSound bgmusic = new GreenfootSound("meleeMenu1.wav");
    static GreenfootImage selectCharBackground = new GreenfootImage("Super_Smash_Bros_Lawl_Poster_Background.png");
    private boolean clicked; 
    private MouseInfo mouse;
    static SelectWorld selectWorldScreen = new SelectWorld();
    static Cursor cursor = new Cursor();
    static Cursor2 cursor2 = new Cursor2();
    static CharacterPortrait sonicPortrait = new SonicPortrait();
    static CharacterPortrait tailsPortrait = new TailsPortrait();
    static CharacterPortrait knucklesPortrait = new KnucklesPortrait();
    static CharacterPortrait linkPortrait = new LinkPortrait();
    static CharacterPortrait pikachuPortrait = new PikachuPortrait();
    static CharacterPortrait megamanPortrait = new MegamanPortrait();
    static CharacterPortrait marioPortrait = new MarioPortrait();
    static CharacterPortrait gokuPortrait = new GokuPortrait();
    static CharacterPortrait mrIncrediblePortrait = new MrIncrediblePortrait();
    Instructions instructions = new Instructions();
    
    static String p1CharacterName;//will be used to determine which character p1 will be
    static String p2CharacterName;//will be used to determine which character p2 will be
    public SelectCharacter()
    {    
        //setBackground(selectCharBackground);
        super(684, 513, 1);
        enterCounter = 0;
        
        addObject(sonicPortrait,135,85);
        addObject(tailsPortrait,338,85);
        addObject(knucklesPortrait,540,85);
        addObject(linkPortrait,540,385);
        addObject(pikachuPortrait,135,237);
        addObject(megamanPortrait,338,237);
        //addObject(marioPortrait,547,233);
        addObject(gokuPortrait,135,385);
        addObject(mrIncrediblePortrait,338,385);
        addObject(instructions,526,243);
        addObject(cursor, 100, 100);
        addObject(cursor2, 200, 100);
        clicked = false;        
    }
    public void act()
    {
        setPaintOrder(Cursor.class);
        setPaintOrder(Cursor2.class);
        bgmusic.setVolume(70);
        bgmusic.playLoop();
        dragIcon(cursor);
        dragIcon(cursor2);
        enterCountDown();
        if (enterCounter == 0 && Greenfoot.isKeyDown("enter"))
        {
            Greenfoot.playSound("enter.wav");
            selectWorldScreen.setEnterCounter(110);
            Greenfoot.setWorld(selectWorldScreen); 
            p1CursorCharacter();
            p2CursorCharacter();
        }
    }
    public GreenfootSound getbgmusic()
    {
        return bgmusic;
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
            cursorSound.setVolume(85);
            cursorSound.play();
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
    public void p1CursorCharacter() // detects which character portrait cursor1 is on to generate character in world1
    {
        if(cursor.getP1Cursor() == sonicPortrait)
        {
             p1CharacterName = "Sonic";
        }
        else if(cursor.getP1Cursor() == tailsPortrait)
        {
             p1CharacterName = "Tails";
        }
        else if(cursor.getP1Cursor() == knucklesPortrait)
        {
             p1CharacterName = "Knuckles";
        }
        else if(cursor.getP1Cursor() == linkPortrait)
        {
             p1CharacterName = "Link";
        }
        else if(cursor.getP1Cursor() == pikachuPortrait)
        {
             p1CharacterName = "Pikachu";
        }
        else if(cursor.getP1Cursor() == megamanPortrait)
        {
             p1CharacterName = "Megaman";
        }
        else if(cursor.getP1Cursor() == marioPortrait)
        {
             p1CharacterName = "Mario";
        }
        else if(cursor.getP1Cursor() == null)
        {//make this random later!
             p1CharacterName = "Sonic";
        }
        else if(cursor.getP1Cursor() == gokuPortrait)
        {
             p1CharacterName = "Goku";
        }
        else if(cursor.getP1Cursor() == mrIncrediblePortrait)
        {
             p1CharacterName = "MrIncredible";
        }
    }
        public void p2CursorCharacter() // detects which character portrait cursor1 is on to generate character in world1
    {
        if(cursor2.getP2Cursor() == sonicPortrait)
        {
             p2CharacterName = "Sonic";
        }
        else if(cursor2.getP2Cursor() == tailsPortrait)
        {
             p2CharacterName = "Tails";
        }
        else if(cursor2.getP2Cursor() == knucklesPortrait)
        {
             p2CharacterName = "Knuckles";
        }
        else if(cursor2.getP2Cursor() == linkPortrait)
        {
             p2CharacterName = "Link";
        }
        else if(cursor2.getP2Cursor() == pikachuPortrait)
        {
             p2CharacterName = "Pikachu";
        }
        else if(cursor2.getP2Cursor() == megamanPortrait)
        {
             p2CharacterName = "Megaman";
        }
        else if(cursor2.getP2Cursor() == marioPortrait)
        {
             p2CharacterName = "Mario";
        }
        else if(cursor2.getP2Cursor() == gokuPortrait)
        {
             p2CharacterName = "Goku";
        }
        else if(cursor2.getP2Cursor() == mrIncrediblePortrait)
        {
            p2CharacterName = "MrIncredible";
        }
        else if(cursor2.getP2Cursor() == null)
        {//MAKE THIS RANDOM LATER
             p2CharacterName = "Sonic";
        }
    }
}