package fr.umlv.element;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import fr.umlv.board.Direction;
import fr.umlv.board.Position;
import fr.umlv.properties.Property;

public abstract class AbstractBloc implements Bloc {
	private Position pos;
	private boolean isProperty;
	private HashMap<Property, Boolean> states;
	private final Element elt;
	ImageIcon icon;
	
	public AbstractBloc(int x, int y, Element elt, Boolean isProperty) {
		this.pos = new Position(x, y);
		this.elt = elt;
		this.isProperty = isProperty;
		this.states = new HashMap<>();
	}
	
	public void initImageIcon(String fileName) throws IOException {
		File f = new File(fileName);
		InputStream inputS = new FileInputStream(f);
		Image img = ImageIO.read(inputS);
		JFrame imgO = new JFrame();
        ImageIcon icon = new ImageIcon(fileName);
        JLabel jl = new JLabel(icon);
        imgO.getContentPane().add(jl);
        imgO.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        icon.setImage(img);
        icon.setImageObserver(imgO);
        this.icon = icon;
	}
	
	public String pathImage() {
		if(isProperty)
			return elt.pathElementText();
		return elt.pathElement();
	}
	
	public Element element() {
		return elt;
	}
	
	public void translate(Direction d) {
		pos.translate(d);
	}
	
	public ImageIcon image() {
		return icon;
	}
	
	public void image(ImageIcon icon) {
		this.icon = icon;
	}
	
	public Position position() {
		return pos.clone();
	}
	
	public void position(int x, int y) {
		pos.x(x);
		pos.y(y);
	}
	
	public Boolean getState(Property prop) {
		if(states.get(prop) == null)
			return false;
		return true;
	}
	
	public void putState(Property prop) {
		states.put(prop, true);
	}
	
	public void removeState(Property prop) {
		states.remove(prop);
	}
	
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
