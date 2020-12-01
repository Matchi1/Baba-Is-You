package fr.umlv.element;

import javax.swing.ImageIcon;

import fr.umlv.board.Direction;
import fr.umlv.board.Position;
import fr.umlv.properties.Property;

public interface Bloc {
	public Element element();
	public void translate(Direction d);
	
	public ImageIcon image();
	public void image(ImageIcon icon);
	
	public Position position();
	public void position(int x, int y);
	
	public Boolean getState(Property prop);
	public void putState(Property prop);
	public void removeState(Property prop);
	
	public String toString();
}
