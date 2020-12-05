package fr.umlv.property;

import fr.umlv.bloc.Bloc;
import fr.umlv.element.ElementCategory;

public interface IsProperty extends Bloc {
	public PropertyCategory property();
	
	public ElementCategory applyTo();
	public void applyTo(ElementCategory elt);
}
