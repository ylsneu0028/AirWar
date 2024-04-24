package Model;

import Utils.Coordinate;
import javax.swing.ImageIcon;
import lombok.Getter;

/**
 * A class representing the background in a game.
 * The background provides the visual backdrop for the game environment.
 */
@Getter
public class Background {
  Coordinate coordinate;
  ImageIcon image;
  int width;
  int height;
  int ySpeed;

  /**
   * Constructs a new Background object with default values.
   * The background is initialized with a specific coordinate, image, and speed.
   */
  public Background() {
    coordinate = new Coordinate(30, -700);
    image = new ImageIcon("image/Background.png");
    width = image.getIconWidth();
    height = image.getIconHeight();
    ySpeed = 1;
  }

  /**
   * Moves the background by updating its y-coordinate, causing it to scroll upwards.
   * If the background reaches the top of the screen, it resets to its initial position.
   */
  public void move() {
    this.coordinate.setY(this.coordinate.getY() + ySpeed);
    System.out.println("Bk" + this.coordinate.getY());
    if (this.coordinate.getY() >= 0) {
      this.coordinate.setY(-500);
    }
  }
}
