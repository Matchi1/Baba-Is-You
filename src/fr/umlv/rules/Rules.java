package fr.umlv.rules;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

import fr.umlv.bloc.Bloc;
import fr.umlv.bloc.Direction;
import fr.umlv.bloc.Position;
import fr.umlv.board.Board;
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
		Objects.requireNonNull(b);
		Objects.requireNonNull(pos);
		var nextPos = new Position(pos);
		if(b.outsideBoard(pos))
			return 0;
		var lst = b.blocSet(pos);
		if(lst == null)
			return 1;
		for(var bloc : lst) {
			if(bloc.containState(PropertyCategory.Stop))
				return 0;
			if(bloc.containState(PropertyCategory.Push)) {
				if(!bloc.position().equals(pos))
					return 1;
				nextPos.translate(d);
				if(pushBloc(b, nextPos, d) == 0)
					return 0;
				bloc.translate(d);
			}
		}
		return 1;
	}
	
	/**
	 * Verify if the next position of an element in the board
	 * is legal or not
	 * @param pos the position of an element
	 * @return true if it is legal else false
	 */
	public boolean isLegal(Board b, Position pos) {
		Objects.requireNonNull(pos);
		if(b.outsideBoard(pos))
			return false;
		var currentBloc = b.blocSet(pos);
		if(currentBloc != null) {
			for(var bloc : currentBloc) {
				if(bloc.containState(PropertyCategory.Stop)) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Apply the move action on the element You 
	 * in the specified direction
	 * @param b the specified board
	 * @param d the specified direction
	 */
	public void applyMove(Board b, Direction d) {
		Objects.requireNonNull(b);
		Objects.requireNonNull(d);
		for(var group : b.allElement()) {
			for(var bloc : group) {
				var pos = new Position(bloc.position());
				pos.translate(d);
				if(bloc.containState(PropertyCategory.You)) {
					if(isLegal(b, pos) && pushBloc(b, pos, d) == 1)
						bloc.translate(d);
				}
			}
		}
	}
	
	/**
	 * Apply the Hot to Melt action in the specified board
	 * @param b the specified board
	 */
	private void applyHotMelt(Board b, Position pos) {
		Objects.requireNonNull(b);
		Objects.requireNonNull(pos);
		var containsHot = false;
		Bloc elementMelt = null;
		var lst = b.blocSet(pos);
		if(lst != null) {
			for(var bloc : lst) {
				if(bloc.containState(PropertyCategory.Hot)) 
					containsHot = true;
					else if(bloc.containState(PropertyCategory.Melt)) 
					elementMelt = bloc;		
			}
			if(containsHot && elementMelt != null) {
				b.remove(elementMelt);
			}
		}
	}
	
	/**
	 * Apply the Sink action in the specified board
	 * @param b the specified board
	 */
	private void applySink(Board b, Position pos) {
		Objects.requireNonNull(b);
		Objects.requireNonNull(pos);
		var containsSink = false;
		var containsOther = false;
		var lst = b.blocSet(pos);
		if(lst != null) {
			for(var bloc : b.blocSet(pos)) {
				if(bloc.containState(PropertyCategory.Sink))
					containsSink = true;
				else
					containsOther = true;
			}
			if(containsSink && containsOther)
				b.removePos(pos);
		}
	}
	
	/**
	 * Apply the Defeat action in the specified board
	 * @param b the specified board
	 */
	public void applyDefeat(Board b, Position pos) {
		Objects.requireNonNull(b);
		Objects.requireNonNull(pos);
		var containsDefeat = false;
		Bloc eltYou = null;
		var lst = b.blocSet(pos);
		if(lst != null) {
			for(var elt : b.blocSet(pos)) {
				if(elt.containState(PropertyCategory.Defeat))
					containsDefeat = true;
				else if(elt.containState(PropertyCategory.You)) 
					eltYou = elt;
			}
			if(containsDefeat && eltYou != null)
				b.remove(eltYou);
		}
	}
	
	/**
	 * Find the nearest Element from the specified position that contains the Tunnel property
	 * in the specified board 
	 * @param b the specified board
	 * @param origin the specified Position
	 * @return the position of the nearest tunnel
	 */
	public Position findTunnel(Board b, Position origin) {
		Position pos = null;
		for(var group : b.allElement()) {
			for(var elt : group) {
				if(elt.containState(PropertyCategory.Tunnel) && !elt.position().equals(origin)) {
					if(pos == null)
						pos = elt.position();
					else {
						if(origin.distance(elt.position()) < origin.distance(pos))
							pos = elt.position();
					}		
				}
			}
		}
		return pos;
	}
	
	/**
	 * Apply the Tunnel action in the specified board
	 * @param b the specified board
	 */
	public void applyTunnel(Board b, Position pos) {
		Objects.requireNonNull(b);
		Objects.requireNonNull(pos);
		var containsTunnel = false;
		Bloc eltYou = null;
		var lst = b.blocSet(pos);
		if(lst != null) {
			for(var elt : b.blocSet(pos)) {
				if(elt.containState(PropertyCategory.Tunnel))
					containsTunnel = true;
				else if(elt.containState(PropertyCategory.You)) 
					eltYou = elt;
			}
			if(containsTunnel && eltYou != null) {
				Position nextPos = findTunnel(b, pos);
				if(nextPos != null)
					eltYou.position(nextPos.x(), nextPos.y());
			}
		}
	}
	
	/**
	 * Apply all the rules in the specified board
	 * @param b the specified board
	 */
	public void applyRules(Board b) {
		Objects.requireNonNull(b);
		for(var pos : b.positionSet()) {
			applyHotMelt(b, pos);
			applySink(b, pos);
			applyDefeat(b, pos);
			applyTunnel(b, pos);
		}
	}
	
	/**
	 * Update all the rules applied in the specified board
	 * @param b the specified board
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void updateProperty(Board b) throws FileNotFoundException, IOException {
		pu.updateProperty(b);
	}
	
	/**
	 * Verify if the user have won the game in the specified board
	 * @param b the specified board
	 * @return true if the user have won else false
	 */
	public boolean verifyWin(Board b, Position pos) {
		Objects.requireNonNull(b);
		Objects.requireNonNull(pos);

		var containsWin = false;
		var containsYou = false;
		var lst = b.blocSet(pos);
		if(lst != null) {
			for(var elt : lst) {
				if(elt.containState(PropertyCategory.Win)) 
					containsWin = true;
				else if(elt.containState(PropertyCategory.You)) 
					containsYou = true;
			}
			if(containsWin && containsYou)
				return true;
		}
		return false;
	}
	
	/**
	 * Verify if the user have lost the game in the specified board
	 * @param b the specified board
	 * @return true if the user have lost else false
	 */
	public boolean verifyDefeat(Board b, Position pos) {
		Objects.requireNonNull(b);
		Objects.requireNonNull(pos);
		for(var group : b.allElement()) {
			for(var elt : group) {
				if(elt.containState(PropertyCategory.You))
					return false;
			}
		}
		return true;
	}

	/**
	 * Verify if the user have won or lost the game in the specified board
	 * @param b the specified board
	 * @return 1 if the user have won, -1 if the player have lost else 0
	 */
	public int playerWin(Board b) {
		Objects.requireNonNull(b);
		var isAlive = false;
		for(var pos : b.positionSet()) {
			if(verifyWin(b, pos))
				return 1;
			if(!verifyDefeat(b, pos))
				isAlive = true;
		}
		if(!isAlive)
			return -1; 
		return 0;
	}
}
