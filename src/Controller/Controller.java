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
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import Model.*;
import View.*;


public class Controller extends MouseAdapter implements ActionListener, MouseListener {

  public static int status = 0; //0: before start; 1: on progress; 2: stop; 3 game over; 4: victory;
  public static int score = 0;
  public static Hero hero;
  private final ViewFrame view;
  private Enemy enemyOne;
  private Enemy enemyTwo;
  private Boss bossOne;
  private Bullet bulletOne;
  private BossBullet bossBulletOne;
  public EnemyCollection enemyOnes;
  public EnemyCollection enemyTwos;
  private Buff buffOne;
  public BuffCollection buffOnes;
  /* Step 6*/
  public BossCollection bossOnes;
  public BulletCollection bulletOnes;
  public BossBulletCollection bossBulletOnes;
  public Background background = new Background();

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


  public void go() {
    Initialization();
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

  public void judgeScore() {
    if (score >= 15) {
      status = 4;
      Initialization();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (status == 1) {
      moveBackground();

      hero.move();

      this.enemyOnes.createEnemies(this.enemyOnes, enemyOneImagePath, enemyOneXspeed,
          enemyOneYspeed, enemyOneLife,
          enemyOneType);
      this.enemyOnes.moveEnemies(this.enemyOnes);
      this.enemyOnes.removeEnemy(this.enemyOnes);
      this.enemyOnes.enemyHitHero(this.enemyOnes);
      this.enemyTwos.createEnemies(this.enemyTwos, enemyTwoImagePath, enemyTwoXspeed,
          enemyTwoYspeed, enemyTwoLife,
          enemyTwoType);
      this.enemyTwos.moveEnemies(this.enemyTwos);
      this.enemyTwos.enemyHitHero(this.enemyTwos);
      this.enemyTwos.removeEnemy(this.enemyTwos);

      this.bulletOnes.createBullets(this.bulletOnes, 1, bulletOneWidth, bulletOneImagePath);
      this.bulletOnes.moveBullets(this.bulletOnes);
      this.bulletOnes.removeBullet(this.bulletOnes);
      this.bulletOnes.bulletHitEnemy(this.bulletOnes, this.enemyOnes, 1);
      this.bulletOnes.bulletHitEnemy(this.bulletOnes, this.enemyTwos, 2);
      this.bulletOnes.bulletHitBoss(this.bulletOnes, this.bossOnes, 1);

      this.buffOnes.createBuffs(this.buffOnes);
      this.buffOnes.moveBuffs(this.buffOnes);
      this.buffOnes.removeBuff(this.buffOnes);
      this.buffOnes.buffHitHero(this.buffOnes);

      this.bossOnes.createBoss(this.bossOnes, 1);
      this.bossOnes.moveBoss(this.bossOnes);
      this.bossOnes.removeBoss(this.bossOnes);
      this.bossOnes.bossHitHero(this.bossOnes);

      this.bossBulletOnes.creatBossBullets(this.bossOnes, this.bossBulletOnes,
          bossBulletOneImagePath, bossBulletOneWidth,
          bossBulletOneType, bossBulletOneLevel);
      this.bossBulletOnes.bossBulletHitHero(this.bossBulletOnes);
      this.bossBulletOnes.moveBossBullets(this.bossBulletOnes);
      this.bossBulletOnes.removeBossBullet(this.bossBulletOnes);
      judgeScore();

      if (status == 3) {
        Initialization();
      }

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
      this.view.setStatus(this.status);
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
    if (status == 0) {
      System.out.println("after 0: " + status);
      status = 1;
      System.out.println("after 1: " + status);
      view.setStatus(this.status);
      new Timer(70, this).start();
      System.out.println("timer status: "+ status);
    } else if (status == 1) {
      status = 2;
      view.setStatus(this.status);
    } else if (status == 2) {
      status = 1;
      view.setStatus(this.status);
      view.paint();
    }
//    }else if(status == 3){
//      status = 0;
//      System.out.println("after 3: " + status);
//      view.setStatus(this.status);
//      view.paint();
//    }else if(status == 4){
//      status = 0;
//      System.out.println("after 4: " + status);
//      view.setStatus(this.status);
//      view.paint();
//    }

  }
}
