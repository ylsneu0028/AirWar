package Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests of Class Bullet
 */
public class BulletTest {

    /**
     * Tests the constructor
     */
    @Test
    public void testConstructor() {
        int x = 10;
        int y = 20;
        String bulletOneImagePath = "bullet.png";
        int typeVal = 1;

        Bullet bullet = new Bullet(x, y, bulletOneImagePath, typeVal);

        // Check if bullet's coordinates are initialized correctly
        assertEquals(x, bullet.getCoordinate().getX(), "X coordinate should match initial value.");
        assertEquals(y, bullet.getCoordinate().getY(), "Y coordinate should match initial value.");

        // Check if bullet's image is initialized correctly
        assertNotNull(bullet.getImage(), "Image should not be null.");
    }

    /**
     * Tests the move method
     */
    @Test
    public void testBulletMovement() {
        int x = 10;
        int y = 20;
        String bulletOneImagePath = "bullet.png";
        int typeVal = 1;

        Bullet bullet = new Bullet(x, y, bulletOneImagePath, typeVal);

        // Move the bullet
        bullet.move();

        // Check if bullet's coordinates have been updated after movement
        assertNotEquals(y, bullet.getCoordinate().getY(), "Y coordinate should change after movement.");
    }
}
