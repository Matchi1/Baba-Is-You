package fr.umlv.bloc;

import java.io.IOException;

import javax.swing.ImageIcon;

import fr.umlv.element.ElementCategory;
import fr.umlv.property.PropertyCategory;

/**
 * An element of the game.
 * The user of this interface can manipulate the position of this element.
 * The user of this interface can also change its states, its type element
 * or even what property it represents.
 */
public interface Bloc {
	
	/**
	 * Translate the position of this object
	 * depending on the direction.
	 */
	public void translate(Direction d);
	
	/**
	 * Returns the Image of this object
	 */
	public ImageIcon image();
	
	/**
	 * Set the image of this object
	 */
	public void image(ImageIcon icon);
	
	/**
	 * Initialize the icon field with the animated image 
	 * with the path of the image file.
	 * @param pathImage the path to the image file
	 * @throws IOException
	 */
	public void initImageIcon() throws IOException;
	
	/**
	 * Returns the position of this object
	 */
	public Position position();
	
	/**
	 * Set the position of this object
	 */
	public void position(int x, int y);
	
	/**
	 * Returns True if the hashmap in this objects contains
	 * the specified element.
	 * @param prop The specified element
	 * @returns true if the hashmap of the states contains the specified
	 */
	public Boolean containState(PropertyCategory prop);
	
	/**
	 * Associates a true value to the specified key in the states map
	 * @param prop the specified key
	 */
	public void putState(PropertyCategory prop);
	
	/**
	 * removes the mappings from this specified key from states map if present
	 * @param prop the specified key
	 */
	public void removeState(PropertyCategory prop);
	
	/**
	 * Clear all of the mappings from the states map
	 */
	public void clearState();
	
	/**
	 * Returns the type of element this object is
	 */
	public ElementCategory element();
	
	/**
	 * Returns the type of element this object is
	 */
	public void element(ElementCategory elt);
	
	/**
	 * Returns the type of property this object is
	 * @return None object from PropertyCategory
	 */
	public PropertyCategory property();
	
	/**
	 * Returns the Element that this object represent
	 * @return None object from ElementCategory
	 */
	public ElementCategory represent();
	
	/**
	 * Returns a String representation of this object
	 * @return a String representation of this object
	 */
	public String toString();
}
