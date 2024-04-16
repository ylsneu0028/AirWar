package View;

import Model.*;
import java.awt.event.MouseAdapter;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import Utils.*;

public class ViewFrame extends JFrame {

  private ViewPanel panel;

  public ViewFrame() {
    this.setTitle("Air War");
    this.setSize(560, 824);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(true);

    System.out.println("1111");
    this.panel = new ViewPanel();
    System.out.println("2222");
    this.add(panel);
    System.out.println("33333");
    this.setVisible(true);
    System.out.println("444444");

  }


  public void paint() {
    panel.repaint();
  }

  public void setHeroCoordinate(Coordinate coordinate) {
    panel.setHeroCoordinate(coordinate);
  }

  public void setBackground(Coordinate backgroundCoordinate) { panel.setBackground(backgroundCoordinate);}

  public void setHeroImage(ImageIcon heroImage) {
    panel.setHeroImage(heroImage);
  }

  public void setEnemyOnes(Enemy[] enemyOnes) {
    panel.setEnemyOnes(enemyOnes);
  }

  /* Step 5*/
  public void setBossOnes(Boss[] bossOnes) {
    panel.setBossOnes(bossOnes);
  }
  public void setBulletOnes(Bullet[] bulletOnes) { panel.setBulletOnes(bulletOnes);}

  public void setBossBulletOnes(BossBullet[] bossBulletOnes) { panel.setBossBulletOnes(bossBulletOnes);}

  public void addMouseAListener(MouseAdapter mouseEvent) {
    addMouseListener(mouseEvent);
    addMouseMotionListener(mouseEvent);
    System.out.println("view add listener");
  }
}
