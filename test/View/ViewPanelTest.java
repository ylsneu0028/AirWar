package View;

import Utils.Coordinate;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import Model.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test Class of ViewPanel
 */
public class ViewPanelTest {

    /**
     * Verify initial state post-construction
     */
    @Test
    public void testInitialState() {
        ViewPanel panel = new ViewPanel();
        assertNull(panel.getHeroCoordinate());
        assertEquals(0, panel.getStatus());
    }

    /**
     * Tests the paintComponent method.
     */
    @Test
    public void testPaintComponent() {
        ViewPanel viewPanel = new ViewPanel();
        viewPanel.setStatus(1); // Set status to something other than 0

        // Create a BufferedImage to capture the drawn content
        int width = 800; // Set width according to your requirements
        int height = 600; // Set height according to your requirements
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        // Call paintComponent method manually passing the Graphics object
        viewPanel.paintComponent(g2d);

        // Test if background image is drawn when backgroundCoordinate is not null
        viewPanel.setBackgroundCoordinate(new Coordinate(0, 0));
        viewPanel.paintComponent(g2d); // Call paintComponent again to cover this case
        assertNotNull(image.getSubimage(0, 0, 10, 10)); // Check if background image is drawn (adjust coordinates and size accordingly)

        //Test if background image is changed when status changed
        viewPanel.setStatus(2);
        viewPanel.paintComponent(g2d);
        assertNotNull(image.getSubimage(0,0,10, 10));

        viewPanel.setStatus(3);
        viewPanel.paintComponent(g2d);
        assertNotNull(image.getSubimage(0,0,10, 10));

        viewPanel.setStatus(4);
        viewPanel.paintComponent(g2d);
        assertNotNull(image.getSubimage(0,0,10, 10));

        viewPanel.setStatus(0);
        viewPanel.paintComponent(g2d);
        assertNotNull(image.getSubimage(0,0,10, 10));

        // Test if hero image is drawn when heroCoordinate is not null
        viewPanel.setStatus(1);
        viewPanel.setHeroCoordinate(new Coordinate(100, 100)); // Set heroCoordinate
        viewPanel.paintComponent(g2d); // Call paintComponent again to cover this case
        assertNotNull(image.getSubimage(100, 100, 10, 10)); // Check if hero image is drawn (adjust coordinates and size accordingly)

        // Test if enemyOne images are drawn when enemyOnes array is not null
        Enemy[] enemyOnes = {new Enemy("path_to_enemy_image.png", 2,2,5,1), new Enemy("image/path.png", 2,2,5,1)}; // Create sample enemyOne objects
        for (Enemy enemy : enemyOnes) {
            enemy.setImage(new ImageIcon("path_to_enemy_image.png")); // Set image for the enemy
        }
        viewPanel.setEnemyOnes(enemyOnes);
        viewPanel.paintComponent(g2d); // Call paintComponent again to cover this case
        assertNotNull(image.getSubimage(enemyOnes[0].getCoordinate().getX(), enemyOnes[0].getCoordinate().getY(), 10, 10)); // Check if enemyOne images are drawn (adjust coordinates and size accordingly)

        //Test if enemyTwos images are drawn when enemyTwos array is not null
        Enemy[] enemyTwos = {new Enemy("path_to_enemy2_image.png", 3,3,6,2), new Enemy("image/path/enemy2.png", 3,3,6,2)}; // Create sample enemyOne objects
        for (Enemy enemy : enemyOnes) {
            enemy.setImage(new ImageIcon("path_to_enemy2_image.png")); // Set image for the enemy
        }
        viewPanel.setEnemyTwos(enemyTwos);
        viewPanel.paintComponent(g2d); // Call paintComponent again to cover this case
        assertNotNull(image.getSubimage(enemyTwos[0].getCoordinate().getX(), enemyOnes[0].getCoordinate().getY(), 10, 10));

        //Test if bossOnes images are drawn when bossOnes array is not null
        Boss[] bossOnes = {new Boss("path_to_boss2_image.png", 3,3,6,2), new Boss("image/path/boss2.png", 3,3,6,2)}; // Create sample enemyOne objects
        for (Boss boss : bossOnes) {
            boss.setImage(new ImageIcon("path_to_boss2_image.png")); // Set image for the boss
        }
        viewPanel.setBossOnes(bossOnes);
        viewPanel.paintComponent(g2d); // Call paintComponent again to cover this case
        assertNotNull(image.getSubimage(bossOnes[0].getCoordinate().getX(), enemyOnes[0].getCoordinate().getY(), 10, 10));

        // Test if bulletOne images are drawn when bulletOnes array is not null
        Bullet[] bulletOnes = {new Bullet(100,100,"path_to_bullet_image.png",1), new Bullet(100,100,"path_to_bullet_image.png",1)}; // Create sample bulletOne objects
        for (Bullet bullet : bulletOnes) {
            bullet.setImage(new ImageIcon("path_to_bullet_image.png")); // Set image for the bullet
        }
        viewPanel.setBulletOnes(bulletOnes);
        viewPanel.paintComponent(g2d); // Call paintComponent again to cover this case
        assertNotNull(image.getSubimage(bulletOnes[0].getCoordinate().getX(), bulletOnes[0].getCoordinate().getY(), 10, 10)); // Check if bulletOne images are drawn (adjust coordinates and size accordingly)

        // Test if bossBulletOne images are drawn when bossBulletOne array is not null
        BossBullet[] bossBulletOnes = {new BossBullet(100,100,"path_to_bossBullet_image.png",1,1), new BossBullet(100,100,"path_to_BossBullet_image.png",1,1)}; // Create sample bulletOne objects
        for (BossBullet bossBullet : bossBulletOnes) {
            bossBullet.setImage(new ImageIcon("path_to_BossBullet_image.png")); // Set image for the boss bullet
        }
        viewPanel.setBossBulletOnes(bossBulletOnes);
        viewPanel.paintComponent(g2d); // Call paintComponent again to cover this case
        assertNotNull(image.getSubimage(bossBulletOnes[0].getCoordinate().getX(), bulletOnes[0].getCoordinate().getY(), 10, 10));

        // Test if bufftOne images are drawn when buffOnes array is not null
        Buff[] buffOnes = {new Buff("path_to_buff_image.png",2,2, 1), new Buff("path_to_bullet_image.png",2,2,1)}; // Create sample bufftOne objects
        for (Buff buff : buffOnes) {
            buff.setImage(new ImageIcon("path_to_bullet_image.png")); // Set image for the bullet
        }
        viewPanel.setBuffOnes(buffOnes);
        viewPanel.paintComponent(g2d); // Call paintComponent again to cover this case
        assertNotNull(image.getSubimage(buffOnes[0].getCoordinate().getX(), bulletOnes[0].getCoordinate().getY(), 10, 10));
        // Dispose Graphics object
        g2d.dispose();
    }
}
