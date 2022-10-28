package fr.umlv.main;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

import fr.umlv.bloc.Direction;
import fr.umlv.board.Board;
import fr.umlv.file.OpenWorld;
import fr.umlv.options.Options;
import fr.umlv.rules.Rules;
import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.Event.Action;
import fr.umlv.zen5.KeyboardKey;
import fr.umlv.zen5.ScreenInfo;

/**
 * Main Class that will execute the game
 *
 */
public class Game {

	/**
	 * Initialize the background of the window according to the specified context, 
	 * width and height
	 * @param context the specified context
	 * @param width the specified width
	 * @param height the specified height
	 */
	public static void initScreen(ApplicationContext context, int width, int height) {
		context.renderFrame(graphics -> {
			graphics.setColor(Color.BLACK);
			graphics.fill(new Rectangle2D.Float(0, 0, width, height));
		});
	}
	
	/**
	 * Verify if a key has been pressed and do the following action
	 * @param context the specified context
	 * @param b the Board
	 * @param rules the Rules
	 * @param event the event type
	 * @param posX the position of the board in the X axis
	 * @param posY the position of the board in the Y axis
	 * @return 1 if the user wants to continue the level else 0
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static int pressedKey(ApplicationContext context, Board b, Rules rules, Event event, int posX, int posY) throws FileNotFoundException, IOException {
		Action action = event.getAction();
		if (action == Action.KEY_PRESSED) {
			if(event.getKey() == KeyboardKey.Q) { // Quit the game
				return 0;
				
			// Move the element containing the You properties
			} else if(event.getKey() == KeyboardKey.UP || event.getKey() == KeyboardKey.DOWN
				  || event.getKey() == KeyboardKey.RIGHT || event.getKey() == KeyboardKey.LEFT) {
				Direction d = Direction.convertKeyboardKeyToDirection(event.getKey());
				rules.applyMove(b, d);		// Move controllable element
				b.refresh();
				rules.updateProperty(b); 	// update the property of all elements on the board
				rules.applyRules(b);		// Apply the rules on the board
				b.refresh();
			}
			b.draw(context, posX, posY);
		}
		return 1;
	}
	
	/**
	 * Runs a level according to the specified context
	 * @param context the specified context
	 * @param screenWidth the width of the screen
	 * @param screenHeight the height of the screen
	 * @param path the path to the level file
	 * @throws IOException
	 */
	public static void game(ApplicationContext context, int screenWidth, int screenHeight, String path) throws IOException {
		Objects.requireNonNull(context);
		Objects.requireNonNull(path);
		Board b = OpenWorld.createBoard(screenWidth , screenHeight, path);
		Rules rules = new Rules();
		int posX = (int) ((screenWidth - b.lengthBloc() * b.length().x()) / 2);
		int posY = (int) ((screenHeight - b.lengthBloc() * b.length().y()) / 2);
	  
		initScreen(context, screenWidth, screenHeight);
		rules.updateProperty(b);		// update the property of all elements on the board
		b.draw(context, posX, posY);
		for(;;) {
			Event event = context.pollOrWaitEvent(10);
			if (event == null) continue; // no event
			// Verify if the game is finished
			if(pressedKey(context, b, rules, event, posX, posY) == 0 || rules.playerWin(b) != 0)
				return;
		}
	}
	
	/**
	 * Runs the game
	 * @param args the arguments from the run command of the program
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Application.run(Color.BLACK, context -> {
			ScreenInfo screenInfo = context.getScreenInfo();
			int width = (int) screenInfo.getWidth();
			int height = (int) screenInfo.getHeight();
			if(width < 0 || height < 0)
				context.exit(0);
			try {
				var lstPath = Options.option(args);
				for(int i = 0; i < lstPath.size(); i++) {
					game(context, width, height, "worlds/perso_world.txt");
				}
			} catch (IOException e) {
				System.out.println("error : file not found");
				e.printStackTrace();
			}
			context.exit(0);
		});
	}
}
