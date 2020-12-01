package fr.umlv.properties;

public enum Property {
	Win("Win", "img/Properties/WIN/Prop_WIN.gif"),
	Defeat("Defeat", "img/Properties/DEFEAT/Prop_DEFEAT.gif"),
	Hot("Hot", "img/Properties/HOT/Prop_HOT.gif"),
	Melt("Melt", "img/Properties/MELT/Prop_MELT.gif"),
	Push("Push", "img/Properties/PUSH/Prop_PUSH.gif"),
	Sink("Sink", "img/Properties/SINK/Prop_SINK.gif"),
	Stop("Stop", "img/Properties/STOP/Prop_STOP.gif"),
	None("None", null);
	
	private String name;
	private String pathProperty;
	
	private Property(String name, String pathProperty) {
		this.name = name;
		this.pathProperty = pathProperty;
	}
	
	public String Name() {
		return name;
	}
	
	public String pathProperty() {
		return pathProperty;
	}
	
	@Override
	public String toString() {
		return "Property : " + this.name ;
	}
}
