package fr.umlv.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import fr.umlv.bloc.Bloc;
import fr.umlv.board.Board;
import fr.umlv.element.Element;
import fr.umlv.name.Name;
import fr.umlv.operator.Operator;
import fr.umlv.property.Property;

public class OpenWorld {
	/**
	 * Create an item parser
	 * @return a map of item parser
	 */
	public static Map<String, Function<String[], Bloc>> createItemParserMap() {
		 Map<String, Function<String[], Bloc>> itemParser;
			itemParser = Map.of(
	            FileCategory.ELEMENT.symbol(), parts -> Element.createElement(parts),
	            FileCategory.NAME.symbol(), parts -> Name.createName(parts),
	            FileCategory.OPERATOR.symbol(), parts -> Operator.createOperator(parts),
	            FileCategory.PROPERTY.symbol(), parts -> Property.createProperty(parts)
	        );
		return itemParser;
	}
	
	/**
	 * Read the file with a specified file path
	 * each line of the file will be converted into a array of string 
	 * and will be converted into a object by the specified item parser
	 * @param filePath the specified file path
	 * @param itemParserMap the specified item parser
	 * @return the list of read element
	 * @throws IOException
	 */
	private static ArrayList<Bloc> readFile(String filePath, Map<String, Function<String[], Bloc>> itemParserMap) throws IOException{
		Objects.requireNonNull(filePath);
		Objects.requireNonNull(itemParserMap);
		var list = new ArrayList<Bloc>();
		InputStream inputStream = new FileInputStream(filePath);
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		
		while ((line = br.readLine()) != null) {
			var arrStr = line.split("#");
			var function = itemParserMap.get(arrStr[0]);
			if(function != null)
				list.add(function.apply(arrStr));
		}
		inputStream.close();
		br.close();
		return list;
	}
	
	/**
	 * Read the line concerning the creation of the board
	 * @param screenWidth width of the screen
	 * @param screenHeight height of the screen
	 * @param filePath the specified file path
	 * @return a Board object 
	 * @throws IOException
	 */
	public static Board readBoard(int screenWidth, int screenHeight, String filePath) throws IOException {
		Objects.requireNonNull(filePath);
		InputStream inputStream = new FileInputStream(filePath);
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		String line;	
		while((line = br.readLine()) != null) {
			var arrStr = line.split("#");
			if(arrStr[0].equals("BO")) {
				inputStream.close();
				br.close();
				return Board.createBoard(screenWidth, screenHeight, Integer.parseInt(arrStr[1]), Integer.parseInt(arrStr[2]));
			}
		}
		inputStream.close();
		br.close();
		return null;
	}
	
	/**
	 * Create and initialize a Board according to the information contained in
	 * the specified file path
	 * @param screenWidth width of the screen
	 * @param screenHeight height of the screen
	 * @param filePath the specified file path
	 * @return the initialized Board object
	 * @throws IOException
	 */
	public static Board createBoard(int screenWidth, int screenHeight, String filePath) throws IOException {
		Objects.requireNonNull(filePath);
		Map<String, Function<String[], Bloc>> itemParserMap = createItemParserMap();
		var lstBloc = readFile(filePath, itemParserMap);
		var b = readBoard(screenWidth, screenHeight, filePath);
		for(var elt : lstBloc) {
			b.addBloc(elt.position(), elt);
		}
		return b;
	}
}

	
