package fr.umlv.element;

import java.io.IOException;

public class Baba extends AbstractBloc{
	
	private Baba(int x, int y, boolean isProp) {
		super(x, y, Element.Baba, isProp);
	}
	
	public static Baba createBaba(int x, int y, Boolean isProp) throws IOException {
		Baba baba = new Baba(x, y, isProp);
		String fileName = baba.pathImage();
        baba.initImageIcon(fileName);
		return baba;
	}
}
