package Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 *  This class tests the BossBullet Class
 */
public class BossBulletTest {
  private BossBullet bossBullet;

  /**
   *  Creates a bossBullet for test.
   */
  @Before
  public void setUp() {
    bossBullet = new BossBullet(100, 200, "path/to/image.png", 1, 3);
  }

  /**
   * Tests the move method.
   */
  @Test
  public void testMove() {
    int initialY = bossBullet.getCoordinate().getY();
    int initialX = bossBullet.getCoordinate().getX();
    bossBullet.move();
    assertEquals("Y coordinate should increase by 2.", initialY + 2, bossBullet.getCoordinate().getY());
    assertEquals("X coordinate should not be changed.", initialX, bossBullet.getCoordinate().getX());
  }


}