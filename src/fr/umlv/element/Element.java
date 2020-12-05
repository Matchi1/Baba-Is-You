package fr.umlv.element;

import java.io.IOException;
import java.util.Objects;

import fr.umlv.bloc.AbstractBloc;

public class Element extends AbstractBloc {
	private Element(int x, int y, ElementCategory elt) {
		super(x, y, elt);
	}
	
	
	public static Element createElement(int x, int y, ElementCategory elt) throws IOException {
		Objects.requireNonNull(elt);
		Element element = new Element(x, y, elt);
		String fileImage = element.pathImage();
		element.initImageIcon(fileImage);
		return element;
	}
}
