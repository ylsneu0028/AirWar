package Model;

import Utils.Constants;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Controller.*;
import Utils.*;


/**
 *  The test class for BuffCollection
 */
class BuffCollectionTest {

    @Test
    void testCreateBuffs() {
        BuffCollection buffs = new BuffCollection();
        buffs.createBuffs(buffs);
        // Check if buff index is incremented
        assertEquals(1, buffs.getBuffIndex());
        // Check if a buff entity is added to the buff array
        assertEquals(0, buffs.getBuffArray().length);
    }

    /**
     * Test moveBuffs method.
     */
    @Test
    void testMoveBuffs() {
        BuffCollection buffs = new BuffCollection();
        // Create a buff entity
        Buff buff = new Buff(Constants.buffOneImagePath, Constants.buffOneXspeed,
                Constants.buffOneYspeed, Constants.buffOneType);
        buffs.setBuffArray(new Buff[]{buff});
        // Move the buff entity
        buffs.moveBuffs(buffs);
        // Check if the buff entity has moved
        assertNotEquals(buff.getCoordinate().getX(), Constants.buffOneXspeed);
        assertNotEquals(buff.getCoordinate().getY(), Constants.buffOneYspeed);
    }

    /**
     *  Tests buffHitHero method.
     */
    @Test
    public void testBuffHitHero() {
        BuffCollection buffCollection = new BuffCollection();
        Buff buff = new Buff("buff", 1, 1, 1);
        buff.setCoordinate(new Coordinate(100,100));
        buff.setHeight(10);
        buff.setWidth(10);
        buffCollection.setBuffArray(new Buff[]{buff});
        Controller.hero = new Hero();
        Controller.hero.setCoordinate(new Coordinate(100, 100));
        Controller.hero.setWidth(10);
        Controller.hero.setHeight(10);
        Controller.hero.setFire(1);
        Controller.hero.setLife(1);
        buffCollection.buffHitHero(buffCollection);
        assertEquals(0, buffCollection.getBuffArray().length);
        assertEquals(2, Controller.hero.getFire());
        assertEquals(2, Controller.hero.getLife());
    }

    /**
     * Tests removeBuff method
     */
    @Test
    void testRemoveBuff() {
        BuffCollection buffs = new BuffCollection();
        // Create a buff entity
        Buff buff = new Buff(Constants.buffOneImagePath, Constants.buffOneXspeed,
                Constants.buffOneYspeed, Constants.buffOneType);
        // Set the buff array with the buff entity
        buffs.setBuffArray(new Buff[]{buff});
        // Move the buff entity off the screen
        buff.getCoordinate().setY(Constants.windowHeight * 2 + 1);
        // Perform removeBuff method
        buffs.removeBuff(buffs);
        // Check if the buff entity is removed from the buff array
        assertEquals(0, buffs.getBuffArray().length);
    }
}
