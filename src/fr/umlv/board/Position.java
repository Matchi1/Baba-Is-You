package fr.umlv.board;

import java.util.Objects;

public class Position {
	private int x, y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Position(Position pos) {
		this(pos.x(), pos.y());
	}
	
	public int x() {
		return x;
	}
	
	public void x(int value) {
		x = value;
	}
	
	public int y() {
		return y;
	}
	
	public void y(int value) {
		y = value;
	}
	
	public void translate(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}
	
	public void translate(Direction d, int step) {
		switch(d) {
		case North:
			translate(0, -step); break;
		case South:
			translate(0, step); break;
		case East:
			translate(step, 0); break;
		default:
			translate(-step, 0); break;
		}
	}
	
	public void translate(Direction d) {
		translate(d, 1);
	}
	
	public Position clone() {
		return new Position(this);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		if(obj.getClass() != getClass())
			return false;
		Position pos = (Position)obj;
		if(pos.x() == x && pos.y() == y)
			return true;
		return false;
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
