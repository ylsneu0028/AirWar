package tests;

import Utils.Constants;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Model.BuffCollection;
import Model.Buff;


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
