package Model;

import static org.junit.Assert.*;

import Utils.Constants;
import org.junit.Before;
import org.junit.Test;

public class BossTest {
  private Boss boss;

  @Before
  public void setUp() {
    // Initialize Boss with some typical parameters
    boss = new Boss("path/to/image.png", 5, 5, 100, 1);
  }

  @Test
  public void testInitialMovement() {
    boss.move();  // Move the boss once
    assertEquals("Y coordinate after initial movement should be -boss's height + ySpeed", -boss.getHeight() + 5, boss.getCoordinate().getY());
  }

  @Test
  public void testMovementStopAtThreshold() {
    // Set Y just below 150 and move the boss
    boss.getCoordinate().setY(145);
    boss.move();
    assertEquals("Y coordinate should stop changing at 150", 150, boss.getCoordinate().getY());

    // Further moves should not change Y
    boss.move();
    assertEquals("Y coordinate should remain at 150 after further moves", 150, boss.getCoordinate().getY());
    assertEquals("Y speed should be set to 0", 0, boss.getYSpeed());
  }

  @Test
  public void testBoundaryHandling() {
    // Test movement at the edge of the window width
    boss.getCoordinate().setX(Constants.windowWidth - boss.getWidth());
    boss.move();
    assertTrue("X speed should invert when hitting the right boundary", boss.getXSpeed() < 0);

    // Test movement at the left edge
    boss.getCoordinate().setX(0);
    boss.move();
    assertTrue("X speed should invert when hitting the left boundary", boss.getXSpeed() > 0);
  }


}

