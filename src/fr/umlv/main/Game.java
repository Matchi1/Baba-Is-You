package fr.umlv.main;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import fr.umlv.zen5.Application;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.ScreenInfo;
import fr.umlv.zen5.Event.Action;

import fr.umlv.element.Rock;

public class Game {
  public static void main(String[] args) {
    Application.run(Color.BLACK, context -> {
      
      // get the size of the screen
      ScreenInfo screenInfo = context.getScreenInfo();
      float width = screenInfo.getWidth();
      float height = screenInfo.getHeight();
      System.out.println("size of the screen (" + width + " x " + height + ")");
      
      context.renderFrame(graphics -> {
        graphics.setColor(Color.BLACK);
        graphics.fill(new  Rectangle2D.Float(0, 0, width, height));
      });
      Rock rock = new Rock(50, 50);

      for(;;) {
    	  Event event = context.pollOrWaitEvent(10);
          if (event == null) {  // no event
            continue;
          }
          Action action = event.getAction();
          if (action == Action.KEY_PRESSED || action == Action.KEY_RELEASED) {
            System.out.println("abort abort !");
            context.exit(0);
            return;
          }
          Point2D.Float location = event.getLocation();
          rock.draw(context, location.x, location.y);
      }
    });
  }
}
