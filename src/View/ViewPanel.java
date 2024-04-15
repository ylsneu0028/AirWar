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
  private Coordinate backgroundCoordinate;

  private ImageIcon bulletOneImage;
  @Setter
  private Bullet[] bulletOnes;
  private ImageIcon bossOneImage;
  @Setter
  private Boss[] bossOnes;


  public ViewPanel() {
    this.backImage = new ImageIcon("image/background.png");
    this.heroImage = new ImageIcon("image/hero1-0.png");
    this.enemyOneImage = new ImageIcon(Constants.enemyOneImagePath);
    this.bulletOneImage = new ImageIcon(Constants.bulletOneImagePath);
    this.bulletOneImage = new ImageIcon((Constants.bossOneImagePath));
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    // Draw background
    g.drawImage(backImage.getImage(), 0, backgroundCoordinate.getY(), this);
    System.out.println(backgroundCoordinate.getY());
    // Draw hero
    g.drawImage(heroImage.getImage(), heroCoordinate.getX(), heroCoordinate.getY(), this);
    System.out.println("draw hero");
    // Draw enemy
    for (int i = 0; i < enemyOnes.length; i++) {
      g.drawImage(enemyOnes[i].getImage().getImage(), enemyOnes[i].getCoordinate().getX(),
          enemyOnes[i].getCoordinate().getY(), null);
    }
    // Draw boss
    for (int i = 0; i < bossOnes.length; i++) {
      g.drawImage(bossOnes[i].getImage().getImage(), bossOnes[i].getCoordinate().getX(), bossOnes[i].getCoordinate().getY(), null);
    }
    // Draw bullet
    for (int i = 0; i < bulletOnes.length; i++) {
      g.drawImage(bulletOnes[i].getImage().getImage(), bulletOnes[i].getCoordinate().getX(), bulletOnes[i].getCoordinate().getY(), null);
      System.out.println("draw bullet");
    }

  }
}
