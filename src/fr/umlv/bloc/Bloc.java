package fr.umlv.bloc;

import javax.swing.ImageIcon;

import fr.umlv.board.Direction;
import fr.umlv.board.Position;
import fr.umlv.element.IsElement;
import fr.umlv.property.IsProperty;
import fr.umlv.property.PropertyCategory;

public interface Bloc extends IsElement {
	public void translate(Direction d);
	
	public ImageIcon image();
	public void image(ImageIcon icon);
	
	public Position position();
	public void position(int x, int y);
	
	public Boolean containState(PropertyCategory prop);
	public void putState(PropertyCategory prop);
	public void removeState(PropertyCategory prop);
	
	public String toString();
}
