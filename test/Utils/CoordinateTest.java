package Utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test class of Coordinate
 */
public class CoordinateTest {
    private Coordinate coordinate;

    /**
     * Creates a coordinate for testing.
     */
    @BeforeEach
    public void setUp() {
        coordinate = new Coordinate(5, 10);
    }

    /**
     * tests Constructor and getters
     */
    @Test
    public void testConstructorAndGetters() {
        assertEquals(5, coordinate.getX());
        assertEquals(10, coordinate.getY());
    }

    /**
     *  tests X setter
     */
    @Test
    public void testSetX() {
        coordinate.setX(20);
        assertEquals(20, coordinate.getX());

        coordinate.setX(1);
        assertNotEquals(20, coordinate.getX());
    }

    /**
     * Tests Y setter.
     */
    @Test
    public void testSetY() {
        coordinate.setY(30);
        assertEquals(30, coordinate.getY());

        coordinate.setY(0);
        assertEquals(0, coordinate.getY());
    }

    /**
     * Tests equal method.
     */
    @Test
    public void testEquals() {
        Coordinate sameCoordinates = new Coordinate(5, 10);
        Coordinate differentCoordinates = new Coordinate(5, 11);

        assertTrue(coordinate.equals(sameCoordinates));
        assertFalse(coordinate.equals(differentCoordinates));
        assertFalse(coordinate.equals(null));
        assertFalse(coordinate.equals(new Object()));
    }
}
