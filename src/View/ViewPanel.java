package View;

import Utils.Coordinate;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import lombok.Setter;
import Model.*;


public class ViewPanel extends JPanel {

  private ImageIcon backImage;
  @Setter
  private ImageIcon heroImage;
  @Setter
  private Coordinate heroCoordinate;
  private ImageIcon enemyOneImage;
  @Setter
  private EnemyOne[] enemyOnes;


  public ViewPanel() {
    this.backImage = new ImageIcon("image/background1.png");
    this.heroImage = new ImageIcon("image/hero1-0.png");
    this.enemyOneImage = new ImageIcon("image/enemy1.png");

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(backImage.getImage(), 0, 0, this);
    g.drawImage(heroImage.getImage(), heroCoordinate.getX(), heroCoordinate.getY(), this);
    System.out.println("draw hero");

    for (int i = 0; i < enemyOnes.length; i++) {
      g.drawImage(enemyOnes[i].getImage().getImage(), enemyOnes[i].getCoordinate().getX(),
          enemyOnes[i].getCoordinate().getY(), null);
    }
  }
}
