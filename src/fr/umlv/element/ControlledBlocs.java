package fr.umlv.element;

import java.util.ArrayList;

import fr.umlv.board.Board;
import fr.umlv.board.Position;

public class ControlledBlocs {
	private ArrayList<Bloc> group;
	
	public ControlledBlocs(Board b, Element elt) {
		this.group = new ArrayList<Bloc>();
		initGroup(b, elt);
	}
	
	public void initGroup(Board b, Element elt) {
		this.group.clear();
		for(int i = 0; i < b.length().x(); i++) {
			for(int j = 0; j < b.length().y(); j++) {
				for(var bloc : b.arrBloc(i, j)) {
					if(bloc.element() == elt)
						this.group.add(bloc);
				}
			}
		}
	}
	
	public ArrayList<Bloc> group(){
		return  (ArrayList<Bloc>)group.clone();
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
