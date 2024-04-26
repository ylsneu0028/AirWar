package Model;

import static org.junit.jupiter.api.Assertions.*;

import Utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class of class Boss
 */
public class BossTest {
    private Boss boss;
    private static final int INITIAL_Y_SPEED = 5;
    private static final int STOP_THRESHOLD_Y = 150; // Define this if it's a constant used in your game logic

    /**
     * Creates a boss for testing.
     */
    @BeforeEach
    public void setUp() {
        boss = new Boss("path/to/image.png", 5, 5, 100, 1);
    }

    /**
     * Tests initial movement of the boss
     */
    @Test
    public void testInitialMovement() {
        boss.move();
        int expectedY = -boss.getHeight() + INITIAL_Y_SPEED;
        assertEquals(expectedY, boss.getCoordinate().getY(),
                "Y coordinate after initial movement should be adjusted by initial Y speed minus boss height.");
    }

    /**
     * Tests that the movement of the boss stops at a set threshold.
     */
    @Test
    public void testMovementStopAtThreshold() {
        boss.getCoordinate().setY(STOP_THRESHOLD_Y - INITIAL_Y_SPEED);
        boss.move();
        assertEquals(STOP_THRESHOLD_Y, boss.getCoordinate().getY(),
                "Y coordinate should stop at the set threshold of 150.");

        boss.move();
        assertEquals(STOP_THRESHOLD_Y, boss.getCoordinate().getY(),
                "Y coordinate should remain at threshold after further moves.");
        assertEquals(0, boss.getYSpeed(),
                "Y speed should be set to 0 when reaching or exceeding the threshold.");
    }

    /**
     * Tests boundary handling to ensure the boss reverses direction appropriately.
     */
    @Test
    public void testBoundaryHandling() {
        // Test right boundary
        boss.getCoordinate().setX(Constants.windowWidth - boss.getWidth());
        boss.move();
        assertTrue(boss.getXSpeed() < 0,
                "X speed should invert when hitting the right boundary.");

        // Test left boundary
        boss.getCoordinate().setX(0);
        boss.move();
        assertTrue(boss.getXSpeed() > 0,
                "X speed should invert when hitting the left boundary.");
    }
}


