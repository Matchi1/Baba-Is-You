package fr.umlv.element;

/**
 * This enum class is used to identify the Category of an Element in the board
 */
public enum ElementCategory {
	
	Baba("Baba", "img/Names/BABA/Text_BABA_0.gif", "img/Names/BABA/BABA_0.gif"), 
	Flag("Flag", "img/Names/FLAG/Text_FLAG_0.gif", "img/Names/FLAG/FLAG_0.gif"), 
	Lava("Lava", "img/Names/LAVA/Text_LAVA_0.gif", "img/Names/LAVA/LAVA_0.gif"), 
	Name("Name"), 
	Operator("Operator"), 
	Property("Property"), 
	Skull("Skull", "img/Names/SKULL/Text_SKULL_0.gif", "img/Names/SKULL/SKULL_0.gif"), 
	Rock("Rock", "img/Names/ROCK/Text_ROCK_0.gif", "img/Names/ROCK/ROCK_0.gif"), 
	Wall("Wall", "img/Names/WALL/Text_WALL_0.gif", "img/Names/WALL/WALL_0.gif"), 
	Water("Water", "img/Names/WATER/Text_WATER_0.gif", "img/Names/WATER/WATER_0.gif"), 
	None("None");
	
	private String name;
	private String pathElementCategoryText;
	private String pathElementCategory;
	
	private ElementCategory(String name, String pathElementCategoryText, String pathElementCategory) {
		this.name = name;
		this.pathElementCategoryText = pathElementCategoryText;
		this.pathElementCategory = pathElementCategory;
	}
	
	private ElementCategory(String name) {
		this.name = name;
		this.pathElementCategoryText = null;
		this.pathElementCategory = null;
	} 
	
	/**
	 * Return the name corresponding to this object
	 * @return the name corresponding to this object
	 */
	public String Name() {
		return name;
	}
	
	/**
	 * Return the image path to this object (Name object)
	 * @return  the image path to this object
	 */
	public String pathElementCategoryText() {
		return pathElementCategoryText;
	}
	
	/**
	 * Return the image path to this object (Element object)
	 * @return  the image path to this object
	 */
	public String pathElementCategory() {
		return pathElementCategory;
	}
	
	/**
	 * convert the specified name into an object of this class
	 * @param name the specified name
	 * @return the enum corresponding to this name else None enum
	 */
	public static ElementCategory convertStr(String name) {
		for(var elt : ElementCategory.values()) {
			if(name.equals(elt.name()))
				return elt;
		}
		return None;
	}
	
	/**
	 * Returns a String representation of this Category
	 * @return a String representation of this Category
	 */
	@Override
	public String toString() {
		return "Element : " + this.name();
	}
}
