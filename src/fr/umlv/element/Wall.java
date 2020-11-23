package fr.umlv.element;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Wall extends AbstractBloc {
	public Wall(int x, int y) {
		super(x, y, new Color(15, 27, 149), Element.Wall, new Rectangle.Float());
	}
	
	public void shape(int x, int y, int len) {
		super.shape(new Rectangle2D.Float(x, y, len, len));
	}
}
