package Model;

import lombok.Data;

@Data
public class BulletCollection {

  public int bulletIndex = 0;
  public Bullet[] bulletArray = new Bullet[0];
}
