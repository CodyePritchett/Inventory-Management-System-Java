package com.example.schoolprojectjava1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *  handles launching the program
 */
public class HelloApplication extends Application {
    /**
     * The start method is the entry point of the JavaFX application.
     *
     * @param stage The primary stage (window) of the application.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Load the FXML file for the main screen
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent mainScreenRoot = fxmlLoader.load();

        // Get the controller associated with the FXML file
        HelloController controller = fxmlLoader.getController();

        // Create a Scene from the main screen root
        Scene mainScreenScene = new Scene(mainScreenRoot);

        // Configure the stage (main window) settings
        stage.setTitle("Hello!"); // Set the title of the window
        stage.setScene(mainScreenScene); // Set the scene to display in the stage
        stage.show(); // Display the stage
    }

    /**
     * The main method is the entry point for Java applications.
     *
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        // Launch the JavaFX application
        launch();
    }
}


