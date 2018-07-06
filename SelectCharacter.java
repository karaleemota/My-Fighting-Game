import greenfoot.*;

public class SelectCharacter extends World
{
    private int enterCounter;
    private boolean isGrabbed;
    private GreenfootSound cursorSound;
    private GreenfootSound bgmusic = new GreenfootSound("meleeMenu1.mp3");
    private boolean clicked; 
    private MouseInfo mouse;
    World world1;
    Cursor cursor = new Cursor();
    Cursor2 cursor2 = new Cursor2();
    CharacterPortrait sonicPortrait = new SonicPortrait();
    CharacterPortrait tailsPortrait = new TailsPortrait();
    CharacterPortrait knucklesPortrait = new KnucklesPortrait();
    CharacterPortrait linkPortrait = new LinkPortrait();
    CharacterPortrait pikachuPortrait = new PikachuPortrait();
    CharacterPortrait megamanPortrait = new MegamanPortrait();
    CharacterPortrait marioPortrait = new MarioPortrait();
    CharacterPortrait gokuPortrait = new GokuPortrait();
    CharacterPortrait mrIncrediblePortrait = new MrIncrediblePortrait();
    public SelectCharacter()
    {    
        super(625, 450, 1);
        enterCounter = 100;
        cursorSound = new GreenfootSound("cursor.wav");
        addObject(cursor, 100, 100);
        addObject(cursor2, 200, 100);
        addObject(sonicPortrait,100,75);
        addObject(tailsPortrait,300,75);
        addObject(knucklesPortrait,500,75);
        addObject(linkPortrait,500,375);
        addObject(pikachuPortrait,100,227);
        addObject(megamanPortrait,300,227);
        addObject(marioPortrait,547,233);
        addObject(gokuPortrait,100,375);
        addObject(mrIncrediblePortrait,300,375);
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
            world1 = new World1(); 
            Greenfoot.setWorld(world1);
            p1CursorCharacter();
            p2CursorCharacter();
            bgmusic.stop();
        }
    }
    public GreenfootSound getbgmusic()
    {
        return bgmusic;
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
             world1.addObject(new Sonic(),100,200);
        }
        else if(cursor.getP1Cursor() == tailsPortrait)
        {
             world1.addObject(new Tails(),100,200);
        }
        else if(cursor.getP1Cursor() == knucklesPortrait)
        {
             world1.addObject(new Knuckles(),100,200);
        }
        else if(cursor.getP1Cursor() == linkPortrait)
        {
             world1.addObject(new Link(),100,200);
        }
        else if(cursor.getP1Cursor() == pikachuPortrait)
        {
             world1.addObject(new Pikachu(),100,200);
        }
        else if(cursor.getP1Cursor() == megamanPortrait)
        {
             world1.addObject(new Megaman(),100,200);
        }
        else if(cursor.getP1Cursor() == marioPortrait)
        {
             world1.addObject(new Mario(),100,200);
        }
        else if(cursor.getP1Cursor() == null)
        {
             world1.addObject(new Sonic(),100,200);
        }
        else if(cursor.getP1Cursor() == gokuPortrait)
        {
             world1.addObject(new Goku(),100,200);
        }
        else if(cursor.getP1Cursor() == mrIncrediblePortrait)
        {
             world1.addObject(new MrIncredible(),100,200);
        }
    }
        public void p2CursorCharacter() // detects which character portrait cursor1 is on to generate character in world1
    {
        if(cursor2.getP2Cursor() == sonicPortrait)
        {
             world1.addObject(new Sonic2(),460,200);
        }
        else if(cursor2.getP2Cursor() == tailsPortrait)
        {
             world1.addObject(new Tails2(),460,200);
        }
        else if(cursor2.getP2Cursor() == knucklesPortrait)
        {
             world1.addObject(new Knuckles2(),460,200);
        }
        else if(cursor2.getP2Cursor() == linkPortrait)
        {
             world1.addObject(new Link2(),460,200);
        }
        else if(cursor2.getP2Cursor() == pikachuPortrait)
        {
             world1.addObject(new Pikachu2(),460,200);
        }
        else if(cursor2.getP2Cursor() == megamanPortrait)
        {
             world1.addObject(new Megaman2(),460,200);
        }
        else if(cursor2.getP2Cursor() == marioPortrait)
        {
             world1.addObject(new Mario2(),460,200);
        }
        else if(cursor2.getP2Cursor() == gokuPortrait)
        {
             world1.addObject(new Goku2(),460,200);
        }
        else if(cursor2.getP2Cursor() == null)
        {
             world1.addObject(new Sonic2(),460,200);
        }
    }
}