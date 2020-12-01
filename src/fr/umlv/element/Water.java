package fr.umlv.element;

import java.io.IOException;

public class Water extends AbstractBloc {	
	public Water(int x, int y, boolean isProp) {
		super(x, y, Element.Water, isProp);
	}
	
	public static Water createWater(int x, int y, Boolean isProp) throws IOException {
		Water water = new Water(x, y, isProp);
		String fileName = water.pathImage();
        water.initImageIcon(fileName);
		return water;
	}
}
