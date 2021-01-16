package fr.umlv.bloc;

import java.awt.Image;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import fr.umlv.element.Element;
import fr.umlv.element.ElementCategory;
import fr.umlv.property.PropertyCategory;

/**
 * This class provides a skeletal implementation of the Bloc interface to minimize
 * the effort required to implement this interface. 
 */
public abstract class AbstractBloc implements Bloc {
	private Position pos;
	private HashMap<PropertyCategory, Boolean> states;
	private ElementCategory elt;
	private ImageIcon icon;

	public AbstractBloc(int x, int y, ElementCategory elt) {
		this.pos = new Position(x, y);
		this.elt = Objects.requireNonNull(elt);
		this.states = new HashMap<>();
	}
	
	public AbstractBloc(Bloc bloc) {
		this.pos = new Position(bloc.position());
		this.elt = Objects.requireNonNull(bloc.element());
		this.states = new HashMap<>();
	}
	
	/**
	 * Initialize the icon field with the animated image 
	 * with the path of the image file.
	 * @param pathImage the path to the image file
	 * @throws IOException
	 */
	public void initImageIcon() throws IOException {
		Image img = ImageIO.read(new FileInputStream(pathImage()));
		JFrame imgO = new JFrame();
        ImageIcon icon = new ImageIcon(pathImage());
        imgO.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        icon.setImage(img);
        icon.setImageObserver(imgO);
        this.icon = icon;
	}
	
	/**
	 * Initialize the state of the bloc
	 */
	public void initStates() {
		if(elt == ElementCategory.Property) 
			putState(PropertyCategory.Push);
	}
	
	/**
	 * Returns the path to the image file
	 * @return the path to an image file
	 */
	public String pathImage() {
		return elt.pathElementCategory();
	}
	
	/**
	 * Returns the type of element this object is
	 */
	public ElementCategory element() {
		return elt;
	}
	
	/**
	 * Returns the type of element this object is
	 */
	public void element(ElementCategory elt) {
		this.elt = Objects.requireNonNull(elt);
	}
	
	/**
	 * Translate the position of this object
	 * depending on the direction.
	 */
	public void translate(Direction d) {
		pos.translate(Objects.requireNonNull(d));
	}
	
	/**
	 * Returns the Image of this object
	 */
	public ImageIcon image() {
		return icon;
	}
	
	/**
	 * Set the image of this object
	 */
	public void image(ImageIcon icon) {
		this.icon = Objects.requireNonNull(icon);
	}
	
	/**
	 * Returns the position of this object
	 */
	public Position position() {
		return pos.clone();
	}
	
	/**
	 * Set the position of this object
	 */
	public void position(int x, int y) {
		pos.x(x);
		pos.y(y);
	}
	
	/**
	 * Returns True if the hashmap in this objects contains
	 * the specified element.
	 * @param prop The specified element
	 * @returns true if the hashmap of the states contains the specified
	 */
	public Boolean containState(PropertyCategory prop) {
		Objects.requireNonNull(prop);
		if(states.get(Objects.requireNonNull(prop)) == null)
			return false;
		return true;
	}
	
	/**
	 * Associates a true value to the specified key in the states map
	 * @param prop the specified key
	 */
	public void putState(PropertyCategory prop) {
		states.put(Objects.requireNonNull(prop), true);
	}
	
	/**
	 * removes the mappings from this specified key from states map if present
	 * @param prop the specified key
	 */
	public void removeState(PropertyCategory prop) {
		states.remove(Objects.requireNonNull(prop));
	}
	
	/**
	 * Clear all of the mappings from the states map
	 */
	public void clearState() {
		this.states.clear();
	}
	
	/**
	 * Returns the type of property this object is
	 * @return None object from PropertyCategory
	 */
	@Override
	public PropertyCategory property() {
		return PropertyCategory.None;
	}
	
	/**
	 * Returns the Element that this object represent
	 * @return None object from ElementCategory
	 */
	@Override
	public ElementCategory represent() {
		return ElementCategory.None;
	}
	
	/**
	 * Returns a hash code value for this object
	 */
	@Override
	public int hashCode() {
		return Objects.hash(pos, elt);
	}
	
	/**
	 * Indicates whether some other object is "equal to" this one.
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		if(obj.getClass() != this.getClass())
			return false;
		Element elt = (Element) obj;
		if(this.element() != elt.element())
			return false;
		return elt.position().equals(this.position());
	}
	
	/**
	 * Returns a String representation of this object
	 * @return a String representation of this object
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(elt);
		str.append(pos);
		str.append("\n");
		for(var key : states.keySet()) {
			str.append(key.toString());
			str.append("\n");
		}
		return str.toString();
	}
}
