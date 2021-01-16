package fr.umlv.property;

/**
 * This enum class is used to identify the category of an Property in the board
 */
public enum PropertyCategory {
	You("You", "img/Properties/YOU/Prop_YOU.gif" ),
	Win("Win", "img/Properties/WIN/Prop_WIN.gif"),
	Defeat("Defeat", "img/Properties/DEFEAT/Prop_DEFEAT.gif"),
	Hot("Hot", "img/Properties/HOT/Prop_HOT.gif"),
	Melt("Melt", "img/Properties/MELT/Prop_MELT.gif"),
	Push("Push", "img/Properties/PUSH/Prop_PUSH.gif"),
	Sink("Sink", "img/Properties/SINK/Prop_SINK.gif"),
	Stop("Stop", "img/Properties/STOP/Prop_STOP.gif"),
	Tunnel("Tunnel", "img/Properties/TUNNEL/Prop_TUNNEL.png"),
	None("None");
	
	private String name;
	private String pathPropertyCategory;
	
	private PropertyCategory(String name, String pathProperty) {
		this.name = name;
		this.pathPropertyCategory = pathProperty;
	}
	
	private PropertyCategory(String name) {
		this.name = name;
		this.pathPropertyCategory = null;
	}
	
	/**
	 * Return the name corresponding to this object
	 * @return the name corresponding to this object
	 */
	public String Name() {
		return name;
	}
	
	/**
	 * Return the image path to this object
	 * @return  the image path to this object
	 */
	public String pathProperty() {
		return pathPropertyCategory;
	}
	
	/**
	 * convert the specified name into an object of this class
	 * @param name the specified name
	 * @return the enum corresponding to this name else None enum
	 */
	public static PropertyCategory convertStr(String name) {
		for(var elt : PropertyCategory.values()) {
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
		return "Property : " + this.name ;
	}
}
