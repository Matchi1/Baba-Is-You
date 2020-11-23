package fr.umlv.element;

public enum Element {
	Baba, Flag, Lava, Name, Operator, Rock, Wall, Water;
	
	@Override
	public String toString() {
		switch(this) {
		case Baba:
			return "Element: Baba\n"; 
		case Flag:
			return "Element: Flag\n";
		case Lava:
			return "Element: Lava\n"; 
		case Name:
			return "Element: Name\n"; 
		case Operator:
			return "Element: Operator\n";
		case Rock:
			return "Element: Rock\n";
		case Wall:
			return "Element: Wall\n";
		default:
			return "Element: Water\n";
		}
	}
}
