package fr.umlv.board;

public enum Direction {
	North, South, West, East;
	
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
