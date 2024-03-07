package com.karolis.futbolfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App is the main class of the application. It extends Application, which is the base class for JavaFX applications.
 * This class starts the application and manages scenes.
 * @author kjaks
 */
public class App extends Application {

    private static Scene scene; // Static scene used for changing views.

    /**
     * The start method, required for any JavaFX application.
     * It creates the main scene and shows it on the window.
     * @param stage The main stage of the application.
     * @throws IOException If there is an error loading the FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"),  1280.0,720.0); // Creates a new scene with the specified dimensions.
        stage.setScene(scene); // Sets the scene on the stage.
        stage.show(); // Shows the stage.
    }

    /**
     * Static method used to change the root of the scene.
     * @param fxml The path to the FXML file to be used for loading the new view.
     * @throws IOException If there is an error loading the FXML file.
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml)); // Sets the root of the scene to a new view loaded from the specified FXML file.
    }

    /**
     * Private method for loading FXML files.
     * @param fxml The path to the FXML file to be loaded.
     * @return The root node of the loaded UI hierarchy from the FXML file.
     * @throws IOException If there is an error loading the FXML file.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml")); // Creates an FXML loader to load the FXML file.
        return fxmlLoader.load(); // Loads and returns the root node of the UI hierarchy.
    }

    /**
     * The main method, entry point of the application.
     * @param args The command-line arguments, if any.
     */
    public static void main(String[] args) {
        launch(); // Launches the JavaFX application.
    }

}
