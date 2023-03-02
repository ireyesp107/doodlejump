package doodlejump;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/*
The Doodle class is responsible for creating the character of the game. In the Doodle class we have containment with
the Rectangle class. The instance of the Rectangle will represent the character of the game.
 */
public class Doodle {
    private Rectangle doodle;
    private double currentVelocity;
    private double  currentPosition;
    public Doodle(Pane root){
        this.currentVelocity = 0;
        this.currentPosition = 600;
        this.doodle = new Rectangle(250, this.currentPosition, Constants.DOODLE_WIDTH, Constants.DOODLE_HEIGHT);
        this.doodle.setFill(Color.GREEN);
        root.getChildren().add(this.doodle);
    }
    //moveLeft() will move our character left
    public void moveLeft(){
        this.doodle.setX(this.doodle.getX() - 25);
    }
    //moveRight() will move our character right
    public void moveRight(){
        this.doodle.setX(this.doodle.getX() + 25);
    }
    //wrap() will move our character to the opposite end of the screen when it goes further than our scene width
    public void wrap(){
        if (this.doodle.getX() > 500){
            this.doodle.setX(1);}
        else if (this.doodle.getX()<0){
            this.doodle.setX(499);}
    }
    /*updateHeight will be called in the timeline and is responsible for calculating and updating the velocity/position
    through the usage of physics and Constants.
     */

    public void updateHeight(){
        this.currentVelocity = (this.currentVelocity + Constants.GRAVITY * Constants.DURATION);
        this.doodle.setY(this.currentPosition + this.currentVelocity * Constants.DURATION);
        this.currentPosition = (this.currentPosition + this.currentVelocity * Constants.DURATION);
        this.wrap();
    }

    //getVelocity() is an accessor which will be used in the Game class
    public double getVelocity(){
        return this.currentVelocity;
    }
    //rebound() is responsible for updating the velocity in the Game class
    public void rebound(){
        this.currentVelocity= Constants.REBOUND_VELOCITY;
    }
    //extraRebound is responsible for updating the velocity in the Game class when the green platform is bounced on
    public void extraRebound(){
        this.currentVelocity= Constants.BOUNCY_REBOUND_VELOCITY;
    }
    /*checkIntersect is used to call the intersects() method in the Rectangle class which will be called in the Game
    class to determine whether the character is on the platform
     */

    public boolean checkIntersect(double x, double y, int width, int height){
        return this.doodle.intersects(x,y,width,height);
    }
    //getY is an accessor in the used Game and moving class. This returns the Y coordinate from the Rectangle class
    public double getY(){
        return this.doodle.getY();
    }
    //setY is a mutator which sets the Y coordinate
    public void setY(double loc){
        this.doodle.setY(loc);
    }
    //setYback is used to resolve logical issues to prevent infinite scrolling
    public void setYback(double loc){
        this.currentPosition=loc;
    }
    //getX is an accessor which is used to return the X Coordinate
    public double getX(){
        return this.doodle.getX();
    }
    //The setX is a mutator used to set the X Coordinate of our character
    public void setX(double loc){
        this.doodle.setX(loc);
    }

}
