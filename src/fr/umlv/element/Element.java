package fr.umlv.element;

import java.io.IOException;
import java.util.Objects;

import fr.umlv.bloc.AbstractBloc;
import fr.umlv.property.PropertyCategory;

/**
 * This class provides an implementation of the AbstractBloc class.
 * It is a more specific type of Bloc
 *
 */
public class Element extends AbstractBloc {
	private Element(int x, int y, ElementCategory elt) {
		super(x, y, elt);
	}
	
	/**
	 * Creates a Element object
	 * This function initializes the image this object has to display in a window
	 * It also initializes the element with the specified element this object is, 
	 * and its position in the board with the specified position 
	 * @param x position of this object in the X axis
	 * @param y position of this object in the Y axis
	 * @param elt the specified element this object is
	 * @return a Element object
	 * @throws IOException
	 */
	public static Element createElement(int x, int y, ElementCategory elt) throws IOException {
		Objects.requireNonNull(elt);
		Element element = new Element(x, y, elt);
		String fileImage = element.pathImage();
		element.initImageIcon(fileImage);
		return element;
	}
}
