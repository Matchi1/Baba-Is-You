package fr.umlv.element;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

import fr.umlv.zen5.ApplicationContext;

public class Rock extends AbstractBloc {
	private Ellipse2D.Float ellipse = new Ellipse2D.Float(0, 0, 0, 0);
	
	public Rock(int x, int y) {
		super(x, y);
	}
	
	public void draw(ApplicationContext context, float x, float y) {
      context.renderFrame(graphics -> {
        // hide the previous rectangle
        graphics.setColor(Color.BLACK);
        graphics.fill(ellipse);
        
        // show a new ellipse at the position of the pointer
        graphics.setColor(Color.orange);
        ellipse = new Ellipse2D.Float(x - 20, y - 20, 40, 40);
        graphics.fill(ellipse);
      });
	}
}
