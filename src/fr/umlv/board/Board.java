package fr.umlv.board;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

import javax.swing.ImageIcon;

import fr.umlv.bloc.Bloc;
import fr.umlv.bloc.Position;
import fr.umlv.zen5.ApplicationContext;

/**
 * 
 *
 */
public class Board {
	private final Position length;
	private Shape rectangle;
	private HashMap<Position, ArrayList<Bloc>> board;
	private final int lengthBloc = 24;
	
	public Board(int lenX, int lenY) {
		if(lenX <= 0 || lenY <= 0)
			throw new IllegalArgumentException("The length of the the board have to be greater than 0.");
		this.length = new Position(lenX, lenY);
		this.board = new HashMap<>();
	}
	
	/**
	 * Creates a Board
	 * @param screenWidth width of the screen
	 * @param screenHeight height of the screen
	 * @param lenX number of bloc in the X axis
	 * @param lenY number of bloc in the Y axis
	 * @return a Board
	 */
	public static Board createBoard(int screenWidth, int screenHeight, int lenX, int lenY) {
		if(screenWidth <= 0 || screenHeight <= 0 || lenX <= 0 || lenY <= 0)
			throw new IllegalArgumentException("Lengths have to be greater than 0");
		Board b = new Board(lenX, lenY);
		b.initRectangle(screenWidth, screenHeight, lenX, lenY);
		return b;
	}
	
	public Collection<ArrayList<Bloc>> allElement(){
		return board.values();
	}
	
	/**
	 * Returns the ArrayList contained in the specified position
	 * in the board
	 * @param x position in the board in the X axis
	 * @param y position in the board in the Y axis
	 * @return an ArrayList
	 */
	public ArrayList<Bloc> blocSet(int x, int y){
		return blocSet(new Position(x, y));
	}
	
	/**
	 * Returns the ArrayList contained in the specified position
	 * in the board
	 * @param x position in the board in the X axis
	 * @param y position in the board in the Y axis
	 * @return an ArrayList
	 */
	public ArrayList<Bloc> blocSet(Position pos){
		Objects.requireNonNull(pos);
		if(outsideBoard(pos))
			throw new IllegalArgumentException("Position have to be between 0 and the length of the board");
		return board.get(pos);
	}
	
	public Set<Position> positionSet(){
		return board.keySet();
	}
	
	/**
	 * Append a bloc into the ArrayList in the specified position
	 * @param x position in the board in the X axis
	 * @param y position in the board in the Y axis
	 * @param bloc a Bloc
	 */
	public void addBloc(int x, int y, Bloc bloc) {
		Objects.requireNonNull(bloc);
		var pos = new Position(x, y);
		if(outsideBoard(pos))
			throw new IllegalArgumentException("Position have to be between 0 and the length of the board");
		addBloc(pos, bloc);
	}
	
	/**
	 * Append a bloc into the ArrayList in the specified position
	 * @param pos the specified position
	 * @param bloc a Bloc object 
	 */
	public void addBloc(Position pos, Bloc bloc) {
		Objects.requireNonNull(pos);
		Objects.requireNonNull(bloc);
		if(outsideBoard(pos))
			throw new IllegalArgumentException("Position have to be between 0 and the length of the board");
		var set = board.get(pos);
		if(set == null) {
			board.put(pos, new ArrayList<>());
			set = board.get(pos);
		}
		set.add(Objects.requireNonNull(bloc));
	}
	
	/**
	 * Returns the lengths of this object 
	 * @return the lengths of this object
	 */
	public Position length() {
		return length.clone();
	}
	
	/**
	 * Returns the length of a bloc in the board
	 * @return the length of a bloc in the board
	 */
	public int lengthBloc() {
		return lengthBloc;
	}
	
