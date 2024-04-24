package Model;

import Utils.Coordinate;
import javax.swing.ImageIcon;
import lombok.Data;
/**
 * An abstract class representing a bullet entity in a game.
 * Bullets are projectiles fired by player or enemy entities.
 */
@Data
public class AbstractBullet implements Entity{
  protected Coordinate coordinate;
  protected ImageIcon image;
  protected int width;
  protected int height;
  protected int type;

  @Override
  public void move() {
    this.coordinate.setY(this.coordinate.getY() - 2);
  }

}
