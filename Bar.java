// AUTHOR: danpost
// Version: 2
// Last modified: February, 8, 2012
//this class is borrowed from the person above


import greenfoot.*;
import java.awt.Color;
import java.awt.Font;

/**
 * Class Bar: for use as a health bar or progress bar as well as for other types of measurements for visual of value in a bar form
 * DO NOT EDIT THIS CLASS as everything can be controlled through method calls.
 */
public class Bar extends Actor
{
    private int barWidth = 100; // the width of the color portion of the bar
    private int barHeight = 10; // the height of the color portion of the bar
    private int breakPercent = 20; // the percentage amount that changes the color of the bar
    private int breakValue = 20; // in tandem with breakPercent
    private boolean usingBreakValue = false;
    private boolean breakLow = true; // when true, with low-percent values bar is dangerColor, else safeColor; reversed when false
    private Color backgroundColor = new Color(0, 0, 0, 0); // the background color of the entire object
    private Color textColor = Color.BLACK; // the color of all text and the frame of the bar itself
    private Color safeColor = Color.GREEN; // the color of the bar while in the safe range
    private Color dangerColor = Color.RED; // the color of the bar while in the danger range
//  The color of the bar AT the breakpoint will be the average color between the safe color and the danger color
    private float fontSize = 18.0f; // the size of the text
    private int value = 0; // the current value of the bar
    private int maximumValue = 0; // the maximum value of the bar
    private int minimumValue = 0; // the minimum value of the bar
    private String referenceText = ""; // the title string (who or what the meter/bar is for)
    private String unitOfMeasure = ""; // the unit of measure of the bar (any quantitative standard of measurement) 
    private boolean showTextualUnits = true; // determines whether or not the textual quantity of the bar is to show
    
    /**
     * Bar Constructor: saves the initial values that are brought in and creates the bar image through the 'add(int)' call,
     * which sets the initial value of the bar and calls the 'newImage' method to build and set a new image for the bar.
     *
     * @param 'refText': a text string to specify what the bar is for (be specific enough so that all bars can be distinguished one from another)
     * @param 'unitType': a text string to specify what measure is being used in the bar ("percentage", "points", "frames per second", or whatever)
     * @param 'initValue': the value the bar should be initially set to
     * @param 'maxValue': the highest value the bar is allowed to hold
     */
    public Bar(String refText, String unitType, int initValue, int maxValue)
    {
        referenceText = refText;
        unitOfMeasure = unitType;
        maximumValue = maxValue;
        add(initValue);
    }
    
    /**
     * Method 'newImage': builds a new image for the bar, determined by the values set for it
     */
    private void newImage()
    {
        int barValue = (int) (barWidth * (value - minimumValue) / (maximumValue - minimumValue));
        GreenfootImage leftImg = new GreenfootImage(referenceText + " ", (int) fontSize, textColor, backgroundColor);
        GreenfootImage rightImg = (showTextualUnits) ? new GreenfootImage(" " + value + " " + unitOfMeasure, (int) fontSize, textColor, backgroundColor) : new GreenfootImage(1, 1);
        int maxX = (leftImg.getWidth() > rightImg.getWidth()) ? leftImg.getWidth() : rightImg.getWidth();
        GreenfootImage barImg = new GreenfootImage(barWidth + 4, barHeight + 4);
        barImg.setColor(backgroundColor);
        barImg.fill();
        barImg.setColor(textColor);
        barImg.drawRect(0, 0, barImg.getWidth() - 1, barImg.getHeight() - 1);
        if (value > minimumValue)
        {
            if (breakLow)
            {
                if (value > (usingBreakValue ? breakValue : (int) (breakPercent * (maximumValue - minimumValue) / 100 + minimumValue))) barImg.setColor(safeColor);
                else barImg.setColor(dangerColor);
            }
            else
            {
                if (value < (usingBreakValue ? breakValue : (int) (breakPercent * (maximumValue - minimumValue) / 100 + minimumValue))) barImg.setColor(safeColor);
                else barImg.setColor(dangerColor);
            }
            if (value == (usingBreakValue ? breakValue : (int) (breakPercent * (maximumValue - minimumValue) / 100 + minimumValue)))
            {
                int r = (int) ((safeColor.getRed() + dangerColor.getRed()) / 2);
                int g = (int) ((safeColor.getGreen() + dangerColor.getGreen()) / 2);
                int b = (int) ((safeColor.getBlue() + dangerColor.getBlue()) / 2);
                barImg.setColor(new Color(r, g, b));
            }
            barImg.fillRect(2, 2, barValue, barHeight);
        }
        int sumX = 2 * maxX + barImg.getWidth();
        int maxY = 0;
        if (leftImg.getHeight() > maxY) maxY = leftImg.getHeight();
        if (barImg.getHeight() > maxY) maxY = barImg.getHeight();
        if (rightImg.getHeight() > maxY) maxY = rightImg.getHeight();
        GreenfootImage image = new GreenfootImage(sumX, maxY);
        image.setColor(backgroundColor);
        image.fill();
        image.drawImage(leftImg, maxX - leftImg.getWidth(), (image.getHeight() - leftImg.getHeight()) / 2);
        image.drawImage(barImg, maxX, (image.getHeight() - barImg.getHeight()) / 2);
        image.drawImage(rightImg, maxX + barImg.getWidth(), (image.getHeight() - rightImg.getHeight()) / 2);
        setImage(image);
    }
    
