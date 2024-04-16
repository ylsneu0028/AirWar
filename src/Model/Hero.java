package Model;

import javax.swing.ImageIcon;
import lombok.Data;
import lombok.Setter;
import Utils.*;

@Data
public class Hero implements Entity {

  Coordinate coordinate;
  private ImageIcon image;
  private int width;
  private int height;
  //int score;
  int life;
  private int fire;
  private ImageIcon[] images;
  private int index;

  public Hero() {
    coordinate = new Coordinate(140, 400);
    image = new ImageIcon("image/hero1-0.png");
    width = image.getIconWidth();
    height = image.getIconHeight();
    //score=0;
    //life=3;
    fire = 1;//初始值为0
    images = new ImageIcon[4];
    life = 5;

    for (int i = 0; i < images.length; i++) {
      images[i] = new ImageIcon("image/hero1-" + i + ".png");
      System.out.println("bomb!");
    }
    index = 0;

  }

  @Override
  public void move() {
    index++;
    image = images[index / 4 % 3];
  }

}
