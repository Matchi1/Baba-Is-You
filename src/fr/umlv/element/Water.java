package fr.umlv.element;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Water extends AbstractBloc {	
	public Water(int x, int y) {
		super(x, y, new Color(0, 220, 255), Element.Water, new Rectangle2D.Float());
	}
	
	public void shape(int x, int y, int len) {
		super.shape(new Ellipse2D.Float(x, y, len, len));
	}
}
