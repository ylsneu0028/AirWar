import Controller.Controller;
import Model.*;
import View.ViewFrame;

public class Main {

  public static void main(String[] args) {

    Hero hero = new Hero();
    EnemyOne enemy1 = new EnemyOne();
    ViewFrame view = new ViewFrame();
    Controller controller = new Controller(hero, enemy1, view);
    controller.go();
  }
}