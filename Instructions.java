import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)
import java.awt.Color;
import java.awt.Font;

import java.util.Calendar;

public class Instructions extends Actor
{
    public static final float FONT_SIZE = 16.0f;
    public static final int WIDTH = 200;
    public static final int HEIGHT = 140; 
    
    /**
     * Create a score board with dummy result for testing.
     */
    public Instructions()
    {
        makeImage(" Using the mouse drag the\n P1 and P2 cursors onto a\n character, then press the\n Enter key to play\n");
    }

    /**
     * Make the score board image.
     */
    private void makeImage(String title)
    {
        GreenfootImage image = new GreenfootImage(WIDTH, HEIGHT);
        image.setColor(new Color(0, 0, 0, 10));
        image.fillRect(0, 0, WIDTH, HEIGHT);
        //image.setColor(new Color(255, 255, 255, 0));
        image.fillRect(5, 5, WIDTH-10, HEIGHT-10);
        Font font = image.getFont();
        font = font.deriveFont(FONT_SIZE);
        image.setFont(font);
        image.setColor(new Color(0, 76, 198));
        image.drawString(title, 10, 25);
        setImage(image);
    }
}
