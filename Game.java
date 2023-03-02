package doodlejump;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import java.util.ArrayList;
/*
Game class is the top level logic class and is responsible for the logic of the doodlejump game. We use an arrayList
to store our multiple Platforms since we don't know how many we will have. We used an instance variable of Standard in
order to have our Doodle start on a platform
 */
public class Game {
    private ArrayList<Platform> myPlatform;
    private Doodle myDoodle;
    private Pane doodlePane;
    private Standard myStandard;
    private Timeline myTimeline;
    private Label score;
    private int myScore;
    //The game has association with the Pane and Label class

    public Game(Pane aPane, Label aLabel) {
        this.myPlatform = new ArrayList<>();
        this.doodlePane = aPane;
        this.score =aLabel;
        this.myDoodle = new Doodle(this.doodlePane);
        this.myStandard = new Standard(250, 600, javafx.scene.paint.Color.BLACK, this.doodlePane, this.myDoodle);
        this.myPlatform.add(this.myStandard);
        this.setupTimeline();
    }
    /*
    generatePlatform() will generate to platforms in our arraylist through a randomPlatform method defined below. The
    method will also set the topPlatform as the new generated platform
     */
    public void generatePlatform(){

        Platform topPlatform = this.myPlatform.get(this.myPlatform.size()-1);
        while (topPlatform.getY()>0){
            double lowX = (int)Math.max(0, topPlatform.getX() - Constants.X_OFFSET);

            double highX = (int)Math.min(460 - 40, topPlatform.getX()) + Constants.X_OFFSET;
            double randomX = (int)(Math.random() * (highX - lowX)) + lowX;

            double lowY = topPlatform.getY()-Constants.Y_OFFSETMIN;
            double highY = topPlatform.getY()-Constants.Y_OFFSETMAX;
            double randomY= (int)(Math.random()*(highY-lowY))+lowY;
            Platform currentPlatform=this.randomPlatform(randomX,randomY);
            this.myPlatform.add(currentPlatform);
            topPlatform= currentPlatform;
            }
        }
        /*
        Checks to see if the platform is landed. If the character lands on the platform than there will be a rebound
        Lastly this method is responsible for the special attribute of the green platform because once the platform is
        jumped on it will disappear
         */
    public void checkIntersect() {
        for (int i = 0; i <= myPlatform.size()-1; i++) {
            if(this.myDoodle.checkIntersect(this.myPlatform.get(i).getX(),this.myPlatform.get(i).getY(),Constants.PLATFORM_WIDTH,Constants.PLATFORM_HEIGHT)){
                if(myDoodle.getVelocity()>0) {
                    this.myDoodle.rebound();
                    if(this.myPlatform.get(i).getColor()==Color.GREEN){
                        Platform remove = this.myPlatform.remove(i);
                        remove.removeFromPane();
                    }
                }
            }
        }
    }
    /*
    The if statement includes ==Color.blue because if the platform that was intersected is blue then it will create
    the extraRebound as defined in the Doodle class
     */
    public void checkExtraBouncy(){
        for (int i = 0; i <= myPlatform.size()-1; i++) {
            if(this.myDoodle.checkIntersect(this.myPlatform.get(i).getX(),this.myPlatform.get(i).getY(),Constants.PLATFORM_WIDTH,Constants.PLATFORM_HEIGHT)&&this.myPlatform.get(i).getColor()==Color.BLUE){

                    this.myDoodle.extraRebound();

            }
        }
    }
    /*
    will be called in the timeline which is responsible for the logic of the game
     */
    public void updateDoodle(){
        this.myDoodle.updateHeight();
        this.myDoodle.wrap();
        this.generatePlatform();
        this.checkIntersect();
        this.changeDirection();
        this.scrollPlatform();
        this.updateGame();
        this.checkExtraBouncy();

    }
    /*
    Sets up the timeline which is in charge of keeping the game running and handle scenarios mentioned below
     */
    public void setupTimeline() {
        KeyFrame kf = new KeyFrame(Duration.seconds(Constants.DURATION),
                (ActionEvent e) -> this.updateDoodle());
        this.myTimeline = new Timeline(kf);
        this.myTimeline.setCycleCount(Animation.INDEFINITE);
        this.myTimeline.playFromStart();
        this.myTimeline.setAutoReverse(true);
    }
    //Is responsible for setting up our character's movement depends on what whether we press the left or right key
    public void handleKeyPress(KeyEvent e){
        KeyCode keyPressed = e.getCode();
        switch (keyPressed){
            case LEFT:
                this.myDoodle.moveLeft();
                break;
            case RIGHT:
                this.myDoodle.moveRight();
                break;
            default:
                break;
        }
        e.consume();
    }
    /*
    the Change direction is responsible for handling the key event and letting our character move left or right
     */
    public void changeDirection(){
        this.doodlePane.setOnKeyPressed((KeyEvent e)-> this.handleKeyPress(e) );
        this.doodlePane.setFocusTraversable(true);
        if (this.myDoodle.getY()>700){
        this.doodlePane.setFocusTraversable(false);
    }}
    /*
    Once the Player reaches the midpoint of the screen it will scroll and delete the platforms below the screen
    the method is also responsible for updating the score label each time a scroll occurs
     */
    public void scrollPlatform(){
        if(this.myDoodle.getY()<350){
            double difference = 350-this.myDoodle.getY();

            for(int i=0;i<=this.myPlatform.size()-1;i++){
                Platform tempPlat=this.myPlatform.get(i);
                this.myPlatform.get(i).setYloc(tempPlat.getY()+difference);
            }
            this.myDoodle.setY(350);
            this.myDoodle.setYback(350);
            this.myScore+=5;
            this.updateScore();

        }
        for(int i=0;i<=myPlatform.size()-1;i++){
            if(myPlatform.get(i).getY()>700){
                Platform remove=this.myPlatform.remove(i);
                remove.removeFromPane();
                i--;
            }
        }
    }
    /*The randomPlatform method has a switch statement in order to randomly generate the type of platform. There
    are multiple cases of the Standard platform to make the game more playable

     */
    public Platform randomPlatform(double x, double y){
        int random = (int)(Math.random()*6);
        Platform newPlatform = null;
        switch (random){
            case 0:
                newPlatform = new Standard(x, y, Color.BLACK ,this.doodlePane, this.myDoodle);
                break;
            case 1:
                newPlatform = new Moving(x,y, Color.RED, this.doodlePane, this.myDoodle);
                break;
            case 2:
                newPlatform = new Disappearing(x,y,Color.GREEN, this.doodlePane, this.myDoodle);
                break;
            case 3:
                newPlatform= new Extra(x,y,Color.BLUE,this.doodlePane,this.myDoodle);
                break;
            case 4:
                newPlatform = new Standard(x, y, Color.BLACK ,this.doodlePane, this.myDoodle);
                break;
            case 5:
                newPlatform = new Standard(x, y, Color.BLACK ,this.doodlePane, this.myDoodle);
                break;


            default:
                newPlatform = new Standard(x, y, Color.BLACK ,this.doodlePane, this.myDoodle);
                break;
        }
        return newPlatform;
    }
    /*updateGame stops the timeline and the game once the character falls off the screen. The method is also responsible
    for producing a Label that states game over
     */
    public void updateGame(){
        if (this.myDoodle.getY()>700) {

            this.myTimeline.stop();

            Label label = new Label("Game Over!");
            HBox labelBox = new HBox(label);
            labelBox.setAlignment(Pos.CENTER);
            label.setStyle("-fx-font: italic bold 25px arial, serif;-fx-text-alignment: center;-fx-text-fill: black;");
            this.doodlePane.getChildren().add(labelBox);


}
    }

//updateScore is responsible for displaying the score on the screen
        private void updateScore() {
            this.score.setText("Score: "+this.myScore );
        }
    }










