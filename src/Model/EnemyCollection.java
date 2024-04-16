package Model;

import lombok.Data;

@Data
public class EnemyCollection {

  public int enemyIndex = 0;
  public Enemy[] enemyArray = new Enemy[0];
}