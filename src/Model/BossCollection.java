package Model;

import lombok.Data;

@Data
public class BossCollection {
  public int bossIndex = 0;
  public Boss[] bossArray = new Boss[0];
}

