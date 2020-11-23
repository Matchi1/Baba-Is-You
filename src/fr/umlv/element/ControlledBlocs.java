package fr.umlv.element;

import java.util.ArrayList;

import fr.umlv.board.Board;
import fr.umlv.board.Position;

public class ControlledBlocs {
	private ArrayList<Bloc> group;
	
	public ControlledBlocs() {
		this.group = new ArrayList<Bloc>();
	}
	
	public void initGroup(Board b, Element elt) {
		this.group.clear();
		for(int i = 0; i < b.taille().x(); i++) {
			for(int j = 0; j < b.taille().y(); j++) {
				for(var bloc : b.arrBloc(i, j)) {
					if(bloc.element() == elt)
						this.group.add(bloc);
				}
			}
		}
	}
	
	public ArrayList<Bloc> group(){
		ArrayList<Bloc> lst;
		if(group.clone().getClass() == this.group.getClass()) {
			lst = (ArrayList<Bloc>)group.clone();
		} else
			return null;
		
		return lst;
	}
	
	public void translate(int dx, int dy) {
		for(var bloc : group) {
			Position next = bloc.position();
			next.translate(dx, dy);
			bloc.position(next.x(), next.y());
		}
	}
	
	public void displayPos() {
		for(var bloc : group) {
			System.out.println(bloc.position());
		}
	}
}
