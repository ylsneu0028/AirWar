package Model;

import Controller.Controller;
import Utils.Coordinate;
import java.util.Arrays;
import javax.swing.ImageIcon;

public class Bullet extends AbstractBullet{

  public Bullet(int x,int y, String bulletOneImagePath, int typeVal) {
    this.coordinate = new Coordinate(x, y);
    image = new ImageIcon(bulletOneImagePath);
    width = image.getIconWidth();
    height = image.getIconHeight();
    type = typeVal;
  }

}
