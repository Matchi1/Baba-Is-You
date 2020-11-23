package fr.umlv.main;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import fr.umlv.board.Board;
import fr.umlv.element.Baba;
import fr.umlv.element.Bloc;
import fr.umlv.element.ControlledBlocs;
import fr.umlv.element.Element;
import fr.umlv.zen5.Application;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.Event.Action;
import fr.umlv.zen5.KeyboardKey;
import fr.umlv.zen5.ScreenInfo;

public class Game {
	
	public Board createBoard(int lenX, int lenY) {
		Board b = new Board(lenX, lenY);
		int x, y;
		x = lenX - 1;
		y = lenY - 1;
		b.addBloc(x, y, new Baba(x, y));
		return b;
	}
	
  public static void main(String[] args) {
	Application.run(Color.BLACK, context -> {
	  ScreenInfo screenInfo = context.getScreenInfo();
	  Board b = new Board(3, 3);
	  ControlledBlocs cb = new ControlledBlocs();
	  int x = 2, y = 2;
	  float width = screenInfo.getWidth();
	  float height = screenInfo.getHeight();
	  float pos_x = (width - b.length_bloc() * b.taille().x()) / 2;
	  float pos_y = (height - b.length_bloc() * b.taille().y()) / 2;
	  b.addBloc(x, y, new Baba(x, y));
	  // get the size of the screen
	  
	  cb.initGroup(b, Element.Baba);
	  System.out.println("size of the screen (" + width + " x " + height + ")");
	  
	  context.renderFrame(graphics -> {
	    graphics.setColor(Color.BLACK);
	    graphics.fill(new Rectangle2D.Float(0, 0, width, height));
	  });
	  
	  for(;;) {
		  Event event = context.pollOrWaitEvent(10);
	      if (event == null) {  // no event
	    	  continue;
	      }
	      b.draw(context, pos_x, pos_y);
		  Action action = event.getAction();
		  if (action == Action.KEY_PRESSED) {
			  if(event.getKey() == KeyboardKey.Q) {
				  System.out.println("abort abort !");
	              context.exit(0);
	              return;
			  } else if(event.getKey() == KeyboardKey.UP) {
				  cb.translate(0, -1);
				  if(!b.isLegal(cb.group()))
					  cb.translate(0, 1);
			  } else if(event.getKey() == KeyboardKey.RIGHT) {
				  cb.translate(1, 0);
				  if(!b.isLegal(cb.group()))
					  cb.translate(-1, 0);
			  } else if(event.getKey() == KeyboardKey.DOWN) {
				  cb.translate(0, 1);
				  if(!b.isLegal(cb.group()))
					  cb.translate(0, -1);
			  } else if(event.getKey() == KeyboardKey.LEFT) {
				  cb.translate(-1, 0);
				  if(!b.isLegal(cb.group()))
					  cb.translate(1, 0);
			  }
			  b.refresh();
			  b.draw(context, pos_x, pos_y);
	      }
	  }
	});
  }
}
