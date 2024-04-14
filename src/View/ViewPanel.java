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

  private ImageIcon bulletOneImage;
  @Setter
  private Bullet[] bulletOnes;
//  private ImageIcon enemyTwoImage;
//  @Setter
//  private EnemyOne[] enemyTwos;


  public ViewPanel() {
    this.backImage = new ImageIcon("image/background1.png");
    this.heroImage = new ImageIcon("image/hero1-0.png");
    this.enemyOneImage = new ImageIcon(Constants.enemyOneImagePath);
    this.bulletOneImage = new ImageIcon(Constants.bulletOneImagePath);
//    this.enemyTwoImage =  new ImageIcon("image/enemy2.png");

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    // Draw background
    g.drawImage(backImage.getImage(), 0, 0, this);
    // Draw hero
    g.drawImage(heroImage.getImage(), heroCoordinate.getX(), heroCoordinate.getY(), this);
    System.out.println("draw hero");
    // Draw enemy
    for (int i = 0; i < enemyOnes.length; i++) {
      g.drawImage(enemyOnes[i].getImage().getImage(), enemyOnes[i].getCoordinate().getX(),
          enemyOnes[i].getCoordinate().getY(), null);
    }
    System.out.println("wtffff" + bulletOnes.length);
    // Draw bullet
    for (int i = 0; i < bulletOnes.length; i++) {
      g.drawImage(bulletOnes[i].getImage().getImage(), bulletOnes[i].getCoordinate().getX(), bulletOnes[i].getCoordinate().getY(), null);
      System.out.println("draw bullet");
    }



//    for (int i = 0; i < enemyTwos.length; i++) {
//      g.drawImage(enemyTwos[i].getImage().getImage(), enemyTwos[i].getCoordinate().getX(), enemyTwos[i].getCoordinate().getY(), null);
//    }
  }
}
