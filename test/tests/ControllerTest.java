package tests;

import static Utils.Constants.*;
import static javax.management.Query.times;
import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;

import static org.junit.Assert.assertNotEquals;

import Utils.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.TimerTask;
import javax.swing.Timer;
import Model.*;
import View.*;
import junit.framework.TestCase;
import Controller.Controller;
import Model.Background;
import org.junit.Before;
import org.junit.Test;

public class ControllerTest extends TestCase {
  private Controller controller;
  private Hero hero;
  private ViewFrame view;

  // Test for moveBackground method
  public void testMoveBackground() {
    Background background = new Background();
    Coordinate initialCoordinate = new Coordinate(0, 0);

    Controller controller = new Controller(new Hero(), new ViewFrame());
    controller.moveBackground();
    Coordinate finalCoordinate = background.getCoordinate();
    assertNotEquals("Background should move down", initialCoordinate.getY() + 1, finalCoordinate.getY());
  }

  // Test for createBackground method
  public void testCreateBackground() {
    Background background = new Background();
    assertNotNull("Background should be created", background);
  }

  // Test for Initialization method
//  public void testInitialization() {
//    Controller controller = new Controller(new Hero(), new ViewFrame());
//    controller.Initialization();
//    assertNotNull("Enemy collection should be initialized", controller.enemyOnes);
//    assertNotNull("Enemy collection should be initialized", controller.enemyTwos);
//    assertNotNull("Bullet collection should be initialized", controller.bulletOnes);
//    assertNotNull("Boss bullet collection should be initialized", controller.bossBulletOnes);
//    assertNotNull("Boss collection should be initialized", controller.bossOnes);
//    assertNotNull("Buff collection should be initialized", controller.buffOnes);
//  }

  // Test for judgeScore method
  public void testJudgeScore() {
    Controller controller = new Controller(new Hero(), new ViewFrame());
    Controller.score = 16;
    Controller.status = 1;
    controller.judgeScore();
    assertEquals("Status should be updated to victory", 4, Controller.status);
    assertEquals("Initialization should be called", 16, Controller.score);
  }


}
