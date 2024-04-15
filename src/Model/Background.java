package Model;
import Utils.Coordinate;
import javax.swing.ImageIcon;
import lombok.Getter;

public class Background {
  @Getter
  Coordinate coordinate;
  ImageIcon image;
  int width;
  int height;
  int ySpeed;

  public Background() {
    this.coordinate = new Coordinate(0, 0);
    image = new ImageIcon("image/Background.png");
    width = image.getIconWidth();
    height = image.getIconHeight();

    ySpeed = 1;
  }

  public void move() {
    this.coordinate.setY(this.coordinate.getY() + ySpeed);
    System.out.println("Bk"+ this.coordinate.getY());
    //System.out.println("this.Y="+this.Y);
    if (this.coordinate.getY()>= 100) {
      this.coordinate.setY(0);

    }
  }
}
