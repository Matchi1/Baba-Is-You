package fr.umlv.rules;

import java.util.HashMap;
import java.util.HashSet;

import fr.umlv.board.Board;
import fr.umlv.board.Direction;
import fr.umlv.board.Position;
import fr.umlv.element.ElementCategory;
import fr.umlv.name.IsName;
import fr.umlv.property.PropertyCategory;

public class PropertyUpdater {
	private HashMap<ElementCategory, HashSet<PropertyCategory>> applyTo;
	
	public PropertyUpdater() {
		this.applyTo = new HashMap<>();
	}
	
	public void addElement(ElementCategory key, PropertyCategory prop) {
		if(!applyTo.containsKey(key))
			applyTo.put(key, new HashSet<>());
		var set = applyTo.get(key);
		set.add(prop);
	}
	
	private boolean containsOperator(Board b, Position pos) {
		var group = b.blocSet(pos);
		if(group == null)
			return false;
		for(var bloc : group) {
			if(bloc.element() == ElementCategory.Operator) {
				return true;
			}
		}
		return false;
	}
	
	private ElementCategory containsName(Board b, Position pos) {
		var group = b.blocSet(pos);
		if(group == null)
			return ElementCategory.None;
		for(var bloc : group) {
			if(bloc.element() == ElementCategory.Name) {
				return bloc.represent();
			}
		}
		return ElementCategory.None;
	}
	
	private ElementCategory verifyProperty(Board b, Position pos, Direction d) {
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
	
	public void verifyProperty(Board b) {
		var set = b.allElement();
		for(var group : set) {
			for(var bloc : group) {
				if(bloc.element() == ElementCategory.Property) {
					var elt = verifyProperty(b, bloc.position(), Direction.West);
					if(elt != ElementCategory.None)
						addElement(elt, bloc.property());
				}
			}
		}
	}
	
	public void applyProperty(Board b) {
		var set = b.allElement();
		for(var group : set) {
			for(var bloc : group) {
				if(bloc.element() != ElementCategory.Property
					&& bloc.element() != ElementCategory.Name
					&& bloc.element() != ElementCategory.Operator) {
					bloc.clearState();
				}
					
				var propertySet = applyTo.get(bloc.element());
				if(propertySet != null) {
					for(var prop : propertySet) {
						bloc.putState(prop);
					}
				}
			}
		}
		reset();
	}
	
	public void updateProperty(Board b) {
		verifyProperty(b);
		applyProperty(b);
	}
	
	public void reset() {
		this.applyTo = new HashMap<>();
	}
}
