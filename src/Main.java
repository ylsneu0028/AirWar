import Controller.Controller;
import Model.*;
import Utils.Constants;
import View.ViewFrame;

public class Main {

  public static void main(String[] args) {

    Hero hero = new Hero();
    ViewFrame view = new ViewFrame();
    Controller controller = new Controller(hero, view);
    controller.go();
  }
}