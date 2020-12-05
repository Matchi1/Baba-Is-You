package fr.umlv.board;

import fr.umlv.zen5.KeyboardKey;

public enum Direction {
	North, South, West, East;
	
	public Direction contrary() {
		switch(this) {
		case North: return South;
		case South: return North;
		case East: return West;
		default: return East;
		}
	}
	
	public static Direction convertKeyboardKeyToDirection(KeyboardKey k) {
		switch(k) {
		case UP: return North;
		case DOWN: return South;
		case LEFT: return West;
		default: return East;
		}
	}
	
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
