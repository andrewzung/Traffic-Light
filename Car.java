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

public class Car
{   
  private int x = 0;
  private int y = 0;
  private BufferedImage img = null;
  private String fileLocation = "";
  private char direction = ' ';
  private int length = 0;
  private int width = 0;
  private Boolean safeToMove = false;
  private String whichTrafficLight = "";
  private StopLight sl;
  private Boolean allowedToMove = false;
  private Boolean lightIsGreen = false;
  private CityIntersection ci;
  private Boolean blockingIntersection = false;
  private Boolean isPast = false;
  private Boolean isBefore = false;
  private Boolean isFirstCar = false;
  private Car carinfront;
  private Boolean shouldStop = true;
  private Car carinback;
  
  public Car(int x, int y, String fileLocation, char direction, StopLight whichLight, CityIntersection whichIntersection, Boolean isFirstCar)
    {
      this.x = x;
      this.y = y;
      this.fileLocation = fileLocation;
      this.direction = direction;
      if(direction == 'N' || direction == 'S')
      {
        this.whichTrafficLight = "NorthSouth";
      }
      else
      {
        this.whichTrafficLight = "EastWest";
      }
      sl = whichLight;
      ci = whichIntersection;
      this.isFirstCar = isFirstCar;
      try
      {
        img = ImageIO.read(new File(fileLocation));
      } catch (IOException e)
      {
        System.out.println("No Image");
      }
      int width = img.getHeight();
      int length = img.getWidth();
    }
  
  public int returnXPosition()
  {
    return x;
  }
  
  public int returnYPosition()
  {
    return y;
  }
  
  public char returnDirection()
  {
    return direction;
  }
  
  public Boolean returnIsFirstCar()
  {
    return isFirstCar;
  }
  
  public void setFriends(Car whichCarFront, Car whichCarBack)
  {
    carinfront = whichCarFront;
    carinback = whichCarBack;
  }
  
  public void checkIfPast()
  {
    switch (direction) {
      case 'N':  if (y<690)
      {
        isPast = true;
        isFirstCar = false;
        if (carinback != null)
        {
          carinback.isFirstCar = true;
        }
      }
      break;
      case 'E':  if (x>(810-101))
      {
        isPast = true;
        isFirstCar = false;
        if (carinback != null)
        {
          carinback.isFirstCar = true;
        }
      }
      break;
      case 'S':  if (y>(390-42))
      {
        isPast = true;
        isFirstCar = false;
        if (carinback != null)
        {
          carinback.isFirstCar = true;
        }
      }
      break;
      case 'W':  if (x<1110)
      {
        isPast = true;
        isFirstCar = false;
        if (carinback != null)
        {
          carinback.isFirstCar = true;
        }
      }
      break;
      default:  System.out.println(fileLocation + "Error");
      break;
    }
  }
  
  public void checkIfBefore()
  {
    isBefore = false;
    switch (direction) {
      case 'N':  if (y>700){isBefore = true;}
      break;
      case 'E':  if (x<(800-img.getWidth())){isBefore = true;}
      break;
      case 'S':  if (y<(380-img.getHeight())){isBefore = true;};
      break;
      case 'W':  if (x>1120){isBefore = true;}
      break;
      default:  System.out.println("Error");
      break;
    }
  }
  
  public Boolean approachingCar()
  {
    if (carinfront != null)
    {
      switch (direction) {
        case 'N':  if (carinfront.y + carinfront.img.getHeight() +10 == y)
        {
          return true;
        }
        break;
        case 'E':  if (carinfront.x-10 == x + img.getWidth())
        {
          return true;
        }
        break;
        case 'S':  if (carinfront.y-10 == y + img.getHeight())
        {
          return true;
        }
        break;
        case 'W':  if ((carinfront.x + carinfront.img.getWidth() + 10) == x)
        {
//          System.out.println(carinfront.x + fileLocation);
//          System.out.println("Car in front width" + carinfront.img.getHeight());
          return true;
        }
        break;
        default:  System.out.println(fileLocation + "Error");
        break;
      }
    }
    return false;
  }
  
  public void checkIfSafe()
  {
    safeToMove = false;
    checkIfPast();
    lightIsGreen = sl.returnIsGreen();
    allowedToMove = ci.isIntersecionClear();
    checkIfBefore();
    
    if(lightIsGreen && allowedToMove)
    {
      safeToMove = true;
    }
    
    if (isPast)
    {
      safeToMove = true;
    }
    
    if (isFirstCar && isBefore)
    {
      safeToMove = true;
    }
    
    if(isBefore && !approachingCar())
    {
      safeToMove = true;
    }
  }
  
  public void move()
  {
    checkIfSafe();
    if (safeToMove == true)
    {
      switch (direction) {
        case 'N':  y--;
        break;
        case 'E':  x++;
        break;
        case 'S':  y++;
        break;
        case 'W':  x--;
        break;
        default:  System.out.println("Error");
        break;
      }
    }
  }
  
  public void paint(Graphics2D g2d)
  {
    g2d.drawImage(img, x, y, null);
  }
}