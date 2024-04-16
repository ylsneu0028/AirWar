package Model;

import Utils.Coordinate;
import javax.swing.ImageIcon;
import lombok.Getter;


public class BossBullet extends AbstractBullet{
  @Getter
  private int level;
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
