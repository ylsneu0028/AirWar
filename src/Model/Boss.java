package Model;

import Utils.Constants;
/**
 *  This class represents the boss
 */
/* step 1*/
public class Boss extends AbstractEnemy{

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
