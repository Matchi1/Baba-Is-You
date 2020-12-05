package fr.umlv.operator;

import java.io.IOException;
import java.util.Objects;

import fr.umlv.bloc.AbstractBloc;
import fr.umlv.element.ElementCategory;
import fr.umlv.property.PropertyCategory;

public class Operator extends AbstractBloc {
	private OperatorCategory op;
	
	private Operator(int x, int y, OperatorCategory op) {
		super(x, y, ElementCategory.Operator);
		this.op = op;
	}
	
	@Override
	public String pathImage(){
		return op.pathOperator();
	}
	
	public static Operator createOperator(int x, int y, OperatorCategory op) throws IOException {
		Objects.requireNonNull(op);
		Operator newOp = new Operator(x, y, op);
		String fileImage = newOp.pathImage();
		newOp.putState(PropertyCategory.Push);
		newOp.initImageIcon(fileImage);
		return newOp;
	}
}
