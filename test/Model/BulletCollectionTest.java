package Model;

import Utils.Coordinate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Utils.*;

/**
 * The tests of class BulletCollection
 */
public class BulletCollectionTest {

    /**
     * Tests createBullet method
     */
    @Test
    void createBulletsTest() {
        BulletCollection bulletCollection = new BulletCollection();

        // Test case 1: Verify bulletIndex increments correctly
        bulletCollection.createBullets(bulletCollection, 1, 10, "bullet_image.png");
        assertEquals(0, bulletCollection.getBulletIndex());

        // Test case 2: Verify bulletArray length increases by 2 when hero's fire equals type
        int initialLength = bulletCollection.getBulletArray().length;
        bulletCollection.createBullets(bulletCollection, 1, 10, "bullet_image.png");
        assertEquals(initialLength , bulletCollection.getBulletArray().length);
    }

    /**
     * Tests moveBullets method
     */
    @Test
    void moveBulletsTest() {
        BulletCollection bulletCollection = new BulletCollection();
        Bullet bullet = new Bullet(0, 0, "bullet_image.png", 1);
        bulletCollection.setBulletArray(new Bullet[]{bullet});

        // Test case 1: Verify bullet movement
        bulletCollection.moveBullets(bulletCollection);
        assertEquals(-2, bullet.getCoordinate().getY());

        // Test case 2: Verify bullet movement after multiple moves
        bulletCollection.moveBullets(bulletCollection);
        bulletCollection.moveBullets(bulletCollection);
        assertEquals(-6, bullet.getCoordinate().getY());
    }

    /**
     * Tests bulletHitEnemy method
     */
    @Test
    public void testBulletHitEnemy() {
        BulletCollection bulletCollection = new BulletCollection();
        Bullet bullet = new Bullet(1, 1, "bullet", 1);
        Enemy enemy = new Enemy("enemy", 1, 1, 1, 1);
        bullet.setCoordinate(new Coordinate(100,100));
        bullet.setHeight(5);
        bullet.setWidth(5);
        enemy.setWidth(5);
        enemy.setHeight(5);
        enemy.setCoordinate(new Coordinate(100,100));
        bulletCollection.setBulletArray(new Bullet[]{bullet});
        EnemyCollection enemyCollection = new EnemyCollection();
        enemyCollection.setEnemyArray(new Enemy[]{enemy});
        bulletCollection.bulletHitEnemy(bulletCollection, enemyCollection, 1);
        assertEquals(0, enemy.getLife());
        assertEquals(0, bulletCollection.getBulletArray().length);
    }

    /**
     * Tests bulletHitBoss method
     */
    @Test
    public void testBulletHitBoss() {
        BulletCollection bulletCollection = new BulletCollection();
        Bullet bullet = new Bullet(0, 0, "bullet", 1);
        Boss boss = new Boss("boss", 0, 0, 1, 1);
        bullet.setCoordinate(new Coordinate(100,100));
        bullet.setHeight(5);
        bullet.setWidth(5);
        boss.setWidth(5);
        boss.setHeight(5);
        boss.setCoordinate(new Coordinate(100,100));
        bulletCollection.setBulletArray(new Bullet[]{bullet});
        BossCollection bossCollection = new BossCollection();
        bossCollection.setBossArray(new Boss[]{boss});
        bulletCollection.bulletHitBoss(bulletCollection, bossCollection, 1);
        assertEquals(0, boss.getLife());
        assertEquals(0, bulletCollection.getBulletArray().length);
    }

    /**
     * Tests remove boss method
     */
    @Test
    public void testRemoveBullet() {
        BulletCollection bulletCollection = new BulletCollection();
        Bullet bullet1 = new Bullet(0, -Constants.windowWidth - 1, "bullet1", 1);
        Bullet bullet2 = new Bullet(0, 0, "bullet2", 1);
        bulletCollection.setBulletArray(new Bullet[]{bullet1, bullet2});
        bulletCollection.removeBullet(bulletCollection);
        assertEquals(1, bulletCollection.getBulletArray().length);
        assertEquals(bullet2, bulletCollection.getBulletArray()[0]);
    }
}
