package doodlejump;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
/*Inherits from the Platform superclass to create a distinct platform
This class is unique due to the separate timeline in order to keep the platform moving back and forth
 */

public class Moving extends Platform{
    private Doodle myDoodle;
    private Timeline myTimeline;
    private double tempMotion;

    public Moving(double x, double y, Color c, Pane p, Doodle d) {
        super(x, y, c, p, d);
        this.myDoodle=d;
        this.setupTimeline();
        this.tempMotion=500-this.getX();

    }
/*The movePlatform method handles the logic of restarting the platform once it reaches the end. The variable tempMotion
is used to determine when to switch the movement. When the platform reaches the end of the screen we set the tempMotion
to a random negative number until the platform reaches the other end of the screen. When it reaches the other end of the
screen it will switch back to positive. The method will also stop the timeline is the character falls down the screen

 */
    @Override
    public void movePlatform(){
        if(tempMotion>0) {
            this.setXloc(this.getX() + 2);
            if(this.getX()>500){
                this.tempMotion=-500;
            }
        }
        else if(tempMotion<0){
            this.setXloc(this.getX() - 2);
            if(this.getX()<0){
                this.tempMotion=1;
            }
        }
        if(this.myDoodle.getY()>700){
            this.myTimeline.stop();
        }

    }
    //Sets up the timeline where the moving platform will move back and forth
    public void setupTimeline() {
        KeyFrame kf = new KeyFrame(Duration.seconds(Constants.DURATION),
                (ActionEvent e) -> this.movePlatform());
        this.myTimeline = new Timeline(kf);
        this.myTimeline.setCycleCount(Animation.INDEFINITE);
        this.myTimeline.playFromStart();
        this.myTimeline.setAutoReverse(true);
    }

}


