package fr.umlv.properties;

import java.io.IOException;

public class Defeat extends AbstractProperty {
    public Defeat(int x, int y) {
        super(x, y, Property.Defeat);
    }
    
    public static Defeat createDefeat(int x, int y) throws IOException {
        Defeat defeat = new Defeat(x, y);
        String fileName = defeat.pathImage();
        defeat.initImageIcon(fileName);
        defeat.initStates();
        return defeat;
    }
}
