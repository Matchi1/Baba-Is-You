package fr.umlv.board;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import fr.umlv.bloc.Bloc;
import fr.umlv.group.ControlledBlocs;
import fr.umlv.property.PropertyCategory;
import fr.umlv.zen5.ApplicationContext;

public class Board {
	private final Position length;
	private Shape rectangle;
	private ArrayList<Bloc>[][] board;
	private final int lengthBloc = 22; 
	
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
	
	public void addBloc(Position pos, Bloc bloc) {
		board[pos.x()][pos.y()].add(bloc);
	}
	
	public void removeBloc(int x, int y, Bloc bloc) {
		board[x][y].remove(bloc);
	}
	
	public Position length() {
		return length.clone();
	}
	
	public int lengthBloc() {
		return lengthBloc;
	}
	
	public void initRectangle(int screenWidth, int screenHeight, int len_x, int len_y) {
		int width = (screenWidth - lengthBloc * length.x()) / 2;
		int height = (screenHeight - lengthBloc * length.y()) / 2;
		rectangle = new Rectangle2D.Float(width, height , len_x * lengthBloc, len_y * lengthBloc);
	}
	
	public void draw(ApplicationContext context, int x, int y) {
		context.renderFrame(graphics -> {
			
		    // hide the previous rectangle
		    graphics.setColor(new Color(0, 19, 49));
		    graphics.fill(rectangle);
		    
		    for(int i = 0; i < length.x(); i++) {
		    	int xBloc = x + lengthBloc * i;
		    	for(int j = 0; j < length.y(); j++) {
		    		int yBloc = y + lengthBloc * j;
		    		for(var bloc : board[i][j]) {	
		    			ImageIcon icon = bloc.image();
		    			ImageObserver imgO = icon.getImageObserver();
		    			imgO.imageUpdate(icon.getImage(), ImageObserver.FRAMEBITS, xBloc, yBloc, lengthBloc, lengthBloc);
		    			graphics.drawImage(icon.getImage(), xBloc, yBloc, imgO);
		    		}
		    	}
		    }
	  });
	}
	
	public boolean isLegal(Position pos) {
		if(pos.x() < 0 || length().x() <= pos.x() || pos.y() < 0 || length().y() <= pos.y())
			return false;
		ArrayList<Bloc> currentBloc = board[pos.x()][pos.y()];
		for(var bloc : currentBloc) {
			if(bloc.containState(PropertyCategory.Stop)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean blocsPositionIsLegal() {
		for(int i = 0; i < length.x(); i++) {
			for(int j = 0; j < length.y(); j++) {
				for(var bloc : board[i][j]) {
					if(bloc.position().x() != i || bloc.position().y() != j) {
						if(!isLegal(bloc.position()))
							return false;
					}
				}
			}
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
	
	private int pushBloc(Position pos, Direction d) {
		if(pos.x() < 0 || length().x() <= pos.x() || pos.y() < 0 || length().y() <= pos.y())
			return 0;
		ArrayList<Bloc> lst = board[pos.x()][pos.y()];
		for(var bloc : lst) {
			if(bloc.containState(PropertyCategory.Push)) {
				bloc.translate(d);
				return pushBloc(bloc.position(), d);
			}
		}
		return 1;
	}
	
	public int moveControlledBlocs(ControlledBlocs cb, Direction d) {
		cb.translate(d);
		for(var bloc : cb.group()) {
			if(pushBloc(bloc.position(), d) == 0) {
				resetBoard();
				return 0;
			}
		}
		return 1;
	}
	
	public void resetBoard() {
		for(int i = 0; i < length.x(); i++) {
			for(int j = 0; j < length.y(); j++) {
				for(var bloc : board[i][j]) {
					if(bloc.position().x() != i || bloc.position().y() != j)
						bloc.position(i, j);
				}
			}
		}
	}
}
