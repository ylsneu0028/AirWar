package Controller;

import Utils.Coordinate;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import javax.swing.Timer;
import Model.*;
import View.*;


public class Controller extends MouseAdapter implements ActionListener, MouseListener {

  private final Hero hero;
  private final ViewFrame view;
  private EnemyOne enemyOne;
  private int enemyOneIndex = 0;
  private EnemyOne[] enemyOnes;

  public Controller(Hero hero, EnemyOne enemyOne, ViewFrame view) {
    this.hero = hero;
    this.enemyOne = enemyOne;
    this.view = view;
    this.enemyOnes = new EnemyOne[0];
    this.view.addMouseAListener(this);
    this.view.setHeroImage(this.hero.getImage());
    this.view.setHeroCoordinate(this.hero.getCoordinate());
  }

  public void go() {
    new Timer(100, this).start();
  }

  public void creatEnemy1s() {
    // 控制住敌机生成的频率
    enemyOneIndex++;
    if (enemyOneIndex % 96 == 0) {
      enemyOneIndex = 0;
      EnemyOne enemy = new EnemyOne();
      enemyOnes = Arrays.copyOf(enemyOnes, enemyOnes.length + 1);
      enemyOnes[enemyOnes.length - 1] = enemy;
      System.out.println("敌机1：" + enemyOnes.length);
    }
  }

  public void moveEnemy1s() {
    // 面板中每架敌机都得移动
    for (int i = 0; i < enemyOnes.length; i++) {
      enemyOnes[i].move();
    }
  }

  public void removeEnemy1() {
    for (int i = 0; i < enemyOnes.length; i++) {
      if (enemyOnes[i] != null && enemyOnes[i].getCoordinate().getY() > 560 * 2) {
        enemyOnes[i] = enemyOnes[enemyOnes.length - 1];
        enemyOnes = Arrays.copyOf(enemyOnes, enemyOnes.length - 1);
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    hero.move();
    this.view.setHeroImage(this.hero.getImage());
    this.view.setHeroCoordinate(this.hero.getCoordinate());

    creatEnemy1s();
    moveEnemy1s();
    removeEnemy1();
    this.view.setEnemys(this.enemyOnes);

    view.paint();
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    System.out.println("鼠标移动事件");
    // 获取鼠标位置
    int heroWidth = hero.getWidth();
    int heroHeight = hero.getHeight();
    Coordinate heroCoordinate = new Coordinate(e.getX() - heroWidth / 2, e.getY() - heroHeight / 2);
    hero.setCoordinate(heroCoordinate);
    view.paint();
  }

  public void mouseClicked(MouseEvent e) {
    System.out.println("鼠标点击事件");
    this.go();
  }
}
