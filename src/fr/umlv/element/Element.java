package fr.umlv.element;

public enum Element {
	
	Baba("Baba", "img/PixelArt/BABA/Text_BABA_0.gif", "img/PixelArt/BABA/BABA_0.gif"), 
	Flag("Flag", "img/PixelArt/FLAG/Text_FLAG_0.gif", "img/PixelArt/FLAG/FLAG_0.gif"), 
	Lava("Lava", "img/PixelArt/LAVA/Text_LAVA_0.gif", "img/PixelArt/LAVA/LAVA_0.gif"), 
	Name("Name", null, null), 
	Operator("Operator", null, null), 
	Property("Property", null, null), 
	Skull("Skull", "img/PixelArt/SKULL/Text_SKULL_0.gif", "img/PixelArt/SKULL/SKULL_0.gif"), 
	Rock("Rock", "img/PixelArt/ROCK/Text_ROCK_0.gif", "img/PixelArt/ROCK/ROCK_0.gif"), 
	Wall("Wall", "img/PixelArt/WALL/Text_WALL_0.gif", "img/PixelArt/WALL/WALL_0.gif"), 
	Water("Water", "img/PixelArt/WATER/Text_WATER_0.gif", "img/PixelArt/WATER/WATER_0.gif"), 
	None("None", null, null);
	
	private String name;
	private String pathElementText;
	private String pathElement;
	
	private Element(String name, String pathElementText, String pathElement) {
		this.name = name;
		this.pathElementText = pathElementText;
		this.pathElement = pathElement;
	} 
	
	public String Name() {
		return name;
	}
	
	public String pathElementText() {
		return pathElementText;
	}
	
	public String pathElement() {
		return pathElement;
	}
	
	public static Element convertStr(String str) {
		for(var elt : Element.values()) {
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
