package Model;

import lombok.Data;

/**
 * This class initialize the bossIndex and bossArray that would be used in Controller.
 */
/* Step 2 */
@Data
public class BossCollection {

  public int bossIndex = 0;
  public Boss[] bossArray = new Boss[0];
}

