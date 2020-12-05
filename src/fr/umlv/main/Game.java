package fr.umlv.main;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import fr.umlv.board.Board;
import fr.umlv.board.Direction;
import fr.umlv.board.Position;
import fr.umlv.element.Element;
import fr.umlv.element.ElementCategory;
import fr.umlv.group.AllElement;
import fr.umlv.group.ControlledBlocs;
import fr.umlv.name.Name;
import fr.umlv.operator.Operator;
import fr.umlv.operator.OperatorCategory;
import fr.umlv.property.Property;
import fr.umlv.property.PropertyCategory;
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
		var name1 = Name.createName(1, 1, ElementCategory.Baba);
		var op1 = Operator.createOperator(2, 1, OperatorCategory.Is);
		var prop1 = Property.createProperty(3, 1, PropertyCategory.You);
		var baba1 = Element.createElement(4, 4, ElementCategory.Baba);
		var baba2 = Element.createElement(1, 2, ElementCategory.Baba);
		var wall1 = Element.createElement(1, 4, ElementCategory.Wall);
		wall1.putState(PropertyCategory.Stop);
		b.addBloc(name1.position(), name1);
		b.addBloc(op1.position(), op1);
		b.addBloc(prop1.position(), prop1);
		b.addBloc(baba1.position(), baba1);
		b.addBloc(baba2.position(), baba2);
		b.addBloc(wall1.position(), wall1);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	  AllElement altElt = AllElement.createAllElement(b);
	  
	  ControlledBlocs cb = ControlledBlocs.createCB(altElt, ElementCategory.Baba);
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
			  } else if(event.getKey() == KeyboardKey.UP || event.getKey() == KeyboardKey.DOWN
					  || event.getKey() == KeyboardKey.RIGHT || event.getKey() == KeyboardKey.LEFT) {
				  Direction d = Direction.convertKeyboardKeyToDirection(event.getKey());
				  if(b.moveControlledBlocs(cb, d) == 1) {
					  if(b.blocsPositionIsLegal()) {
						  b.refresh();
					  } else {
						  b.resetBoard();
					  }
				  }
			  }
	      }
	  }
	});
  }
}
