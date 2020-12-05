package fr.umlv.group;

import java.util.ArrayList;

import fr.umlv.bloc.Bloc;
import fr.umlv.board.Board;
import fr.umlv.board.Position;
import fr.umlv.element.ElementCategory;
import fr.umlv.property.PropertyCategory;

public class AllElement {
	private ArrayList<Bloc> allBaba;
	private ArrayList<Bloc> allRock;
	private ArrayList<Bloc> allWall;
	private ArrayList<Bloc> allFlag;
	private ArrayList<Bloc> allLava;
	private ArrayList<Bloc> allWater;
	private ArrayList<Bloc> allName;
	private ArrayList<Bloc> allOperators;
	private ArrayList<Bloc> allProperties;
	
	private AllElement() {
		this.allBaba = new ArrayList<>();
		this.allRock = new ArrayList<>();
		this.allWall = new ArrayList<>();
		this.allFlag = new ArrayList<>();
		this.allLava = new ArrayList<>();
		this.allWater = new ArrayList<>();
		this.allName = new ArrayList<>();
		this.allOperators = new ArrayList<>();
		this.allProperties = new ArrayList<>();
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

	public ArrayList<Bloc> chooseGroup(ElementCategory elt){
		switch(elt) {
		case Rock: return allRock;
		case Wall: return allWall;
		case Flag: return allFlag;
		case Lava: return allLava;
		case Water: return allWater;
		case Name: return allName;
		case Operator: return allOperators;
		case Property: return allProperties;
		default: return allBaba;
		}
	}
	
	public boolean verifyOperators(Position pos) {
		return true;
	}
	
	public Bloc verifyProperty(Position pos) {
		for(var bloc : allProperties) {
			if(pos.equals(bloc.position()))
				return bloc;
		}
		return null;
	}
}
