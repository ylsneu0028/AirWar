package Model;

import Controller.Controller;
import Utils.Coordinate;
import java.util.Arrays;
import javax.swing.ImageIcon;

/**
 * A class representing a bullet entity in the game.
 * Bullets are projectiles fired by the player character.
 */
public class Bullet extends AbstractBullet{

  /**
   * Constructs a new Bullet object with the specified parameters.
   * @param x The x-coordinate of the bullet's initial position.
   * @param y The y-coordinate of the bullet's initial position.
   * @param bulletOneImagePath The path to the image file representing the bullet.
   * @param typeVal The type of the bullet.
   */
  public Bullet(int x,int y, String bulletOneImagePath, int typeVal) {
    this.coordinate = new Coordinate(x, y);
    image = new ImageIcon(bulletOneImagePath);
    width = image.getIconWidth();
    height = image.getIconHeight();
    type = typeVal;
  }

}
