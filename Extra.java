package doodlejump;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
//Inherits from the Platform superclass to create a distinct platform
public class Extra extends Platform{
    public Extra(double x, double y, Color c, Pane p, Doodle d){
        super(x, y, c, p, d);
    }
}
