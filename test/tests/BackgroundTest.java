package tests;

import org.junit.Test;
import static org.junit.Assert.*;
import Model.Background;

public class BackgroundTest {

    @Test
    public void testConstructor() {
        Background background = new Background();
        
        assertNotNull("Background coordinate should not be null", background.getCoordinate());
        assertNotNull("Background image should not be null", background.getImage());
    }

    @Test
    public void testMove() {
        Background background = new Background();
        int initialY = background.getCoordinate().getY();
        
        // Move the background
        background.move();
        
        // Assert that the y-coordinate has been updated
        assertNotEquals("Y-coordinate should have changed after moving", initialY, background.getCoordinate().getY());
        
        // Move the background again
        background.move();
        
        // Assert that the background resets to its initial position when it reaches the top
        assertEquals("Y-coordinate should be reset to initial position when reaching the top", -698, background.getCoordinate().getY());
    }
}
