import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.*;
import java.awt.Graphics2D;

public class StopLight
{   
  private int x = 0;
  private int y = 0;
  private int width = 0;
  private int height = 0;
  private Boolean isGreen = false; 
  private char NOrE = ' ';
  
  public StopLight(int x, int y, int width, int height,Boolean isGreen, char NOrE)
  {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.isGreen = isGreen;
    this.NOrE = NOrE;
  }
  
  public Boolean returnIsGreen()
  {
    return isGreen;
  }
  
  public void paintBox(Graphics2D g2d)
  {
    //Stoplight Main Box
    g2d.setColor(Color.gray);
    g2d.fillRect(x,y,width,height);
  }
  
  public void keyPressed(KeyEvent e) 
  {      
  }
  
  public void keyReleased(KeyEvent e) 
  {
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
      isGreen = !isGreen;
  }
  
  public void paint(Graphics2D g2d)
  {
    //Stoplight Colours
    if (isGreen == true)
    {
      g2d.setColor(Color.green);
      g2d.fillOval(x+5, y+5, 40, 40);
      if (NOrE=='N')
        g2d.fillOval(x+5, y+100, 40, 40);
      else
        g2d.fillOval(x+105, y+5, 40, 40);
    }
    else
    {
      g2d.setColor(Color.red);
      g2d.fillOval(x+5, y+5, 40, 40);
      if (NOrE=='N')
        g2d.fillOval(x+5, y+100, 40, 40);
      else
        g2d.fillOval(x+105, y+5, 40, 40);
    }
  }
}