package Model;
import Utils.Constants;
import Utils.Coordinate;
import lombok.Data;

import java.util.Random;
import javax.swing.ImageIcon;


/**
 * An abstract class representing a buff entity in a game.
 * Buffs are power-ups or bonuses that provide advantages to the player.
 */
@Data
abstract public class AbstractBuff implements Entity {
  protected Coordinate coordinate;
  protected ImageIcon image;
  protected int width;
  protected int height;
  protected int ySpeed;
  protected int xSpeed;
  protected int life;
  protected int type;

  /**
   * Constructs a new AbstractBuff object with the specified image path, velocities, and type.
   * @param imgPath The path to the image file representing the buff.
   * @param xVelocity The horizontal velocity of the buff.
   * @param yVelocity The vertical velocity of the buff.
   * @param typeVal The type of the buff.
   */
  public AbstractBuff(String imgPath, int xVelocity, int yVelocity, int typeVal) {
    image = new ImageIcon(imgPath);
    width = image.getIconWidth();
    height = image.getIconHeight();
    type = typeVal;

    Random r = new Random();
    int X = r.nextInt(Constants.windowWidth - width);
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
