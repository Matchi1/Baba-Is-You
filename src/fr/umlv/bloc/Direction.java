package fr.umlv.bloc;

import fr.umlv.zen5.KeyboardKey;

/**
 * Enum that represents a direction
 */
public enum Direction {
	North, South, West, East;
	
	/**
	 * Return the opposite direction of this object
	 * @return Return the opposite direction of this object
	 */
	public Direction contrary() {
		switch(this) {
		case North: return South;
		case South: return North;
		case East: return West;
		default: return East;
		}
	}
	
	/**
	 * Convert the directional key into a Direction
	 * @param k the directional key
	 * @return the Direction representing the key
	 */
	public static Direction convertKeyboardKeyToDirection(KeyboardKey k) {
		switch(k) {
		case UP: return North;
		case DOWN: return South;
		case LEFT: return West;
		default: return East;
		}
	}
	
	/**
	 * Returns a String representation of this Direction
	 */
	@Override
	public String toString() {
		switch(this) {
		case North:
			return "Direction: North\n";
		case South:
			return "Direction: South\n";
		case East:
			return "Direction: East\n";
		default:
			return "Direction: West\n";
		}
	}
}
