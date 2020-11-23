package fr.umlv.board;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import fr.umlv.element.Bloc;
import fr.umlv.zen5.ApplicationContext;

public class Board {
	private final Position length;
	private Shape rectangle;
	private ArrayList<Bloc>[][] board;
	private final int length_bloc = 40; 
	
	public Board(int lenX, int lenY) {
		if(lenX < 0 || lenY < 0)
			throw new IllegalArgumentException("The length of the the board have to be positive.");
		this.length = new Position(lenX, lenY);
		this.board = new ArrayList[lenX][lenY];
	}
	
	public static Board createBoard(int screenWidth, int screenHeight, int lenX, int lenY) {
		Board b = new Board(lenX, lenY);
		b.initRectangle(screenWidth, screenHeight, lenX, lenY);
		b.initBoard();
		return b;
	}
	
	public void initBoard() {
		for(int i = 0; i < length.x(); i++) {
			for(int j = 0; j < length.y(); j++) {
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
	
	public Position length() {
		return length.clone();
	}
	
	public int length_bloc() {
		return length_bloc;
	}
	
	public void initRectangle(int screenWidth, int screenHeight, int len_x, int len_y) {
		int width = (screenWidth - length_bloc * length.x()) / 2;
		int height = (screenHeight - length_bloc * length.y()) / 2;
		rectangle = new Rectangle2D.Float(width, height , len_x * length_bloc, len_y * length_bloc);
	}
	
	public void draw(ApplicationContext context, float x, float y) {
		context.renderFrame(graphics -> {
			
		    // hide the previous rectangle
		    graphics.setColor(new Color(0, 19, 49));
		    graphics.fill(rectangle);
		    
		    for(int i = 0; i < length.x(); i++) {
		    	for(int j = 0; j < length.y(); j++) {
		    		for(var bloc : board[i][j]) {
		    			float xBloc = x + length_bloc * i;
		    			float yBloc = y + length_bloc * j;
		    			graphics.setColor(bloc.color());
		    			bloc.shape((int)xBloc, (int)yBloc, length_bloc);
		    			graphics.fill(bloc.shape());
		    		}
		    	}
		    }
	  });
	}
	
	private int isLegal(ArrayList<Bloc> lst) {
		for(var bloc : lst) {
			if(bloc.stop())
				return -1;
			else if(bloc.push())
				return 0;
		}
		return 1;
	}
	
	public boolean isLegal(Position nextPos, Direction d) {
		if(nextPos.x() < 0 || length().x() <= nextPos.x() || nextPos.y() < 0 || length().y() <= nextPos.y())
			return false;
		ArrayList<Bloc> currentLst = board[nextPos.x()][nextPos.y()];
		if(currentLst.isEmpty()) 					// Test if there is no bloc in this position
			return true;
		int returnValue = isLegal(currentLst);
		if(returnValue == -1) 						// the bloc can't be moved in this location
			return false;
		else if(returnValue == 0) {					// there is a pushable bloc in this location
			Position newPos = new Position(nextPos);
			newPos.translate(d);
			if(isLegal(newPos, d)) {				// Verify if we can push the next bloc
				for(var bloc : currentLst) {
					if(bloc.push())
						bloc.translate(d);
				}
			} else {
				return false;
			}
		}
		return true;
	}
	
	public boolean isLegal(ArrayList<Position> lstPos, Direction d) {
		for(var pos : lstPos) {
			if(!isLegal(pos, d))
				return false;
		}
		return true;
	}
	
	private void refresh(int x, int y) {
		ArrayList<Bloc> tmp = new ArrayList<Bloc>();
		for(var bloc : board[x][y]) {
			Position nextPos = new Position(bloc.position());
			// Verify if the next position of the bloc has been set
			if(nextPos.x() != x || nextPos.y() != y) {
				board[nextPos.x()][nextPos.y()].add(bloc);
				tmp.add(bloc);
			}
		}
		// Remove all the old blocs from the their previous location
		for(var bloc : tmp) {
			removeBloc(x, y, bloc);
		}
	}
	
	public void refresh() {
		for(int i = 0; i < length.x(); i++) {
			for(int j = 0; j < length.y(); j++) {
				refresh(i, j);
			}
		}
	}
}
