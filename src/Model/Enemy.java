package Model;

import Utils.Coordinate;
import java.util.Random;
import javax.swing.ImageIcon;

public class Enemy extends AbstractEnemy {

  public Enemy(String imgPath, int xVelocity, int yVelocity, int lifeVal, int typeVal) {
    image = new ImageIcon(imgPath);
    width = image.getIconWidth();
    height = image.getIconHeight();
    life = lifeVal;
    type = typeVal;

    Random r = new Random();
    int X = r.nextInt(560 - width);
    int Y = -height;
    coordinate = new Coordinate(X, Y);

    ySpeed = yVelocity;
    xSpeed = xVelocity;
  }

}