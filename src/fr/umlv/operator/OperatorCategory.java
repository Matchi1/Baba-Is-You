package fr.umlv.operator;

public enum OperatorCategory {
	Is("Is", "img/Operators/IS/Ope_IS.gif");
	
	private String name;
	private String pathOperator;
	
	private OperatorCategory(String name, String pathOperator) {
		this.name = name;
		this.pathOperator = pathOperator;
	}
	
	public String pathOperator() {
		return pathOperator;
	}
	
	@Override
	public String toString() {
		return "Operator : name : " + name;
	}
}
