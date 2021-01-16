package fr.umlv.property;

import java.io.IOException;
import java.util.Objects;

import fr.umlv.bloc.AbstractBloc;
import fr.umlv.bloc.Bloc;
import fr.umlv.element.ElementCategory;
import fr.umlv.file.FileCategory;

/**
 * This class provides an implementation of the AbstractBloc class.
 * It is a more specific type of Bloc
 *
 */
public class Property extends AbstractBloc {
	private PropertyCategory prop;
	
	public Property(int x, int y, PropertyCategory prop) {
		super(x, y, ElementCategory.Property);
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
		Objects.requireNonNull(prop);
		Property newProp = new Property(x, y, prop);
		newProp.putState(PropertyCategory.Push);
		newProp.initImageIcon();
		return newProp;
	}
	
	/**
	 * Create an Property type with the specified array
	 * @param parts the specified array
	 * @return The create Element
	 */
	public static Bloc createProperty(String[] parts) {
		Objects.requireNonNull(parts);
		Bloc elt = null;
		try {
			elt = Property.createProperty(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]),
            		PropertyCategory.convertStr(FileCategory.convertSymbolToName(parts[3])));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return elt;
	}

	/**
	 * Returns the String representation of the path of this object's image
	 */
	@Override
	public String pathImage() {
		return prop.pathProperty();
	}
	
	/**
	 * Returns the category of this property
	 */
	@Override
	public PropertyCategory property() {
		return prop;
	}
}
