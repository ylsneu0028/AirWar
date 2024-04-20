package Model;

import static Utils.Constants.windowHeight;

import Controller.Controller;
import java.util.Arrays;
import lombok.Data;

@Data
public class BossBulletCollection {

  public int bossBulletIndex;
  public BossBullet[] bossBulletArray;

  public BossBulletCollection() {
    this.bossBulletIndex = 0;
    this.bossBulletArray = new BossBullet[0];
  }

  public void creatBossBullets(BossCollection bosses, BossBulletCollection bossBullets,
      String bossBulletImagePath, int bossBulletWidth, int typeVal, int level) {
    if (Controller.status == 1) {
      for (int i = 0; i < bosses.getBossArray().length; i++) {
        if (bosses.getBossArray()[i] != null) {
          bossBullets.setBossBulletIndex(bossBullets.getBossBulletIndex() + 1);
          if (bossBullets.getBossBulletIndex() % 200 == 0) {
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
            System.out.println("Boss bullet：" + bossBulletsCopy.length);
            bossBullets.setBossBulletArray(bossBulletsCopy);
          }
        }
      }
    }
  }

  public void moveBossBullets(BossBulletCollection bossBullets) {
    for (BossBullet one : bossBullets.getBossBulletArray()) {
      one.move();
    }
  }


  public void bossBulletHitHero(BossBulletCollection bossBullets) {
    int flag2 = -1;
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
          flag2 = i;
          break;
        }
      }
    }
    while (flag2 != -1) {
      bossBullets.getBossBulletArray()[flag2] = bossBullets.getBossBulletArray()[
          bossBullets.getBossBulletArray().length - 1];
      bossBullets.setBossBulletArray(Arrays.copyOf(bossBullets.getBossBulletArray(),
          bossBullets.getBossBulletArray().length - 1));
      flag2 = -1;

    }
    if (Controller.hero.getLife() <= 0) {
      Controller.status = 3;
    }
  }

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
