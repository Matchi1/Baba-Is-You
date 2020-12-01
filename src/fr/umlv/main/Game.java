package fr.umlv.main;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import fr.umlv.board.Board;
import fr.umlv.board.Direction;
import fr.umlv.element.Baba;
import fr.umlv.element.Bloc;
import fr.umlv.element.Element;
import fr.umlv.element.Rock;
import fr.umlv.element.Wall;
import fr.umlv.group.AllElement;
import fr.umlv.group.ControlledBlocs;
import fr.umlv.properties.Property;
import fr.umlv.zen5.Application;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.Event.Action;
import fr.umlv.zen5.KeyboardKey;
import fr.umlv.zen5.ScreenInfo;

public class Game {
	
  public static void main(String[] args) {
	Application.run(Color.BLACK, context -> {
	  ScreenInfo screenInfo = context.getScreenInfo();
	  int width = (int)screenInfo.getWidth();
	  int height = (int)screenInfo.getHeight();
	  
	  Board b = Board.createBoard(width, height, 10, 10);
	  try {
		Bloc rock = Rock.createRock(1, 1, false);
		rock.putState(Property.Push);
		Bloc wall = Wall.createWall(5, 5, false);
		wall.putState(Property.Stop);
		b.addBloc(2, 2, Baba.createBaba(2, 2, false));
		b.addBloc(0, 0, rock);
		b.addBloc(1, 1, wall);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	  AllElement altElt = AllElement.createAllElement(b);
	  
	  ControlledBlocs cb = ControlledBlocs.createCB(altElt, Element.Baba);
	  int pos_x = (int) ((width - b.lengthBloc() * b.length().x()) / 2);
	  int pos_y = (int) ((height - b.lengthBloc() * b.length().y()) / 2);
	  
	  context.renderFrame(graphics -> {
	    graphics.setColor(Color.BLACK);
	    graphics.fill(new Rectangle2D.Float(0, 0, width, height));
	  });
	  
	  for(;;) {
		  b.refresh();
		  b.draw(context, pos_x, pos_y);
		  Event event = context.pollOrWaitEvent(10);
	      if (event == null) {  // no event
	    	  continue;
	      }
		  Action action = event.getAction();
		  if (action == Action.KEY_PRESSED) {
			  if(event.getKey() == KeyboardKey.Q) {
				  System.out.println("abort abort !");
	              context.exit(0);
	              return;
			  } else if(event.getKey() == KeyboardKey.UP) {
				  cb.translate(0, -1);
				  if(!b.isLegal(cb.lstPosition(), Direction.North))
					  cb.translate(0, 1);
			  } else if(event.getKey() == KeyboardKey.RIGHT) {
				  cb.translate(1, 0);
				  if(!b.isLegal(cb.lstPosition(), Direction.East))
					  cb.translate(-1, 0);
			  } else if(event.getKey() == KeyboardKey.DOWN) {
				  cb.translate(0, 1);
				  if(!b.isLegal(cb.lstPosition(), Direction.South))
					  cb.translate(0, -1);
			  } else if(event.getKey() == KeyboardKey.LEFT) {
				  cb.translate(-1, 0);
				  if(!b.isLegal(cb.lstPosition(), Direction.West))
					  cb.translate(1, 0);
			  }
	      }
	  }
	});
  }
}
