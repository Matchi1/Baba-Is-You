package fr.umlv.element;

import fr.umlv.board.Position;
import fr.umlv.zen5.ApplicationContext;

public interface Bloc {
	public final int nb_actions = 6;
	
	public void draw(ApplicationContext context, float x, float y, int len);
	
	public Element element();
	
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
	
	public String toString();
}
