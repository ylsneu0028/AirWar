package View;

import Model.*;
import Utils.Constants;
import Utils.Coordinate;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import lombok.Setter;


public class ViewPanel extends JPanel {

  @Setter
  private int status;
  private ImageIcon backImage;
  @Setter
  private ImageIcon heroImage;
  private ImageIcon startImage;
  @Setter
  private Coordinate heroCoordinate;
  private ImageIcon enemyOneImage;
  private ImageIcon enemyTwoImage;
  @Setter
  private ImageIcon buffImage;
  @Setter
  private Enemy[] enemyOnes;
  @Setter
  private Enemy[] enemyTwos;
  @Setter
  private Buff[] buffOnes ;
  //private Coordinate backgroundCoordinate = new Coordinate(0, 0);
  @Setter
  private Coordinate backgroundCoordinate;
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
  @Setter
  private int score;
  @Setter
  private int life;
  @Setter
  private int fire;


  public ViewPanel() {
    this.backImage = new ImageIcon("image/background.png");
    this.startImage = new ImageIcon((Constants.startImagePath));
    this.heroImage = new ImageIcon("image/hero1-0.png");
    this.enemyOneImage = new ImageIcon(Constants.enemyOneImagePath);
    this.enemyTwoImage = new ImageIcon(Constants.enemyTwoImagePath);
    this.bulletOneImage = new ImageIcon(Constants.bulletOneImagePath);
    this.bossOneImage = new ImageIcon((Constants.bossOneImagePath));
    this.bossBulletOneImage = new ImageIcon(Constants.bossBulletOneImagePath);
    this.buffImage = new ImageIcon(Constants.buffOneImagePath);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if(backgroundCoordinate != null) {
      // Draw background
      g.drawImage(backImage.getImage(), 0, backgroundCoordinate.getY(), this);
      System.out.println(backgroundCoordinate.getY());
    }

    switch (status) {
      case 0:
        g.drawImage(startImage.getImage(), 0, 0, null);
        break;
      case 1:
        break;
//      case 2:
//        g.drawImage(pauseImage.getImage(), 0, 0, null);
//        break;
//      case 3:
//        g.drawImage(gameoverImage.getImage(), 0, 0, null);
//        break;
//      case 4:
//        g.drawImage(victoryImage.getImage(), 0, 0, null);
//        break;
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

    if (enemyTwos != null) {
      for (int i = 0; i < enemyTwos.length; i++) {
        g.drawImage(enemyTwos[i].getImage().getImage(), enemyTwos[i].getCoordinate().getX(),
            enemyTwos[i].getCoordinate().getY(), null);
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

    // Draw Buff
    if(buffOnes != null){
      for (int i = 0; i < buffOnes.length; i++) {
        g.drawImage(buffOnes[i].getImage().getImage(), buffOnes[i].getCoordinate().getX(),
            buffOnes[i].getCoordinate().getY(), null);
      }
    }

    g.setColor(Color.white);
    g.setFont(new Font("微软雅黑", Font.BOLD, 22));
    g.drawString("Score：" + score, 10, 30);
    g.drawString("Life ：" + life, 10, 60);
    g.drawString("Fire ：" + fire, 10, 90);
  }

}
