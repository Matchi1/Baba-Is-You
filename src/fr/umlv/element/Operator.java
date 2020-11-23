package fr.umlv.element;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

import fr.umlv.zen5.ApplicationContext;

public class Operator extends AbstractBloc {
	public Operator(int x, int y) {
		super(x, y, new Color(0, 0, 0, 0), Element.Operator, new Ellipse2D.Float());
	}
	
	public void shape(int x, int y, int len) {
		super.shape(new Ellipse2D.Float(x, y, len, len));
	}
	
	public void draw(ApplicationContext context, float x, float y, int len) {
      context.renderFrame(graphics -> {
        // hide the previous rectangle
        graphics.setColor(Color.BLACK);
        graphics.fill(super.shape());
        
        // show a new ellipse at the position of the pointer
        graphics.setColor(getColor());
        super.shape(new Ellipse2D.Float(x - len/2, y - len/2, len, len));
        graphics.fill(super.shape());
      });
	}
}
