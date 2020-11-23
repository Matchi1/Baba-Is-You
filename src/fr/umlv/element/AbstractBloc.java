package fr.umlv.element;

import java.awt.Color;

import fr.umlv.board.Position;

abstract class AbstractBloc implements Bloc{
	private Position pos;
	private boolean[] actions;
	private Color color;
	private final Element elt;
	
	/*
	 * actions[0] : push
	 * actions[1] : win
	 * actions[2] : defeat
	 * actions[3] : sink
	 * actions[4] : stop
	 * actions[5] : melt
	 */
	
	AbstractBloc(int x, int y, Color color, Element elt) {
		this.pos = new Position(x, y);
		this.actions = new boolean[nb_actions];
		this.color = color;
		this.elt = elt;
		init_actions();
	}
	
	private void init_actions() {
		for(int i = 0; i < nb_actions; i++) {
			actions[i] = false;
		}
	}
	
	public Element element() {
		return elt;
	}
	
	public Position position() {
		return pos.clone();
	}
	
	public void position(int x, int y) {
		pos.x(x);
		pos.y(y);
	}
	
	public Color getColor() {
		return color;
	}
	
	/**
	 * Show if the Bloc can be pushed by other Bloc.
	 * @return true if it can be pushed false otherwise.
	 */
	public boolean push() {
		return actions[0];
	}
	
	public void push(boolean state) {
		actions[0] = state;
	}
	
	/**
	 * Show if the Bloc is a condition of win for the player.
	 * @return true if it can make the player win the game false otherwise.
	 */
	public boolean win() {
		return actions[1];
	}
	
	public void win(boolean state) {
		actions[1] = state;
	}
	
	/**
	 * Show if the Bloc is a condition of defeat for the player.
	 * @return true if it can make the player lose the game false otherwise.
	 */
	public boolean defeat() {
		return actions[2];
	}
	
	public void defeat(boolean state) {
		actions[2] = state;
	}
	
	/**
	 * Show if the Bloc can sink other Bloc.
	 * @return true if it can sink false otherwise.
	 */
	public boolean sink() {
		return actions[3];
	}
	
	public void sink(boolean state) {
		actions[3] = state;
	}
	
	/**
	 * Show if the Bloc can stop other Bloc.
	 * @return true if it can stop false otherwise.
	 */
	public boolean stop() {
		return actions[4];
	}
	
	public void stop(boolean state) {
		actions[4] = state;
	}
	
	/**
	 * Show if the Bloc can melt other Bloc.
	 * @return true if it can melt false otherwise.
	 */
	public boolean melt() {
		return actions[5];
	}
	
	public void melt(boolean state) {
		actions[5] = state;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(elt);
		str.append(pos);
		str.append("\n");
		str.append("push : ");
		str.append(actions[0]);
		str.append(" win : ");
		str.append(actions[1]);
		str.append(" defeat : ");
		str.append(actions[2]);
		str.append(" sink : ");
		str.append(actions[3]);
		str.append(" stop : ");
		str.append(actions[4]);
		str.append(" melt : ");
		str.append(actions[5]);
		str.append("\n");
		str.append(color);
		return str.toString();
	}
}
