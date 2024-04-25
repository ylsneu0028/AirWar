package tests;

import static org.junit.Assert.*;

import Model.Bullet;
import org.junit.Test;
public class BulletTest {

    @Test
    public void testConstructor() {
        int x = 10;
        int y = 20;
        String bulletOneImagePath = "bullet.png";
        int typeVal = 1;
        
        Bullet bullet = new Bullet(x, y, bulletOneImagePath, typeVal);
        
        // Check if bullet's coordinates are initialized correctly
        assertEquals(x, bullet.getCoordinate().getX());
        assertEquals(y, bullet.getCoordinate().getY());
        
        // Check if bullet's image is initialized correctly
        assertNotNull(bullet.getImage());
    }
    
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

        assertNotEquals(y, bullet.getCoordinate().getY());
    }
}
