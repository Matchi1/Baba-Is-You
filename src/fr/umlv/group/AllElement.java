package fr.umlv.group;

import java.util.ArrayList;

import fr.umlv.board.Board;
import fr.umlv.element.Bloc;
import fr.umlv.element.Element;
import fr.umlv.properties.Property;

public class AllElement {
	private ArrayList<Bloc> allBaba;
	private ArrayList<Bloc> allRock;
	private ArrayList<Bloc> allWall;
	private ArrayList<Bloc> allFlag;
	private ArrayList<Bloc> allLava;
	private ArrayList<Bloc> allWater;
	private ArrayList<Bloc> allName;
	
	private AllElement() {
		this.allBaba = new ArrayList<>();
		this.allRock = new ArrayList<>();
		this.allWall = new ArrayList<>();
		this.allFlag = new ArrayList<>();
		this.allLava = new ArrayList<>();
		this.allWater = new ArrayList<>();
		this.allName = new ArrayList<>();
	}
	
	public static AllElement createAllElement(Board b) {
		AllElement altElt = new AllElement();
		for(int i = 0; i < b.length().x(); i++) {
			for(int j = 0; j < b.length().y(); j++) {
				for(var bloc : b.arrBloc(i, j)) {
					altElt.addBloc(bloc);
				}
			}
		}
		return altElt;
	}
	
	public void addBloc(Bloc bloc) {
		ArrayList<Bloc> group = chooseGroup(bloc.element());
		group.add(bloc);
	}

	public ArrayList<Bloc> chooseGroup(Element elt){
		switch(elt) {
		case Rock: return allRock;
		case Wall: return allWall;
		case Flag: return allFlag;
		case Lava: return allLava;
		case Water: return allWater;
		case Name: return allName;
		default: return allBaba;
		}
	}
	
	public void applyRules(Element elt, Property prop, boolean putState) {
		ArrayList<Bloc> group = chooseGroup(elt);
		for(var bloc : group) {
			if(putState) {
				bloc.putState(prop);
			} else {
				bloc.removeState(prop);
			}
		}
	}
}
