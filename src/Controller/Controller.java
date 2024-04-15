package Controller;

import static Utils.Constants.bulletOneImagePath;
import static Utils.Constants.bulletOneWidth;

import Utils.Constants;
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

  private int status = 1;
  private int score = 0;
  private final Hero hero;
  private final ViewFrame view;
  private Enemy enemyOne;
  private Bullet bulletOne;
  private Bullet bulletTwo;
  private int enemyOneIndex = 0;
  private Enemy[] enemyOnes;
  private BulletCollection bulletOnes;
  private Background background;
  public Controller(Hero hero, ViewFrame view) {
    this.view.setBackground();
    this.hero = hero;
    this.view = view;
    this.view.addMouseAListener(this);
    this.view.setHeroImage(this.hero.getImage());
    this.view.setHeroCoordinate(this.hero.getCoordinate());
    Initialization();
  }

  public void Initialization() {
    this.enemyOne = new Enemy(Constants.enemyOneImagePath, Constants.enemyOneXspeed,
        Constants.enemyOneYspeed, Constants.enemyOneLife, 1);
    this.enemyOnes = new Enemy[0];
    this.bulletOnes = new BulletCollection();
  }


  public void go() {
    new Timer(70, this).start();
  }

  public void creatEnemys() {
    // 控制住敌机生成的频率
    enemyOneIndex++;
    if (enemyOneIndex % 96 == 0) {
      enemyOneIndex = 0;
      Enemy enemy = new Enemy(Constants.enemyOneImagePath, Constants.enemyOneXspeed,
          Constants.enemyOneYspeed, Constants.enemyOneLife, 1);
      enemyOnes = Arrays.copyOf(enemyOnes, enemyOnes.length + 1);
      enemyOnes[enemyOnes.length - 1] = enemy;
      System.out.println("敌机1：" + enemyOnes.length);
    }
  }


  public void createBullets(BulletCollection bulletArray, int type, int bulletWidth,
      String bulletImagePath) {
    if (status == 1) {
      bulletArray.setBulletIndex(bulletArray.getBulletIndex() + 1);
      if (bulletArray.getBulletIndex() % 50 == 0) {
        bulletArray.setBulletIndex(0);
        if (hero.getFire() == type) {
          // 发射双发子弹
          Bullet bullet1 = new Bullet(
              hero.getCoordinate().getX() + hero.getWidth() / 4 - bulletWidth / 2,
              hero.getCoordinate().getY(), bulletImagePath, type);
          Bullet bullet2 = new Bullet(
              hero.getCoordinate().getX() + hero.getWidth() * 3 / 4 - bulletWidth / 2,
              hero.getCoordinate().getY(), bulletImagePath, type);
          System.out.println("new bullet");
          Bullet[] bulletCopy = Arrays.copyOf(bulletArray.getBulletArray(),
              bulletArray.getBulletArray().length + 2);
          bulletCopy[bulletCopy.length - 2] = bullet1;
          bulletCopy[bulletCopy.length - 1] = bullet2;
          bulletArray.setBulletArray(bulletCopy);
        }
//        else if (hero.getFire() == type + 1) {
//          // 发射三发子弹
//          Bullet bullet1 = new Bullet(hero.getCoordinate().getX() + hero.getWidth() / 4 - bulletWidth / 2, hero.getCoordinate().getY(),bulletImagePath,type);
//          Bullet bullet2 = new Bullet(hero.getCoordinate().getX() + hero.getWidth() * 3 / 4 - bulletWidth / 2,
//              hero.getCoordinate().getY(), bulletImagePath, type);
//          Bullet bullet3 = new Bullet(hero.getCoordinate().getX() + hero.getWidth() / 2 - bulletWidth / 2, hero.Y);
//          bullet1s = Arrays.copyOf(bullet1s, bullet1s.length + 3);
//          bullet1s[bullet1s.length - 3] = bullet1;
//          bullet1s[bullet1s.length - 2] = bullet2;
//          bullet1s[bullet1s.length - 1] = bullet3;
//          System.out.println("子弹：" + bullet1s.length);
//        } else if (hero.fire >= 3) {
//          // 发射四发子弹
//          Bullet1 bullet1 = new Bullet1(hero.X + hero.width * 1 / 4 - bullet1Image.getIconWidth() / 2,
//              hero.Y);
//          Bullet1 bullet2 = new Bullet1(hero.X + hero.width * 2 / 4 - bullet1Image.getIconWidth() / 2,
//              hero.Y);
//          Bullet1 bullet3 = new Bullet1(hero.X + hero.width * 3 / 4 - bullet1Image.getIconWidth() / 2,
//              hero.Y);
//          Bullet1 bullet4 = new Bullet1(hero.X + hero.width * 4 / 4 - bullet1Image.getIconWidth() / 2,
//              hero.Y);
//          bullet1s = Arrays.copyOf(bullet1s, bullet1s.length + 4);
//          bullet1s[bullet1s.length - 4] = bullet1;
//          bullet1s[bullet1s.length - 3] = bullet2;
//          bullet1s[bullet1s.length - 2] = bullet3;
//          bullet1s[bullet1s.length - 1] = bullet4;
//          System.out.println("子弹：" + bullet1s.length);
//        }
//
//        else {
//          // 发射单发子弹
//
//          bullet1Index = 0;
//          Bullet1 bullet = new Bullet1(hero.X + hero.width / 2 - bullet1Image.getIconWidth() / 2, hero.Y);
//          bullet1s = Arrays.copyOf(bullet1s, bullet1s.length + 1);
//          bullet1s[bullet1s.length - 1] = bullet;
//          System.out.println("子弹：" + bullet1s.length);
//        }
      }
    }
  }

  public void moveEnemys() {
    // 面板中每架敌机都得移动
    for (Enemy one : enemyOnes) {
      one.move();
    }
  }

  public void moveBullets(BulletCollection bullets) {
    // 面板中每发子弹都得移动
    for (int i = 0; i < bullets.getBulletArray().length; i++) {
      bullets.getBulletArray()[i].move();
    }
  }


  public void EnemyHitHero() {
    for (int i = 0; i < enemyOnes.length; i++) {
      if (enemyOnes[i] != null) {
        if ((
            hero.getCoordinate().getX() - hero.getWidth() / 2 >= enemyOnes[i].getCoordinate().getX()
                && hero.getCoordinate().getX() - hero.getWidth() / 2
                <= enemyOnes[i].getCoordinate().getX() + enemyOnes[i].getWidth()) || (
            hero.getCoordinate().getX() + hero.getWidth() / 2 >= enemyOnes[i].getCoordinate().getX()
                && hero.getCoordinate().getX() + hero.getWidth() / 2
                <= enemyOnes[i].getCoordinate().getX() + enemyOnes[i].getWidth())) {
          if ((hero.getCoordinate().getY() >= enemyOnes[i].getCoordinate().getY()
              && hero.getCoordinate().getY()
              <= enemyOnes[i].getCoordinate().getY() + enemyOnes[i].getHeight()) || (
              hero.getCoordinate().getY() + hero.getHeight() / 2 >= enemyOnes[i].getCoordinate()
                  .getY() && hero.getCoordinate().getY() + hero.getHeight() / 2
                  <= enemyOnes[i].getCoordinate().getY() + enemyOnes[i].getHeight())) {
            hero.setLife(hero.getLife() - 1);
            enemyOnes[i] = enemyOnes[enemyOnes.length - 1];
            enemyOnes = Arrays.copyOf(enemyOnes, enemyOnes.length - 1);

            if (hero.getLife() <= 0) {
              //initialization();
              status = 3;
            }
          }
        }
      }
    }
  }

  public void bulletHitEnemy(BulletCollection bullets) {
    int flag1 = -1;
    int flag2 = -1;
    for (int i = 0; i < bullets.getBulletArray().length; i++) {
      for (int j = 0; j < enemyOnes.length; j++) {
        if (bullets.getBulletArray()[i].getCoordinate().getX() >= enemyOnes[j].getCoordinate()
            .getX() && bullets.getBulletArray()[i].getCoordinate().getX()
            <= enemyOnes[j].getCoordinate().getX() + enemyOnes[j].getWidth()) {
          if (bullets.getBulletArray()[i].getCoordinate().getY() >= enemyOnes[j].getCoordinate()
              .getY() && bullets.getBulletArray()[i].getCoordinate().getY()
              <= enemyOnes[j].getCoordinate().getY() + enemyOnes[j].getHeight()) {
            enemyOnes[j].setLife(enemyOnes[j].getLife() - 1);
            flag2 = i;
            if (enemyOnes[j].getLife() <= 0) {
              System.out.println("score=" + score);
              flag1 = j;
            }
            break;
          }
        }
      }
      while (flag1 != -1) {
        enemyOnes[flag1] = enemyOnes[enemyOnes.length - 1];
        enemyOnes = Arrays.copyOf(enemyOnes, enemyOnes.length - 1);
        score += 2;
        flag1 = -1;
      }

    }
    while (flag2 != -1) {
      bullets.getBulletArray()[flag2] = bullets.getBulletArray()[bullets.bulletArray.length - 1];
      bullets.setBulletArray(Arrays.copyOf(bullets.getBulletArray(), bullets.getBulletArray().length - 1));
      flag2 = -1;
    }
  }

  public void removeEnemyOne() {
    for (int i = 0; i < enemyOnes.length; i++) {
      if (enemyOnes[i] != null && enemyOnes[i].getCoordinate().getY() > 560 * 2) {
        enemyOnes[i] = enemyOnes[enemyOnes.length - 1];
        enemyOnes = Arrays.copyOf(enemyOnes, enemyOnes.length - 1);
      }
    }
  }

  public void removeBullet(BulletCollection bullets) {
    for (int i = 0; i < bullets.getBulletArray().length; i++) {
      if (bullets.getBulletArray()[i] != null
          && bullets.getBulletArray()[i].getCoordinate().getY() < -Constants.windowWidth) {
        bullets.getBulletArray()[i] = bullets.getBulletArray()[bullets.getBulletArray().length - 1];
        bullets.setBulletArray(
            Arrays.copyOf(bullets.getBulletArray(), bullets.getBulletArray().length - 1));
      }
    }
  }
  public void moveBackground() {
    background.move();
  }

  public void createBackground() {
    background = new Background();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    createBackground();
    moveBackground();
    hero.move();
    this.view.setHeroImage(this.hero.getImage());
    this.view.setHeroCoordinate(this.hero.getCoordinate());
    moveBackground();
    creatEnemys();
    moveEnemys();
    EnemyHitHero();
    createBullets(this.bulletOnes, 1, bulletOneWidth, bulletOneImagePath);
    moveBullets(this.bulletOnes);
    removeBullet(this.bulletOnes);
    bulletHitEnemy(this.bulletOnes);
    removeEnemyOne();
    this.view.setEnemyOnes(this.enemyOnes);
    this.view.setBulletOnes(this.bulletOnes.getBulletArray());
    view.paint();
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    System.out.println("Mouse movement event");
    // Mouse movement event
    int heroWidth = hero.getWidth();
    int heroHeight = hero.getHeight();
    Coordinate heroCoordinate = new Coordinate(e.getX() - heroWidth / 2, e.getY() - heroHeight / 2);
    hero.setCoordinate(heroCoordinate);
    view.paint();
  }

  public void mouseClicked(MouseEvent e) {
    System.out.println("Mouse click event");
//    this.go();
  }
}
