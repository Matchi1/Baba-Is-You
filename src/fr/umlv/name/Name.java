package fr.umlv.name;

import java.io.IOException;
import java.util.Objects;

import fr.umlv.bloc.AbstractBloc;
import fr.umlv.element.ElementCategory;
import fr.umlv.property.PropertyCategory;

/**
 * This class provides an implementation of the AbstractBloc class.
 * It is a more specific type of Bloc
 *
 */
public class Name extends AbstractBloc {
	private final ElementCategory rep;
	private ElementCategory applyTo;
	
	private Name(int x, int y, ElementCategory rep) {
		super(x, y, ElementCategory.Name);
		this.rep = Objects.requireNonNull(rep);
		this.applyTo = ElementCategory.None;
	}
	
	public String pathImage() {
		return rep.pathElementCategoryText();
	}
	
	/**
	 * Creates a Name object
	 * This function initializes the default states of the object and the image this object
	 * has to display in a window
	 * It also initializes the element with the specified element this object represents, 
	 * and its position in the board with the specified position 
	 * @param x position of this object in the X axis
	 * @param y position of this object in the Y axis
	 * @param rep the specified element this object represents
	 * @return a Name object
	 * @throws IOException
	 */
	public static Name createName(int x, int y, ElementCategory rep) throws IOException {
		Objects.requireNonNull(rep);
		Name newName = new Name(x, y, rep);
		String fileImage = newName.pathImage();
		newName.putState(PropertyCategory.Push);
		newName.initImageIcon(fileImage);
		return newName;
	}
	
	/**
	 * Returns the element this object represents
	 * @return an ElementCategory object
	 */
	@Override
	public ElementCategory represent() {
		return this.rep;
	}
	
	/**
	 * Returns the element this object applies to
	 * @return an ElementCategory object 
	 */
	@Override
	public ElementCategory applyTo() {
		return this.applyTo;
	}

	/**
	 * Set the specified element object that this object applies to
	 * @param elt the specified element
	 */
	@Override
	public void applyTo(ElementCategory elt) {
		this.applyTo = elt;
	}
}
