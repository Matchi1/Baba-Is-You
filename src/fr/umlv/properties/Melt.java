package fr.umlv.properties;

import java.io.IOException;

public class Melt extends AbstractProperty {
    public Melt(int x, int y) {
        super(x, y, Property.Melt);
    }
    
    public static Melt createMelt(int x, int y) throws IOException {
        Melt melt = new Melt(x, y);
        String fileName = melt.pathImage();
        melt.initImageIcon(fileName);
        melt.initStates();
        return melt;
    }
}
