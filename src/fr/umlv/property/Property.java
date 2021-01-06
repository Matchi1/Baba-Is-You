package fr.umlv.property;

import java.io.IOException;

import fr.umlv.bloc.AbstractBloc;
import fr.umlv.element.ElementCategory;

/**
 * This class provides an implementation of the AbstractBloc class.
 * It is a more specific type of Bloc
 *
 */
public class Property extends AbstractBloc implements IsProperty {
	private PropertyCategory prop;
	private ElementCategory applyTo;
	
	private Property(int x, int y, PropertyCategory prop) {
		super(x, y, ElementCategory.Property);
		this.applyTo = ElementCategory.None;
		this.prop = prop;
	}
	
	/**
	 * Creates a Property object
	 * This function initializes the image this object has to display in a window
	 * It also initializes this object with the specified property this object is, 
	 * and its position in the board with the specified position 
	 * @param x position of this object in the X axis
	 * @param y position of this object in the Y axis
	 * @param prop the specified property this object is
	 * @return a Property object
	 * @throws IOException
	 */
	public static Property createProperty(int x, int y, PropertyCategory prop) throws IOException {
		Property newProp = new Property(x, y, prop);
		String fileImage = newProp.pathImage();
		newProp.putState(PropertyCategory.Push);
		newProp.initImageIcon(fileImage);
		return newProp;
	}

	@Override
	public String pathImage() {
		return prop.pathProperty();
	}
	
	@Override
	public PropertyCategory property() {
		return prop;
	}
	
	@Override
	public ElementCategory applyTo() {
		return applyTo;
	}

	@Override
	public void applyTo(ElementCategory elt) {
		this.applyTo = elt;
	}
}
