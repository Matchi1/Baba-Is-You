package fr.umlv.rules;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

import fr.umlv.bloc.Bloc;
import fr.umlv.bloc.Direction;
import fr.umlv.bloc.Position;
import fr.umlv.board.Board;
import fr.umlv.element.ElementCategory;
import fr.umlv.property.PropertyCategory;

/**
 * todo
 *
 */
public class PropertyUpdater {
	private HashMap<ElementCategory, HashSet<PropertyCategory>> applyTo;
	private HashMap<ElementCategory, ElementCategory> change;
	
	public PropertyUpdater() {
		this.applyTo = new HashMap<>();
		this.change = new HashMap<>();
	}
	
	/**
	 * Add the specified key associated with its specified value
	 * @param key the specified key
	 * @param prop the specified value
	 */
	public void addElement(ElementCategory key, PropertyCategory prop) {
		Objects.requireNonNull(key);
		Objects.requireNonNull(prop);
		if(!applyTo.containsKey(key))
			applyTo.put(key, new HashSet<>());
		var set = applyTo.get(key);
		set.add(prop);
	}
	
	/**
	 * Add the specified key associated with its specified value
	 * @param eltToChange the specified key
	 * @param elt the specified value
	 */
	public void addElement(ElementCategory eltToChange, ElementCategory elt) {
		Objects.requireNonNull(eltToChange);
		Objects.requireNonNull(elt);
		if(!change.containsKey(eltToChange))
			change.put(eltToChange, elt);
		else if(eltToChange != change.get(eltToChange))
			change.put(eltToChange, elt);
	}
	
	/**
	 * Return if the an Operator is present in the specified board
	 * in the specified position
	 * @param b the specified board
	 * @param pos the specified position
	 * @return true if there is an Operator else false
	 */
	private boolean containsOperator(Board b, Position pos) {
		Objects.requireNonNull(b);
		Objects.requireNonNull(pos);
		var group = b.blocSet(pos);
		if(group == null)
			return false;
		for(var bloc : group) {
			if(bloc.element() == ElementCategory.Operator) 
				return true;
		}
		return false;
	}
	
	/**
	 * Return if the an Name is present in the specified board
	 * in the specified position
	 * @param b the specified board
	 * @param pos the specified position
	 * @return true if there is an Operator else false
	 */
	private ElementCategory containsName(Board b, Position pos) {
		Objects.requireNonNull(b);
		Objects.requireNonNull(pos);
		var group = b.blocSet(pos);
		if(group == null)
			return ElementCategory.None;
		for(var bloc : group) {
			if(bloc.element() == ElementCategory.Name)
				return bloc.represent();
		}
		return ElementCategory.None;
	}
	
	/**
	 * Verify if a rule needs to be applied on the specified board
	 * @param b the specified board
	 * @param pos the starting position to begin the read of the rule
	 * @param d the direction to read the rule
	 * @return
	 */
	private ElementCategory verifyRule(Board b, Position pos, Direction d) {
		Objects.requireNonNull(b);
		Objects.requireNonNull(pos);
		Objects.requireNonNull(d);
		Position cursor = new Position(pos);
		Position tmp = new Position(pos);
		
		// Verify if it is possible to apply a property
		tmp.translate(d, 2);
		if(b.outsideBoard(tmp))
			return ElementCategory.None;
		
		cursor.translate(d);
		if(!containsOperator(b, cursor))
			return ElementCategory.None;
		
		cursor.translate(d);
		return containsName(b, cursor);
	}
	
	/**
	 * Verify if the property have to be applied on the board
	 * @param b the specified board
	 * @param bloc the specified bloc element
	 */
	private void verifyProperty(Board b, Bloc bloc) {
		Objects.requireNonNull(b);
		Objects.requireNonNull(bloc);
		var elt = verifyRule(b, bloc.position(), Direction.West);
		if(elt != ElementCategory.None)
			addElement(elt, bloc.property());
		elt = verifyRule(b, bloc.position(), Direction.North);
		if(elt != ElementCategory.None)
			addElement(elt, bloc.property());
	}
	
	/**
	 * Verify if the name have to be applied on the board
	 * @param b the specified board
	 * @param bloc the specified bloc element
	 */
	private void verifyName(Board b, Bloc bloc) {
		Objects.requireNonNull(b);
		Objects.requireNonNull(bloc);
		var elt = verifyRule(b, bloc.position(), Direction.West);
		if(elt != ElementCategory.None)
			addElement(elt, bloc.represent());
		elt = verifyRule(b, bloc.position(), Direction.North);
		if(elt != ElementCategory.None)
			addElement(elt, bloc.represent());
	}
	
	/**
	 * Verify all the rules that have to be applied on the specified board
	 * @param b the specified board
	 */
	private void verify(Board b) {
		Objects.requireNonNull(b);
		var set = b.allElement();
		for(var group : set) {
			for(var bloc : group) {
				if(bloc.element() == ElementCategory.Property) {
					verifyProperty(b, bloc);
				} else if(bloc.element() == ElementCategory.Name) {
					verifyName(b, bloc);
				}
			}
		}
	}
	
	/**
	 * Apply the rule that changes the type of an element
	 * into the type contained in the specified bloc
	 * @param bloc the specified bloc
	 */
	private void applyName(Bloc bloc) {
		Objects.requireNonNull(bloc);
		change.entrySet().forEach(it -> {
			if(bloc.element() == it.getKey())
				bloc.element(it.getValue());
		});
	}
	
	/**
	 * Apply the rule that changes the properties applied to the element
	 * contained in the specified bloc
	 * @param bloc the specified bloc
	 */
	private void applyProperty(Bloc bloc) {
		Objects.requireNonNull(bloc);
		var propertySet = applyTo.get(bloc.element());
		if(propertySet != null) {
			propertySet.forEach(value -> bloc.putState(value));
		}
	}
	
	/**
	 * Apply all the rules present in the specified board 
	 * @param b the specified board
	 * @throws FileNotFoundException 
	 * @throws IOException
	 */
	private void apply(Board b) throws FileNotFoundException, IOException {
		Objects.requireNonNull(b);
		for(var group : b.allElement()) {
			for(var bloc : group) {
				if(bloc.element() != ElementCategory.Property && bloc.element() != ElementCategory.Name
					&& bloc.element() != ElementCategory.Operator) {
					bloc.clearState();
				}
				applyName(bloc);
				applyProperty(bloc);
				bloc.initImageIcon();
			}
		}
		reset();
	}
	
	/**
	 * Update the property in the specified board
	 * @param b the specified board
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void updateProperty(Board b) throws FileNotFoundException, IOException {
		Objects.requireNonNull(b);
		verify(b);
		apply(b);
	}
	
	/**
	 * Reset the rules that has to be applied
	 */
	public void reset() {
		this.applyTo = new HashMap<>();
		this.change = new HashMap<>();
	}
}
