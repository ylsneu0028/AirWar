package Model;

import lombok.Data;

@Data
public class BossBulletCollection {
  public int bossBulletIndex = 0;
  public BossBullet[] bossBulletArray = new BossBullet[0];
}
