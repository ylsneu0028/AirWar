package Model;
import Utils.Constants;
import Utils.Coordinate;
import java.util.Random;
import javax.swing.ImageIcon;
import lombok.Data;

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
