package Model;

import static Utils.Constants.windowHeight;

import Utils.Constants;
import java.util.Arrays;
import lombok.Data;
import Controller.*;

/**
 * A class responsible for managing the collection of buff entities in the game.
 * BuffCollection initializes and maintains the buffIndex and buffArray used in the game.
 */
@Data
public class BuffCollection {

  public int buffIndex;
  public Buff[] buffArray ;


  /**
   * Constructs a new BuffCollection object with default values.
   * The buff index is initialized to 0, and the buff array is initialized to an empty array.
   */
  public BuffCollection() {
    this.buffIndex = 0;
    this.buffArray = new Buff[0];
  }

  /**
   * Creates a new buff entity and adds it to the buff array.
   * Buffs are created periodically based on predefined conditions.
   * @param buffs The collection of buff entities.
   */
  public void createBuffs(BuffCollection buffs) {
    buffs.setBuffIndex(buffs.getBuffIndex() + 1);
    if (buffs.getBuffIndex() % 250 == 0) {
      buffs.setBuffIndex(0);
      Buff buff1 = new Buff(Constants.buffOneImagePath, Constants.buffOneXspeed,
          Constants.buffOneYspeed, Constants.buffOneType);
      buffs.setBuffArray(Arrays.copyOf(buffs.getBuffArray(), buffs.getBuffArray().length + 1));
      buffs.getBuffArray()[buffs.getBuffArray().length - 1] = buff1;
      //System.out.println("Buff：" + buffOnes.length);
    }
  }

  /**
   * Moves all buff entities in the collection.
   * @param buffs The collection of buff entities.
   */
  public void moveBuffs(BuffCollection buffs) {
    for (Buff one : buffs.getBuffArray()) {
      one.move();
    }
  }

  /**
   * Checks for collision between buff entities and the player character.
   * If a collision occurs, the player's attributes are updated accordingly, and the buff entity is removed from the collection.
   * @param buffs The collection of buff entities.
   */
  public void buffHitHero(BuffCollection buffs) {
    for (int i = 0; i < buffs.getBuffArray().length; i++) {
      if (buffs.getBuffArray()[i] != null) {
        if ((Controller.hero.getCoordinate().getX() - Controller.hero.getWidth() / 2
            >= buffs.getBuffArray()[i].getCoordinate().getX()
            && Controller.hero.getCoordinate().getX() - Controller.hero.getWidth() / 2
            <= buffs.getBuffArray()[i].getCoordinate().getX() + buffs.getBuffArray()[i].getWidth())
            || (Controller.hero.getCoordinate().getX() + Controller.hero.getWidth() / 2
            >= buffs.getBuffArray()[i].getCoordinate().getX()
            && Controller.hero.getCoordinate().getX() + Controller.hero.getWidth() / 2
            <= buffs.getBuffArray()[i].getCoordinate().getX()
            + buffs.getBuffArray()[i].getWidth())) {
          if ((Controller.hero.getCoordinate().getY() >= buffs.getBuffArray()[i].getCoordinate()
              .getY()
              && Controller.hero.getCoordinate().getY()
              <= buffs.getBuffArray()[i].getCoordinate().getY()
              + buffs.getBuffArray()[i].getHeight())
              || (Controller.hero.getCoordinate().getY() + Controller.hero.getHeight() / 2
              >= buffs.getBuffArray()[i].getCoordinate().getY()
              && Controller.hero.getCoordinate().getY() + Controller.hero.getHeight() / 2
              <= buffs.getBuffArray()[i].getCoordinate().getY()
              + buffs.getBuffArray()[i].getHeight())) {

            buffs.getBuffArray()[i] = buffs.getBuffArray()[buffs.getBuffArray().length - 1];
            buffs.setBuffArray(
                Arrays.copyOf(buffs.getBuffArray(), buffs.getBuffArray().length - 1));
            // Increase player's firepower and life upon collecting buff
            if (Controller.hero.getFire() <= 2) {
              Controller.hero.setFire(Controller.hero.getFire() + 1);
            }
            Controller.hero.setLife(Controller.hero.getLife() + 1);
          }
        }
      }
    }
  }

  /**
   * Removes buff entities that have moved off the screen.
   * @param buffs The collection of buff entities.
   */
  public void removeBuff(BuffCollection buffs) {
    for (int i = 0; i < buffs.getBuffArray().length; i++) {
      if (buffs.getBuffArray()[i] != null && buffs.getBuffArray()[i].getCoordinate().getY() > windowHeight * 2) {
        buffs.getBuffArray()[i] = buffs.getBuffArray()[buffs.getBuffArray().length - 1];
        buffs.setBuffArray(Arrays.copyOf(buffs.getBuffArray(), buffs.getBuffArray().length - 1));
      }
    }
  }
}


