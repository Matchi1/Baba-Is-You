package fr.umlv.name;

import java.io.IOException;
import java.util.Objects;

import fr.umlv.bloc.AbstractBloc;
import fr.umlv.element.ElementCategory;
import fr.umlv.property.PropertyCategory;

public class Name extends AbstractBloc {
	ElementCategory elt;
	ElementCategory rep;
	
	private Name(int x, int y, ElementCategory rep) {
		super(x, y, ElementCategory.Name);
		this.rep = rep;
	}
	
	public String pathImage() {
		return rep.pathElementCategoryText();
	}
	
	public static Name createName(int x, int y, ElementCategory rep) throws IOException {
		Objects.requireNonNull(rep);
		Name newName = new Name(x, y, rep);
		String fileImage = newName.pathImage();
		newName.putState(PropertyCategory.Push);
		newName.initImageIcon(fileImage);
		return newName;
	}
}
