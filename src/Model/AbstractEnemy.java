package Model;

import java.util.Random;
import javax.swing.ImageIcon;
import Utils.*;
import lombok.Data;

/**
 * An abstract class representing an enemy entity in a game.
 * Enemies are entities that oppose the player and typically engage in combat.
 */
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

  /**
   * Constructs a new AbstractEnemy object with the specified image path, velocities, life, and type.
   * @param imgPath The path to the image file representing the enemy.
   * @param xVelocity The horizontal velocity of the enemy.
   * @param yVelocity The vertical velocity of the enemy.
   * @param lifeVal The initial life value of the enemy.
   * @param typeVal The type of the enemy.
   */
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
