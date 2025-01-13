package com.example.morsecodetranslator.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;
import java.util.Objects;

/**
 * The main entry point for the Morse Code Translator application.
 * Sets up the primary stage, loads the FXML layout, and applies stylesheets.
 */
public class MorseTranslatorApplication extends Application {

    /**
     * Starts the application and sets up the main window.
     *
     * @param stage the primary stage for the application
     * @throws IOException if the FXML file or stylesheets cannot be loaded
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                MorseTranslatorApplication.class.getResource(
                        "/com/example/morsecodetranslator/view/morse-translator-view.fxml"
                )
        );
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);

        // Apply BootstrapFX and custom stylesheets
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        scene.getStylesheets().add(
                Objects.requireNonNull(MorseTranslatorApplication.class.getResource(
                        "/com/example/morsecodetranslator/css/style.css"
                )).toExternalForm()
        );

        stage.setTitle("Morse Code Translator");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main method to launch the application.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}
