package Model;

import Utils.Coordinate;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 * Represents an enemy entity in the game, extending AbstractEnemy.
 * Enemies move downward and can be destroyed by bullets.
 */
 public class Enemy extends AbstractEnemy {

  /**
   * Constructs an Enemy object with the specified parameters.
   *
   * @param imgPath The file path to the image representing the enemy.
   * @param xVelocity The horizontal velocity of the enemy.
   * @param yVelocity The vertical velocity of the enemy.
   * @param lifeVal The initial life value of the enemy.
   * @param typeVal The type value of the enemy.
   */
  public Enemy(String imgPath, int xVelocity, int yVelocity, int lifeVal, int typeVal) {
    super(imgPath, xVelocity, yVelocity, lifeVal, typeVal);
  }

}