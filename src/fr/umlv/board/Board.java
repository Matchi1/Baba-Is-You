package fr.umlv.board;

import fr.umlv.element.Bloc;

public class Board {
	private final Position taille;
	private Bloc[][] board;
	
	public Board(int len_x, int len_y) {
		if(len_x < 0 || len_y < 0)
			throw new IllegalArgumentException("The length of the the board have to be positive.");
		this.taille = new Position(len_x, len_y);
		this.board = new Bloc[len_x][len_y];
	}
	
	public Position taille() {
		return taille.clone();
	}
	
	public void refresh() {
		int next_x, next_y;
		for(int i = 0; i < taille.x(); i++) {
			for(int j = 0; i < taille.y(); j++) {
				next_x = board[i][j].position().x();
				next_y = board[i][j].position().y();
				if(next_x != i || next_y != j) {
					board[next_x][next_y] = board[i][j];
				}
			}
		}
	}
}
