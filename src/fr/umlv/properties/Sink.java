package fr.umlv.properties;

import java.io.IOException;

public class Sink extends AbstractProperty {
    public Sink(int x, int y) {
        super(x, y, Property.Sink);
    }
    
    public static Sink createSink(int x, int y) throws IOException {
        Sink sink = new Sink(x, y);
        String fileName = sink.pathImage();
        sink.initImageIcon(fileName);
        sink.initStates();
        return sink;
    }
}
