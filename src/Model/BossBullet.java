package Model;

import Utils.Coordinate;
import javax.swing.ImageIcon;
import lombok.Getter;

/**
 * A class representing the bullets fired by the boss enemy in a game.
 * Boss bullets are projectiles aimed at the player.
 */
public class BossBullet extends AbstractBullet{
  @Getter
  private int level;

  /**
   * Constructs a new BossBullet object with the specified coordinates, image path, type, and level.
   * @param X The x-coordinate of the boss bullet.
   * @param Y The y-coordinate of the boss bullet.
   * @param bossBulletImagePath The path to the image file representing the boss bullet.
   * @param typeVal The type of the boss bullet.
   * @param level The level of the boss bullet.
   */
  public BossBullet(int X, int Y,  String bossBulletImagePath, int typeVal, int level) {
    this.coordinate = new Coordinate(X, Y);
    this.image = new ImageIcon(bossBulletImagePath);
    this.width = image.getIconWidth();
    this.height = image.getIconHeight();
    this.type = typeVal;
    this.level = level;
  }

  @Override
  public void move(){
    this.coordinate.setY(this.coordinate.getY() + 2);
  }
}
