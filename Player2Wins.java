import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)
import java.awt.Color;
import java.awt.Font;

import java.util.Calendar;
/**
 * Write a description of class player2Wins here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player2Wins extends Actor
{
    public static final float FONT_SIZE = 48.0f;
    public static final int WIDTH = 400; 
    public static final int HEIGHT = 300;
    
    /**
     * Create a score board with dummy result for testing.
     */
    public Player2Wins()
    {
        makeImage("\nPlayer 2 Wins!");
    }

    /**
     * Create a score board for the final result.
     */
    public Player2Wins(int score)
    {
        
    }

    /**
     * Make the score board image.
     */
    private void makeImage(String title)
    {
        GreenfootImage image = new GreenfootImage(WIDTH, HEIGHT);
        image.setColor(new Color(0,0,255, 128));
        image.fillRect(0, 0, WIDTH, HEIGHT);
        image.setColor(new Color(0, 0, 0, 128));
        image.fillRect(5, 5, WIDTH-10, HEIGHT-10);
        Font font = image.getFont();
        font = font.deriveFont(FONT_SIZE);
        image.setFont(font);
        image.setColor(Color.WHITE);
        image.drawString(title, 60, 100);
        setImage(image);
    }
}
