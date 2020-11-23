package fr.umlv.board;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import fr.umlv.element.Bloc;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.ScreenInfo;

public class Board {
	private final Position taille;
	private Rectangle2D.Float rectangle;
	private ArrayList<Bloc>[][] board;
	private final int length_bloc = 40; 
	
	public Board(int lenX, int lenY) {
		if(lenX < 0 || lenY < 0)
			throw new IllegalArgumentException("The length of the the board have to be positive.");
		this.taille = new Position(lenX, lenY);
		this.board = new ArrayList[lenX][lenY];
		initRectangle(lenX, lenY);
		initBoard();
	}
	
	public void initBoard() {
		for(int i = 0; i < taille.x(); i++) {
			for(int j = 0; j < taille.y(); j++) {
				board[i][j] = new ArrayList<Bloc>();
			}	
		}
	}
	
	public ArrayList<Bloc> arrBloc(int x, int y){
		return board[x][y];
	}
	
	public void addBloc(int x, int y, Bloc bloc) {
		board[x][y].add(bloc);
	}
	
	public void removeBloc(int x, int y, Bloc bloc) {
		board[x][y].remove(bloc);
	}
	
	public Position taille() {
		return taille.clone();
	}
	
	public int length_bloc() {
		return length_bloc;
	}
	
	public void initRectangle(int len_x, int len_y) {
		rectangle = new Rectangle2D.Float(0 - len_x/2, 0 - len_y/2 , len_x, len_y);
	}
	
	public void draw(ApplicationContext context, float x, float y) {
		context.renderFrame(graphics -> {
		    // hide the previous rectangle
		    graphics.setColor(Color.BLACK);
		    graphics.fill(rectangle);
		    
		    for(int i = 0; i < taille.x(); i++) {
		    	for(int j = 0; j < taille.y(); j++) {
		    		for(var bloc : board[i][j]) {
		    			float xBloc = x + length_bloc * i;
		    			float yBloc = y + length_bloc * j;
		    			bloc.draw(context, xBloc, yBloc, length_bloc);
		    		}
		    	}
		    }
	  });
	}
	
	public boolean isLegal(ArrayList<Bloc> lst) {
		for(var bloc : lst) {
			if(!isLegal(bloc.position()))
				return false;
		}
		return true;
	}
	
	public boolean isLegal(Position newPos) {
		if(newPos.x() < 0 || taille().x() <= newPos.x() || newPos.y() < 0 || taille().y() <= newPos.y())
			return false;
		return true;
	}
	
	private void refresh(int x, int y) {
		ArrayList<Bloc> tmp = new ArrayList<Bloc>();
		for(var bloc : board[x][y]) {
			Position nextPos = new Position(bloc.position());
			System.out.println("board " + x + " " + y);
			System.out.println(bloc);
			if(nextPos.x() != x || nextPos.y() != y) {
				board[nextPos.x()][nextPos.y()].add(bloc);
				tmp.add(bloc);
			}
		}
		for(var bloc : tmp) {
			board[x][y].remove(bloc);
		}
	}
	
	public void refresh() {
		for(int i = 0; i < taille.x(); i++) {
			for(int j = 0; j < taille.y(); j++) {
				refresh(i, j);
			}
		}
	}
}
