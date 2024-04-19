package Utils;

import javax.swing.ImageIcon;

public class Constants {
  public static int windowWidth = 560;
  public static int windowHeight = 824;
  public static String startImagePath = "image/start1.png";
  public static String stopImagePath = "image/stop1.png";
  public static String gameOverImagePah = "image/gameover1.png";
  public static String victoryImagePath = "image/success1.png";
  public static String gameOverImagePath = "image/gameover1.png";

  // Parameters of Enemy type object enemyOne
  public static String enemyOneImagePath = "image/enemy1.png";
  public static int enemyOneLife = 1;
  public static int enemyOneXspeed = 3;
  public static int enemyOneYspeed = 3;
  public static int enemyOneType = 1;

  // Parameters of Enemy type object enemyTwo
  public static String enemyTwoImagePath = "image/enemy2.png";
  public static int enemyTwoLife = 6;
  public static int enemyTwoXspeed = 2;
  public static int enemyTwoYspeed = 2;
  public static int enemyTwoType = 2;


  // Parameters of Boss type object bossOne
  public static String bossOneImagePath = "image/boss1.png";
  public static int bossOneLife = 25;
  public static int bossOneType = 1;
  public static int bossOneXspeed = 1;
  public static int bossOneYspeed = 1;

  // Parameters of Bullet type object bulletOne
  public static String bulletOneImagePath = "image/bullet1.png";
  public static int bulletOneWidth = new ImageIcon(bulletOneImagePath).getIconWidth();

  // Parameters of BossBullet type object BossBulletOne
  public static String bossBulletOneImagePath = "image/boss1bullet1.png";
  public static int bossBulletOneWidth = new ImageIcon(bossBulletOneImagePath).getIconWidth();
  public static int bossBulletOneLevel = 2;
  public static int bossBulletOneType = 1;

  public static String buffOneImagePath = "image/buff1.png";
  public static int buffOneXspeed = 1;
  public static int buffOneYspeed = 1;
  public static int buffOneType = 1;

  //



}
