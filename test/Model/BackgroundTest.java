package Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import Model.Background;

public class BackgroundTest {

    @Test
    public void testConstructor() {
        Background background = new Background();

        assertNotNull(background.getCoordinate(), "Background coordinate should not be null");
        assertNotNull(background.getImage(), "Background image should not be null");
    }

    @Test
    public void testMove() {
        Background background = new Background();
        int initialY = background.getCoordinate().getY();

        // Move the background
        background.move();

        // Assert that the y-coordinate has been updated
        assertNotEquals(initialY, background.getCoordinate().getY(), "Y-coordinate should have changed after moving");

        // Move the background again
        background.move();

        // Check if the background resets to its initial position when it reaches a certain point
        // Assuming -698 is a reset point which should be clarified or stored as a constant for better maintainability
        assertEquals(-698, background.getCoordinate().getY(), "Y-coordinate should be reset to initial position when reaching the reset point");
    }
}
