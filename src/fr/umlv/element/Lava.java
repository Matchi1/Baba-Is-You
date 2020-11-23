package fr.umlv.element;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class Lava extends AbstractBloc {	
	public Lava(int x, int y) {
		super(x, y, Color.red, Element.Lava, new Rectangle2D.Float());
	}
	
	public void shape(int x, int y, int len) {
		super.shape(new Rectangle2D.Float(x, y, len, len));
	}
}
