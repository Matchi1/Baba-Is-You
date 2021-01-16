package fr.umlv.bloc;

import java.util.Objects;

/**
 * Class that contains a pair of value that represents a position
 */
public class Position {
	private int x, y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Position(Position pos) {
		this(pos.x(), pos.y());
	}
	
	/**
	 * Return the x attribute
	 * @return Return the x attribute
	 */
	public int x() {
		return x;
	}
	
	/**
	 * Set the x attribute
	 */
	public void x(int value) {
		x = value;
	}
	
	/**
	 * Return the y attribute
	 * @return Return the y attribute
	 */
	public int y() {
		return y;
	}
	
	/**
	 * Set the y attribute
	 */
	public void y(int value) {
		y = value;
	}
	
	/**
	 * translate the value of this position
	 * @param dx the specified x translation
	 * @param dy the specified y translation
	 */
	public void translate(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}
	
	/**
	 * translate the of this position according to the specified direction
	 * and the specified number of step
	 * @param d the specified direction
	 * @param step the specified step
	 */
	public void translate(Direction d, int step) {
		Objects.requireNonNull(d);
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
	
	/**
	 * translate the of this position according to the specified direction
	 * @param d the specified direction
	 */
	public void translate(Direction d) {
		Objects.requireNonNull(d);
		translate(d, 1);
	}
	
	/**
	 * Return a clone of this position object
	 * @return a clone of this position object
	 */
	public Position clone() {
		return new Position(this);
	}
	
	/**
	 * Compute the distance between this position and the specified position
	 * @param pos the specified position
	 * @return the distance between this position and the specified position
	 */
	public double distance(Position pos) {
		return Math.sqrt((this.x - pos.x) * (this.x - pos.x) + (this.y - pos.y) * (this.y - pos.y));
	}
	
	/**
	 * Returns a hash code value for this object
	 */
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
	
	/**
	 * Indicates whether some other object is "equal to" this one.
	 */
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
	
	/**
	 * Returns a String representation of this Position
	 * @return a String representation of this Position
	 */
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
