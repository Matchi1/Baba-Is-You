package fr.umlv.element;

import java.io.IOException;

public class Rock extends AbstractBloc {	
	public Rock(int x, int y, boolean isProp) {
		super(x, y, Element.Rock, isProp);
	}
	
	public static Rock createRock(int x, int y, Boolean isProp) throws IOException {
		Rock rock = new Rock(x, y, isProp);
		String fileName = rock.pathImage();
        rock.initImageIcon(fileName);
		return rock;
	}
}
