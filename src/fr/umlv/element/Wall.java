package fr.umlv.element;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

import fr.umlv.zen5.ApplicationContext;

public class Wall extends AbstractBloc {
private Ellipse2D.Float ellipse = new Ellipse2D.Float(0, 0, 0, 0);
	
	public Wall(int x, int y) {
		super(x, y);
	}
	
	public void draw(ApplicationContext context, float x, float y, int len) {
      context.renderFrame(graphics -> {
        // hide the previous rectangle
        graphics.setColor(Color.BLACK);
        graphics.fill(ellipse);
        
        Color color = new Color(15, 27, 149);
        // show a new ellipse at the position of the pointer
        graphics.setColor(color);
        ellipse = new Ellipse2D.Float(x - len/2, y - len/2, len, len);
        graphics.fill(ellipse);
      });
	}
}
