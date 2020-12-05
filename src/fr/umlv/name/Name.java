package fr.umlv.name;

import java.io.IOException;

import fr.umlv.bloc.AbstractBloc;
import fr.umlv.element.ElementCategory;

public class Name extends AbstractBloc {
	ElementCategory elt;
	ElementCategory rep;
	
	public Name(int x, int y, ElementCategory rep) {
		super(x, y, ElementCategory.Name);
		this.rep = rep;
	}
	
	public String pathImage() {
		return rep.pathElementCategoryText();
	}
	
	public static Name createName(int x, int y, ElementCategory rep) throws IOException {
		Name newName = new Name(x, y, rep);
		String fileImage = newName.pathImage();
		newName.initImageIcon(fileImage);
		return newName;
	}
}
