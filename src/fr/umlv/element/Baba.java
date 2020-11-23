package fr.umlv.element;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

public class Baba extends AbstractBloc{
	
	public Baba(int x, int y) {
		super(x, y, Color.white, Element.Baba, new Ellipse2D.Float());
	}
	
	public void shape(int x, int y, int len) {
		super.shape(new Ellipse2D.Float(x, y, len, len));
	}
}