    /**
     * Method 'add': add an amount to the value of the bar, checks to make sure the new value is between minimumValue and maximumValue,
     * then, calls 'newImage' to build and set the new image for the bar.
     *
     * @param 'amount': the amount to add (if not negative) or subtract (if negative) to the current value of the bar
     */
    public void add(int amount)
    {
        value += amount;
        checkValue();
        newImage();
    }
    
    /**
     * Method 'subtract': subtracts an amount from the value of the bar, checks to make sure the new value does not overstep its bounds,
     * then, calls 'newImage' to build and set the new image for the bar.
     *
     * @param 'amount': the amount to subtract (if positive) or add (if negative) to the current value of the bar
     */
    public void subtract(int amount)
    {
        value -= amount;
        checkValue();
        newImage();
    }
    
    /**
     * Method 'checkValue': ensures that the new value in between the minimum value and the maximum value for the bar
     */
    private void checkValue()
    {
        if (value < minimumValue) value = minimumValue;
        if (value > maximumValue) value = maximumValue;
    }
    
    /**
     * Method 'getValue': returns the current value of the bar
     *
     * @return: the current value of the bar
     */
    public int getValue() { return value; }
    
    /**
     * Method 'getBarWidth': returns the current width of the bar (color portion only)
     *
     * @return: the current width of the bar
     */
    public int getBarWidth() { return barWidth; }
    
    /**
     * Method 'getBarHeight': returns the current height of the bar (color portion only)
     *
     * @return: the current height of the bar
     */
    public int getBarHeight() { return barHeight; }
    
    /**
     * Method 'getBreakPercent': returns the range percent at which the color of the bar changes between lower values and higher values
     * (the variable 'usingBreakValue' must be 'false' for percent breaking is to be active; using 'setBreakPercent' will automatically
     * set 'usingBreakValue' to 'false'.
     *
     * @return: the percent value that determines where the color of the bar will change
     */
    public int getBreakPercent() { return breakPercent; } // use boolean getUsingBreakValue() method before calling (if false)
    
    /**
     * Method 'getBreakValue': returns the current value at which the color of the bar will change
     * (the variable 'usingBreakValue' must be 'true' for value breaking is to be active; using 'setBreakValue' will automatically
     * set 'usingBreakVaule' to 'true'.
     *
     * @return: the value where the color of the bar will change
     */
    public int getBreakValue() { return breakValue; } // use boolean getUsingBreakValue() method before calling (if true)
    
    /**
     * Method 'getBreakLow': returns the boolean value that determines if the danger color is used at the low end or the high end of the range
     * (setting it to 'minimumValue' or 'maximumValue' will essentially deactivate breaking)
     *
     * @return: the break state (danger color for low values when true) of the bar
     */
    public boolean getBreakLow() { return breakLow; }
    
    /**
     * Method 'getBackgroundColor': returns the current background color of the image for the bar
     *
     * @return: the current color used for the background of the bar image (behind the bar and the text, alike)
     */
    public Color getBackgroundColor() { return backgroundColor; }
    
    /**
     * Method 'getTextColor': returns the current text and frame color
     *
     * @return: the current color used for the text and the frame of the bar
     */
    public Color getTextColor() { return textColor; }
    
    /**
     * Method 'getSafeColor': returns the current color used for values above any break point, if 'breakLow' is 'true', else below
     *
     * @return: the current color of the color portion of the bar used for 'safe' values
     */
    public Color getSafeColor() { return safeColor; }
    
    /**
     * Method 'getDangerColor': returns the current color used for values below any break point, if 'breakLow' is 'true' else above
     *
     * @return: the current color of the color portion of the bar used for 'danger' values
     */
    public Color getDangerColor() { return dangerColor; }
    
    /**
     * Method 'getFontSize': returns the current font size for the text used in the bar image
     *
     * @return: the current font size for the text of the bar
     */
    public float getFontSize() { return fontSize; }
    
    /**
     * Method 'getMaximumValue': returns the current maximum value of the bar
     *
     * @return: the current value set as the maximum value of the bar
     */
    public int getMaximumValue() { return maximumValue; }
    
    /**
     * Method 'getMinimumValue': returns the current minimum value of the bar
     *
     * @return: the current value set as the minimum value of the bar
     */
    public int getMinimumValue() { return minimumValue; }
    
    /**
     * Method 'getReferenceText': returns the string set as the title text of the bar
     *
     * @return: the current title text of the bar
     */
    public String getReferenceText() { return referenceText; }
    
    /**
     * Method 'getUnitOfMeasure': returns the current string used to qualify the value of the bar
     *
     * @return: the current unit of measure for the value of the bar
     */
    public String getUnitOfMeasure() { return unitOfMeasure; }
    
