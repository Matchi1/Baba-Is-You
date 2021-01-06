package fr.umlv.main;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import fr.umlv.board.Board;
import fr.umlv.board.Direction;
import fr.umlv.element.Element;
import fr.umlv.element.ElementCategory;
import fr.umlv.name.Name;
import fr.umlv.operator.Operator;
import fr.umlv.operator.OperatorCategory;
import fr.umlv.property.Property;
import fr.umlv.property.PropertyCategory;
import fr.umlv.rules.Rules;
import fr.umlv.zen5.Application;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.Event.Action;
import fr.umlv.zen5.KeyboardKey;
import fr.umlv.zen5.ScreenInfo;

/**
 * Main Class that will execute the game
 *
 */
public class Game {
	
  public static void main(String[] args) {
	Application.run(Color.BLACK, context -> {
	  ScreenInfo screenInfo = context.getScreenInfo();
	  int width = (int)screenInfo.getWidth();
	  int height = (int)screenInfo.getHeight();
	  
	  Board b = Board.createBoard(width, height, 10, 10);
	  Rules rules = new Rules();
	  
	  try {
		var name1 = Name.createName(1, 1, ElementCategory.Baba);
		b.addBloc(name1.position(), name1);
		var op1 = Operator.createOperator(2, 1, OperatorCategory.Is);
		var prop1 = Property.createProperty(3, 1, PropertyCategory.You);
		var name2 = Name.createName(1, 2, ElementCategory.Wall);
		var op2 = Operator.createOperator(2, 2, OperatorCategory.Is);
		var prop2 = Property.createProperty(3, 2, PropertyCategory.Stop);
		var baba1 = Element.createElement(4, 4, ElementCategory.Baba);
		var wall1 = Element.createElement(1, 4, ElementCategory.Wall);
		wall1.putState(PropertyCategory.Stop);
		b.addBloc(name1.position(), name1);
		b.addBloc(op1.position(), op1);
		b.addBloc(prop1.position(), prop1);
		b.addBloc(baba1.position(), baba1);
		b.addBloc(name2.position(), name2);
		b.addBloc(op2.position(), op2);
		b.addBloc(prop2.position(), prop2);
		b.addBloc(wall1.position(), wall1);
	} catch (IOException e) {
		System.out.println("Error detected while trying to read a file");
		e.printStackTrace();
	}
	  int pos_x = (int) ((width - b.lengthBloc() * b.length().x()) / 2);
	  int pos_y = (int) ((height - b.lengthBloc() * b.length().y()) / 2);
	  
	  context.renderFrame(graphics -> {
	    graphics.setColor(Color.BLACK);
	    graphics.fill(new Rectangle2D.Float(0, 0, width, height));
	  });
	  
	  for(;;) {
		  // refresh the position of all elements on the board
		  b.refresh();
		  // update the property of all elements on the board
		  rules.updateProperty(b);
		  b.draw(context, pos_x, pos_y);
		  Event event = context.pollOrWaitEvent(10);
	      if (event == null) {  // no event
	    	  continue;
	      }
		  Action action = event.getAction();
		  if (action == Action.KEY_PRESSED) {
			  if(event.getKey() == KeyboardKey.Q) { // Quit the game
				  System.out.println("abort abort !");
	              context.exit(0);
	              return;
	          // Move the element containing the You properties
			  } else if(event.getKey() == KeyboardKey.UP || event.getKey() == KeyboardKey.DOWN
					  || event.getKey() == KeyboardKey.RIGHT || event.getKey() == KeyboardKey.LEFT) {
				  Direction d = Direction.convertKeyboardKeyToDirection(event.getKey());
				  rules.applyMove(b, d);
			  }
	      }
	  }
	});
  }
}
