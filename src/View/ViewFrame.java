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

    this.panel = new ViewPanel();
    // jPanel.action();
    this.add(panel);
    this.setVisible(true);
  }


  public void paint() {
    panel.repaint();
  }

  public void setHeroCoordinate(Coordinate coordinate) {
    panel.setHeroCoordinate(coordinate);
  }

  public void setHeroImage(ImageIcon heroImage) {
    panel.setHeroImage(heroImage);
  }

  public void setEnemyOnes(Enemy[] enemyOnes) {
    panel.setEnemyOnes(enemyOnes);
  }

  public void setBulletOnes(Bullet[] bulletOnes) { panel.setBulletOnes(bulletOnes);}

  public void addMouseAListener(MouseAdapter mouseEvent) {
    addMouseListener(mouseEvent);
    addMouseMotionListener(mouseEvent);
    System.out.println("view add listener");
  }
}
