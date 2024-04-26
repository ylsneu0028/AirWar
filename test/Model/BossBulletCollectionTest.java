package Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static Utils.Constants.windowHeight;

import Controller.Controller;
import Utils.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BossBulletCollectionTest {
    private BossBulletCollection bossBullets;
    private BossCollection bosses;

    @BeforeEach
    public void setUp() {
        bossBullets = new BossBulletCollection();
        bosses = new BossCollection();
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
            bossBullets.createBossBullets(bosses,bossBullets, "path/to/bullet.png", 5, 1, 1);
        }
        assertEquals(0, bossBullets.getBossBulletArray().length, "No bullets should be created yet.");
        bossBullets.createBossBullets(bosses, bossBullets,"path/to/bullet.png", 5, 1, 1);
        assertEquals(2, bossBullets.getBossBulletArray().length, "Two bullets should be created after 80th tick.");
    }

    /**
     * Test moveBossBullets method.
     */
    @Test
    public void testMoveBossBullets() {
        BossBullet bullet = new BossBullet(100, 100, "path/to/bullet.png", 1, 1);
        bossBullets.setBossBulletArray(new BossBullet[]{bullet});
        bossBullets.moveBossBullets(bossBullets);
        assertEquals(102, bossBullets.getBossBulletArray()[0].getCoordinate().getY(), "Bullet should move down by 2.");
        assertEquals(100, bossBullets.getBossBulletArray()[0].getCoordinate().getX(), "X coordinate of the bullet should not be changed.");
    }

    /**
     * Test bossBulletHitHero method
     */
    @Test
    public void testBossBulletHitHero() {
        BossBullet bullet = new BossBullet(100, 100, "path/to/bullet.png", 1, 1);
        bossBullets.setBossBulletArray(new BossBullet[]{bullet});
        Controller.hero = new Hero();
        Controller.hero.setCoordinate(new Coordinate(100, 100));
        Controller.hero.setWidth(50);
        Controller.hero.setHeight(50);
        Controller.hero.setLife(10);
        bossBullets.bossBulletHitHero(bossBullets);
        assertEquals(9, Controller.hero.getLife(), "Player life should decrease.");
        assertEquals(0, bossBullets.getBossBulletArray().length, "Bullet should be removed on collision.");
    }

    /**
     * Test removeBossBullet method
     */
    @Test
    public void testRemoveBossBullet() {
        BossBullet bullet = new BossBullet(100, 100, "path/to/bullet.png", 1, 1);
        bossBullets.setBossBulletArray(new BossBullet[]{bullet});
        bossBullets.removeBossBullet(bossBullets);
        assertEquals(1, bossBullets.getBossBulletArray().length, "Bullet should be on the screen.");
        bossBullets.getBossBulletArray()[0].getCoordinate().setY(-windowHeight - 1);
        bossBullets.removeBossBullet(bossBullets);
        assertEquals(0, bossBullets.getBossBulletArray().length, "Bullet should be removed when it goes off-screen.");
    }

}
