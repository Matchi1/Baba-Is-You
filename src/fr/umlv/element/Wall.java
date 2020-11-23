package fr.umlv.element;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import fr.umlv.zen5.ApplicationContext;

public class Wall extends AbstractBloc {
	private Rectangle2D.Float rectangle = new Rectangle2D.Float(0, 0, 0, 0);
	
	public Wall(int x, int y) {
		super(x, y, new Color(15, 27, 149), Element.Wall);
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
        rectangle = new Rectangle2D.Float(x, y, len, len);
        graphics.fill(rectangle);
      });
	}
}
