package fr.umlv.properties;

import fr.umlv.element.AbstractBloc;
import fr.umlv.element.Element;

public abstract class AbstractProperty extends AbstractBloc {
	private Property prop;
	public AbstractProperty(int x, int y, Property prop) {
		super(x, y, Element.Property, true);
		this.prop = prop;
	}
	
	public void initStates() {
		super.putState(Property.Push);
	}
	
	public String pathImage() {
		return prop.pathProperty();
	}
}
