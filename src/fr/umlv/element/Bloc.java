package fr.umlv.element;

import fr.umlv.board.Position;

public interface Bloc {
	public final int nb_actions = 6;
	
	public Position position();
	public void position(int x, int t);
	
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
}
