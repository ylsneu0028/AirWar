package Model;

import Utils.Coordinate;
import java.util.Random;
import javax.swing.ImageIcon;
import lombok.Data;

public class EnemyOne extends AbstractEnemy {

  public EnemyOne() {
    image = new ImageIcon("image/enemy1.png");
    width = image.getIconWidth();
    height = image.getIconHeight();
    life = 1;

    Random r = new Random();
    int X = r.nextInt(560 - width);
    int Y = -height;
    coordinate = new Coordinate(X, Y);

    ySpeed = 3;
    xSpeed = 3;
  }

}
