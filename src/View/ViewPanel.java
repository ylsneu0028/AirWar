package View;

import Model.*;
import Utils.Constants;
import Utils.Coordinate;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import lombok.Setter;


public class ViewPanel extends JPanel {

  private ImageIcon backImage;
  @Setter
  private ImageIcon heroImage;
  @Setter
  private Coordinate heroCoordinate;
  private ImageIcon enemyOneImage;
  @Setter
  private Enemy[] enemyOnes;
  @Setter
  private Coordinate backgroundCoordinate = new Coordinate(0, 0);
  private ImageIcon bulletOneImage;
  @Setter
  private Bullet[] bulletOnes;
  /* Step 3 */
  private ImageIcon bossOneImage;
  @Setter
  private Boss[] bossOnes;
  private ImageIcon bossBulletOneImage;
  @Setter
  private BossBullet[] bossBulletOnes;


  public ViewPanel() {
    this.backImage = new ImageIcon("image/background.png");
    this.heroImage = new ImageIcon("image/hero1-0.png");
    this.enemyOneImage = new ImageIcon(Constants.enemyOneImagePath);
    this.bulletOneImage = new ImageIcon(Constants.bulletOneImagePath);
    this.bulletOneImage = new ImageIcon((Constants.bossOneImagePath));
    this.bossBulletOneImage = new ImageIcon(Constants.bossBulletOneImagePath);
  }

  @Override
  protected void paintComponent(Graphics g) {
    System.out.println("wtttttttttffffff");
    super.paintComponent(g);
    // Draw background
    if (backgroundCoordinate != null) {
      g.drawImage(backImage.getImage(), 0, backgroundCoordinate.getY(), this);
      System.out.println("Y" + backgroundCoordinate.getY());
    }
    // Draw hero
    if (heroCoordinate != null) {
      g.drawImage(heroImage.getImage(), heroCoordinate.getX(), heroCoordinate.getY(), this);
      System.out.println("draw hero");
    }
    // Draw enemy
    if (enemyOnes != null) {
      for (int i = 0; i < enemyOnes.length; i++) {
        g.drawImage(enemyOnes[i].getImage().getImage(), enemyOnes[i].getCoordinate().getX(),
            enemyOnes[i].getCoordinate().getY(), null);
      }
    }
    // Draw boss: Step 4
    if (bossOnes != null) {
      for (int i = 0; i < bossOnes.length; i++) {
        g.drawImage(bossOnes[i].getImage().getImage(), bossOnes[i].getCoordinate().getX(),
            bossOnes[i].getCoordinate().getY(), null);
      }
    }
    // Draw bullet
    if (bulletOnes != null) {
      for (int i = 0; i < bulletOnes.length; i++) {
        g.drawImage(bulletOnes[i].getImage().getImage(), bulletOnes[i].getCoordinate().getX(),
            bulletOnes[i].getCoordinate().getY(), null);
        System.out.println("draw bullet");
      }
    }
    // Draw bossBullet
    if (bossBulletOnes != null) {
      for (int i = 0; i < bossBulletOnes.length; i++) {
        g.drawImage(bossBulletOnes[i].getImage().getImage(), bossBulletOnes[i].getCoordinate().getX(), bossBulletOnes[i].getCoordinate().getY(), null);
      }
    }
  }

  public void setBackground(Coordinate backgroundCoordinate) {
  }
}
