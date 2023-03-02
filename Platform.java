package doodlejump;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Platform {
    private Rectangle platform;
    private Pane myPane;
    private Doodle myDoodle;
    private Color myColor;
    public Platform(double x, double y, Color c, Pane p, Doodle aDoodle){
        this.platform = new Rectangle(x, y, Constants.PLATFORM_WIDTH, Constants.PLATFORM_HEIGHT);
        this.platform.setFill(c);
        this.myPane=p;
        this.myDoodle=aDoodle;
        this.myColor=c;
        this.myPane.getChildren().add(this.platform);

    }
    public void movePlatform(){
    }


    public Pane getPane(){
        return this.myPane;
    }
    public double getX(){
        return this.platform.getX();
    }
    public double getY(){
        return this.platform.getY();
    }
    public void setYloc(double loc){
        this.platform.setY(loc);
    }
    public void setXloc(double loc){this.platform.setX(loc);}
    public Color getColor(){
        return this.myColor;
    }
    public void removeFromPane(){
        this.myPane.getChildren().remove(this.platform);
    }


}