    /**
     * Method 'getShowTextualUnits': returns the current state of showing/hiding of the textual value and unit of measure of the bar
     *
     * @return: the current state of showing/hiding of the string representation of the value and unit of measure on the right side of the bar
     */
    public boolean getShowTextualUnits() { return showTextualUnits; }
    
    /**
     * Method 'setValue': sets a new value for the bar, if in bounds
     *
     * @param 'val': the new value for the bar
     */
    public void setValue(int val) { value = val; checkValue(); newImage(); }
    
    /**
     * Method 'setBarWidth': sets a new width for the color portion of the bar
     *
     * @param 'width': the new width for the color portion of the bar
     */
    public void setBarWidth(int width) { if (width > 9) { barWidth = width; newImage(); } }
    
    /**
     * Method 'setBarHeight': sets a new height for the color portion of the bar
     *
     * @param 'height': the new height for the color portion of the bar
     */
    public void setBarHeight(int height) { if (height > 1) { barHeight = height; newImage(); } }
    
    /**
     * Method 'setBreakPercent': sets a new percentage value where the bar changes colors between safe and danger values
     * (if set to zero or one hundred, the bar will stay a constant color)
     * 'usingBreakValue' will be automatically set to 'false' when this method is called
     *
     * @param 'percent': the new percentage in the range of allowable values in the bar at which the color of the bar changes between 'safeColor' and 'dangerColor'
     */
    public void setBreakPercent(int percent) { if (percent >= 0 && percent <= 100) { breakPercent = percent; usingBreakValue = false; newImage(); } }
    
    /**
     * Method 'setBreakValue': sets a new value for where the color of the color portion of the bar will change between safe and danger values
     * (if set to minimumValue or maximumValue, the bar will stay a constant color)
     * 'usingBreakValue' will be automatically set to 'true' when this method is called
     *
     * @param 'brkVal': the new value where the color of the color portion of the bar will change between 'safeColor' and 'dangerColor'
     */
    public void setBreakValue(int brkVal) { if (brkVal >= minimumValue && brkVal <= maximumValue) {breakValue = brkVal; usingBreakValue = true; newImage(); } }
    
    /**
     * Method setBreakLow: sets the direction of safe to danger values; true: danger values are low and safe values are high; false: danger values are high ...
     *
     * @param 'lowBreak': the new state of break direction to use with the bar
     */
    public void setBreakLow(boolean lowBreak) { breakLow = lowBreak; newImage(); }
    
    /**
     * Method 'setBackgroundColor': sets a new color to use behind the text and bar of the image
     *
     * @param 'color': the new color to use for the background color of the bar image
     */
    public void setBackgroundColor(Color color) { backgroundColor = color; newImage(); }
    
    /**
     * Method 'setTextColor': sets a new color for the text and frame of the bar image
     *
     * @param 'color': the new color for the text and frame of the bar image
     */
    public void setTextColor(Color color) { textColor = color; newImage(); }
    
    /**
     * Method 'setSafeColor': sets a new color for the color portion of the bar for safe values
     *
     * @param 'color': the new color for safe values in the color portion of the bar
     */
    public void setSafeColor(Color color) { safeColor = color; newImage(); }
    
    /**
     * Method 'setDangerColor': sets a new color for the color portion of the bar for danger values
     *
     * @param 'color': the new color for danger values in the color portion of the bar
     */
    public void setDangerColor(Color color) { dangerColor = color; newImage(); }
    
    /**
     * Method 'setFontSize': sets the font size to use for all text in the bar image
     *
     * @param 'size': the new font size for text used in the bar
     */
    public void setFontSize(float size) { if (size > 7) { fontSize = size; newImage(); } }
    
    /**
     * Method 'setMaximumValue': sets a new maximum for allowable values for the bar
     *
     * @param 'maxVal': the new maximum for the value of the bar
     */
    public void setMaximumValue(int maxVal) { if (maxVal > minimumValue) { maximumValue = maxVal; newImage(); } }
    
    /**
     * Method 'setMinimumValue': sets a new minimum for allowable values for the bar
     *
     * @param 'minVal': the new minimum for the value of the bar
     */
    public void setMinimumValue(int minVal) { if (minVal < maximumValue) { minimumValue = minVal; newImage(); } }
    
    /**
     * Method 'setReferenceText': sets new title text for the bar
     *
     * @param 'refText': the new title text (or reference text) for the bar
     */
    public void setReferenceText(String refText) { referenceText = refText; newImage(); }
    
    /**
     * Method 'setUnitOfMeasure': sets new unit of measure text for the bar
     *
     * @param 'uom': the new unit of measure text for the bar
     */
    public void setUnitOfMeasure(String uom) { unitOfMeasure = uom; newImage(); }
    
    /**
     * Method 'setShowTextualUnits': sets the state of showing/hiding of the textual value and unit of measure on the right side of the bar
     *
     * @param 'show': the new state of showing/hiding of value text for the bar
     */
    public void setShowTextualUnits(boolean show) { showTextualUnits = show; newImage(); }
}