	/**
	 * Initialize the shape of this object
	 * this is necessary to represent this object in a window
	 * @param screenWidth width of the screen
	 * @param screenHeight height of the screen
	 * @param lenX length of the board in the X axis
	 * @param lenY length of the board in the Y axis
	 */
	private void initRectangle(int screenWidth, int screenHeight, int lenX, int lenY) {
		if(screenWidth <= 0 || screenHeight <= 0 || lenX <= 0 || lenY <= 0)
			throw new IllegalArgumentException("Lengths have to be greater than 0");
		int width = (screenWidth - lengthBloc * length.x()) / 2;
		int height = (screenHeight - lengthBloc * length.y()) / 2;
		rectangle = new Rectangle2D.Float(width, height , lenX * lengthBloc, lenY * lengthBloc);
	}
	
	/**
	 * Display this object in a window
	 * More specifically, it displays this object and every object
	 * contained in this object
	 * @param context 
	 * @param x position in the board in the X axis
	 * @param y position in the board in the Y axis
	 */
	public void draw(ApplicationContext context, int x, int y) {
		Objects.requireNonNull(context);
		context.renderFrame(graphics -> {
			
		    // hide the previous rectangle
		    graphics.setColor(new Color(0, 19, 49));
		    graphics.fill(rectangle);
		    
		    for(var pos : board.keySet()) {
		    	for(var bloc : board.get(pos)) {
		    		int xBloc = x + lengthBloc * pos.x();
			    	int yBloc = y + lengthBloc * pos.y();
		    		ImageIcon icon = bloc.image();
	    			ImageObserver imgO = icon.getImageObserver();
	    			imgO.imageUpdate(icon.getImage(), ImageObserver.FRAMEBITS, xBloc, yBloc, lengthBloc, lengthBloc);
	    			graphics.drawImage(icon.getImage(), xBloc, yBloc, imgO);
		    	}
		    }
	  });
	}
	
	/**
	 * Refresh all of the element contained in the board field
	 */
	public void refresh() {
		ArrayList<Bloc> lstElement = new ArrayList<>();
		for(var it = board.keySet().iterator(); it.hasNext();) {
			var currentPos = it.next();
			for(var it1 = board.get(currentPos).iterator(); it1.hasNext();) {
				var bloc = it1.next();
				var nextPos = new Position(bloc.position());

				// Verify if the next position of the bloc has been modified
				if(!currentPos.equals(nextPos)) {
					lstElement.add(bloc);
					it1.remove();
				}
			}
		}
		for(var elt : lstElement) 
			addBloc(elt.position(), elt);
		removeEmptyPosition();
	}
	
	/**
	 * Remove the specified bloc element from this board
	 * @param bloc the specified bloc 
	 */
	public void remove(Bloc bloc) {
		Objects.requireNonNull(bloc);
		for(var group : board.values()) {
			if(group.contains(bloc))
				group.remove(bloc);
		}
	}
	
	/**
	 * Remove the specified position from this board
	 * @param pos the specified position
	 */
	public void removePos(Position pos) {
		Objects.requireNonNull(pos);
		var it = board.get(pos).iterator();
		while(it.hasNext()) {
			it.next();
			it.remove();
		}
	}
	
	/**
	 * Remove all the empty position from this board
	 */
	public void removeEmptyPosition() {
		var garbage = new ArrayList<>();
		for(var it : board.entrySet()) {
			if(it.getValue().isEmpty())
				garbage.add(it.getKey());
		}
		for(var elt : garbage)
			board.remove(elt);
	}
	
	/**
	 * Verify if the specified position indicates a position outside the board
	 * @param x position of all the element in the X axis 
	 * @param y position of all the element in the Y axis 
	 * @return false if the specified position is outside the length of the board else true
	 */
	public boolean outsideBoard(int x, int y) {
		if(x < 0 || length().x() <= x || y < 0 || length().y() <= y)
			return true;
		return false;
	}
	
	/**
	 * Verify if the specified position indicates a position outside the board
	 * @param pos a specified position
	 * @return false if the specified position is outside the length of the board else true
	 */
	public boolean outsideBoard(Position pos) {
		Objects.requireNonNull(pos);
		return outsideBoard(pos.x(), pos.y());
	}
	
	/**
	 * Display the String representation of this board
	 */
	public void displayBoard() {
		for(var pos : board.keySet()) {
			System.out.print(pos + " : ");
			for(var bloc : board.get(pos)) {
				System.out.print(bloc + ", ");
			}
			System.out.println();
		}
	}
}
