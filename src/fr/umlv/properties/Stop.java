package fr.umlv.properties;

import java.io.IOException;

public class Stop extends AbstractProperty {
	public Stop(int x, int y) {
        super(x, y, Property.Stop);
    }
    
    public static Stop createStop(int x, int y) throws IOException {
        Stop stop = new Stop(x, y);
        String fileName = stop.pathImage();
        stop.initImageIcon(fileName);
        stop.initStates();
        return stop;
    }
}
