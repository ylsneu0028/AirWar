package Model;

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

  @Override
  public void move() {
    this.coordinate.setY(this.coordinate.getY() + ySpeed);
    this.coordinate.setX(this.coordinate.getX() + xSpeed);
    if (this.coordinate.getX() + this.width >= 560) {
      xSpeed = -(Math.abs(xSpeed));
    }
    if (this.coordinate.getX() <= 0) {
      xSpeed = Math.abs(xSpeed);
    }
  }
}
