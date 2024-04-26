package View;

import Model.*;

import java.awt.event.MouseAdapter;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import Utils.*;
import lombok.Getter;

/**
 * The ViewFrame class represents a JFrame for displaying the game view.
 */
public class ViewFrame extends JFrame {
    @Getter
    private ViewPanel panel;

    /**
     * Constructs a new ViewFrame object.
     */
    public ViewFrame() {
        this.setTitle("Air War");
        this.setSize(560, 824);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);

        this.panel = new ViewPanel();

        this.add(panel);

        this.setVisible(true);

    }

   /**
    * Repaints the view panel.
    */
    public void paint() {
        panel.repaint();
    }

   /**
    * Sets the status of the game.
    * @param status The status of the game.
    */
    public void setStatus(int status) {
        panel.setStatus(status);
    }

   /**
    * Sets the coordinate of the hero.
    * @param coordinate The coordinate of the hero.
    */
    public void setHeroCoordinate(Coordinate coordinate) {
        panel.setHeroCoordinate(coordinate);
    }

   /**
    * Sets the background coordinate.
    * @param backgroundCoordinate The background coordinate.
    */
    public void setBackground(Coordinate backgroundCoordinate) {
        panel.setBackgroundCoordinate(backgroundCoordinate);
    }

   /**
    * Sets the hero image.
    * @param heroImage The hero image.
    */
    public void setHeroImage(ImageIcon heroImage) {
        panel.setHeroImage(heroImage);
    }

   /**
    * Sets the score of the game.
    * @param score The score of the game.
    */
    public void setScore(int score) {
        panel.setScore(score);
    }

   /**
    * Sets the life count of the player.
    * @param life The life count of the player.
    */
    public void setLife(int life) {
        panel.setLife(life);
    }

   /**
    * Sets the fire count of the player.
    * @param fire The fire count of the player.
    */
    public void setFire(int fire) {
        panel.setFire(fire);
    }

   /**
    * Sets the first type of enemies.
    * @param enemyOnes The array of first type enemies.
    */
    public void setEnemyOnes(Enemy[] enemyOnes) {
        panel.setEnemyOnes(enemyOnes);
    }

   /**
    * Sets the second type of enemies.
    * @param enemyTwos The array of second type enemies.
    */
    public void setEnemyTwos(Enemy[] enemyTwos) {
        panel.setEnemyTwos(enemyTwos);
    }

  /**
   * Sets the boss enemies.
   * @param bossOnes The array of boss enemies.
   */
    public void setBossOnes(Boss[] bossOnes) {
        panel.setBossOnes(bossOnes);
    }

   /**
    * Sets the bullets.
    * @param bulletOnes The array of bullets.
    */
    public void setBulletOnes(Bullet[] bulletOnes) {
        panel.setBulletOnes(bulletOnes);
    }

   /**
    * Sets the buffs.
    * @param buffOnes The array of buffs.
    */
    public void setBuffOnes(Buff[] buffOnes) {
        panel.setBuffOnes(buffOnes);
    }

  /**
   * Sets the boss bullets.
   * @param bossBulletOnes
   */
  public void setBossBulletOnes(BossBullet[] bossBulletOnes) {
        panel.setBossBulletOnes(bossBulletOnes);
    }

  /**
   * Adds a mouse listener to the frame.
   * @param mouseEvent The mouse adapter for handling mouse events.
   */
  public void addMouseAListener(MouseAdapter mouseEvent) {
        addMouseListener(mouseEvent);
        addMouseMotionListener(mouseEvent);
        System.out.println("view add listener");
    }
}
