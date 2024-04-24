package Model;

import Utils.Constants;
/**
 * A class representing the boss enemy in a game.
 * The boss is a powerful enemy that often appears at the end of a level or stage.
 */
public class Boss extends AbstractEnemy{

  /**
   * Constructs a new Boss object with the specified image path, velocities, life, and type.
   * @param imgPath The path to the image file representing the boss.
   * @param xVelocity The horizontal velocity of the boss.
   * @param yVelocity The vertical velocity of the boss.
   * @param lifeVal The initial life value of the boss.
   * @param typeVal The type of the boss.
   */
  public Boss(String imgPath, int xVelocity, int yVelocity, int lifeVal, int typeVal) {
    super(imgPath, xVelocity, yVelocity, lifeVal, typeVal);
  }

  @Override
  public void move() {
    super.move();
    if(this.coordinate.getY() >= 150){
      ySpeed = 0;
    }
  }

}
