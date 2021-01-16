package fr.umlv.name;

import java.io.IOException;
import java.util.Objects;

import fr.umlv.bloc.AbstractBloc;
import fr.umlv.bloc.Bloc;
import fr.umlv.element.ElementCategory;
import fr.umlv.file.FileCategory;
import fr.umlv.property.PropertyCategory;

/**
 * This class provides an implementation of the AbstractBloc class.
 * It is a more specific type of Bloc
 *
 */
public class Name extends AbstractBloc {
	private final ElementCategory rep;
	
	public Name(int x, int y, ElementCategory rep) {
		super(x, y, ElementCategory.Name);
		this.rep = Objects.requireNonNull(rep);
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
		newName.putState(PropertyCategory.Push);
		newName.initImageIcon();
		return newName;
	}
	
	public static Bloc createName(String[] parts) {
		Bloc elt = null;
		try {
			elt = Name.createName(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), 
            		ElementCategory.convertStr(FileCategory.convertSymbolToName(parts[3])));
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
	 * Returns the element this object represents
	 * @return an ElementCategory object
	 */
	@Override
	public ElementCategory represent() {
		return this.rep;
	}
}
