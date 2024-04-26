package Model;
import Model.Buff;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests of class Buff
 */
public class BuffTest {
    /**
     * Tests constructors
     */
    @Test
    public void testConstructor() {
        int expectedXspeed = 5;
        int expectedYspeed = 5;
        int type = 1;
        Buff buff = new Buff("img/buff.png", expectedXspeed, expectedYspeed, type);

        // Assert that the x coordinate is set correctly
        assertEquals(expectedXspeed, buff.getXSpeed(), "X velocity should match the constructor parameter");

        // Assert that the y coordinate is set correctly
        assertEquals(expectedYspeed, buff.getYSpeed(), "Y velocity should match the constructor parameter");

        // Optionally check other initializations like type
        assertEquals(type, buff.getType(), "Type should match the constructor parameter");

    }

}
