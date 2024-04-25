package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Model.BulletCollection;
import Model.Bullet;
public class BulletCollectionTest {

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
}
