package Model;

import static Utils.Constants.windowWidth;

import java.util.Arrays;
import lombok.Data;
import Controller.*;

@Data
public class EnemyCollection {
  public int enemyIndex;
  public Enemy[] enemyArray;

  public EnemyCollection() {
    this.enemyIndex = 0;
    this.enemyArray = new Enemy[0];
  }

  public void createEnemies(EnemyCollection enemies, String enemyImagePath, int enemyXspeed,
      int enemyYspeed, int enemyLife, int enemyType) {
    enemies.setEnemyIndex(enemies.getEnemyIndex() + 1);
    if (enemies.getEnemyIndex() % 96 == 0) {
      enemies.setEnemyIndex(0);
      Enemy enemy = new Enemy(enemyImagePath, enemyXspeed, enemyYspeed, enemyLife, enemyType);
      enemies.setEnemyArray(
          Arrays.copyOf(enemies.getEnemyArray(), enemies.getEnemyArray().length + 1));
      enemies.getEnemyArray()[enemies.getEnemyArray().length - 1] = enemy;
      System.out.println("Enemys：" + enemies.getEnemyArray().length);
    }
  }

  public void moveEnemies(EnemyCollection enemies) {
    for (Enemy one : enemies.getEnemyArray()) {
      one.move();
    }
  }

  public void enemyHitHero(EnemyCollection enemies) {
    for (int i = 0; i < enemies.getEnemyArray().length; i++) {
      if (enemies.getEnemyArray()[i] != null) {
        if ((Controller.hero.getCoordinate().getX() - Controller.hero.getWidth() / 2
            >= enemies.getEnemyArray()[i].getCoordinate().getX()
            && Controller.hero.getCoordinate().getX() - Controller.hero.getWidth() / 2
            <= enemies.getEnemyArray()[i].getCoordinate().getX()
            + enemies.getEnemyArray()[i].getWidth()) || (
            Controller.hero.getCoordinate().getX() + Controller.hero.getWidth() / 2
                >= enemies.getEnemyArray()[i].getCoordinate().getX()
                && Controller.hero.getCoordinate().getX() + Controller.hero.getWidth() / 2
                <= enemies.getEnemyArray()[i].getCoordinate().getX()
                + enemies.getEnemyArray()[i].getWidth())) {
          if ((Controller.hero.getCoordinate().getY() >= enemies.getEnemyArray()[i].getCoordinate().getY()
              && Controller.hero.getCoordinate().getY() <= enemies.getEnemyArray()[i].getCoordinate().getY()
              + enemies.getEnemyArray()[i].getHeight()) || (
              Controller.hero.getCoordinate().getY() + Controller.hero.getHeight() / 2
                  >= enemies.getEnemyArray()[i].getCoordinate().getY()
                  && Controller.hero.getCoordinate().getY() + Controller.hero.getHeight() / 2
                  <= enemies.getEnemyArray()[i].getCoordinate().getY()
                  + enemies.getEnemyArray()[i].getHeight())) {
            Controller.hero.setLife(Controller.hero.getLife() - 1);
            enemies.getEnemyArray()[i] = enemies.getEnemyArray()[enemies.getEnemyArray().length
                - 1];
            enemies.setEnemyArray(
                Arrays.copyOf(enemies.getEnemyArray(), enemies.getEnemyArray().length - 1));

            if (Controller.hero.getLife() <= 0) {
              Controller.status = 3;
            }
          }
        }
      }
    }
  }

  public void removeEnemy(EnemyCollection enemies) {
    for (int i = 0; i < enemies.getEnemyArray().length; i++) {
      if (enemies.getEnemyArray()[i] != null
          && enemies.getEnemyArray()[i].getCoordinate().getY() > windowWidth * 2) {
        enemies.getEnemyArray()[i] = enemies.getEnemyArray()[enemies.getEnemyArray().length - 1];
        enemies.setEnemyArray(
            Arrays.copyOf(enemies.getEnemyArray(), enemies.getEnemyArray().length - 1));
      }
    }
  }
}

