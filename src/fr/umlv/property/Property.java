package fr.umlv.property;

import java.io.IOException;

import fr.umlv.bloc.AbstractBloc;
import fr.umlv.element.ElementCategory;

public class Property extends AbstractBloc implements IsProperty {
	private PropertyCategory prop;
	private ElementCategory applyTo;
	
	private Property(int x, int y, PropertyCategory prop) {
		super(x, y, ElementCategory.Property);
		this.applyTo = ElementCategory.None;
		this.prop = prop;
	}
	
	public static Property createProperty(int x, int y, PropertyCategory prop) throws IOException {
		Property newProp = new Property(x, y, prop);
		String fileImage = newProp.pathImage();
		newProp.putState(PropertyCategory.Push);
		newProp.initImageIcon(fileImage);
		return newProp;
	}

	
	public String pathImage() {
		return prop.pathProperty();
	}
	
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
