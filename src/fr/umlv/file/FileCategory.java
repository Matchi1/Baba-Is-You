package fr.umlv.file;

/**
 * This enum class is necessary while reading a file representing
 * a level for the "BABA IS YOU"
 * It makes the conversion between a symbol and the name of the
 * element it represents
 */
public enum FileCategory {
	ELEMENT("EL"),
	NAME("NA"), 
	OPERATOR("OP"), 
	PROPERTY("PR"), 
	YOU("YO", "You"),
	WIN("WI", "Win"),
	DEFEAT("DE", "Defeat"),
	HOT("HO", "Hot"),
	MELT("ME", "Melt"),
	PUSH("PU", "Push"),
	SINL("SI", "Sink"),
	STOP("ST", "Stop"),
	BABA("BA", "Baba"),
	FLAG("FL", "Flag"),
	LAVA("LA", "Lava"), 
	SKULL("SK", "Skull"), 
	ROCK("RO", "Rock"), 
	WALL("WA", "Wall"), 
	WATER("WT", "Water"),
	TUNNEL("TU", "Tunnel"),
	IS("IS", "Is");
	 
	private String symbol;
	private String realName;
	private FileCategory(String symbol, String realName) {
		this.symbol = symbol;
		this.realName = realName;
	}
	
	private FileCategory(String symbol) {
		this.symbol = symbol;
		this.realName = null;
	}
	
	/**
	 * Return the symbol of this object
	 * @return the symbol of this object
	 */
	public String symbol() {
		return symbol;
	}
	
	/**
	 * Return the name of this object
	 * @return the name of this object
	 */
	public String realName() {
		return realName;
	}
	
	/**
	 * Convert the specified symbol into the associated name
	 * @param symbol the specified symbol
	 * @return the associated name of the specified symbol
	 */
	public static String convertSymbolToName(String symbol) {
		for(var elt : FileCategory.values()) {
			if(elt.symbol().equals(symbol))
				return elt.realName();
		}
		return null;
	}
}
