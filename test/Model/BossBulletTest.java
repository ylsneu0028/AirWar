package Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *  This class tests the BossBullet Class
 */
public class BossBulletTest {
    private BossBullet bossBullet;

    /**
     *  Creates a bossBullet for test.
     */
    @BeforeEach
    public void setUp() {
        bossBullet = new BossBullet(100, 200, "path/to/image.png", 1, 3);
    }

    /**
     * Tests Constructor of BossBullet.
     */
    @Test
    public void testConstructor() {
        assertEquals(100, bossBullet.getCoordinate().getX(), "Initial X coordinate should be 100.");
        assertEquals(200, bossBullet.getCoordinate().getY(), "Initial Y coordinate should be 200.");
        assertEquals(1, bossBullet.getType(), "Type should be 1.");
        assertEquals(3, bossBullet.getLevel(), "Level should be 3.");
    }

    /**
     * Tests the move method.
     */
    @Test
    public void testMove() {
        int initialY = bossBullet.getCoordinate().getY();
        int initialX = bossBullet.getCoordinate().getX();
        bossBullet.move();
        assertEquals(initialY + 2, bossBullet.getCoordinate().getY(), "Y coordinate should increase by 2.");
        assertEquals(initialX, bossBullet.getCoordinate().getX(), "X coordinate should not be changed.");
    }

}
