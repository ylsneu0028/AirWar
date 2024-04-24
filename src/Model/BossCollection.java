package Model;

import static Utils.Constants.bossOneImagePath;
import static Utils.Constants.bossOneLife;
import static Utils.Constants.bossOneXspeed;
import static Utils.Constants.bossOneYspeed;
import static Utils.Constants.windowHeight;

import java.util.Arrays;
import lombok.Data;
import Controller.*;

/**
 * A class responsible for managing the collection of boss entities in the game.
 * BossCollection initializes and maintains the bossIndex and bossArray used in the Controller.
 */
@Data
public class BossCollection {
  public int bossIndex;
  public Boss[] bossArray;

  /**
   * Constructs a new BossCollection object with default values.
   * The boss index is initialized to 0, and the boss array is initialized to an empty array.
   */
  public BossCollection() {
    this.bossIndex = 0;
    this.bossArray = new Boss[0];
  }

  /**
   * Creates a new boss entity and adds it to the boss array.
   * Bosses are created periodically based on the specified type.
   * @param bosses The collection of boss entities.
   * @param typeVal The type of boss entity to create.
   */
  public void createBoss(BossCollection bosses, int typeVal) {
    bosses.setBossIndex(bosses.getBossIndex() + 1);
    if (bosses.getBossIndex() % 350 == 0) {
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

  /**
   * Moves all boss entities in the collection.
   * @param bosses The collection of boss entities.
   */
  public void moveBoss(BossCollection bosses) {
    for (Boss one : bosses.getBossArray()) {
      one.move();
    }
  }

  /**
   * Checks for collision between boss entities and the player character.
   * If a collision occurs, the player's life is reduced, and the boss entity is removed from the collection.
   * @param bosses The collection of boss entities.
   */
  public void bossHitHero(BossCollection bosses) {
    for (int i = 0; i < bosses.getBossArray().length; i++) {
      if (bosses.getBossArray()[i] != null) {
        // Detect collision
        if ((Controller.hero.getCoordinate().getX() - Controller.hero.getWidth() / 2
            >= bosses.getBossArray()[i].getCoordinate().getX()
            && Controller.hero.getCoordinate().getX() - Controller.hero.getWidth() / 2
            <= bosses.getBossArray()[i].getCoordinate().getX()
            + bosses.getBossArray()[i].getWidth()) || (
            Controller.hero.getCoordinate().getX() + Controller.hero.getWidth() / 2
                >= bosses.getBossArray()[i].getCoordinate().getX()
                && Controller.hero.getCoordinate().getX() + Controller.hero.getWidth() / 2
                <= bosses.getBossArray()[i].getCoordinate().getX()
                + bosses.getBossArray()[i].getWidth())) {
          if ((Controller.hero.getCoordinate().getY() >= bosses.getBossArray()[i].getCoordinate().getY()
              && Controller.hero.getCoordinate().getY() <= bosses.getBossArray()[i].getCoordinate().getY()
              + bosses.getBossArray()[i].getHeight()) || (
              Controller.hero.getCoordinate().getY() + Controller.hero.getHeight() / 2
                  >= bosses.getBossArray()[i].getCoordinate().getY()
                  && Controller.hero.getCoordinate().getY() + Controller.hero.getHeight() / 2
                  <= bosses.getBossArray()[i].getCoordinate().getY()
                  + bosses.getBossArray()[i].getHeight())) {
            Controller.hero.setLife(Controller.hero.getLife() - bosses.getBossArray()[i].getLife());
            bosses.getBossArray()[i] = bosses.getBossArray()[bosses.getBossArray().length - 1];
            bosses.setBossArray(
                Arrays.copyOf(bosses.getBossArray(), bosses.getBossArray().length - 1));
            // If player's life reaches 0, set game status to game over
            if (Controller.hero.getLife() <= 0) {
              Controller.status = 3;
            }
          }
        }
      }
    }
  }

  /**
   * Removes boss entities that have moved off the screen.
   * @param bosses The collection of boss entities.
   */
  public void removeBoss(BossCollection bosses) {
    for (int i = 0; i < bosses.getBossArray().length; i++) {
      if (bosses.getBossArray()[i] != null
          && bosses.getBossArray()[i].getCoordinate().getY() > windowHeight * 2) {
        bosses.getBossArray()[i] = bosses.getBossArray()[bosses.getBossArray().length - 1];
        bosses.setBossArray(Arrays.copyOf(bosses.getBossArray(), bosses.getBossArray().length - 1));
      }
    }
  }
}

