package fr.umlv.element;

import java.awt.Color;
import java.awt.Shape;

import fr.umlv.board.Direction;
import fr.umlv.board.Position;

public interface Bloc {
	public final int nb_actions = 6;
	
	public Element element();
	
	public void translate(Direction d);
	
	public Position position();
	public void position(int x, int y);
	
	public boolean push();
	public void push(boolean state);
	
	public boolean win();
	public void win(boolean state);
	
	public boolean defeat();
	public void defeat(boolean state);
	
	public boolean sink();
	public void sink(boolean state);
	
	public boolean stop();
	public void stop(boolean state);
	
	public boolean melt();
	public void melt(boolean state);
	
	public Shape shape();
	public void shape(int x, int y, int len);
	
	public Color color();
	public void color(Color c);
	
	public String toString();
}
