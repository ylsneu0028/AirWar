package Model;

import Utils.Constants;
import java.util.Arrays;
import lombok.Data;
import Controller.*;

/**
 * A class responsible for managing the collection of bullet entities in the game.
 * BulletCollection initializes and maintains the bulletIndex and bulletArray used in the game.
 */
@Data
public class BulletCollection {
  public int bulletIndex;
  public Bullet[] bulletArray;

  /**
   * Constructs a new BulletCollection object with default values.
   * The bullet index is initialized to 0, and the bullet array is initialized to an empty array.
   */
  public BulletCollection() {
    this.bulletIndex = 0;
    this.bulletArray = new Bullet[0];
  }

  /**
   * Creates new bullet entities and adds them to the bullet array.
   * Bullets are created periodically based on predefined conditions.
   * @param bulletArray The collection of bullet entities.
   * @param type The type of the bullet.
   * @param bulletWidth The width of the bullet.
   * @param bulletImagePath The path to the image file representing the bullet.
   */
  public void createBullets(BulletCollection bulletArray, int type, int bulletWidth,
      String bulletImagePath) {
    if (Controller.status == 1) {
      bulletArray.setBulletIndex(bulletArray.getBulletIndex() + 1);
      if (bulletArray.getBulletIndex() % 35 == 0) {
        bulletArray.setBulletIndex(0);
        if (Controller.hero.getFire() == type) {
          // Two bullets
          Bullet bullet1 = new Bullet(
              Controller.hero.getCoordinate().getX() + Controller.hero.getWidth() / 4 - bulletWidth / 2,
              Controller.hero.getCoordinate().getY(), bulletImagePath, type);
          Bullet bullet2 = new Bullet(
              Controller.hero.getCoordinate().getX() + Controller.hero.getWidth() * 3 / 4 - bulletWidth / 2,
              Controller.hero.getCoordinate().getY(), bulletImagePath, type);
          System.out.println("new bullet");
          Bullet[] bulletCopy = Arrays.copyOf(bulletArray.getBulletArray(),
              bulletArray.getBulletArray().length + 2);
          bulletCopy[bulletCopy.length - 2] = bullet1;
          bulletCopy[bulletCopy.length - 1] = bullet2;
          bulletArray.setBulletArray(bulletCopy);
        }
        else if (Controller.hero.getFire() == type + 1) {
          // Three bullets
          Bullet bullet1 = new Bullet(Controller.hero.getCoordinate().getX() + Controller.hero.getWidth() / 4 - bulletWidth / 2, Controller.hero.getCoordinate().getY(),bulletImagePath,type);
          Bullet bullet2 = new Bullet(Controller.hero.getCoordinate().getX() + Controller.hero.getWidth() * 3 / 4 - bulletWidth / 2,
              Controller.hero.getCoordinate().getY(), bulletImagePath, type);
          Bullet bullet3 = new Bullet(Controller.hero.getCoordinate().getX() + Controller.hero.getWidth() / 2 - bulletWidth / 2, Controller.hero.getCoordinate().getY(),bulletImagePath, type);
          Bullet[] bulletCopy = Arrays.copyOf(bulletArray.getBulletArray(), bulletArray.getBulletArray().length + 3);
          bulletCopy[bulletCopy.length - 3] = bullet1;
          bulletCopy[bulletCopy.length - 2] = bullet2;
          bulletCopy[bulletCopy.length - 1] = bullet3;
          bulletArray.setBulletArray((bulletCopy));
        } else if(Controller.hero.getFire() >= type + 2) {
          // Four bullets
          Bullet bullet1 = new Bullet(
              Controller.hero.getCoordinate().getX() + Controller.hero.getWidth() * 1 / 4 - bulletWidth / 2,
              Controller.hero.getCoordinate().getY(), bulletImagePath, type);
          Bullet bullet2 = new Bullet(
              Controller.hero.getCoordinate().getX() + Controller.hero.getWidth() * 2 / 4 - bulletWidth / 2,
              Controller.hero.getCoordinate().getY(), bulletImagePath, type);
          Bullet bullet3 = new Bullet(
              Controller.hero.getCoordinate().getX() + Controller.hero.getWidth() * 3 / 4 - bulletWidth / 2,
              Controller.hero.getCoordinate().getY(), bulletImagePath, type);
          Bullet bullet4 = new Bullet(
              Controller.hero.getCoordinate().getX() + Controller.hero.getWidth() * 4 / 4 - bulletWidth / 2,
              Controller.hero.getCoordinate().getY(), bulletImagePath, type);
          Bullet[] bulletCopy = Arrays.copyOf(bulletArray.getBulletArray(),
              bulletArray.getBulletArray().length + 4);
          bulletCopy[bulletCopy.length - 4] = bullet1;
          bulletCopy[bulletCopy.length - 3] = bullet2;
          bulletCopy[bulletCopy.length - 2] = bullet3;
          bulletCopy[bulletCopy.length - 1] = bullet4;
          bulletArray.setBulletArray(bulletCopy);
        }
      }
    }
  }

