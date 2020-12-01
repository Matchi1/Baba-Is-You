package fr.umlv.element;

import java.io.IOException;

public class Lava extends AbstractBloc {	
	public Lava(int x, int y, boolean isProp) {
		super(x, y, Element.Lava, isProp);
	}
	
	public static Lava createLava(int x, int y, Boolean isProp) throws IOException {
		Lava lava = new Lava(x, y, isProp);
		String fileName = lava.pathImage();
        lava.initImageIcon(fileName);
		return lava;
	}
}
