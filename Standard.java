package doodlejump;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Standard extends Platform {
//Inherits from the Platform superclass to create a distinct platform
    public Standard(double x, double y, Color c, Pane p, Doodle d) {
        super(x, y, c, p, d);
    }

}
