package fr.umlv.operator;

/**
 * This enum class is used to identify the category of an Operator in the board
 */
public enum OperatorCategory {
	Is("Is", "img/Operators/IS/Ope_IS.gif"),
	NONE();
	
	private String name;
	private String pathOperator;
	
	private OperatorCategory(String name, String pathOperator) {
		this.name = name;
		this.pathOperator = pathOperator;
	}
	
	private OperatorCategory() {
		this.name = null;
		this.pathOperator = null;
	}
	
	/**
	 * Return the image path to this object
	 * @return  the image path to this object
	 */
	public String pathOperator() {
		return pathOperator;
	}
	
	/**
	 * convert the specified name into an object of this class
	 * @param name the specified name
	 * @return the enum corresponding to this name else None enum
	 */
	public static OperatorCategory convertStr(String name) {
		for(var elt : OperatorCategory.values()) {
			if(name.equals(elt.name()))
				return elt;
		}
		return NONE;
	}
	
	/**
	 * Returns a String representation of this Category
	 * @return a String representation of this Category
	 */
	@Override
	public String toString() {
		return "Operator : name : " + name;
	}
}
