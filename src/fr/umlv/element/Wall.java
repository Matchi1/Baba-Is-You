package fr.umlv.element;

import java.io.IOException;

public class Wall extends AbstractBloc {
	public Wall(int x, int y, boolean isProp) {
		super(x, y, Element.Wall, isProp);
	}

	public static Wall createWall(int x, int y, Boolean isProp) throws IOException {
		Wall wall = new Wall(x, y, isProp);
		String fileName = wall.pathImage();
        wall.initImageIcon(fileName);
		return wall;
	}
}
