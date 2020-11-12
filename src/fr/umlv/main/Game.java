package fr.umlv.main;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import fr.umlv.element.Lava;
import fr.umlv.element.Rock;
import fr.umlv.element.Water;
import fr.umlv.zen5.Application;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.Event.Action;
import fr.umlv.zen5.KeyboardKey;
import fr.umlv.zen5.ScreenInfo;

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
      Lava lava = new Lava(50, 50);
      Water water = new Water(50, 50);
      for(;;) {
    	  Event event = context.pollOrWaitEvent(10);
          if (event == null) {  // no event
            continue;
          }
          Action action = event.getAction();
          if (action == Action.KEY_PRESSED || action == Action.KEY_RELEASED) {
        	  if(event.getKey() == KeyboardKey.Q) {
        		  System.out.println("abort abort !");
                  context.exit(0);
                  return;
        	  }
        	  if(event.getKey() == KeyboardKey.W) {
        		  water.draw(context, 30, 50, 30);
        	  } else if(event.getKey() == KeyboardKey.R) {
        		  rock.draw(context, 50, 30, 30);
        	  } else if(event.getKey() == KeyboardKey.L) {
        		  lava.draw(context, 30, 30, 30);
        	  }
          } else {
        	  Point2D.Float location = event.getLocation();
              rock.draw(context, location.x, location.y, 30);
          }   
      }
    });
  }
}
