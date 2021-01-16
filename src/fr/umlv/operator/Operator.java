package fr.umlv.operator;

import java.io.IOException;
import java.util.Objects;

import fr.umlv.bloc.AbstractBloc;
import fr.umlv.bloc.Bloc;
import fr.umlv.element.ElementCategory;
import fr.umlv.file.FileCategory;
import fr.umlv.property.PropertyCategory;

/**
 * This class provides an implementation of the AbstractBloc class.
 * It is a more specific type of Bloc
 *
 */
public class Operator extends AbstractBloc {
	private OperatorCategory op;
	
	private Operator(int x, int y, OperatorCategory op) {
		super(x, y, ElementCategory.Operator);
		this.op = Objects.requireNonNull(op);
	}
	
	@Override
	public String pathImage(){
		return op.pathOperator();
	}
	
	/**
	 * Creates a Operator object
	 * This function initializes the image this object has to display in a window
	 * It also initializes this object with the specified operator this object is, 
	 * and its position in the board with the specified position 
	 * @param x position of this object in the X axis
	 * @param y position of this object in the Y axis
	 * @param op the specified operator this object is
	 * @return a Operator object
	 * @throws IOException
	 */
	public static Operator createOperator(int x, int y, OperatorCategory op) throws IOException {
		Objects.requireNonNull(op);
		Operator newOp = new Operator(x, y, op);
		newOp.putState(PropertyCategory.Push);
		newOp.initImageIcon();
		return newOp;
	}
	
	/**
	 * Create an Operator type with the specified array
	 * @param parts the specified array
	 * @return The create Operator
	 */
	public static Bloc createOperator(String[] parts) {
		Objects.requireNonNull(parts);
		Bloc elt = null;
		try {
			elt = Operator.createOperator(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]),
            		OperatorCategory.convertStr(FileCategory.convertSymbolToName(parts[3])));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return elt;
	}
}
