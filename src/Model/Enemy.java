package Model;

import Utils.Coordinate;
import java.util.Random;
import javax.swing.ImageIcon;

public class Enemy extends AbstractEnemy {

  public Enemy(String imgPath, int xVelocity, int yVelocity, int lifeVal, int typeVal) {
    super(imgPath, xVelocity, yVelocity, lifeVal, typeVal);
  }

}