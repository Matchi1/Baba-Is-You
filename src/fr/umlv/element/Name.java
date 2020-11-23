package fr.umlv.element;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import fr.umlv.zen5.ApplicationContext;

public class Name extends AbstractBloc {
	private String text;
	
	public Name(int x, int y, String text) {
		super(x, y, new Color(0, 0, 0, 0), Element.Name, new Rectangle2D.Float());
		this.text = text;
	}
	
	public void shape(int x, int y, int len) {
		super.shape(new Rectangle2D.Float(x, y, len, len));
	}
	
	public void draw(ApplicationContext context, float x, float y, int len) {
      context.renderFrame(graphics -> {
        // hide the previous rectangle
        graphics.setColor(Color.BLACK);
        graphics.fill(super.shape());
        
        // show a new ellipse at the position of the pointer
        graphics.setColor(getColor());
        super.shape(new Rectangle2D.Float(x, y, len, len));
        graphics.fill(super.shape());
        graphics.setColor(Color.RED);
        graphics.drawString(text, x, y);
      });
	}
}