  /**
   * Moves all bullet entities in the collection.
   * @param bullets The collection of bullet entities.
   */
  public void moveBullets(BulletCollection bullets) {
    for (Bullet one : bullets.getBulletArray()) {
      one.move();
    }
  }

  /**
   * Checks for collision between bullets and enemy entities.
   * If a collision occurs, the enemy's attributes are updated accordingly, and the bullet entity is removed from the collection.
   * @param bullets The collection of bullet entities.
   * @param enemies The collection of enemy entities.
   * @param typeVal The value used for scoring.
   */
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
              System.out.println("score=" + Controller.score);
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
        Controller.score += typeVal;
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

  /**
   * Checks for collision between bullets and boss entities.
   * If a collision occurs, the boss's attributes are updated accordingly, and the bullet entity is removed from the collection.
   * @param bullets The collection of bullet entities.
   * @param bosses The collection of boss entities.
   * @param typeVal The value used for scoring.
   */
  public void bulletHitBoss(BulletCollection bullets, BossCollection bosses, int typeVal) {
    // Initialize flags to track boss and bullet collisions// Initialize flags to track boss and bullet collisions
    int flag1 = -1;
    int flag2 = -1;

    for (int i = 0; i < bullets.getBulletArray().length; i++) {
      for (int j = 0; j < bosses.getBossArray().length; j++) {
        // Check if the bullet's x-coordinate is within the boss's x-coordinate range
        if (bullets.getBulletArray()[i].getCoordinate().getX()
            >= bosses.getBossArray()[j].getCoordinate().getX()
            && bullets.getBulletArray()[i].getCoordinate().getX()
            <= bosses.getBossArray()[j].getCoordinate().getX()
            + bosses.getBossArray()[j].getWidth()) {
          // Check if the bullet's y-coordinate is within the boss's y-coordinate range
          if (bullets.getBulletArray()[i].getCoordinate().getY()
              >= bosses.getBossArray()[j].getCoordinate().getY()
              && bullets.getBulletArray()[i].getCoordinate().getY()
              <= bosses.getBossArray()[j].getCoordinate().getY()
              + bosses.getBossArray()[j].getHeight()) {

            bosses.getBossArray()[j].setLife(bosses.getBossArray()[j].getLife() - 1);
            flag2 = i;
            if (bosses.getBossArray()[j].getLife() <= 0) {

              System.out.println("score=" + Controller.score);
              flag1 = j;
            }
            break;
          }
        }
      }
      // Remove the bullet that collided with the boss
      while (flag1 != -1) {
        bosses.getBossArray()[flag1] = bosses.getBossArray()[bosses.getBossArray().length - 1];
        bosses.setBossArray(Arrays.copyOf(bosses.getBossArray(), bosses.getBossArray().length - 1));
        Controller.score += typeVal * 10;
        flag1 = -1;

      }
    }
    // Remove the bullet that collided with the boss
    while (flag2 != -1) {
      bullets.getBulletArray()[flag2] = bullets.getBulletArray()[bullets.getBulletArray().length
          - 1];
      bullets.setBulletArray(
          Arrays.copyOf(bullets.getBulletArray(), bullets.getBulletArray().length - 1));
      flag2 = -1;
    }
  }

  /**
   * Removes bullet entities that have moved off the screen.
   * @param bullets The collection of bullet entities.
   */
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
}
