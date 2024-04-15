package Model;

import java.util.Random;
import javax.swing.ImageIcon;
import Utils.*;
import lombok.Data;

@Data
abstract class AbstractEnemy implements Entity {

  protected Coordinate coordinate;
  protected ImageIcon image;
  protected int width;
  protected int height;
  protected int ySpeed;
  protected int xSpeed;
  protected int life;
  protected int type;

  public AbstractEnemy(String imgPath, int xVelocity, int yVelocity, int lifeVal, int typeVal) {
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

  @Override
  public void move() {
    this.coordinate.setY(this.coordinate.getY() + ySpeed);
    this.coordinate.setX(this.coordinate.getX() + xSpeed);
    if (this.coordinate.getX() + this.width >= Constants.windowWidth) {
      xSpeed = -(Math.abs(xSpeed));
    }
    if (this.coordinate.getX() <= 0) {
      xSpeed = Math.abs(xSpeed);
    }
  }
}
