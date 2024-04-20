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
 *  This class initialize the bossIndex and bossArray that would be used in Controller.
 */
/* Step 2 */
@Data
public class BossCollection {
  public int bossIndex;
  public Boss[] bossArray;

  public BossCollection() {
    this.bossIndex = 0;
    this.bossArray = new Boss[0];
  }

  public void createBoss(BossCollection bosses, int typeVal) {
    bosses.setBossIndex(bosses.getBossIndex() + 1);
    if (bosses.getBossIndex() % 300 == 0) {
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

  public void moveBoss(BossCollection bosses) {
    for (Boss one : bosses.getBossArray()) {
      one.move();
    }
  }

  public void bossHitHero(BossCollection bosses) {
    for (int i = 0; i < bosses.getBossArray().length; i++) {
      if (bosses.getBossArray()[i] != null) {
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
            if (Controller.hero.getLife() <= 0) {
              Controller.status = 3;
            }
          }
        }
      }
    }
  }

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

