package fr.umlv.element;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import fr.umlv.zen5.ApplicationContext;

public class Lava extends AbstractBloc {
	private Rectangle2D.Float rectangle = new Rectangle2D.Float(0, 0, 0, 0);
	
	public Lava(int x, int y) {
		super(x, y, Color.red, Element.Lava);
	}
	
	public Color getColor() {
		return super.getColor();
	}
	
	public void draw(ApplicationContext context, float x, float y, int len) {
      context.renderFrame(graphics -> {
        // hide the previous rectangle
        graphics.setColor(Color.BLACK);
        graphics.fill(rectangle);
        
        // show a new rectangle at the position of the pointer
        graphics.setColor(getColor());
        rectangle = new Rectangle2D.Float(x - len/2, y - len/2, len, len);
        graphics.fill(rectangle);
      });
	}
}
