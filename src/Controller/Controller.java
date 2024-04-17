package Controller;

import static Utils.Constants.bossBulletOneImagePath;
import static Utils.Constants.bossBulletOneLevel;
import static Utils.Constants.bossBulletOneType;
import static Utils.Constants.bossBulletOneWidth;
import static Utils.Constants.bossOneImagePath;
import static Utils.Constants.bossOneLife;
import static Utils.Constants.bossOneXspeed;
import static Utils.Constants.bossOneYspeed;
import static Utils.Constants.bulletOneImagePath;
import static Utils.Constants.bulletOneWidth;
import static Utils.Constants.enemyOneImagePath;
import static Utils.Constants.enemyOneLife;
import static Utils.Constants.enemyOneType;
import static Utils.Constants.enemyOneXspeed;
import static Utils.Constants.enemyOneYspeed;
import static Utils.Constants.enemyTwoImagePath;
import static Utils.Constants.enemyTwoLife;
import static Utils.Constants.enemyTwoType;
import static Utils.Constants.enemyTwoXspeed;
import static Utils.Constants.enemyTwoYspeed;
import static Utils.Constants.windowHeight;
import static Utils.Constants.windowWidth;

import Utils.Constants;
import Utils.Coordinate;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import Model.*;
import View.*;


public class Controller extends MouseAdapter implements ActionListener, MouseListener {

  private int status = 0; //0: before start; 1: on progress; 2: stop; 3 game over; 4: victory;
  private int score = 0;
  private final Hero hero;
  private final ViewFrame view;
  private Enemy enemyOne;
  private Enemy enemyTwo;
  private Boss bossOne;
  private Bullet bulletOne;
  private BossBullet bossBulletOne;
  private EnemyCollection enemyOnes;
  private EnemyCollection enemyTwos;
  private Buff buffOne;
  private BuffCollection buffOnes;
  /* Step 6*/
  private BossCollection bossOnes;
  private BulletCollection bulletOnes;
  private BossBulletCollection bossBulletOnes;
  private Background background = new Background();

  public Controller(Hero hero, ViewFrame view) {
    this.hero = hero;
    this.view = view;
    this.view.addMouseAListener(this);
  }

  public void Initialization() {
    this.enemyOnes = new EnemyCollection();
    this.enemyTwos = new EnemyCollection();
    this.bulletOnes = new BulletCollection();
    this.bossBulletOnes = new BossBulletCollection();
    /* Step 7*/
    this.bossOnes = new BossCollection();
    this.buffOnes = new BuffCollection();
    this.createBackground();
  }


  public void go() {Initialization();}

