package tests;
import Model.Buff;
import Model.AbstractBuff;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class BuffTest {

    @Test
    public void testConstructor() {
        Buff buff = new Buff("img/buff.png", 5, 5, 1);

        // Assert that the x velocity is set correctly
        assertNotEquals(6, buff.getCoordinate().getX());

        // Assert that the y velocity is set correctly
        assertNotEquals(6, buff.getCoordinate().getY());

    }

}