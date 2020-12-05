package fr.umlv.element;

public enum ElementCategory {
	
	Baba("Baba", "img/Names/BABA/Text_BABA_0.gif", "img/Names/BABA/BABA_0.gif"), 
	Flag("Flag", "img/Names/FLAG/Text_FLAG_0.gif", "img/Names/FLAG/FLAG_0.gif"), 
	Lava("Lava", "img/Names/LAVA/Text_LAVA_0.gif", "img/Names/LAVA/LAVA_0.gif"), 
	Name("Name", null, null), 
	Operator("Operator", null, null), 
	Property("Property", null, null), 
	Skull("Skull", "img/Names/SKULL/Text_SKULL_0.gif", "img/Names/SKULL/SKULL_0.gif"), 
	Rock("Rock", "img/Names/ROCK/Text_ROCK_0.gif", "img/Names/ROCK/ROCK_0.gif"), 
	Wall("Wall", "img/Names/WALL/Text_WALL_0.gif", "img/Names/WALL/WALL_0.gif"), 
	Water("Water", "img/Names/WATER/Text_WATER_0.gif", "img/Names/WATER/WATER_0.gif"), 
	None("None", null, null);
	
	private String name;
	private String pathElementCategoryText;
	private String pathElementCategory;
	
	private ElementCategory(String name, String pathElementCategoryText, String pathElementCategory) {
		this.name = name;
		this.pathElementCategoryText = pathElementCategoryText;
		this.pathElementCategory = pathElementCategory;
	} 
	
	public String Name() {
		return name;
	}
	
	public String pathElementCategoryText() {
		return pathElementCategoryText;
	}
	
	public String pathElementCategory() {
		return pathElementCategory;
	}
	
	public static ElementCategory convertStr(String str) {
		for(var elt : ElementCategory.values()) {
			if(str.equals(elt.name()))
				return elt;
		}
		return None;
	}
	
	@Override
	public String toString() {
		return "Element : " + this.name();
	}
}
