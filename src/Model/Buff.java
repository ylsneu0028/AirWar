package Model;


/**
 * A class representing a buff entity in a game.
 * Buffs are beneficial items that provide advantages to the player when collected.
 */
public class Buff extends AbstractBuff  {


  /**
   * Constructs a new Buff object with the specified parameters.
   * @param imgPath The path to the image file representing the buff.
   * @param xVelocity The horizontal velocity of the buff.
   * @param yVelocity The vertical velocity of the buff.
   * @param typeVal The type of the buff.
   */
  public Buff(String imgPath, int xVelocity, int yVelocity, int typeVal)  {
    super(imgPath, xVelocity, yVelocity, typeVal);
  }
}
