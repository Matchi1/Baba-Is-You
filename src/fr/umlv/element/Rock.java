package fr.umlv.element;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class Rock extends AbstractBloc {	
	public Rock(int x, int y) {
		super(x, y, new Color(172, 103, 0), Element.Rock, new Rectangle.Float());
	}
	
	public void shape(int x, int y, int len) {
		super.shape(new Ellipse2D.Float(x, y, len, len));
	}
}
