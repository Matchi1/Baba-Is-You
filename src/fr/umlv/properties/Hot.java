package fr.umlv.properties;

import java.io.IOException;

public class Hot extends AbstractProperty {
    public Hot(int x, int y) {
        super(x, y, Property.Hot);
    }
    
    public static Hot createHot(int x, int y) throws IOException {
        Hot hot = new Hot(x, y);
        String fileName = hot.pathImage();
        hot.initImageIcon(fileName);
        hot.initStates();
        return hot;
    }
}
