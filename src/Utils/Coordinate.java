package Utils;

public class Coordinate {

  private int x;
  private int y;

  public Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  /**
   * Override the equals() function to compare other objects with the snakeMVC.utils.Coordinate
   *
   * @param obj the object to be compared
   * @return true if 2 Coordinates have the same coordinates value
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Coordinate) {
      Coordinate other = (Coordinate) obj;
      return other.x == this.x && other.y == this.y;
    }
    return false;
  }
}