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

public class CityIntersection extends JPanel
{
  private Roads r = new Roads();
  Car[] list = new Car[16];
  private StopLight NorthSouth = new StopLight(935,465,50,150,true, 'N');
  private StopLight EastWest = new StopLight(885,515,150,50,false, 'E');
  private boolean intersectionClear = true;
  
  public CityIntersection()
  {
    list[0] = new Car(1023, 690, "Car_1.PNG", 'N', NorthSouth, this, true);
    list[1] = new Car(1023, 788, "Car_2.PNG", 'N', NorthSouth, this, false);
    list[2] = new Car(1023, 884, "Car_3.PNG", 'N', NorthSouth, this, false);
    list[3] = new Car(1023, 980, "Car_4.PNG", 'N', NorthSouth, this, false);
    list[4] = new Car(703, 589, "Car_5.PNG", 'E', EastWest, this, true);
    list[5] = new Car(502, 589, "Car_6.PNG", 'E', EastWest, this, false);
    list[6] = new Car(300, 599, "Car_7.PNG", 'E', EastWest, this, false);
    list[7] = new Car(98, 589, "Car_8.PNG", 'E', EastWest, this, false);
    list[8] = new Car(873, 336, "Car_9.PNG", 'S', NorthSouth, this, true);
    list[9] = new Car(873, 241, "Car_10.PNG", 'S', NorthSouth, this, false);
    list[10] = new Car(873, 145, "Car_11.PNG", 'S', NorthSouth, this, false);
    list[11] = new Car(873, 49, "Car_12.PNG", 'S', NorthSouth, this, false);
    list[12] = new Car(1116, 440, "Car_13.PNG", 'W', EastWest, this, true);
    list[13] = new Car(1317, 440, "Car_14.PNG", 'W', EastWest, this, false);
    list[14] = new Car(1519, 440, "Car_15.PNG", 'W', EastWest, this, false);
    list[15] = new Car(1721, 440, "Car_16.PNG", 'W', EastWest, this, false);
    
    list[0].setFriends(null, list[1]);
    list[1].setFriends(list[0], list[2]);
    list[2].setFriends(list[1], list[3]);
    list[3].setFriends(list[2], null);
    list[4].setFriends(null, list[5]);
    list[5].setFriends(list[4], list[6]);
    list[6].setFriends(list[5], list[7]);
    list[7].setFriends(list[6], null);
    list[8].setFriends(null, list[9]);
    list[9].setFriends(list[9], list[10]);
    list[10].setFriends(list[9], list[11]);
    list[11].setFriends(list[10], null);
    list[12].setFriends(null, list[13]);
    list[13].setFriends(list[12], list[14]);
    list[14].setFriends(list[13], list [15]);
    list[15].setFriends(list[14], null);
    
    addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
      }
      @Override
      public void keyReleased(KeyEvent e) {
        NorthSouth.keyReleased(e);
        EastWest.keyReleased(e);
      }
      @Override
      public void keyPressed(KeyEvent e) {
        NorthSouth.keyPressed(e);
        EastWest.keyPressed(e);
      }
    });
    setFocusable(true);
  }
  
  public Boolean isIntersecionClear()
  {
    return intersectionClear;
  }
  
  public void paint(Graphics g)
  {
    super.paint(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
    intersectionClear = true;
    for (Car c:list)
    { 
      if (NorthSouth.returnIsGreen())
      {
        if (c.returnDirection() == 'E' || c.returnDirection() == 'W')
        {
          if ((c.returnXPosition()>(810-101) && c.returnXPosition()<1110))
          {
            intersectionClear = false;
          }
        }
      }
      else
      {
        if (c.returnDirection() == 'N' || c.returnDirection() == 'S')
        {
          if ((c.returnYPosition()>(390-42) && c.returnYPosition()<690))
          {
            intersectionClear = false;
          }
        }
      }
    }
    
    r.paint(g2d);
    for (Car c:list)
    {
      c.paint(g2d);
    }
    NorthSouth.paintBox(g2d);
    EastWest.paintBox(g2d);
    NorthSouth.paint(g2d);
    EastWest.paint(g2d);
  }
  
  private void move()
  {
    for (Car c:list)
    {
      c.move();
    }
  }
  
  public static void main(String[] args) throws InterruptedException
  {
    JFrame f = new JFrame("City Intersection");
    CityIntersection ci = new CityIntersection();
    f.add(ci);
    f.setSize(1920,1080);
    f.setVisible(true);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    while (true)
    {
      ci.move();
      ci.repaint();
      Thread.sleep(10);
    }
  }
}
