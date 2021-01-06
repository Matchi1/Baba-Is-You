package fr.umlv.rules;

import java.util.Objects;

import fr.umlv.bloc.Bloc;
import fr.umlv.board.Board;
import fr.umlv.board.Direction;
import fr.umlv.board.Position;
import fr.umlv.element.ElementCategory;
import fr.umlv.property.PropertyCategory;

public class Rules {
	private PropertyUpdater pu;
	
	public Rules() {
		this.pu = new PropertyUpdater();
	}
	
	/**
	 * Move an element in the board according to a direction
	 * and a specified position
	 * @param pos a specified position
	 * @param d a specified direction
	 * @return 0 if the next position of the Bloc object is outside the length of the board else 1
	 */
	public int pushBloc(Board b, Position pos, Direction d) {
		Objects.requireNonNull(d);
		if(b.outsideBoard(pos))
			return 0;
		var lst = b.blocSet(pos);
		System.out.println(lst);
		if(lst == null)
			return 1;
		for(var bloc : lst) {
			if(bloc.containState(PropertyCategory.Push)) {
				if(!bloc.position().equals(pos))
					return 1;
				bloc.translate(d);
				if(pushBloc(b, bloc.position(), d) == 0) {
					bloc.translate(d.contrary());
					return 0;
				}
			}
		}
		return 1;
	}
	
	public void applyMove(Board b, Direction d) {
		Objects.requireNonNull(b);
		var set = b.allElement();
		for(var group : set) {
			for(var bloc : group) {
				var pos = new Position(bloc.position());
				pos.translate(d);
				if(bloc.containState(PropertyCategory.You)) {
					if(b.isLegal(pos) && pushBloc(b, pos, d) == 1)
						bloc.translate(d);
				}
			}
		}
	}
	
	public void applyHotMelt(Board b) {
		Objects.requireNonNull(b);
		var set = b.allElement();
		for(var group : set) {
			var containsHot = false;
			for(var bloc : group) {
				Bloc elementMelt = null;
				if(bloc.property() == PropertyCategory.Hot)
					containsHot = true;
				else if(bloc.property() == PropertyCategory.Melt) 
					elementMelt = bloc;
				if(containsHot && elementMelt != null)
					b.remove(elementMelt);
			}
		}
	}
	
	public void applySink(Board b) {
		Objects.requireNonNull(b);
		var pos = new Position(0, 0);
		var set = b.allElement();
		for(var group : set) {
			var containsSink = false;
			for(var bloc : group) {
				pos = bloc.position();
				if(bloc.property() == PropertyCategory.Sink)
					containsSink = true;
			}
			if(containsSink)
				b.removePos(pos);
		}
	}
	
	public boolean verifyWin(Board b) {
		Objects.requireNonNull(b);
		var set = b.allElement();
		var containsWin = false;
		var containsYou = false;
		
		for(var group : set) {
			for(var elt : group) {
				if(elt.property() == PropertyCategory.Win) 
					containsWin = true;
				else if(elt.property() == PropertyCategory.You) 
					containsYou = true;
				
				if(containsWin && containsYou)
					return true;
			}
		}
		return false;
	}
	
	public boolean VerifyDefeat(Board b) {
		Objects.requireNonNull(b);
		var set = b.allElement();
		var containsDefeat = false;
		var containsYou = false;
		
		for(var group : set) {
			for(var elt : group) {
				if(elt.property() == PropertyCategory.Win) 
					containsDefeat = true;
				else if(elt.property() == PropertyCategory.You) 
					containsYou = true;
				
				if(containsDefeat && containsYou)
					return true;
			}
		}
		return false;
	}
	
	public void applyRules(Board b) {
		Objects.requireNonNull(b);
		applyHotMelt(b);
		applySink(b);
	}
	
	public void updateProperty(Board b) {
		pu.updateProperty(b);
	}
	
	public int playerWin(Board b) {
		Objects.requireNonNull(b);
		if(verifyWin(b))
			return 1;
		if(VerifyDefeat(b))
			return -1;
		return 0;
	}
}
