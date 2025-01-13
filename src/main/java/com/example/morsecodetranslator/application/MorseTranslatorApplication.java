package com.example.morsecodetranslator.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;
import java.util.Objects;

public class MorseTranslatorApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                MorseTranslatorApplication.class.getResource(
                        "/com/example/morsecodetranslator/view/morse-translator-view.fxml"
                )
        );
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);

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

    public static void main(String[] args) {
        launch();
    }
}
