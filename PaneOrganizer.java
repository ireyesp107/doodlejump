package doodlejump;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


/*
The PaneOrganizer the graphic top level. It is responsible for creating an instance of the Game and loading it on the
scene
 */
public class PaneOrganizer {
    private BorderPane root;
    private Label score;

    public PaneOrganizer() {
        this.root = new BorderPane();
        this.score = new Label();
        Pane doodlePane = new Pane();
        this.root.setCenter(doodlePane);
        new Game(doodlePane, score);
        this.createButtonPane();
        this.createLabelPane();
    }

    public Pane getRoot() {
        return this.root;
    }
    //createButtonPane creates the Quit button
    public void createButtonPane() {
        HBox buttonPane = new HBox();
        buttonPane.setPrefSize(50, 45);
        buttonPane.setStyle("-fx-background-color: #778899");
        Button button = new Button("Quit"); //This instantiates the buttonp
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.getChildren().add(button);
        this.root.setBottom(buttonPane);
        button.setOnAction((ActionEvent e) -> System.exit(0));

    }
    //Creates the Scorelabel on the screen
    public void createLabelPane(){
        HBox labelBox = new HBox();
        labelBox.getChildren().add(this.score);
        labelBox.setAlignment(Pos.TOP_RIGHT);
        this.score.setStyle("-fx-font: italic bold 25px arial, serif;-fx-text-alignment: center;-fx-text-fill: black;");
        this.root.setTop(labelBox);
    }

}
