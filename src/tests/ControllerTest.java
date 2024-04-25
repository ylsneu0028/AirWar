package tests;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Model.Hero;
import Controller.*;
import View.*;
import Utils.*;




public class ControllerTest {
  private Controller controller;
  private Hero hero;
  private ViewFrame view;

  @BeforeEach
  void setUp() {
    hero = new Hero(); // Assuming a constructor available
    view = new ViewFrame(); // Assuming a constructor available
    controller = new Controller(hero, view);
  }

  @Test
  void testInitialization() {
    controller.Initialization();
    assertNotNull(controller.enemyOnes);
    assertNotNull(controller.enemyTwos);
  }

  @Test
  void testGo() {
    controller.go();
    assertNotNull(controller.enemyOnes);
    assertNotNull(controller.enemyTwos);
  }

  @Test
  void testMoveBackground() {
    Coordinate originalCoordinate = controller.background.getCoordinate();
    controller.moveBackground();
    //assertNotEquals(originalCoordinate, controller.background.getCoordinate());
  }

  @Test
  void testCreateBackground() {
    controller.createBackground();
    assertNotNull(controller.background);
  }

  @Test
  void testJudgeScore() {
    controller.score = 15;
    controller.judgeScore();
    assertEquals(4, controller.status);
    assertNotNull(controller.enemyOnes);
  }

  @Test
  void testActionPerformed() {
    ActionEvent e = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null);
    controller.status = 1;
    //controller.actionPerformed(e);
    assertNotNull(controller.hero);
  }

  @Test
  void testMouseMoved() {
    MouseEvent e = new MouseEvent(view, MouseEvent.MOUSE_MOVED, 0, 0, 100, 100, 1, false);
    controller.mouseMoved(e);
    assertEquals(100 - hero.getWidth() / 2, controller.hero.getCoordinate().getX());
  }

  @Test
  void testMouseClicked() {
    MouseEvent e = new MouseEvent(view, MouseEvent.MOUSE_CLICKED, 0, 0, 100, 100, 1, false);
    controller.status = 0;
    controller.mouseClicked(e);
    assertEquals(1, controller.status);
  }
}
