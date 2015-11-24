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

public class Roads
{    
  public void paint(Graphics2D g2d)
  {
    //Grass
    g2d.setColor(Color.green);
    g2d.fillRect(0,0,1920,1080);
    
    //Roads
    g2d.setColor(Color.black);
    g2d.fillRect(810,0,300,1080);
    g2d.fillRect(0,390,1920,300);

    //Lines
    g2d.setColor(Color.yellow);
    g2d.fillRect(960,0,6,390);
    g2d.fillRect(960,690,6,390);
    g2d.fillRect(0,540,810,6);
    g2d.fillRect(1110,540,810,6);
  }
}