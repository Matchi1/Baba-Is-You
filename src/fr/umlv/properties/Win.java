package fr.umlv.properties;

import java.io.IOException;

public class Win extends AbstractProperty {
    public Win(int x, int y) {
        super(x, y, Property.Win);
    }
    
    public static Win createWin(int x, int y) throws IOException {
        Win win = new Win(x, y);
        String fileName = win.pathImage();
        win.initImageIcon(fileName);
        win.initStates();
        return win;
    }
}
