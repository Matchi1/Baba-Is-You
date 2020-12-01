package fr.umlv.properties;

import java.io.IOException;

public class Push extends AbstractProperty {
    public Push(int x, int y) {
        super(x, y, Property.Push);
    }
    
    public static Push createPush(int x, int y) throws IOException {
        Push push = new Push(x, y);
        String fileName = push.pathImage();
        push.initImageIcon(fileName);
        push.initStates();
        return push;
    }
}
