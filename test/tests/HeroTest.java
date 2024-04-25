package tests;

import static org.junit.Assert.assertNotEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import Model.Hero;
import javax.swing.ImageIcon;
import org.junit.Test;
public class HeroTest {

    @Test
    public void move() {
        Hero hero = new Hero();
        ImageIcon originalImage = hero.getImage(); // Get the original image
        hero.move(); // Perform movement
        assertNotEquals(originalImage, hero.getImage()); // Check if the image has changed after movement
    }

    @Test
    public void constructor() {
        Hero hero = new Hero();
        assertNotNull(hero.getCoordinate()); // Check if the coordinate is not null after construction
        assertNotNull(hero.getImage()); // Check if the image is not null after construction
    }
}
