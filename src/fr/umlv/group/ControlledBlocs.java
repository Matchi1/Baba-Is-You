package fr.umlv.group;

import java.util.ArrayList;

import fr.umlv.bloc.Bloc;
import fr.umlv.board.Position;
import fr.umlv.element.ElementCategory;

public class ControlledBlocs {
	private ArrayList<Bloc> group;
	
	public ControlledBlocs() {
		this.group = new ArrayList<Bloc>();
	}
	
	public static ControlledBlocs createCB(AllElement allElt, ElementCategory elt) {
		ControlledBlocs cb = new ControlledBlocs();
		cb.initGroup(allElt, elt);
		return cb;
	}
	
	public void initGroup(AllElement allElt, ElementCategory elt) {
		ArrayList<Bloc> elements = allElt.chooseGroup(elt);
		this.group = elements;
	}
	
	public ArrayList<Bloc> group(){
		return group;
	}
	
	public void translate(int dx, int dy) {
		for(var bloc : group) {
			Position next = bloc.position();
			next.translate(dx, dy);
			bloc.position(next.x(), next.y());
		}
	}
	
	public ArrayList<Position> lstPosition(){
		ArrayList<Position> lst = new ArrayList<>();
		for(var bloc : group) {
			lst.add(bloc.position());
		}
		return lst;
	}
	
	public void displayPos() {
		for(var bloc : group) {
			System.out.println(bloc.position());
		}
	}
}
