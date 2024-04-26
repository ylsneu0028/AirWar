package Model;

import Utils.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Controller.*;
import static Utils.Constants.windowWidth;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test class of class EnemyCollection.
 */
public class EnemyCollectionTest {
    private EnemyCollection enemyCollection;

    /**
     * Set up objects for testing
     */
    @BeforeEach
    public void setUp() {
        enemyCollection = new EnemyCollection();
    }

    /**
     * Tests the createEnemies method.
     */
    @Test
    public void testCreateEnemies() {
        String imagePath = "path/to/enemy.png";
        int xSpeed = 2, ySpeed = 3, life = 100, type = 1;

        // Simulate game ticks
        for (int i = 0; i < 75; i++) {
            enemyCollection.createEnemies(enemyCollection,imagePath, xSpeed, ySpeed, life, type);
        }
        assertEquals(0, enemyCollection.getEnemyArray().length, "No enemy should be created before reaching the 76th tick.");
        enemyCollection.createEnemies(enemyCollection,imagePath, xSpeed, ySpeed, life, type);
        assertEquals(1, enemyCollection.getEnemyArray().length, "An enemy should be created on the 76th tick.");
    }

    /**
     * Tests moveEnemies method
     */
    @Test
    public void testMoveEnemies() {
        Enemy enemy = new Enemy("path/to/enemy.png", 2, 3, 100, 1);
        enemy.getCoordinate().setY(100);
        enemy.getCoordinate().setX(100);
        enemyCollection.setEnemyArray(new Enemy[]{enemy});
        enemyCollection.moveEnemies(enemyCollection);
        assertEquals(103, enemy.getCoordinate().getY(), "Enemy should move downward.");
        assertEquals(102, enemy.getCoordinate().getX(), "Enemy should move right.");
    }

    /**
     * Test enemyHitHero method
     */
    @Test
    public void testEnemyHitHero() {
        Enemy enemy = new Enemy("path/to/enemy.png", 0, 0, 100, 1);
        Controller.hero = new Hero();
        Controller.hero.setCoordinate(new Coordinate(100, 100));
        Controller.hero.setWidth(50);
        Controller.hero.setHeight(50);
        Controller.hero.setLife(10);
        enemy.getCoordinate().setX(100);
        enemy.getCoordinate().setY(100);
        enemy.setWidth(50);
        enemy.setHeight(50);
        enemyCollection.setEnemyArray(new Enemy[]{enemy});
        enemyCollection.enemyHitHero(enemyCollection);
        assertEquals(9, Controller.hero.getLife(), "Hero life should decrease.");
        assertEquals(0, enemyCollection.getEnemyArray().length, "Enemy should be removed on collision.");
    }

    /**
     * Tests removeEnemy method.
     */
    @Test
    public void testRemoveEnemy() {
        Enemy enemy = new Enemy("path/to/enemy.png", 0, 0, 100, 1);
        enemy.getCoordinate().setY(100);
        enemyCollection.setEnemyArray(new Enemy[]{enemy});
        enemyCollection.removeEnemy(enemyCollection);
        assertEquals(1, enemyCollection.getEnemyArray().length, "Enemy should be on the screen.");

        enemy.getCoordinate().setY(windowWidth * 3); // Simulate enemy far off the screen
        enemyCollection.removeEnemy(enemyCollection);
        assertEquals(0, enemyCollection.getEnemyArray().length, "Enemy should be removed if off-screen.");
    }


}
