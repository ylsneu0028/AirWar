package View;

import Utils.Coordinate;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import Model.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test class of Class View Frame.
 */

public class ViewFrameTest {

    /**
     * Test the constructor
     */
    @Test
    public void testViewFrameConstructor() {
        ViewFrame frame = new ViewFrame();
        assertNotNull(frame.getPanel());
        assertEquals("Air War", frame.getTitle());
        assertEquals(new Dimension(560, 824), frame.getSize());
        assertTrue(frame.isResizable());
        assertEquals(JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
        assertTrue(frame.isVisible());
    }

    /**
     * Test paint method.
     */
    @Test
    public void testPaint() {
        ViewFrame frame = new ViewFrame();
        frame.paint();
        // Test that panel's repaint method was called.
    }

    /**
     * Tests setStatus method
     */
    @Test
    public void testSetStatus() {
        ViewFrame frame = new ViewFrame();
        frame.setStatus(1);
        assertEquals(1, frame.getPanel().getStatus());
        frame.setStatus(0);
        assertEquals(0, frame.getPanel().getStatus());
    }

    /**
     * Tests setHeroCoordinate method
     */
    @Test
    public void testSetHeroCoordinate() {
        ViewFrame frame = new ViewFrame();
        Coordinate coord = new Coordinate(10, 20);
        frame.setHeroCoordinate(coord);
        assertEquals(coord, frame.getPanel().getHeroCoordinate());
        assertNotEquals(new Coordinate(10, 30), frame.getPanel().getHeroCoordinate());
    }

    /**
     * Tests setHeroImage method
     */
    @Test
    public void testSetHeroImage() {
        ViewFrame frame = new ViewFrame();
        ImageIcon heroImage = new ImageIcon("path/to/image.png");
        frame.setHeroImage(heroImage);
        assertEquals(heroImage, frame.getPanel().getHeroImage());
        assertNotEquals("path/to/hero", frame.getPanel().getHeroImage());
    }

    /**
     * Tests setBackground method
     */
    @Test
    public void testSetBackground(){
        ViewFrame frame = new ViewFrame();
        Coordinate coord = new Coordinate(100, 100);
        frame.setBackground(coord);
        assertEquals(coord, frame.getPanel().getBackgroundCoordinate());
        assertNotEquals(new Coordinate(90,90),frame.getPanel().getBackgroundCoordinate());
    }

    /**
     * Tests setScore method
     */
    @Test
    public void testSetScore() {
        ViewFrame frame = new ViewFrame();
        frame.setScore(100);
        assertEquals(100, frame.getPanel().getScore());
        assertNotEquals(99,frame.getPanel().getScore() );
    }

    /**
     * Tests setLife method
     */
    @Test
    public void testSetLife(){
        ViewFrame frame = new ViewFrame();
        frame.setLife(10);
        assertEquals(10,frame.getPanel().getLife());
        assertNotEquals(0,frame.getPanel().getLife());
    }

    /**
     * Tests setFire method
     */
    @Test
    public void testSetFire() {
        ViewFrame frame = new ViewFrame();
        int fire = 5;
        frame.setFire(fire);
        assertEquals(fire, frame.getPanel().getFire(), "Fire should be set correctly on the panel.");
        assertNotEquals(6,frame.getPanel().getFire(), "Fire should be set correctly on the panel.");
    }

    /**
     * Tests setEnemyOnes method
     */
    @Test
    public void testSetEnemyOnes() {
        ViewFrame frame = new ViewFrame();
        Enemy[] enemies = {new Enemy("path", 1, 1, 5,1), new Enemy("path", 1, 1, 5,1)};
        frame.setEnemyOnes(enemies);
        assertArrayEquals(enemies, frame.getPanel().getEnemyOnes(), "Enemy ones should be set correctly on the panel.");
        assertEquals(2,frame.getPanel().getEnemyOnes().length, "Enemy ones should have a length of 2");
    }

    /**
     * Test setEnemyTwos method
     */
    @Test
    public void testSetEnemyTwos() {
        ViewFrame frame = new ViewFrame();
        Enemy[] enemies = {new Enemy("path2",2,2,3,2), new Enemy("path2",2,2,3,2)};
        frame.setEnemyTwos(enemies);
        assertArrayEquals(enemies, frame.getPanel().getEnemyTwos(), "Enemy twos should be set correctly on the panel.");
        assertEquals(2,frame.getPanel().getEnemyTwos().length, "Enemy twos should have a length of 2");
    }

    /**
     * Test setBossOnes method
     */
    @Test
    public void testSetBossOnes() {
        ViewFrame frame = new ViewFrame();
        Boss[] bosses = {new Boss("Path/Boss",5,5,50,1), new Boss("Path/Boss",5,5,50,1)};
        frame.setBossOnes(bosses);
        assertArrayEquals(bosses, frame.getPanel().getBossOnes(), "Boss ones should be set correctly on the panel.");
        assertEquals(2,frame.getPanel().getBossOnes().length, "Boss ones should have a length of 2");
    }

    /**
     * Tests setBulletOnes method
     */
    @Test
    public void testSetBulletOnes() {
        ViewFrame frame = new ViewFrame();
        Bullet[] bullets = {new Bullet(50,50,"path/bullet",1), new Bullet(50,50,"path/bullet",1)};
        frame.setBulletOnes(bullets);
        assertArrayEquals(bullets, frame.getPanel().getBulletOnes(), "Bullets should be set correctly on the panel.");
        assertEquals(2,frame.getPanel().getBulletOnes().length, "Bullets should have a length of 2");
    }

    /**
     * Tests setBuffOnes method
     */
    @Test
    public void testSetBuffOnes() {
        ViewFrame frame = new ViewFrame();
        Buff[] buffs = {new Buff("Buff/Path", 3,3,1), new Buff("Buff/Path", 3,3,1)};
        frame.setBuffOnes(buffs);
        assertArrayEquals(buffs, frame.getPanel().getBuffOnes(), "Buffs should be set correctly on the panel.");
        assertEquals(2,frame.getPanel().getBuffOnes().length);
    }

    /**
     * Tests setBossBulletOnes method.
     */
    @Test
    public void testSetBossBulletOnes() {
        ViewFrame frame = new ViewFrame();
        BossBullet[] bossBullets = {new BossBullet(100,100,"path/bossBullet",1,1), new BossBullet(100,100,"path/bossBullet",1,1)};
        frame.setBossBulletOnes(bossBullets);
        assertArrayEquals(bossBullets, frame.getPanel().getBossBulletOnes(), "Boss bullets should be set correctly on the panel.");
        assertEquals(2,frame.getPanel().getBossBulletOnes().length, "Boss bullets should have a length of 2.");
    }

    /**
     * Test addMouseAListener method
     */
    @Test
    public void testAddMouseAListener() {
        ViewFrame frame = new ViewFrame();
        MouseAdapter mouseAdapter = new MouseAdapter() {};
        frame.addMouseAListener(mouseAdapter);
        assertTrue(Arrays.asList(frame.getMouseListeners()).contains(mouseAdapter));
        assertTrue(Arrays.asList(frame.getMouseMotionListeners()).contains(mouseAdapter));
    }







}
