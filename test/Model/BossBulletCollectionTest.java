package Model;

import static Utils.Constants.windowHeight;
import static org.junit.Assert.*;

import Controller.Controller;
import Utils.Coordinate;
import org.junit.Before;
import org.junit.Test;

public class BossBulletCollectionTest {
  private BossBulletCollection bossBullets;
  private BossCollection bosses;

  @Before
  public void setUp() {
    bossBullets = new BossBulletCollection();
    bosses = new BossCollection();
    // Assuming Boss class and a simple setup can be initialized here
    Boss boss = new Boss("bossImagePath", 5, 5, 10,  1);
    boss.setWidth(50);
    boss.setHeight(50);
    boss.setCoordinate(new Coordinate(100, 100));
    bosses.setBossArray(new Boss[]{boss});
  }

  /**
   * Test createBossBullet method.
   */
  @Test
  public void testCreateBossBullets() {
    Controller.status = 1;
    for (int i = 0; i < 79; i++) {
      bossBullets.creatBossBullets(bosses, bossBullets, "path/to/bullet.png", 5, 1, 1);
      System.out.println(bossBullets.getBossBulletIndex());
    }
    assertEquals("No bullets should be created yet.", 0, bossBullets.getBossBulletArray().length);
    bossBullets.creatBossBullets(bosses, bossBullets, "path/to/bullet.png", 5, 1, 1);
    assertEquals("Two bullets should be created after 80th tick.", 2, bossBullets.getBossBulletArray().length);
  }

  /**
   * Test moveBossBullets method.
   */
  @Test
  public void testMoveBossBullets() {
    BossBullet bullet = new BossBullet(100, 100, "path/to/bullet.png", 1, 1);
    bossBullets.setBossBulletArray(new BossBullet[]{bullet});
    bossBullets.moveBossBullets(bossBullets);
    assertEquals("Bullet should move down by 2.", 102, bossBullets.getBossBulletArray()[0].getCoordinate().getY());
    assertEquals("X coordinate of the bullet should not be changed.", 100, bossBullets.getBossBulletArray()[0].getCoordinate().getX());
  }

  /**
   * Test bossBulletHitHero method
   */
  @Test
  public void testBossBulletHitHero() {
    BossBullet bullet = new BossBullet(100, 100, "path/to/bullet.png", 1, 1);
    bossBullets.setBossBulletArray(new BossBullet[]{bullet});
    // Setup player position to simulate a collision
    Controller.hero = new Hero();
    Controller.hero.setCoordinate(new Coordinate(100, 100));
    Controller.hero.setWidth(50);
    Controller.hero.setHeight(50);
    Controller.hero.setLife(10);
    bossBullets.bossBulletHitHero(bossBullets);
    assertEquals("Player life should decrease.", 9, Controller.hero.getLife());
    assertEquals("Bullet should be removed on collision.", 0, bossBullets.getBossBulletArray().length);
  }

  /**
   * Test removeBossBullet method
   */
  @Test
  public void testRemoveBossBullet() {
    BossBullet bullet = new BossBullet(100, 100, "path/to/bullet.png", 1, 1);
    bossBullets.setBossBulletArray(new BossBullet[]{bullet});
    bossBullets.removeBossBullet(bossBullets);
    assertEquals("Bullet should be on the screen",1, bossBullets.getBossBulletArray().length);
    bossBullets.getBossBulletArray()[0].getCoordinate().setY(-windowHeight - 1);
    bossBullets.removeBossBullet(bossBullets);
    assertEquals("Bullet should be removed when it goes off-screen.", 0, bossBullets.getBossBulletArray().length);
  }




}