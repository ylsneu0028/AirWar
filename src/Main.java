import Controller.Controller;
import Model.*;
import Utils.Constants;
import View.ViewFrame;
/**
 * The Main class contains the entry point for the Air War game.
 */
public class Main {
  /**
   * The main method initializes game components and starts the game.
   *
   * @param args The command-line arguments (unused).
   */
  public static void main(String[] args) {

    Hero hero = new Hero();
    ViewFrame view = new ViewFrame();
    Controller controller = new Controller(hero, view);
    controller.go();
  }
}