  public void createEnemies(EnemyCollection enemies, String enemyImagePath, int enemyXspeed,
      int enemyYspeed, int enemyLife, int enemyType) {
    enemies.setEnemyIndex(enemies.getEnemyIndex() + 1);
    if (enemies.getEnemyIndex() % 96 == 0) {
      enemies.setEnemyIndex(0);
      Enemy enemy = new Enemy(enemyImagePath, enemyXspeed, enemyYspeed, enemyLife, enemyType);
      enemies.setEnemyArray(
          Arrays.copyOf(enemies.getEnemyArray(), enemies.getEnemyArray().length + 1));
      enemies.getEnemyArray()[enemies.getEnemyArray().length - 1] = enemy;
      System.out.println("Enemys：" + enemies.getEnemyArray().length);
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

  public void createBuffs(BuffCollection buffs) {
    // 控制住Buff生成的频率
    buffs.setBuffIndex(buffs.getBuffIndex() + 1);
    //System.out.println("buffOneIndex"+buff1Index);
    if (buffs.getBuffIndex() % 50 == 0) {
      buffs.setBuffIndex(0);
      Buff buff1 = new Buff(Constants.buffOneImagePath, Constants.buffOneXspeed,
          Constants.buffOneYspeed, Constants.buffOneType);
      buffs.setBuffArray(Arrays.copyOf(buffs.getBuffArray(), buffs.getBuffArray().length + 1));
      buffs.getBuffArray()[buffs.getBuffArray().length - 1] = buff1;
      //System.out.println("Buff：" + buffOnes.length);
    }
  }

  public void moveBuffs(BuffCollection buffs) {
    // 面板中每个buff都得移动
    for (Buff one : buffs.getBuffArray()) {
      one.move();
    }
  }

  /* Step 8 */
  public void createBoss(BossCollection bosses, int typeVal) {
    // 控制住敌机生成的频率
    bosses.setBossIndex(bosses.getBossIndex() + 1);
    if (bosses.getBossIndex() % 1000 == 0) {
      bosses.setBossIndex(0);
      Boss boss = null;
      if (typeVal == 1) {
        boss = new Boss(bossOneImagePath, bossOneXspeed, bossOneYspeed, bossOneLife, 1);
      }
      Boss[] bossesCopy = Arrays.copyOf(bosses.getBossArray(), bosses.getBossArray().length + 1);
      bossesCopy[bossesCopy.length - 1] = boss;
      bosses.setBossArray(bossesCopy);
      System.out.println("boss1：" + bosses.getBossArray().length);
    }

  }

  public void creatBossBullets(BossCollection bosses, BossBulletCollection bossBullets,
      String bossBulletImagePath, int bossBulletWidth, int typeVal, int level) {
    if (status == 1) {
      for (int i = 0; i < bosses.getBossArray().length; i++) {
        if (bosses.getBossArray()[i] != null) {
          bossBullets.setBossBulletIndex(bossBullets.getBossBulletIndex() + 1);
          if (bossBullets.getBossBulletIndex() % 200 == 0) {
            bossBullets.setBossBulletIndex(0);
            // Shoot two bullets
            BossBullet bullet1 = new BossBullet(bosses.getBossArray()[i].getCoordinate().getX()
                + bosses.getBossArray()[i].getWidth() / 4 - bossBulletWidth,
                bosses.getBossArray()[i].getCoordinate().getY()
                    + bosses.getBossArray()[i].getHeight() / 2, bossBulletImagePath, typeVal,
                level);
            BossBullet bullet2 = new BossBullet(bosses.getBossArray()[i].getCoordinate().getX()
                + bosses.getBossArray()[i].getWidth() * 3 / 4 - bossBulletWidth,
                bosses.getBossArray()[i].getCoordinate().getY()
                    + bosses.getBossArray()[i].getHeight() / 2, bossBulletImagePath, typeVal,
                level);
            BossBullet[] bossBulletsCopy = Arrays.copyOf(bossBullets.getBossBulletArray(),
                bossBullets.getBossBulletArray().length + 2);
            bossBulletsCopy[bossBulletsCopy.length - 2] = bullet1;
            bossBulletsCopy[bossBulletsCopy.length - 1] = bullet2;
            System.out.println("Boss bullet：" + bossBulletsCopy.length);
            bossBullets.setBossBulletArray(bossBulletsCopy);
          }
        }
      }

    }

  }

  public void moveEnemies(EnemyCollection enemies) {
    for (Enemy one : enemies.getEnemyArray()) {
      one.move();
    }
  }

  /* Step 9 */
  public void moveBoss(BossCollection bosses) {
    for (Boss one : bosses.getBossArray()) {
      one.move();
    }
  }

  public void moveBullets(BulletCollection bullets) {
    for (Bullet one : bullets.getBulletArray()) {
      one.move();
    }
  }

  public void moveBossBullets(BossBulletCollection bossBullets) {
    for (BossBullet one : bossBullets.getBossBulletArray()) {
      one.move();
    }
  }

  public void enemyHitHero(EnemyCollection enemies) {
    for (int i = 0; i < enemies.getEnemyArray().length; i++) {
      if (enemies.getEnemyArray()[i] != null) {
        if ((hero.getCoordinate().getX() - hero.getWidth() / 2
            >= enemies.getEnemyArray()[i].getCoordinate().getX()
            && hero.getCoordinate().getX() - hero.getWidth() / 2
            <= enemies.getEnemyArray()[i].getCoordinate().getX()
            + enemies.getEnemyArray()[i].getWidth()) || (
            hero.getCoordinate().getX() + hero.getWidth() / 2
                >= enemies.getEnemyArray()[i].getCoordinate().getX()
                && hero.getCoordinate().getX() + hero.getWidth() / 2
                <= enemies.getEnemyArray()[i].getCoordinate().getX()
                + enemies.getEnemyArray()[i].getWidth())) {
          if ((hero.getCoordinate().getY() >= enemies.getEnemyArray()[i].getCoordinate().getY()
              && hero.getCoordinate().getY() <= enemies.getEnemyArray()[i].getCoordinate().getY()
              + enemies.getEnemyArray()[i].getHeight()) || (
              hero.getCoordinate().getY() + hero.getHeight() / 2
                  >= enemies.getEnemyArray()[i].getCoordinate().getY()
                  && hero.getCoordinate().getY() + hero.getHeight() / 2
                  <= enemies.getEnemyArray()[i].getCoordinate().getY()
                  + enemies.getEnemyArray()[i].getHeight())) {
            hero.setLife(hero.getLife() - 1);
            enemies.getEnemyArray()[i] = enemies.getEnemyArray()[enemies.getEnemyArray().length
                - 1];
            enemies.setEnemyArray(
                Arrays.copyOf(enemies.getEnemyArray(), enemies.getEnemyArray().length - 1));

            if (hero.getLife() <= 0) {
              //initialization();
              status = 3;
            }
          }
        }
      }
    }
  }

  public void bossHitHero(BossCollection bosses) {
    for (int i = 0; i < bosses.getBossArray().length; i++) {
      if (bosses.getBossArray()[i] != null) {
        if ((hero.getCoordinate().getX() - hero.getWidth() / 2
            >= bosses.getBossArray()[i].getCoordinate().getX()
            && hero.getCoordinate().getX() - hero.getWidth() / 2
            <= bosses.getBossArray()[i].getCoordinate().getX()
            + bosses.getBossArray()[i].getWidth()) || (
            hero.getCoordinate().getX() + hero.getWidth() / 2
                >= bosses.getBossArray()[i].getCoordinate().getX()
                && hero.getCoordinate().getX() + hero.getWidth() / 2
                <= bosses.getBossArray()[i].getCoordinate().getX()
                + bosses.getBossArray()[i].getWidth())) {
          if ((hero.getCoordinate().getY() >= bosses.getBossArray()[i].getCoordinate().getY()
              && hero.getCoordinate().getY() <= bosses.getBossArray()[i].getCoordinate().getY()
              + bosses.getBossArray()[i].getHeight()) || (
              hero.getCoordinate().getY() + hero.getHeight() / 2
                  >= bosses.getBossArray()[i].getCoordinate().getY()
                  && hero.getCoordinate().getY() + hero.getHeight() / 2
                  <= bosses.getBossArray()[i].getCoordinate().getY()
                  + bosses.getBossArray()[i].getHeight())) {
            hero.setLife(hero.getLife() - bosses.getBossArray()[i].getLife());
            bosses.getBossArray()[i] = bosses.getBossArray()[bosses.getBossArray().length - 1];
            bosses.setBossArray(
                Arrays.copyOf(bosses.getBossArray(), bosses.getBossArray().length - 1));
            if (hero.getLife() <= 0) {
              status = 3;
            }
          }
        }
      }
    }
  }

  public void bulletHitEnemy(BulletCollection bullets, EnemyCollection enemies, int typeVal) {
    int flag1 = -1;
    int flag2 = -1;
    for (int i = 0; i < bullets.getBulletArray().length; i++) {
      for (int j = 0; j < enemies.getEnemyArray().length; j++) {
        if (bullets.getBulletArray()[i].getCoordinate().getX()
            >= enemies.getEnemyArray()[j].getCoordinate().getX()
            && bullets.getBulletArray()[i].getCoordinate().getX()
            <= enemies.getEnemyArray()[j].getCoordinate().getX()
            + enemies.getEnemyArray()[j].getWidth()) {
          if (bullets.getBulletArray()[i].getCoordinate().getY()
              >= enemies.getEnemyArray()[j].getCoordinate().getY()
              && bullets.getBulletArray()[i].getCoordinate().getY()
              <= enemies.getEnemyArray()[j].getCoordinate().getY()
              + enemies.getEnemyArray()[j].getHeight()) {
            enemies.getEnemyArray()[j].setLife(enemies.getEnemyArray()[j].getLife() - 1);
            flag2 = i;
            if (enemies.getEnemyArray()[j].getLife() <= 0) {
              System.out.println("score=" + score);
              flag1 = j;
            }
            break;
          }
        }
      }
      while (flag1 != -1) {
        enemies.getEnemyArray()[flag1] = enemies.getEnemyArray()[enemies.getEnemyArray().length
            - 1];
        enemies.setEnemyArray(
            Arrays.copyOf(enemies.getEnemyArray(), enemies.getEnemyArray().length - 1));
        this.score += typeVal;
        flag1 = -1;
      }

    }
    while (flag2 != -1) {
      bullets.getBulletArray()[flag2] = bullets.getBulletArray()[bullets.bulletArray.length - 1];
      bullets.setBulletArray(
          Arrays.copyOf(bullets.getBulletArray(), bullets.getBulletArray().length - 1));
      flag2 = -1;
    }
  }

  public void bulletHitBoss(BulletCollection bullets, BossCollection bosses, int typeVal) {
    int flag1 = -1;
    int flag2 = -1;
    for (int i = 0; i < bullets.getBulletArray().length; i++) {
      for (int j = 0; j < bosses.getBossArray().length; j++) {
        if (bullets.getBulletArray()[i].getCoordinate().getX()
            >= bosses.getBossArray()[j].getCoordinate().getX()
            && bullets.getBulletArray()[i].getCoordinate().getX()
            <= bosses.getBossArray()[j].getCoordinate().getX()
            + bosses.getBossArray()[j].getWidth()) {
          if (bullets.getBulletArray()[i].getCoordinate().getY()
              >= bosses.getBossArray()[j].getCoordinate().getY()
              && bullets.getBulletArray()[i].getCoordinate().getY()
              <= bosses.getBossArray()[j].getCoordinate().getY()
              + bosses.getBossArray()[j].getHeight()) {

            bosses.getBossArray()[j].setLife(bosses.getBossArray()[j].getLife() - 1);
            flag2 = i;
            if (bosses.getBossArray()[j].getLife() <= 0) {

              System.out.println("score=" + score);
              flag1 = j;
            }
            break;
          }
        }

      }
      while (flag1 != -1) {
        bosses.getBossArray()[flag1] = bosses.getBossArray()[bosses.getBossArray().length - 1];
        bosses.setBossArray(Arrays.copyOf(bosses.getBossArray(), bosses.getBossArray().length - 1));
        this.score += typeVal * 10;
        flag1 = -1;

      }
    }
    while (flag2 != -1) {
      bullets.getBulletArray()[flag2] = bullets.getBulletArray()[bullets.getBulletArray().length
          - 1];
      bullets.setBulletArray(
          Arrays.copyOf(bullets.getBulletArray(), bullets.getBulletArray().length - 1));
      flag2 = -1;
    }
  }

  public void bossBulletHitHero(BossBulletCollection bossBullets) {
    int flag2 = -1;
    for (int i = 0; i < bossBullets.getBossBulletArray().length; i++) {
      if (bossBullets.getBossBulletArray()[i].getCoordinate().getX() >= hero.getCoordinate().getX()
          && bossBullets.getBossBulletArray()[i].getCoordinate().getX()
          <= hero.getCoordinate().getX() + hero.getWidth()) {
        if (bossBullets.getBossBulletArray()[i].getCoordinate().getY() >= hero.getCoordinate()
            .getY() && bossBullets.getBossBulletArray()[i].getCoordinate().getY()
            <= hero.getCoordinate().getY() + hero.getHeight()) {
          hero.setLife(hero.getLife() - bossBullets.getBossBulletArray()[i].getLevel());
          flag2 = i;
          break;
        }
      }
    }
    while (flag2 != -1) {
      bossBullets.getBossBulletArray()[flag2] = bossBullets.getBossBulletArray()[
          bossBullets.getBossBulletArray().length - 1];
      bossBullets.setBossBulletArray(Arrays.copyOf(bossBullets.getBossBulletArray(),
          bossBullets.getBossBulletArray().length - 1));
      flag2 = -1;

    }
    if (hero.getLife() <= 0) {
      status = 3;
    }

  }

  public void BuffHitHero(BuffCollection buffs) {
    for (int i = 0; i < buffs.getBuffArray().length; i++) {
      if (buffs.getBuffArray()[i] != null) {
        if ((hero.getCoordinate().getX() - hero.getWidth() / 2 >= buffs.getBuffArray()[i].getCoordinate().getX() && hero.getCoordinate().getX() - hero.getWidth() / 2 <= buffs.getBuffArray()[i].getCoordinate().getX() + buffs.getBuffArray()[i].getWidth())
            || (hero.getCoordinate().getX() + hero.getWidth() / 2 >= buffs.getBuffArray()[i].getCoordinate().getX()
            && hero.getCoordinate().getX() + hero.getWidth() / 2 <= buffs.getBuffArray()[i].getCoordinate().getX() + buffs.getBuffArray()[i].getWidth())) {
          if ((hero.getCoordinate().getY()  >= buffs.getBuffArray()[i].getCoordinate().getY()
              && hero.getCoordinate().getY()  <= buffs.getBuffArray()[i].getCoordinate().getY() + buffs.getBuffArray()[i].getHeight())
              || (hero.getCoordinate().getY() + hero.getHeight() / 2 >= buffs.getBuffArray()[i].getCoordinate().getY()
              && hero.getCoordinate().getY() + hero.getHeight() / 2 <= buffs.getBuffArray()[i].getCoordinate().getY() + buffs.getBuffArray()[i].getHeight())) {

            buffs.getBuffArray()[i] = buffs.getBuffArray()[buffs.getBuffArray().length - 1];
            buffs.setBuffArray(Arrays.copyOf(buffs.getBuffArray(), buffs.getBuffArray().length - 1));
            if(hero.getFire() <= 2)
            hero.setFire(hero.getFire() +1);
            hero.setLife(hero.getLife() + 2);
          }
        }
      }
    }
  }
  public void removeEnemyOne(EnemyCollection enemies) {
    for (int i = 0; i < enemies.getEnemyArray().length; i++) {
      if (enemies.getEnemyArray()[i] != null
          && enemies.getEnemyArray()[i].getCoordinate().getY() > windowWidth * 2) {
        enemies.getEnemyArray()[i] = enemies.getEnemyArray()[enemies.getEnemyArray().length - 1];
        enemies.setEnemyArray(
            Arrays.copyOf(enemies.getEnemyArray(), enemies.getEnemyArray().length - 1));
      }
    }
  }

  /* Step 10*/
  public void removeBoss(BossCollection bosses) {
    for (int i = 0; i < bosses.getBossArray().length; i++) {
      if (bosses.getBossArray()[i] != null
          && bosses.getBossArray()[i].getCoordinate().getY() > windowHeight * 2) {
        bosses.getBossArray()[i] = bosses.getBossArray()[bosses.getBossArray().length - 1];
        bosses.setBossArray(Arrays.copyOf(bosses.getBossArray(), bosses.getBossArray().length - 1));
      }
    }
  }

  public void removeBuffOne(BuffCollection buffs) {
    for (int i = 0; i < buffs.getBuffArray().length; i++) {
      if (buffs.getBuffArray()[i] != null && buffs.getBuffArray()[i].getCoordinate().getY() > windowHeight * 2) {
        buffs.getBuffArray()[i] = buffs.getBuffArray()[buffs.getBuffArray().length - 1];
        buffs.setBuffArray(Arrays.copyOf(buffs.getBuffArray(), buffs.getBuffArray().length - 1));
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

  public void removeBossBullet(BossBulletCollection bossBullets) {
    for (int i = 0; i < bossBullets.getBossBulletArray().length; i++) {
      if (bossBullets.getBossBulletArray()[i] != null
          && bossBullets.getBossBulletArray()[i].getCoordinate().getY() < -windowHeight) {
        bossBullets.getBossBulletArray()[i] = bossBullets.getBossBulletArray()[
            bossBullets.getBossBulletArray().length - 1];
        bossBullets.setBossBulletArray(Arrays.copyOf(bossBullets.getBossBulletArray(),
            bossBullets.getBossBulletArray().length - 1));
      }
    }
  }


  public void moveBackground() {
    // Logic to update the background's position
    background.move();
    // Ensure the coordinate is updated after moving
    this.view.setBackground(background.getCoordinate());
  }

  public void createBackground() {
    background = new Background();
  }

  @Override
  public void actionPerformed(ActionEvent e) {

//    System.out.println("action"+this.background.getCoordinate().getY());

    // Update other game states
    if(status == 1){
      moveBackground();

      hero.move();

      createEnemies(this.enemyOnes, enemyOneImagePath, enemyOneXspeed, enemyOneYspeed, enemyOneLife,
          enemyOneType);
      moveEnemies(this.enemyOnes);
      removeEnemyOne(this.enemyOnes);
      enemyHitHero(this.enemyOnes);

      createEnemies(this.enemyTwos, enemyTwoImagePath, enemyTwoXspeed, enemyTwoYspeed, enemyTwoLife,
          enemyTwoType);
      moveEnemies(this.enemyTwos);
      enemyHitHero(this.enemyTwos);


      createBullets(this.bulletOnes, 1, bulletOneWidth, bulletOneImagePath);
      moveBullets(this.bulletOnes);
      removeBullet(this.bulletOnes);
      bulletHitEnemy(this.bulletOnes, this.enemyOnes, 1);
      bulletHitEnemy(this.bulletOnes, this.enemyTwos, 2);
      bulletHitBoss(this.bulletOnes, this.bossOnes, 1);

      createBuffs(this.buffOnes);
      moveBuffs(this.buffOnes);
      removeBuffOne(this.buffOnes);
      BuffHitHero(this.buffOnes);
      /* Step 11: call the action functions */
      createBoss(this.bossOnes, 1);
      moveBoss(this.bossOnes);
      removeBoss(this.bossOnes);
      bossHitHero(this.bossOnes);

      creatBossBullets(this.bossOnes, this.bossBulletOnes, bossBulletOneImagePath, bossBulletOneWidth,
          bossBulletOneType, bossBulletOneLevel);
      bossBulletHitHero(this.bossBulletOnes);
      moveBossBullets(this.bossBulletOnes);
      removeBossBullet(this.bossBulletOnes);

      /* Step 12: set the updated info to view */
      this.view.setBackground(this.background.getCoordinate());
      this.view.setHeroImage(this.hero.getImage());
      this.view.setHeroCoordinate(this.hero.getCoordinate());
      this.view.setEnemyOnes(this.enemyOnes.getEnemyArray());
      this.view.setEnemyTwos(this.enemyTwos.getEnemyArray());
      this.view.setBulletOnes(this.bulletOnes.getBulletArray());
      this.view.setBossBulletOnes(this.bossBulletOnes.getBossBulletArray());
      this.view.setBossOnes(this.bossOnes.getBossArray());
      this.view.setBuffOnes(this.buffOnes.getBuffArray());
      this.view.setScore(this.score);
      this.view.setLife(this.hero.getLife());
      this.view.setFire(this.hero.getFire());
      view.paint();
    }

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
    if(status == 0){
      status = 1;
      view.setStatus(this.status);
      new Timer(70, this).start();
    }else if(status == 1){
      status = 2;
      view.setStatus(this.status);
    }else if(status == 2){
      status = 1;
      view.setStatus(this.status);
      view.paint();
    }
  }
}
