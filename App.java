package doodlejump;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the main class where your DoodleJump game will start.
 * The main method of this application calls launch, a JavaFX method
 * which eventually calls the start method below. You will need to fill
 * in the start method to start your game!
 *
 * Class comments here...
 */
public class App extends Application {

    @Override
//Sets the scene and has an instance of the PaneOrganize which sets up the game
    public void start(Stage stage) {
        PaneOrganizer organizer = new PaneOrganizer();
        Scene scene = new Scene(organizer.getRoot(), 500,700);
        stage.setScene(scene);
        stage.setTitle("Doodle Jump");
        stage.show();
    }

    public static void main(String[] argv) {
        launch(argv);
    }
}
