package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test class of class Enemy
 */
public class EnemyTest {
    private Enemy enemy;

    /**
     * Creates an enemy for test.
     */
    @BeforeEach
    public void setUp() {
        enemy = new Enemy("path/to/enemy.png", 2, 3, 100, 1);
    }

    /**
     * Tests the constructor
     */
    @Test
    public void testConstructor() {
        assertEquals(2, enemy.getXSpeed(), "X velocity should match constructor parameter.");
        assertEquals(100, enemy.getLife(), "Life value should match constructor parameter.");
        assertEquals(1, enemy.getType(), "Type value should match constructor parameter.");
    }

    /**
     * Tests the move method.
     */
    @Test
    public void testMove() {
        // Assuming initial coordinates are set by AbstractEnemy's constructor
        int initialX = enemy.getCoordinate().getX();
        int initialY = enemy.getCoordinate().getY();
        enemy.move();
        assertEquals(initialX + 2, enemy.getCoordinate().getX(), "Enemy should move horizontally by xVelocity.");
        assertEquals(initialY + 3, enemy.getCoordinate().getY(), "Enemy should move vertically by yVelocity.");
    }
}
