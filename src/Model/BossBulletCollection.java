package Model;

import static Utils.Constants.windowHeight;

import Controller.Controller;
import java.util.Arrays;
import lombok.Data;

/**
 * A class representing a collection of boss bullets in a game.
 * BossBulletCollection manages the creation, movement, collision detection, and removal of boss bullets.
 */
@Data
public class BossBulletCollection {

  public int bossBulletIndex;
  public BossBullet[] bossBulletArray;

  /**
   * Constructs a new BossBulletCollection object with default values.
   * The boss bullet index is initialized to 0, and the boss bullet array is initialized to an empty array.
   */
  public BossBulletCollection() {
    this.bossBulletIndex = 0;
    this.bossBulletArray = new BossBullet[0];
  }

  /**
   * Creates boss bullets based on the positions of bosses.
   * Boss bullets are created periodically and fired towards the player.
   * @param bosses The collection of boss entities.
   * @param bossBullets The collection of boss bullets.
   * @param bossBulletImagePath The path to the image file representing boss bullets.
   * @param bossBulletWidth The width of boss bullets.
   * @param typeVal The type of boss bullets.
   * @param level The level of boss bullets.
   */
  public void createBossBullets(BossCollection bosses, BossBulletCollection bossBullets,
      String bossBulletImagePath, int bossBulletWidth, int typeVal, int level) {
    if (Controller.status == 1) {
      for (int i = 0; i < bosses.getBossArray().length; i++) {
        if (bosses.getBossArray()[i] != null) {
          bossBullets.setBossBulletIndex(bossBullets.getBossBulletIndex() + 1);
          if (bossBullets.getBossBulletIndex() % 80 == 0) {
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
            //System.out.println("Boss bullet：" + bossBulletsCopy.length);
            bossBullets.setBossBulletArray(bossBulletsCopy);
          }
        }
      }
    }
  }

  /**
   * Moves all boss bullets in the collection.
   * Boss bullets travel downwards on the screen.
   * @param bossBullets The collection of boss bullets.
   */
  public void moveBossBullets(BossBulletCollection bossBullets) {
    for (BossBullet one : bossBullets.getBossBulletArray()) {
      one.move();
    }
  }


  /**
   * Checks for collision between boss bullets and the player character.
   * If a collision occurs, the player's life is reduced, and the boss bullet is removed from the collection.
   * @param bossBullets The collection of boss bullets.
   */
  public void bossBulletHitHero(BossBulletCollection bossBullets) {
    int flag2 = -1; // Indicates the index of the boss bullet that hits the player
    for (int i = 0; i < bossBullets.getBossBulletArray().length; i++) {
      if (bossBullets.getBossBulletArray()[i].getCoordinate().getX()
          >= Controller.hero.getCoordinate().getX()
          && bossBullets.getBossBulletArray()[i].getCoordinate().getX()
          <= Controller.hero.getCoordinate().getX() + Controller.hero.getWidth()) {
        if (bossBullets.getBossBulletArray()[i].getCoordinate().getY()
            >= Controller.hero.getCoordinate()
            .getY() && bossBullets.getBossBulletArray()[i].getCoordinate().getY()
            <= Controller.hero.getCoordinate().getY() + Controller.hero.getHeight()) {
          Controller.hero.setLife(
              Controller.hero.getLife() - bossBullets.getBossBulletArray()[i].getLevel());
          flag2 = i; // Set flag2 to the index of the collided boss bullet
          break;
        }
      }
    }
    // Remove the collided boss bullet from the collection
    while (flag2 != -1) {
      bossBullets.getBossBulletArray()[flag2] = bossBullets.getBossBulletArray()[
          bossBullets.getBossBulletArray().length - 1];
      bossBullets.setBossBulletArray(Arrays.copyOf(bossBullets.getBossBulletArray(),
          bossBullets.getBossBulletArray().length - 1));
      flag2 = -1;
    }
    // If player's life reaches 0, set game status to game over
    if (Controller.hero.getLife() <= 0) {
      Controller.status = 3;
    }
  }

  /**
   * Removes boss bullets that have moved off the screen.
   * @param bossBullets The collection of boss bullets.
   */
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
}
