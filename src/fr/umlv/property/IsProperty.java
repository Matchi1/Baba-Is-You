package fr.umlv.property;

import fr.umlv.element.ElementCategory;

public interface IsProperty {
	public PropertyCategory property();
	
	public ElementCategory applyTo();
	public void applyTo(ElementCategory elt);
}
