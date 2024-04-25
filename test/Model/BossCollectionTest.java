package Model;

import static org.junit.Assert.*;
import Controller.*;
import Utils.Constants;
import org.junit.Before;
import org.junit.Test;

/**
 * This class test the BossCollection Class
 */
public class BossCollectionTest {
  private BossCollection bosses;

  @Before
  public void setUp() {
    bosses = new BossCollection();
  }

  /**
   * Test createBoss method;
   */
  @Test
  public void testCreateBoss() {
    for (int i = 0; i < 349; i++) {
      bosses.createBoss(bosses, 1);  // Increment without adding boss
      assertEquals(0, bosses.getBossArray().length);
    }
    bosses.createBoss(bosses, 1);      // Should trigger boss addition
    assertEquals(1, bosses.getBossArray().length);
  }


  /**
   * Test moveBoss method
   */
  @Test
  public void testMoveBosses() {
    SimpleBoss boss = new SimpleBoss();
    bosses.setBossArray(new Boss[]{boss});
    int initialY = boss.getCoordinate().getY();
    int initialX = boss.getCoordinate().getX();
    bosses.moveBoss(bosses);
    assertEquals(initialY + 1, boss.getCoordinate().getY());
    assertEquals(initialX + 1, boss.getCoordinate().getX());
  }

  /**
   *  Test bossHitHero method.
   */
  @Test
  public void testCollisionDetection() {
    //BossCollection bosses = new BossCollection();
    SimpleBoss boss = new SimpleBoss();
    boss.getCoordinate().setX(100);
    boss.getCoordinate().setY(100);
    boss.setWidth(50);
    boss.setHeight(50);
    bosses.setBossArray(new Boss[]{boss});

    // Simulating hero position and size
    Controller.hero = new Hero();
    Controller.hero.getCoordinate().setX(100);
    Controller.hero.getCoordinate().setY(100);
    Controller.hero.setWidth(50);
    Controller.hero.setHeight(50);
    Controller.hero.setLife(100);

    bosses.bossHitHero(bosses);
    assertEquals(0, bosses.getBossArray().length);  // Boss should be removed
    assertTrue(Controller.hero.getLife() == 0);    // Hero life should be reduced
  }

  /**
   *  Test the removeBoss method.
   */
  @Test
  public void testRemoveOffScreen() {
    SimpleBoss boss = new SimpleBoss();
    boss.getCoordinate().setY(Constants.windowHeight);
    bosses.setBossArray(new Boss[]{boss});
    bosses.removeBoss(bosses);
    assertEquals(1, bosses.getBossArray().length); // Boss still on the screen.
    bosses.getBossArray()[0].getCoordinate().setY(Constants.windowHeight * 3);  // Set far off-screen
    bosses.removeBoss(bosses);
    assertEquals(0, bosses.getBossArray().length);  // Boss should be removed
  }

  /**
   *  This subclass mock the generation of boss
   */
  private class SimpleBoss extends Boss {
    // Simplified constructor for testing
    public SimpleBoss() {
      super("path/to/image.png", 1, 1, 100, 1);
    }

    @Override
    public void move() {
      // Simple move that increases Y by ySpeed, X by xSpeed
      this.getCoordinate().setY(this.getCoordinate().getY() + this.getYSpeed());
      this.getCoordinate().setX(this.getCoordinate().getX() + this.getXSpeed());
    }
  }




}