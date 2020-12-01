package fr.umlv.element;

import java.io.IOException;

public class Flag extends AbstractBloc {
	
	private Flag(int x, int y, boolean isProp) {
		super(x, y, Element.Flag, isProp);
	}
	
	public static Flag createFlag(int x, int y, Boolean isProp) throws IOException {
		Flag flag = new Flag(x, y, isProp);
		String fileName = flag.pathImage();
        flag.initImageIcon(fileName);
		return flag;
	}
}
